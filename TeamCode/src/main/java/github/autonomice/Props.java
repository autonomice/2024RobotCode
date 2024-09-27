package github.autonomice;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class Props {
    public static class RobotProps {
        public HardwareMap mHardwareMap;

        public RobotProps(HardwareMap hwMap) { this.mHardwareMap = hwMap; }
    }

    public static class TeleopProps {
        public final GamepadEx gamepad1;
        public final GamepadEx gamepad2;

        public TeleopProps(Gamepad gamepad1, Gamepad gamepad2) {
            this.gamepad1 = new GamepadEx(gamepad1);
            this.gamepad2 = new GamepadEx(gamepad2);
        }
    }


}
