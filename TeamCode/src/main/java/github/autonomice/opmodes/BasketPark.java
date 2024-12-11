package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;

import github.autonomice.Constants;
import github.autonomice.util.AutoBase;

public abstract class BasketPark extends AutoBase {
    @Override
    public Action getAction() {
        Pose2d basket = new Pose2d(57, 58, Math.PI / 4);
        return new SequentialAction(
                this.drive.actionBuilder(getStartingPose())
                        .strafeToLinearHeading(basket.position, basket.heading) // go to basket
                        .build(),
                this.arm.runToPos(Constants.ARM_UP_POS),
                //this.intake.setPower(Constants.INTAKE_OUT_POWER),
                new SleepAction(1.0),
                //this.intake.setPower(0.),
                this.arm.runToPos(0),
                this.drive.actionBuilder(basket)
                        .strafeTo(new Vector2d(basket.position.x - 9, basket.position.y)) // bugfix mostly
                        .splineToLinearHeading(new Pose2d(40, 22, 0), basket.heading) // line up first sample
                        .splineToSplineHeading(basket, 0) // push first sample
                        .strafeTo(new Vector2d(basket.position.x - 9, basket.position.y)) // away from first sample
                        .splineToLinearHeading(new Pose2d(50, 22,0), basket.heading) // line up second
                        .splineToSplineHeading(basket, 0) // push second
                        .strafeToConstantHeading(new Vector2d(basket.position.x, 24)) // position third
                        .strafeToLinearHeading(new Vector2d(63, 24), 0) // get third
                        .strafeTo(new Vector2d(63, 63)) // push third
                        .strafeToLinearHeading(new Vector2d(basket.position.x - 6, 60), 0) // line up park
                        .strafeTo(new Vector2d(-55, 60)) // park */
                        .build()
        );
    }
}
