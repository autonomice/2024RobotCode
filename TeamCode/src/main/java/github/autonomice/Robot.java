package github.autonomice;

import com.arcrobotics.ftclib.command.InstantCommand;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import github.autonomice.subsystems.Arm;
import github.autonomice.subsystems.Intake;
import github.autonomice.subsystems.MecanumDrive;
import github.autonomice.util.BulkReader;

public class Robot extends com.arcrobotics.ftclib.command.Robot {
    // subsystems
    public final MecanumDrive mecanumDrive;
    public final Arm mArm;
    public final Intake mIntake;
    // not subsystem
    private final BulkReader mBulkReader;
    private final Telemetry telemetry;

    public Robot(HardwareMap hwMap, Telemetry telemetry) {
        this.mecanumDrive = new MecanumDrive(hwMap);

        this.mArm = new Arm(hwMap);

        this.mIntake = new Intake(hwMap);

        this.mBulkReader = new BulkReader(hwMap);

        this.telemetry = telemetry;
    }

    public void baseInit() {
        register(this.mecanumDrive, this.mArm, this.mIntake);
    }

    public void teleopInit(Props.TeleopProps props) {
        mecanumDrive.setDefaultCommand(
                new MecanumDrive.DefaultCommand(this.mecanumDrive, props.gamepad1)
        );
        mArm.setDefaultCommand(
                new Arm.DefaultCommand(this.mArm, props.gamepad2)
        );
        mIntake.setDefaultCommand(
                new Intake.DefaultCommand(this.mIntake, props.gamepad2)
        );

        props
                .gamepad2
                .getGamepadButton(Constants.ARM_UP_BUTTON)
                .whenReleased(new InstantCommand(mArm::runUp, mArm));

        props
                .gamepad2
                .getGamepadButton(Constants.ARM_DOWN_BUTTON)
                .whenReleased(new InstantCommand(mArm::runDown, mArm));
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
