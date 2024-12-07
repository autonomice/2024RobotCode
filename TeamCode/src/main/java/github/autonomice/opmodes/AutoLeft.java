package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.TrajectoryActionBuilder;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Constants;
import github.autonomice.util.AutoBase;

@Autonomous(name = "AutonomiceAutoLeft")
public class AutoLeft extends AutoBase {
    @Override
    public Action getAction() {
        TrajectoryActionBuilder actionBuilder = this.r.mecanumDrive.actionBuilder(Constants.STARTING_POSE);
        return new SequentialAction(
            actionBuilder.splineTo(new Vector2d(-12 * 3, 12), Math.PI * 3 / 4).build(),
            r.mArm.runToPos(Constants.ARM_UP_POS),
            r.mIntake.setPower(Constants.INTAKE_OUT_POWER),
            r.mIntake.setPower(0.)
        );
    }
}
