package github.autonomice.subsystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import github.autonomice.Constants;

public class Intake extends SubsystemBase {
    public final DcMotor mMotor;

    public Intake(HardwareMap hwMap) {
        this.mMotor = hwMap.get(DcMotor.class, Constants.INTAKE_KEY);
    }

    public Action setPower(double power) {
        return new Action() {
            @Override
            public boolean run(@NonNull TelemetryPacket telemetryPacket) {
                mMotor.setPower(power);
                return false;
            }
        };
    }

    public static class DefaultCommand extends CommandBase {
        private final Intake mIntake;
        private final GamepadEx mGamepad;

        public DefaultCommand(Intake intake, GamepadEx gamepad) {
            this.mIntake = intake;
            this.mGamepad = gamepad;

            addRequirements(intake);
        }

        @Override
        public void execute() {
            if (mGamepad.getTrigger(Constants.INTAKE_IN) != 0) {
                this.mIntake.mMotor.setPower(Constants.INTAKE_IN_POWER);
            } else if (mGamepad.getTrigger(Constants.INTAKE_OUT) != 0) {
                this.mIntake.mMotor.setPower(Constants.INTAKE_OUT_POWER);
            } else {
                this.mIntake.mMotor.setPower(0.0);
            }
        }
    }
}
