package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.core.colorscheme.ColorScheme;
import com.noahbres.meepmeep.core.colorscheme.scheme.ColorSchemeBlueLight;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

import java.awt.Color;

public class MeepMEepTEsting {
    public static MeepMeep meepMeep = new MeepMeep(800);
    public static void main(String[] args) {

        RoadRunnerBotEntity rightBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d( -33,64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-32, 34.5))

                                .strafeTo(new Vector2d(-36, 9.25))
                                .setTangent(Math.toRadians(180))
                                .splineToConstantHeading(new Vector2d(-59.68, 11.37), Math.toRadians(180.00))
                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(49, 27.5), Math.toRadians(45))
                                .build());

        RoadRunnerBotEntity leftBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d( -33,64.0, Math.toRadians(180)))
                                        .lineToSplineHeading(new Pose2d(-39, 35, Math.toRadians(0)))
                                        //.turn(Math.toRadians(180))
                                        .strafeTo(new Vector2d(-38.5, 35))

                                        //.lineToSplineHeading(new Pose2d(-36, 9.25, Math.toRadians(180)))
                                        .setTangent(Math.toRadians(270))
                                        .splineToSplineHeading(new Pose2d(-59.68, 11.37, Math.toRadians(-179.99)), Math.toRadians(180.00))
                                        .setTangent(Math.toRadians(0))
                                        .splineToConstantHeading(new Vector2d(49 , 42.5), Math.toRadians(90))

                                        .build());

        RoadRunnerBotEntity midBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                                drive.trajectorySequenceBuilder(new Pose2d( -33,64.0, Math.toRadians(180)))
                                        .lineToSplineHeading(new Pose2d(-36, 9, Math.toRadians(90)))
                                        //.turn(Math.toRadians(-90))

                                        .setTangent(Math.toRadians(180))
                                        .splineToSplineHeading(new Pose2d(-59.68, 11.37, Math.toRadians(180)), Math.toRadians(180.00))
                                        .setTangent(Math.toRadians(0))
                                        .splineToConstantHeading(new Vector2d(49, 35), Math.toRadians(60))
                                        .build());

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(midBlue)
                .addEntity(leftBlue)
                .addEntity(rightBlue)
                .start();

    }
}
