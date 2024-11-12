package github.autonomice.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Props;
import github.autonomice.util.AutoBase;

@Autonomous(name = "AutonomiceAutoRight")
public class AutoPark extends AutoBase {
    @Override
    public Props.AutoProps getProps() {
        return new Props.AutoProps(Props.AutoProps.AutoSide.RIGHT);
    }
}
