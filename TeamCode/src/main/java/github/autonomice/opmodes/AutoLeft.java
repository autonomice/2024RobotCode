package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Constants;
import github.autonomice.util.AutoBase;

@Autonomous(name = "AutonomiceAutoLeft")
public class AutoLeft extends AutoBase {
    @Override
    public Action getAction() {
        return new SequentialAction(
            this.drive.actionBuilder(getStartingPose())
                    .splineTo(new Vector2d(15, 57), Math.PI * 3 / 4)
                    .build(),
            this.arm.runToPos(Constants.ARM_UP_POS),
            this.intake.setPower(Constants.INTAKE_OUT_POWER),
            this.intake.setPower(0.),
            this.arm.runToPos(0),
            this.drive.actionBuilder(new Pose2d(new Vector2d(15, 57), Math.PI * 3 / 4))
                    .strafeTo(new Vector2d(15, -48)).build()
        );
    }

    @Override
    public Pose2d getStartingPose() {
        return new Pose2d(new Vector2d(6, 24), 0);
    }
}
