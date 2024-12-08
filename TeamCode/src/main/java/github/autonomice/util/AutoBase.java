package github.autonomice.util;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import github.autonomice.subsystems.MecanumDrive;

public abstract class AutoBase extends LinearOpMode {
    protected MecanumDrive drive;

    @Override
    public void runOpMode() throws InterruptedException {
        this.drive = new MecanumDrive(hardwareMap);

        waitForStart();

        Actions.runBlocking(getAction());
    }

    public abstract Action getAction();
}
