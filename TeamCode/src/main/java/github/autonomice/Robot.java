package github.autonomice;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.robotcore.hardware.HardwareMap;

import github.autonomice.subsystems.Drivetrain;
import github.autonomice.util.BulkReader;
import github.autonomice.util.ImuHandler;

public class Robot extends com.arcrobotics.ftclib.command.Robot {
    // subsystems
    public final Drivetrain mDriveTrain;
    // not subsystem
    private final BulkReader mBulkReader;
    private final ImuHandler mImu;

    public Robot(HardwareMap hwMap) {
        this.mDriveTrain = new Drivetrain(hwMap);

        this.mBulkReader = new BulkReader(hwMap);
        this.mImu = new ImuHandler(hwMap, Constants.imuParameters, 0.0);
    }

    public void baseInit() {
        register(this.mDriveTrain);
    }

    public void teleopInit(Props.TeleopProps props) {
        mDriveTrain.setDefaultCommand(
                new Drivetrain.DefaultDriveCommand(this.mDriveTrain, props.gamepad1, this.mImu)
        );

        props
                .gamepad1
                .getGamepadButton(Constants.IMUPoseResetButton)
                .whenReleased(new InstantCommand(mImu::reset));

        props
                .gamepad1
                .getGamepadButton(Constants.DriveBaseModeSwitchButton)
                .whenReleased(new InstantCommand(mDriveTrain::switchDriveMode, mDriveTrain));
    }

    @Override
    public void run() {
        mBulkReader.bulkRead();
        super.run();
    }
}
