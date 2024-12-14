package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

@Autonomous(name = "AutonomiceAutoRight")
public class AutoRight extends BasketPark {
    @Override
    public Pose2d getStartingPose() {
        return new Pose2d(new Vector2d(-24, 65), -Math.PI / 2);
    }
}
