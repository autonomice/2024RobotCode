package github.autonomice.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import github.autonomice.Props;
import github.autonomice.Robot;


@TeleOp(name = "AutonomiceTeleop")
public class Teleop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        Robot bot = new Robot(hardwareMap, telemetry);
        bot.baseInit();
        bot.teleopInit(new Props.TeleopProps(gamepad1, gamepad2));

        waitForStart();

        while (!isStopRequested() && opModeIsActive()) {
            bot.run();
        }
    }
}
