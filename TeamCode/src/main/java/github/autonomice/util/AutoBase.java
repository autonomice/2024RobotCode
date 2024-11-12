package github.autonomice.util;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import github.autonomice.Props;
import github.autonomice.Robot;

public abstract class AutoBase extends OpMode {
    private Robot r;

    @Override
    public void init() {
        r = new Robot(hardwareMap, telemetry);
        r.autoInit(getProps());
    }


    @Override
    public void init_loop() {
        super.init_loop();

        r.run();
    }

    @Override
    public void start() {
        super.start();

        r.autoStart();
    }

    @Override
    public void loop() {
        r.run();
    }

    public abstract Props.AutoProps getProps();
}
