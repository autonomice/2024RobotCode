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
                new SleepAction(1.0),
                this.arm.runToPos(Constants.ARM_UP_POS),
                //this.intake.setPower(Constants.INTAKE_OUT_POWER),
                this.arm.runToPos(0),
                //this.intake.setPower(0.),
                this.drive.actionBuilder(basket)
                        .splineToConstantHeading(new Vector2d(44, 17), basket.heading)
                        .turnTo(0)
                        .lineToX(48)
                        .turnTo(basket.heading)
                        .splineToConstantHeading(basket.position, basket.heading)
                        .turnTo(-Math.PI / 2)
                        .splineTo(new Vector2d(-60, 66), Math.PI)
                        .build()
        );
    }
}
