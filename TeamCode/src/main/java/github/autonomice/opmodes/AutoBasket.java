package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Constants;
import github.autonomice.Props;
import github.autonomice.util.AutoBase;

@Autonomous(name = "AutonomiceAutoLeft")
public class AutoBasket extends AutoBase {
    @Override
    public Action getAction() {
        return this.r.mecanumDrive.actionBuilder(Constants.STARTING_POSE)
                .splineTo(new Vector2d(30, 30), Math.PI / 2)
                .splineTo(new Vector2d(0, 60), Math.PI)
                .build();
    }
}
