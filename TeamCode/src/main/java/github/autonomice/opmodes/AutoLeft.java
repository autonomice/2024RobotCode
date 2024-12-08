package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Constants;
import github.autonomice.util.AutoBase;

@Autonomous(name = "AutonomiceAutoLeft")
public class AutoLeft extends AutoBase {
    @Override
    public Action getAction() {
        return this.drive.actionBuilder(Constants.STARTING_POSE)
                .splineTo(new Vector2d(15, 33), Math.PI * 3 / 4)
                .build();
    }
}
