package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        Pose2d basket = new Pose2d(56, 56, Math.PI / 4);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 15)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(24, 64, -Math.PI / 2))
                .splineTo(basket.position, basket.heading)
                .strafeTo(new Vector2d(36, basket.position.y))
                .splineToLinearHeading(new Pose2d(44, 24, 0), basket.heading)
                .splineToSplineHeading(basket, 0)
                .strafeTo(new Vector2d(48, basket.position.y))
                .splineToLinearHeading(new Pose2d(51, 24,0), basket.heading)
                .splineToSplineHeading(basket, 0)
                .lineToX(basket.position.x - 12)
                .turnTo(-Math.PI / 2)
                .splineTo(new Vector2d(-55, 62), Math.PI)
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}