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

        RoadRunnerBotEntity rightBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, 64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-32, 34.5))
                                .waitSeconds(0.75)

                                .lineToConstantHeading(new Vector2d(-32, 13))
                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(49, 27.5), Math.toRadians(45))
                                .build()
                );

        RoadRunnerBotEntity leftBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, 64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-39, 35))
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-38.5, 35))
                                .waitSeconds(0.75)

                                .turn(Math.toRadians(-180))
                                .strafeTo(new Vector2d(-39, 15))
                                .setTangent(Math.toRadians(-20))
                                .splineToConstantHeading(new Vector2d(49 , 42.5), Math.toRadians(90))


                                /*.strafeTo(new Vector2d(-34.5, 21))
                                .turn(Math.toRadians(-130))
                                .waitSeconds(0.75)

                                .turn(Math.toRadians(180))
                                .lineToConstantHeading(new Vector2d(-35, 15))
                                .setTangent(Math.toRadians(-20))
                                .splineToConstantHeading(new Vector2d(43.4 , 43.3), Math.toRadians(90))*/
                                .build()
                );

        RoadRunnerBotEntity middleBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5, 12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, 64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-36, 9))
                                .turn(Math.toRadians(-90))
                                .waitSeconds(0.75)

                                .turn(Math.toRadians(90))
                                //.lineToConstantHeading(new Vector2d(-36, 13))
                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(49, 35), Math.toRadians(60))

                                .build()
                );







        RoadRunnerBotEntity rightRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, -64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-32, -34.5))
                                .waitSeconds(0.75)

                                .lineToConstantHeading(new Vector2d(-32, -13))
                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(49, -27.5), Math.toRadians(-45))
                                .build()
                );

        RoadRunnerBotEntity leftRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, -64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-39, -35))
                                .turn(Math.toRadians(-180))
                                .strafeTo(new Vector2d(-38.5, -35))
                                .waitSeconds(0.75)

                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(-39, -15))
                                .setTangent(Math.toRadians(20))
                                .splineToConstantHeading(new Vector2d(49 , -42.5), Math.toRadians(-90))


                                /*.strafeTo(new Vector2d(-34.5, 21))
                                .turn(Math.toRadians(-130))
                                .waitSeconds(0.75)

                                .turn(Math.toRadians(180))
                                .lineToConstantHeading(new Vector2d(-35, 15))
                                .setTangent(Math.toRadians(-20))
                                .splineToConstantHeading(new Vector2d(43.4 , 43.3), Math.toRadians(90))*/
                                .build()
                );

        RoadRunnerBotEntity middleRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5, 12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(-33, -64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(-36, -9))
                                .turn(Math.toRadians(90))
                                .waitSeconds(0.75)

                                .turn(Math.toRadians(-90))
                                //.lineToConstantHeading(new Vector2d(-36, 13))
                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(49, -35), Math.toRadians(-60))

                                .build()
                );
        run();

       meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(rightBlue)
                .addEntity(leftBlue)
                .addEntity(middleBlue)
                .addEntity(rightRed)
                .addEntity(leftRed)
                .addEntity(middleRed)
                .start();
    }

    public static void run() {

        RoadRunnerBotEntity rightBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d( 14,64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(15, 34.5))
                                .waitSeconds(0.75)

                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(49, 27.5), Math.toRadians(0))
                                .build()
                );

        RoadRunnerBotEntity leftBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(14, 64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(14, 35))
                                .turn(Math.toRadians(180))
                                .strafeTo(new Vector2d(8.5, 35))
                                .waitSeconds(0.75)

                                .setTangent(Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(49 , 42.5, Math.toRadians(180)), Math.toRadians(0))
                                .build()
                );

        RoadRunnerBotEntity middleBlue = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5, 12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(14, 64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(11, 9))
                                .turn(Math.toRadians(-90))
                                .waitSeconds(0.75)

                                .turn(Math.toRadians(90))
                                .setTangent(Math.toRadians(45))
                                .splineToConstantHeading(new Vector2d(49, 35), Math.toRadians(45))

                                .build()
                );



        RoadRunnerBotEntity rightRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d( 14,-64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(15, -36))
                                .waitSeconds(0.75)

                                .setTangent(Math.toRadians(0))
                                .splineToConstantHeading(new Vector2d(49, -27.5), Math.toRadians(0))
                                .build()
                );

        RoadRunnerBotEntity leftRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5,12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(14, -64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(14, -35))
                                .turn(Math.toRadians(-180))
                                .strafeTo(new Vector2d(8.5, -35))
                                .waitSeconds(0.75)

                                .setTangent(Math.toRadians(0))
                                .splineToSplineHeading(new Pose2d(49 , -42.5, Math.toRadians(180)), Math.toRadians(0))
                                .build()
                );

        RoadRunnerBotEntity middleRed = new DefaultBotBuilder(meepMeep)
                // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 10.5)
                .setDimensions(12.5, 12.5)
                .followTrajectorySequence(drive ->
                        drive.trajectorySequenceBuilder(new Pose2d(14, -64.0, Math.toRadians(180)))
                                .strafeTo(new Vector2d(11, -9))
                                .turn(Math.toRadians(90))
                                .waitSeconds(0.75)

                                .turn(Math.toRadians(-90))
                                .setTangent(Math.toRadians(-45))
                                .splineToConstantHeading(new Vector2d(49, -35), Math.toRadians(-45))

                                .build()
                );

        meepMeep.setBackground(MeepMeep.Background.FIELD_CENTERSTAGE_JUICE_DARK)
                .setDarkMode(true)
                .setBackgroundAlpha(0.95f)
                .addEntity(rightBlue)
                .addEntity(leftBlue)
                .addEntity(middleBlue)
                .addEntity(rightRed)
                .addEntity(leftRed)
                .addEntity(middleRed)
                .start();
    }
}
