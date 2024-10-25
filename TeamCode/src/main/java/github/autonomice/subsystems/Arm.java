package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import github.autonomice.Constants;

public class Arm extends SubsystemBase {
    public final DcMotor mMotor;
    public float currentPos = 0;

    public Arm(HardwareMap hwMap) {
        this.mMotor = hwMap.get(DcMotor.class, Constants.ArmKey);
        this.mMotor.setTargetPosition(0);
        this.mMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.mMotor.setPower(0.5);
        this.mMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }

    public void runUp() {
        this.currentPos = Constants.ArmUpPos;
    }

    public void runDown() {
        this.currentPos = Constants.ArmDownPos;
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
            this.mArm.currentPos += (float) this.mGamepad.getRightY();
            this.mArm.mMotor.setTargetPosition((int) this.mArm.currentPos);
        }
    }
}
