package github.autonomice.util;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import github.autonomice.Robot;

public abstract class AutoBase extends LinearOpMode {
    protected Robot r;

    @Override
    public void runOpMode() throws InterruptedException {
        this.r = new Robot(hardwareMap, telemetry);

        waitForStart();

        Actions.runBlocking(getAction());
    }

    public abstract Action getAction();
}
