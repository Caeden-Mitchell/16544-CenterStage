package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;
import com.noahbres.meepmeep.roadrunner.trajectorysequence.TrajectorySequence;

public class MeepMEepTEsting {
    public static void main(String[] args) {
        MeepMeep meepMeep = new MeepMeep(800);


       /* TrajectorySequence midDrop = drive.trajectorySequenceBuilder(new Pose2d(-35.57, 35.57, Math.toRadians(0.00)))
                .splineTo(new Vector2d(8.92, 35.50), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(45.0, 35.50, Math.toRadians(180.00)), Math.toRadians(0.00))
                .build();

        TrajectorySequence rightDrop = drive.trajectorySequenceBuilder(new Pose2d(-35.57, 35.57, Math.toRadians(0.00)))
                .splineTo(new Vector2d(8.92, 35.50), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(45, 43.3, Math.toRadians(180.00)), Math.toRadians(0.00))
                .build();

        TrajectorySequence leftDrop = drive.trajectorySequenceBuilder(new Pose2d(-35.57, 35.57, Math.toRadians(0.00)))
                .splineTo(new Vector2d(8.92, 35.50), Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(45, 29.0, Math.toRadians(180.00)), Math.toRadians(0.00))
                .build();*/

        RoadRunnerBotEntity right = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,19.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-37, 64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-36., 35))
                                .lineToConstantHeading(new Vector2d(-36, 13))
                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(43.4, 27), Math.toRadians(30))
                                .build()
                );

        RoadRunnerBotEntity left = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,19.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-37, 64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-34.8, 36))
                                .turn(Math.toRadians(180))

                                .turn(Math.toRadians(180))
                                .lineToConstantHeading(new Vector2d(-36, 13))
                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(43.4 , 43.3), Math.toRadians(90))
                                .build()
                );

        RoadRunnerBotEntity middle = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5, 19.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-37, 64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-36.5,64))
                                .strafeTo(new Vector2d(-37,64))

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                //.addEntity(right)
                //.addEntity(left)
                .addEntity(middle)
                .start();
    }
}