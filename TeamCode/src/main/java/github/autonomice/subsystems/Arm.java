package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import github.autonomice.Constants;

public class Arm extends SubsystemBase {
    public final DcMotor mMotor;

    public Arm(HardwareMap hwMap) {
        this.mMotor = hwMap.get(DcMotor.class, Constants.ArmKey);
        this.mMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.mMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        this.mMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
    }

    public static class DefaultCommand extends CommandBase {
        private final Arm mArm;
        private final GamepadEx mGamepad;

        public DefaultCommand(Arm arm, GamepadEx gamepad) {
            this.mArm = arm;
            this.mGamepad = gamepad;

            addRequirements(this.mArm);
        }

        @Override
        public void execute() {
            this.mArm.mMotor.setPower(this.mGamepad.getRightY());
        }
    }
}
