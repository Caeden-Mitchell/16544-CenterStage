package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.NullAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SleepAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueDark;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Color;

public class MeepMEepTEsting {
    public static MeepMeep meepMeep = new MeepMeep(600,60);
    static RoadRunnerBotEntity rightBlue1;
    static RoadRunnerBotEntity leftBlue1;
    static RoadRunnerBotEntity midBlue1;
    static RoadRunnerBotEntity rightBlue2;
    static RoadRunnerBotEntity leftBlue2;
    static RoadRunnerBotEntity midBlue2;

    static RoadRunnerBotEntity rightRed1;
    static RoadRunnerBotEntity leftRed1;
    static RoadRunnerBotEntity midRed1;
    static RoadRunnerBotEntity rightRed2;
    static RoadRunnerBotEntity leftRed2;
    static RoadRunnerBotEntity midRed2;

    public static void initBots() {
        rightBlue1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setColorScheme(new ColorSchemeBlueDark())
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,17.5)
                .build();

        leftBlue1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,17.5)
                .build();

        midBlue1 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)

                .setDimensions(17.5,17.5)
                .build();

        rightBlue2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,17.5)
                .build();

        leftBlue2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,17.5)
                .build();

        midBlue2 = new DefaultBotBuilder(meepMeep)
                .setColorScheme(new ColorSchemeBlueDark())
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)

                .setDimensions(17.5,17.5)
                .build();
        
        

        rightRed1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,17.5)
                .build();

        leftRed1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,17.5)
                .build();

        midRed1 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)

                .setDimensions(17.5,17.5)
                .build();

        rightRed2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,17.5)
                .build();

        leftRed2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(17.5,17.5)
                .build();

        midRed2 = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(45, 35, Math.toRadians(180), Math.toRadians(180), 10.5)

                .setDimensions(17.5,17.5)
                .build();
    }
    public static void main(String[] args) {

        Pose2d rightRedPose = new Pose2d(14.5,-59, Math.toRadians(270));
        Pose2d leftRedPose  = new Pose2d(-38.4,-58, Math.toRadians(270));
        Pose2d rightBluePose= new Pose2d(-38.8,64, Math.toRadians(90));
        Pose2d leftBluePose = new Pose2d(14.5,64, Math.toRadians(270));

        initBots();
        
        leftRed1.runAction(leftRed1.getDrive().actionBuilder(leftRedPose)
                .setTangent(Math.toRadians(100))
                .splineToSplineHeading(new Pose2d(-35.5, -33,Math.toRadians(180)), Math.toRadians(90))
                .waitSeconds(2)

                .setTangent(Math.toRadians(30))
                .splineToConstantHeading(new Vector2d(-35.5,-5),Math.toRadians(0))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49, -29), Math.toRadians(315))
                .build());

        rightRed1.runAction(rightRed1.getDrive().actionBuilder(leftRedPose)
                .setTangent(Math.toRadians(110))
                .splineToSplineHeading(new Pose2d(-36, -33, Math.toRadians(0)), Math.toRadians(0))
                .waitSeconds(2)

                .setTangent(Math.toRadians(160))
                .splineToSplineHeading(new Pose2d(-35 , 0, Math.toRadians(180)), Math.toRadians(0))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(50 , -32), Math.toRadians(300))
                .build());

        midRed1.runAction(midRed1.getDrive().actionBuilder(leftRedPose)
                .strafeTo(new Vector2d(-36,-10))
                .waitSeconds(2)
                .strafeTo(new Vector2d(-36,-5))
                .turnTo(Math.toRadians(180))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49, -35), Math.toRadians(300))
                //.splineToConstantHeading(new Vector2d(49, -35), Math.toRadians(300))
                .build());

        leftRed2.runAction(leftRed2.getDrive().actionBuilder(rightRedPose)
                .strafeToSplineHeading(new Vector2d(10.75, -30),Math.toRadians(180))
                .waitSeconds(2)

                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49, -29), Math.toRadians(0))
                .build());

        rightRed2.runAction(rightRed2.getDrive().actionBuilder(rightRedPose)
                .strafeToSplineHeading(new Vector2d(33.75, -32),Math.toRadians(180))
                .waitSeconds(2)

                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49 , -41), Math.toRadians(310))
                .build());

        midRed2.runAction(midRed2.getDrive().actionBuilder(rightRedPose)
                .strafeToSplineHeading(new Vector2d(14, -14), Math.toRadians(270))
                .waitSeconds(2)

                .setTangent(Math.toRadians(30))
                .splineToSplineHeading(new Pose2d(49, -35.2, Math.toRadians(180)), Math.toRadians(315))
                .build());

        
        rightBlue1.runAction(rightBlue1.getDrive().actionBuilder(leftBluePose)
                .splineToSplineHeading(new Pose2d(10.75, 30,Math.toRadians(180)),Math.toRadians(180))
                .waitSeconds(2)

                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49, 29), Math.toRadians(0))
                .build());

        leftBlue1.runAction(leftBlue1.getDrive().actionBuilder(leftBluePose)
                .strafeToSplineHeading(new Vector2d(33.75, 32),Math.toRadians(180))
                .waitSeconds(2)

                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49 , 41), Math.toRadians(40))
                .build());

        midBlue1.runAction(midBlue1.getDrive().actionBuilder(leftBluePose)
                .strafeToSplineHeading(new Vector2d(14, 14), Math.toRadians(90))
                .waitSeconds(2)

                .setTangent(Math.toRadians(330))
                .splineToSplineHeading(new Pose2d(49, 35.2, Math.toRadians(180)), Math.toRadians(45))
                .build());

        rightBlue2.runAction(rightBlue2.getDrive().actionBuilder(rightBluePose)
                .setTangent(Math.toRadians(280))
                .splineToSplineHeading(new Pose2d(-35.5, 33, Math.toRadians(180)), Math.toRadians(270))
                .waitSeconds(2)

                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(49, 29), Math.toRadians(45))
                .build());


        leftBlue2.runAction(leftBlue2.getDrive().actionBuilder(rightBluePose)
                .setTangent(Math.toRadians(250))
                .strafeTo(new Vector2d(-36, 33))

                .strafeTo(new Vector2d(-39,33))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-39 , 11))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(56, 41), Math.toRadians(90))
                .build());

        midBlue2.runAction(midBlue2.getDrive().actionBuilder(rightBluePose)
                .strafeTo(new Vector2d(-36,10))
                .waitSeconds(2)

                //.setTangent(Math.toRadians(270))
                //.splineToSplineHeading(new Pose2d(-36, 10, Math.toRadians(180)), Math.toRadians(0))
                .setTangent(Math.toRadians(300))
                .splineToSplineHeading(new Pose2d(56.75, 35.5, Math.toRadians(180)), Math.toRadians(60))
                .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(midBlue2)


                .start();

    }
}
