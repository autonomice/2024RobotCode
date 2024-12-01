package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import github.autonomice.Constants;

public class Intake extends SubsystemBase {
    public final DcMotor mMotor;

    public Intake(HardwareMap hwMap) {
        this.mMotor = hwMap.get(DcMotor.class, Constants.IntakeKey);
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
            if (mGamepad.getTrigger(Constants.IntakeIn) != 0) {
                this.mIntake.mMotor.setPower(0.7);
            } else if (mGamepad.getTrigger(Constants.IntakeOut) != 0) {
                this.mIntake.mMotor.setPower(-0.7);
            } else {
                this.mIntake.mMotor.setPower(0.0);
            }
        }
    }
}
