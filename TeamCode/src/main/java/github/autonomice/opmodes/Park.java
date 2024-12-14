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

@Autonomous(name = "Park")
public class Park extends AutoBase {
    @Override
    public Action getAction() {
        Pose2d basket = new Pose2d(62, 66, Math.PI / 4);
        Action driveAction = new SequentialAction(
                this.intake.setPower(Constants.INTAKE_STOP_POWER),
                new ParallelAction(
                        this.drive.actionBuilder(getStartingPose())
                                .strafeTo(new Vector2d(-24, 63)) // go to basket
                                .build(),
                        this.arm.runToPos(Constants.ARM_NEUTRAL_POS)
                ),
                new SequentialAction(
                        new ParallelAction(
                                this.drive.actionBuilder(new Pose2d(-24, 63, -Math.PI / 2))
                                        .strafeTo(new Vector2d(-44, 63))
                                        .build()
                        )
                )
        );
        return new ParallelAction(
                this.arm.pidUpdateAction(),
                driveAction
        );
    }

    @Override
    public Pose2d getStartingPose() {
        return new Pose2d(new Vector2d(-24, 65), -Math.PI / 2);
    }
}
