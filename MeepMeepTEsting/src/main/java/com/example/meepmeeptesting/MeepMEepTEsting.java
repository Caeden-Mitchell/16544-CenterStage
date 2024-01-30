package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.NullAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Color;

public class MeepMEepTEsting {
    public static MeepMeep meepMeep = new MeepMeep(800);
    public static void main(String[] args) {

        Action a = null;

        RoadRunnerBotEntity rightBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,12.5)
                .build();

        RoadRunnerBotEntity leftBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,12.5)
                .build();

        RoadRunnerBotEntity midBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)

                .setDimensions(17.5,12.5)
                .build();


        rightBlue.runAction(rightBlue.getDrive().actionBuilder(new Pose2d( 17.25,-61.25, Math.toRadians(180)))
                .strafeTo(new Vector2d(7.75, -30))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49.5, -27.5), Math.toRadians(0))
                .build());


        leftBlue.runAction(leftBlue.getDrive().actionBuilder(new Pose2d( 17.25,-61.25, Math.toRadians(180)))
                //.strafeTo(new Vector2d(14, 33))
                .strafeTo(new Vector2d(30.75, -32))
                .setTangent(Math.toRadians(300))
                .splineToConstantHeading(new Vector2d(49.5 , -42), Math.toRadians(0))
                .build());

        midBlue.runAction(midBlue.getDrive().actionBuilder(new Pose2d( 17.25,-61.25, Math.toRadians(180)))
                .strafeToSplineHeading(new Vector2d(14, -16.75), Math.toRadians(270))
                .setTangent(Math.toRadians(30))
                .splineToSplineHeading(new Pose2d(49.5, -35.2, Math.toRadians(180)), Math.toRadians(315))

                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                //.addEntity(midBlue)
                //.addEntity(rightBlue)
                .addEntity(leftBlue)
                .start();

    }
}
