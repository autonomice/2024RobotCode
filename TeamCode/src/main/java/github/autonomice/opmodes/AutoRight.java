package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import github.autonomice.Constants;
import github.autonomice.util.AutoBase;

@Autonomous(name = "AutonomiceAutoRight")
public class AutoRight extends AutoBase {
    @Override
    public Action getAction() {
        return this.r.mecanumDrive.actionBuilder(Constants.STARTING_POSE)
                .lineToX(2 * 12)
                .build();
    }
}
