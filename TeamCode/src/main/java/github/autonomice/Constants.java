package github.autonomice;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.hardware.rev.RevHubOrientationOnRobot;
import com.qualcomm.robotcore.hardware.IMU;

import github.autonomice.subsystems.Drivetrain;

public class Constants {
    public static String LeftFrontKey = "left-front",
            RightFrontKey = "right-front",
            LeftBackKey = "left back",
            RightBackKey = "right-back";
    public static Drivetrain.DriveTrainCoefficients driveTrainCoefficients = new Drivetrain.DriveTrainCoefficients(1.0, 1.0, 1.0);
    public static IMU.Parameters imuParameters = new IMU.Parameters(
            new RevHubOrientationOnRobot(
                    RevHubOrientationOnRobot.LogoFacingDirection.UP,
                    RevHubOrientationOnRobot.UsbFacingDirection.RIGHT)); // TODO: adjust
    public static GamepadKeys.Button IMUPoseResetButton = GamepadKeys.Button.START,
        DriveBaseModeSwitchButton = GamepadKeys.Button.Y;

    public static String IntakeKey = "intake";
    public static GamepadKeys.Trigger IntakeIn = GamepadKeys.Trigger.RIGHT_TRIGGER,
        IntakeOut = GamepadKeys.Trigger.LEFT_TRIGGER;

    public static String ArmKey = "arm";
    public static int ArmUpPos = 360, ArmDownPos = 0;
    public static GamepadKeys.Button ArmUpButton = GamepadKeys.Button.Y,
        ArmDownButton = GamepadKeys.Button.A;
}
