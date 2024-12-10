package github.autonomice.opmodes;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;

import github.autonomice.Constants;
import github.autonomice.util.AutoBase;

public abstract class BasketPark extends AutoBase {
    @Override
    public Action getAction() {
        Pose2d basket = new Pose2d(56, 56, Math.PI / 4);
        return new SequentialAction(
                this.drive.actionBuilder(getStartingPose())
                        .splineTo(basket.position, basket.heading)
                        .build(),
                this.arm.runToPos(Constants.ARM_UP_POS),
                this.intake.setPower(Constants.INTAKE_OUT_POWER),
                new SleepAction(1.0),
                this.intake.setPower(0.),
                this.arm.runToPos(0),
                this.drive.actionBuilder(basket)
                        .strafeTo(new Vector2d(36, basket.position.y))
                        .splineToLinearHeading(new Pose2d(44, 24, 0), basket.heading)
                        .splineToSplineHeading(basket, 0)
                        .strafeTo(new Vector2d(48, basket.position.y))
                        .splineToLinearHeading(new Pose2d(51, 24,0), basket.heading)
                        .splineToSplineHeading(basket, 0)
                        .lineToX(basket.position.x - 12)
                        .turnTo(-Math.PI / 2)
                        .splineTo(new Vector2d(-55, 62), Math.PI)
                        .build()
        );
    }
}
