package github.autonomice.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Props;
import github.autonomice.util.AutoBase;

@Autonomous(name = "AutonomiceAutoLeft")
public class AutoBasket extends AutoBase {
    @Override
    public Props.AutoProps getProps() {
        return new Props.AutoProps(Props.AutoProps.AutoSide.LEFT);
    }
}
