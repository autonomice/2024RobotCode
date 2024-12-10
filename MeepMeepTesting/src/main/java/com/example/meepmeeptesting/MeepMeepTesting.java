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
                .setConstraints(45, 50, Math.PI, Math.PI, 24.1)
                .build();

        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(-24, 64, -Math.PI / 2))
                .strafeToLinearHeading(basket.position, basket.heading) // go to basket
                .strafeTo(new Vector2d(basket.position.x - 8, basket.position.y)) // bugfix mostly
                .splineToLinearHeading(new Pose2d(44, 24, 0), basket.heading) // line up first sample
                .splineToSplineHeading(basket, 0) // push first sample
                .strafeTo(new Vector2d(basket.position.x - 5, basket.position.y)) // away from first sample
                .splineToLinearHeading(new Pose2d(51, 24,0), basket.heading) // line up second
                .splineToSplineHeading(basket, 0) // push second
                .strafeToLinearHeading(new Vector2d(basket.position.x - 6, 60), 0) // line up park
                .strafeTo(new Vector2d(-55, 60)) // park
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}