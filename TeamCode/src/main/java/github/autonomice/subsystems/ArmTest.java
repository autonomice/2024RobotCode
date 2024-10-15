package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;

import github.autonomice.Constants;

public class ArmTest extends SubsystemBase {
    public DcMotor armMotor;

    public ArmTest(HardwareMap hwMap) {
        this.armMotor = hwMap.get(DcMotor.class, Constants.ArmKey);
    }

    public void setPower(double power) {
        this.armMotor.setPower(power);
    }

    public class DefaultCommand extends CommandBase {
        private ArmTest arm;
        private GamepadEx padInput;

        public DefaultCommand(ArmTest arm, GamepadEx padInput) {
            this.arm = arm;
            this.padInput = padInput;
        }

        @Override
        public void execute() {
            this.arm.setPower(padInput.getRightX());
        }
    }
}
