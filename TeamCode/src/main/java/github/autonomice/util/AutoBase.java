package github.autonomice.util;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import github.autonomice.subsystems.Arm;
import github.autonomice.subsystems.Intake;
import github.autonomice.subsystems.MecanumDrive;

public abstract class AutoBase extends LinearOpMode {
    protected MecanumDrive drive;
    protected Arm arm;
    protected Intake intake;

    @Override
    public void runOpMode() throws InterruptedException {
        this.drive = new MecanumDrive(hardwareMap, getStartingPose());
        this.arm = new Arm(hardwareMap);
        this.intake = new Intake(hardwareMap);

        waitForStart();

        Actions.runBlocking(getAction());
    }

    public abstract Pose2d getStartingPose();

    public abstract Action getAction();
}
