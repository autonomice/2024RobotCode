package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Constants;
import github.autonomice.util.AutoBase;

@Autonomous(name = "BasketSamples")
public class BasketSamples extends AutoBase {
    @Override
    public Action getAction() {
        Pose2d basket = new Pose2d(62, 66, Math.PI / 4);
        Vector2d resetPos = new Vector2d(basket.position.x - 9, basket.position.y);
        Action driveAction = new SequentialAction(
                this.intake.setPower(Constants.INTAKE_STOP_POWER),
                new ParallelAction(
                        this.drive.actionBuilder(getStartingPose())
                                .strafeToLinearHeading(basket.position, basket.heading) // go to basket
                                .build(),
                        this.arm.runToPos(Constants.ARM_UP_POS)
                ),
                new ParallelAction(
                        new SequentialAction(
                                new SleepAction(1.2),
                                this.intake.setPower(Constants.INTAKE_STOP_POWER),
                                new ParallelAction(
                                        this.arm.runToPos(Constants.ARM_NEUTRAL_POS),
                                        this.drive.actionBuilder(basket)
                                                .strafeTo(resetPos) // bugfix mostly
                                                .splineToLinearHeading(new Pose2d(46, 22, 0), basket.heading) // line up first sample
                                                .splineToSplineHeading(basket, 0) // push first sample
                                                .strafeTo(resetPos) // away from first sample
                                                .splineToLinearHeading(new Pose2d(60, 25,0), basket.heading) // line up second
                                                .splineToSplineHeading(basket, 0) // push second
                                                .strafeToConstantHeading(new Vector2d(basket.position.x, 27)) // position third
                                                .strafeToLinearHeading(new Vector2d(72.5, 28), 0) // get third
                                                .strafeTo(new Vector2d(70, 63)) // push third
                                                .build()
                                ),
                                this.intake.setPower(Constants.INTAKE_OUT_POWER)
                        )
                )
        );
        return new ParallelAction(
                this.arm.pidUpdateAction(),
                driveAction
        );
    }

    public Pose2d getStartingPose() {
        return new Pose2d(new Vector2d(24, 62), -Math.PI / 2);
    }
}
