package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.Range;

import github.autonomice.Constants;

public class Arm extends SubsystemBase {

    public final DcMotor mMotor;
    public float currentPos = 0;
    private final PIDFController pidController;

    public Arm(HardwareMap hwMap) {
        this.mMotor = hwMap.get(DcMotor.class, Constants.ArmKey);
        this.mMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        this.mMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        this.mMotor.setPower(0.0);
        this.mMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        this.pidController = new PIDFController(
                Constants.ARM_KP,
                Constants.ARM_KI,
                Constants.ARM_KD,
                0
        );
        this.pidController.setTolerance(Constants.ARM_TOLERANCE);
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
            this.mArm.currentPos += (float) (-2 * this.mGamepad.getRightY());

            this.mArm.pidController.setSetPoint(this.mArm.currentPos);

            double pidOutput = this.mArm.pidController.calculate(
                    this.mArm.mMotor.getCurrentPosition()
            );

            pidOutput = Range.clip(pidOutput, -1.0, 1.0);

            this.mArm.mMotor.setPower(pidOutput);
        }
    }
}
