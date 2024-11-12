package github.autonomice;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.hardware.Gamepad;

public class Props {
    public static class TeleopProps {
        public final GamepadEx gamepad1;
        public final GamepadEx gamepad2;

        public TeleopProps(Gamepad gamepad1, Gamepad gamepad2) {
            this.gamepad1 = new GamepadEx(gamepad1);
            this.gamepad2 = new GamepadEx(gamepad2);
        }
    }

    public static class AutoProps {
        public final AutoSide side;

        public AutoProps(AutoSide side) {
            this.side = side;
        }

        public enum AutoSide {
            RIGHT,
            LEFT
        }
    }

}
