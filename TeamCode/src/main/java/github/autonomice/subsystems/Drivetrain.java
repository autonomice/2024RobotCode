package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import github.autonomice.Constants;

public class Drivetrain extends SubsystemBase {
    public final MecanumDrive mDriveBase;
    public DriveTrainCoefficients coefficients = Constants.driveTrainCoefficients;


    public Drivetrain(HardwareMap hwMap) {
        this.mDriveBase = new MecanumDrive(
                        new Motor(hwMap, Constants.LeftFrontKey, Motor.GoBILDA.RPM_312),
                        new Motor(hwMap, Constants.RightFrontKey, Motor.GoBILDA.RPM_312),
                        new Motor(hwMap, Constants.LeftBackKey, Motor.GoBILDA.RPM_312),
                        new Motor(hwMap, Constants.RightBackKey, Motor.GoBILDA.RPM_312));
    }

    public static class DefaultAutoCommand extends CommandBase {
        private final Drivetrain mDriveTrain;
        private final ElapsedTime mTimer;

        public DefaultAutoCommand(Drivetrain drivetrain) {
            this.mDriveTrain = drivetrain;
            this.mTimer = new ElapsedTime();

            addRequirements(this.mDriveTrain);
        }

        @Override
        public void execute() {
            if (!this.isScheduled()) {
                this.mTimer.reset();
            }

            if (this.mTimer.milliseconds() < 1100) {
                this.mDriveTrain.mDriveBase.driveRobotCentric(
                        0., 1., 0.
                );
            } else {
                this.mDriveTrain.mDriveBase.driveRobotCentric(
                        0., 0., 0.
                );
            }
        }
    }

    public static class DefaultCommand extends CommandBase {
        private final Drivetrain mDriveTrain;
        private final GamepadEx mGamePad;

        public DefaultCommand(Drivetrain driveTrain, GamepadEx gamepad) {
            this.mDriveTrain = driveTrain;
            this.mGamePad = gamepad;

            addRequirements(driveTrain);
        }

        @Override
        public void execute() {
            this.mDriveTrain.mDriveBase.driveRobotCentric(
                    mGamePad.getLeftX() * mDriveTrain.coefficients.x,
                    mGamePad.getLeftY() * mDriveTrain.coefficients.y,
                    mGamePad.getRightX() * mDriveTrain.coefficients.z);
        }
    }

    public static class DriveTrainCoefficients {
        double x, y, z;

        public DriveTrainCoefficients(double x, double y, double z) {
            this.x = x;
            this.y = y;
            this.z = z;
        }
    }
}
