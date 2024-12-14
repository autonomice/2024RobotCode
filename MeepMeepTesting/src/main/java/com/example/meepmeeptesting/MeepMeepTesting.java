package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);
        Pose2d basket = new Pose2d(61, 61, Math.PI / 4);
        Vector2d resetPos = new Vector2d(basket.position.x -9, basket.position.y);

        RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 50, Math.PI, Math.PI, 24.1)
                .build();


        /* myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(24, 59.5, -Math.PI / 2))
                .strafeToLinearHeading(basket.position, basket.heading)
                .strafeTo(resetPos) // bugfix mostly
                .splineToLinearHeading(new Pose2d(48, 22, Math.PI), basket.heading) // line up first sample
                .strafeToConstantHeading(basket.position) // push first sample
                .strafeTo(resetPos) // away from first sample
                .strafeToConstantHeading(new Vector2d(52, 22)) // line up second
                .strafeToConstantHeading(basket.position) // push second
                .strafeTo(resetPos) // reset
                .strafeToConstantHeading(new Vector2d(basket.position.x, 24)) // line up third
                .strafeTo(new Vector2d(63, 24)) // get third
                .strafeTo(new Vector2d(63, 63)) // push third
                .strafeTo(new Vector2d(-55, 63)) // park
                .build()); */
        myBot.runAction(myBot.getDrive().actionBuilder(new Pose2d(24, 64, -Math.PI / 2))
                .strafeToLinearHeading(basket.position, basket.heading) // go to basket
                .strafeTo(resetPos) // bugfix mostly
                .splineToLinearHeading(new Pose2d(40, 22, 0), basket.heading) // line up first sample
                //.splineToLinearHeading(new Pose2d(40, 22, Math.PI), basket.heading) // line up first sample
                .splineToSplineHeading(basket, 0) // push first sample
                //.splineToSplineHeading(new Pose2d(basket.position, -Math.PI * 3 / 4), Math.PI) // push first sample
                .strafeTo(resetPos) // away from first sample
                /* .splineToLinearHeading(new Pose2d(50, 22,0), basket.heading) // line up second
                //.splineToLinearHeading(new Pose2d(50, 22,Math.PI), basket.heading) // line up second
                .splineToSplineHeading(basket, 0) // push second
                //.splineToSplineHeading(new Pose2d(basket.position, -Math.PI * 3 / 4), 0) // push second
                .strafeToConstantHeading(new Vector2d(basket.position.x, 24)) // position third
                .strafeToLinearHeading(new Vector2d(63, 24), 0) // get third
                //.strafeToLinearHeading(new Vector2d(63, 24), Math.PI) // get third
                .strafeTo(new Vector2d(63, 63)) // push third
                //.strafeToLinearHeading(new Vector2d(basket.position.x - 6, 60), 0) // line up park
                //.strafeTo(new Vector2d(-55, 60)) // park
                .strafeTo(new Vector2d(-55, 63)) // park*/
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_INTO_THE_DEEP_OFFICIAL)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(myBot)
                .start();
    }
}