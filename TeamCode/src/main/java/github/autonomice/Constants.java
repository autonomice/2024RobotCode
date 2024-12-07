package github.autonomice;

import com.acmerobotics.roadrunner.Pose2d;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class Constants {
    // Intake Constants
    public static final String INTAKE_KEY = "intake";
    public static final GamepadKeys.Trigger INTAKE_IN = GamepadKeys.Trigger.RIGHT_TRIGGER;
    public static final GamepadKeys.Trigger INTAKE_OUT = GamepadKeys.Trigger.LEFT_TRIGGER;
    public static final double INTAKE_IN_POWER = 0.7;
    public static final double INTAKE_OUT_POWER = 0.7;

    // Arm Constants
    public static final String ARM_KEY = "arm";
    public static final int ARM_UP_POS = -1020;
    public static final int ARM_DOWN_POS = -1900;
    public static final double ARM_KP = 0.001;
    public static final double ARM_KI = 0.004;
    public static final double ARM_KD = 0.00015;
    public static final double ARM_TOLERANCE = 5.0;
    public static final GamepadKeys.Button ARM_UP_BUTTON = GamepadKeys.Button.Y;
    public static final GamepadKeys.Button ARM_DOWN_BUTTON = GamepadKeys.Button.A;

    // Starting Pose
    public static final Pose2d STARTING_POSE = new Pose2d(0, 0, 0);

    // Mecanum Drive Parameters
    public static class MecanumDriveParams {
        // IMU Orientation
        public RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.LEFT;
        public RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.UP;

        // Drive Model Parameters
        public double inPerTick = 0.022;
        public double lateralInPerTick = inPerTick;
        public double trackWidthTicks = 1095.834612508758;

        // Feedforward Parameters (in tick units)
        public double kS = 0.8134666294185617;
        public double kV = 0.004359305622916281;
        public double kA = 0.001;

        // Path Profile Parameters (in inches)
        public double maxWheelVel = 50.0;
        public double minProfileAccel = -30.0;
        public double maxProfileAccel = 50.0;

        // Turn Profile Parameters (in radians)
        public double maxAngVel = Math.PI; // Shared with path
        public double maxAngAccel = Math.PI;

        // Path Controller Gains
        public double axialGain = 0.0;
        public double lateralGain = 0.0;
        public double headingGain = 0.0; // Shared with turn

        public double axialVelGain = 0.0;
        public double lateralVelGain = 0.0;
        public double headingVelGain = 0.0; // Shared with turn

        // Teleop Motion Parameters
        public double strafeScale = 1.0;
        public double forwardScale = 1.0;
        public double turnScale = 0.5;
    }
}
