package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import github.autonomice.Constants;
import github.autonomice.util.ImuHandler;

public class Drivetrain extends SubsystemBase {
    public final MecanumDrive mDriveBase;
    public DriveOrientation orientation = DriveOrientation.FIELD;
    public DriveTrainCoefficients coefficients = Constants.driveTrainCoefficients;


    public Drivetrain(HardwareMap hwMap) {
        this.mDriveBase = new MecanumDrive(
                        new Motor(hwMap, Constants.LeftFrontKey, Motor.GoBILDA.RPM_312),
                        new Motor(hwMap, Constants.RightFrontKey, Motor.GoBILDA.RPM_312),
                        new Motor(hwMap, Constants.LeftBackKey, Motor.GoBILDA.RPM_312),
                        new Motor(hwMap, Constants.RightBackKey, Motor.GoBILDA.RPM_312));
    }

    public void switchDriveMode() {
        switch (this.orientation) {
            case FIELD:
                this.orientation = DriveOrientation.ROBOT;
                break;
            case ROBOT:
                this.orientation = DriveOrientation.FIELD;
                break;
        }
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
                        0., -1., 0.
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
        private final ImuHandler mImu;

        public DefaultCommand(Drivetrain driveTrain, GamepadEx gamepad, ImuHandler imu) {
            this.mDriveTrain = driveTrain;
            this.mGamePad = gamepad;
            this.mImu = imu;

            addRequirements(driveTrain);
        }

        @Override
        public void execute() {
            switch (mDriveTrain.orientation) {
                case FIELD:
                    this.mDriveTrain.mDriveBase.driveFieldCentric(
                            mGamePad.getLeftX() * mDriveTrain.coefficients.x,
                            mGamePad.getLeftY() * mDriveTrain.coefficients.y,
                            mGamePad.getRightX() * mDriveTrain.coefficients.z,
                            mImu.getYaw());
                    break;
                case ROBOT:
                    this.mDriveTrain.mDriveBase.driveRobotCentric(
                            mGamePad.getLeftX() * mDriveTrain.coefficients.x,
                            mGamePad.getLeftY() * mDriveTrain.coefficients.y,
                            mGamePad.getRightX() * mDriveTrain.coefficients.z);
                    break;
            }
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

    public enum DriveOrientation {
        FIELD,
        ROBOT
    }
}
