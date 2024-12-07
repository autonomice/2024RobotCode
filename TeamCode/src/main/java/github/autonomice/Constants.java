package github.autonomice;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;

import github.autonomice.subsystems.Drivetrain;

public class Constants {
    public static String LeftFrontKey = "left-front",
            RightFrontKey = "right-front",
            LeftBackKey = "left back",
            RightBackKey = "right-back";
    public static Drivetrain.DriveTrainCoefficients driveTrainCoefficients = new Drivetrain.DriveTrainCoefficients(-1.0, -1.0, -0.5);

    public static String IntakeKey = "intake";
    public static GamepadKeys.Trigger IntakeIn = GamepadKeys.Trigger.RIGHT_TRIGGER,
        IntakeOut = GamepadKeys.Trigger.LEFT_TRIGGER;

    public static String ArmKey = "arm";
    public static int ArmUpPos = -1020, ArmDownPos = -1900;
    public static final double ARM_KP = 0.001,
        ARM_KI = 0.004, ARM_KD = 0.00015, ARM_TOLERANCE = 5;
    public static GamepadKeys.Button ArmUpButton = GamepadKeys.Button.Y,
        ArmDownButton = GamepadKeys.Button.A;
}
