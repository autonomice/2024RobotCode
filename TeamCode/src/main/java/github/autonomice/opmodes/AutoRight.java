package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Constants;
import github.autonomice.util.AutoBase;

@Autonomous(name = "AutonomiceAutoRight")
public class AutoRight extends AutoBase {
    @Override
    public Action getAction() {
        return this.drive.actionBuilder(Constants.STARTING_POSE)
                .strafeTo(new Vector2d(Constants.STARTING_POSE.position.x, -24))
                .build();
    }
}
