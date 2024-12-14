package github.autonomice;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;

public class Constants {
    // Intake Constants
    public static final String INTAKE_KEY = "intake";
    public static final GamepadKeys.Trigger INTAKE_IN = GamepadKeys.Trigger.RIGHT_TRIGGER;
    public static final GamepadKeys.Trigger INTAKE_OUT = GamepadKeys.Trigger.LEFT_TRIGGER;
    public static final double INTAKE_IN_POWER = 1;
    public static final double INTAKE_OUT_POWER = -1;
    public static final double INTAKE_STOP_POWER = 0;

    // Arm Constants
    public static final String ARM_KEY = "arm";
    public static final int ARM_UP_POS = -800;
    public static final int ARM_DOWN_POS = -1800;
    public static final int ARM_NEUTRAL_POS = -420;
    public static final double ARM_KP = 0.001;
    public static final double ARM_KI = 0.004;
    public static final double ARM_KD = 0.00015;
    public static final double ARM_KF = 0.0015;
    public static final double ARM_TOLERANCE = 8.5;
    public static final GamepadKeys.Button ARM_UP_BUTTON = GamepadKeys.Button.Y;
    public static final GamepadKeys.Button ARM_DOWN_BUTTON = GamepadKeys.Button.A;
    public static final GamepadKeys.Button ARM_NEUTRAL_BUTTON = GamepadKeys.Button.X;

    // Mecanum Drive Parameters
    public static class MecanumDriveParams {
        // IMU orientation
        // TODO: fill in these values based on
        //   see https://ftc-docs.firstinspires.org/en/latest/programming_resources/imu/imu.html?highlight=imu#physical-hub-mounting
        public RevHubOrientationOnRobot.LogoFacingDirection logoFacingDirection =
                RevHubOrientationOnRobot.LogoFacingDirection.RIGHT;
        public RevHubOrientationOnRobot.UsbFacingDirection usbFacingDirection =
                RevHubOrientationOnRobot.UsbFacingDirection.UP;

        // drive model parameters
        public double inPerTick = 0.022;
        public double lateralInPerTick = inPerTick;
        public double trackWidthTicks = 1095.834612508758;

        // feedforward parameters (in tick units)
        public double kS = 0.85940577;
        public double kV = 0.00440237;
        public double kA = 0.0001;

        // path profile parameters (in inches)
        public double maxWheelVel = 40;
        public double minProfileAccel = -30;
        public double maxProfileAccel = 50;

        // turn profile parameters (in radians)
        public double maxAngVel = Math.PI; // shared with path
        public double maxAngAccel = Math.PI;

        // path controller gains
        public double axialGain = 3.5;
        public double lateralGain = 5.0;
        public double headingGain = 3.5; // shared with turn

        public double axialVelGain = 0.0;
        public double lateralVelGain = 0.0;
        public double headingVelGain = 0.0; // shared with turn

        // Teleop Motion Parameters
        public double forwardScale = 1.0;
        public double strafeScale = -1.0;
        public double turnScale = -0.5;
    }
}
