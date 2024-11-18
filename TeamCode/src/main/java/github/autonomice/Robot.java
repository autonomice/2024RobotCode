package github.autonomice;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import github.autonomice.subsystems.Arm;
import github.autonomice.subsystems.ColorSensor;
import github.autonomice.subsystems.Drivetrain;
import github.autonomice.subsystems.Intake;
import github.autonomice.util.BulkReader;
import github.autonomice.util.ImuHandler;

public class Robot extends com.arcrobotics.ftclib.command.Robot {
    Props.AutoProps.AutoSide side;
    // subsystems
    public final Drivetrain mDriveTrain;
    public final Arm mArm;
    public final Intake mIntake;
    public final ColorSensor mColorSensor;
    // not subsystem
    private final BulkReader mBulkReader;
    private final ImuHandler mImu;
    private final Telemetry telemetry;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        this.mDriveTrain = new Drivetrain(hwMap);

        this.mArm = new Arm(hwMap);

        this.mIntake = new Intake(hwMap);
        this.mColorSensor = new ColorSensor(hwMap);

        this.mBulkReader = new BulkReader(hwMap);
        this.mImu = new ImuHandler(hwMap, Constants.imuParameters, 0.0);

        this.telemetry = telemetry;
    }

    public void baseInit() {
        register(this.mDriveTrain, this.mArm, this.mIntake, this.mColorSensor);
    }

    public void teleopInit(Props.TeleopProps props) {
        mDriveTrain.setDefaultCommand(
                new Drivetrain.DefaultCommand(this.mDriveTrain, props.gamepad1, this.mImu)
        );
        mArm.setDefaultCommand(
                new Arm.DefaultCommand(this.mArm, props.gamepad1)
        );
        mIntake.setDefaultCommand(
                new Intake.DefaultCommand(this.mIntake, props.gamepad1)
        );
        mColorSensor.setDefaultCommand(
                new ColorSensor.DefaultCommand(this.mColorSensor, telemetry)
        );

        props
                .gamepad1
                .getGamepadButton(Constants.ArmUpButton)
                .whenReleased(new InstantCommand(mArm::runUp, mArm));

        props
                .gamepad1
                .getGamepadButton(Constants.ArmDownButton)
                .whenReleased(new InstantCommand(mArm::runDown, mArm));

        props
                .gamepad1
                .getGamepadButton(Constants.IMUPoseResetButton)
                .whenReleased(new InstantCommand(mImu::reset));

        props
                .gamepad1
                .getGamepadButton(Constants.DriveBaseModeSwitchButton)
                .whenReleased(new InstantCommand(mDriveTrain::switchDriveMode, mDriveTrain));
    }

    public void autoInit(Props.AutoProps props) {
        this.side = props.side;
    }

    public void autoStart() {
        this.mDriveTrain.setDefaultCommand(
                new Drivetrain.DefaultAutoCommand(this.mDriveTrain)
        );
    }

    @Override
    public void run() {
        ElapsedTime time = new ElapsedTime(ElapsedTime.Resolution.MILLISECONDS);
        mBulkReader.bulkRead();
        super.run();
        this.telemetry.addData("time (ms)", "%f", time.milliseconds());
        this.telemetry.update();
    }
}
