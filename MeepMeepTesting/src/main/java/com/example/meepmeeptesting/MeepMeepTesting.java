
package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

    public class MeepMeepTesting {
        static double maxVel = 70;
        static double maxAccel = 100;
        static double maxAngVel = Math.toRadians(360);
        static double maxAngAccel = Math.toRadians(370);
        static double trackWidth = 10.8;
        public static void main(String[] args) {
            MeepMeep meepMeep = new MeepMeep(800);

            RoadRunnerBotEntity myBot = new DefaultBotBuilder(meepMeep)
                    // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
                    .setConstraints(maxVel, maxAccel, maxAngVel, maxAngAccel, trackWidth)
                    .followTrajectorySequence(drive ->
                            drive.trajectorySequenceBuilder(new Pose2d(35.68, 62.31, Math.toRadians(270.00)))
                                    .splineTo(new Vector2d(36.16, 18.89), Math.toRadians(-89.36))
                                    .splineTo(new Vector2d(27.28, 3.39), Math.toRadians(240.19))
                                    .splineTo(new Vector2d(46.33, 12.43), Math.toRadians(25.39))
                                    .splineTo(new Vector2d(69.42, 12.59), Math.toRadians(0.40))
                                    .build()

                    );

            meepMeep.setBackground(MeepMeep.Background.FIELD_POWERPLAY_OFFICIAL)
                    .setDarkMode(true)
                    .setBackgroundAlpha(0.95f)
                    .addEntity(myBot)
                    .start();
        }
    }

