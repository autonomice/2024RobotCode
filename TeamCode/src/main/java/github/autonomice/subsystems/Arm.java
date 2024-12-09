package github.autonomice.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
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
    public float targetPos = 0;
    private final PIDFController pidController;

    public Arm(HardwareMap hwMap) {
        this.mMotor = hwMap.get(DcMotor.class, Constants.ARM_KEY);
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

    public void pidUpdate() {
        this.pidController.setSetPoint(this.targetPos);

        double pidOutput = this.pidController.calculate(
                this.mMotor.getCurrentPosition()
        );

        pidOutput = Range.clip(pidOutput, -1.0, 1.0);

        this.mMotor.setPower(pidOutput);
    }

    public void runUp() {
        this.targetPos = Constants.ARM_UP_POS;
    }

    public void runDown() {
        this.targetPos = Constants.ARM_DOWN_POS;
    }

    public Action runToPos(int pos) {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                targetPos = pos;
                pidUpdate();

                return Math.abs(mMotor.getCurrentPosition() - targetPos) > Constants.ARM_TOLERANCE;
            }
        };
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
            this.mArm.targetPos += (float) (-2 * this.mGamepad.getRightY());
            this.mArm.pidUpdate();
        }
    }
}
