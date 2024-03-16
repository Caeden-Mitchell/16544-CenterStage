package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

public class AutoActions {

    MecanumDrive drive;

    public Action rightBlueRightSpike,rightBlueLeftSpike,rightBlueMidSpike,rightBlueMidDrop,rightBlueLeftDrop,rightBlueRightDrop; // right blue
    public Action leftBlueRightSpike,leftBlueLeftSpike,leftBlueMidSpike,leftBlueMidDrop,leftBlueLeftDrop,leftBlueRightDrop; // left blue

    public Action rightRedRightSpike,rightRedLeftSpike,rightRedMidSpike,rightRedMidDrop,rightRedLeftDrop,rightRedRightDrop; // right red
    public Action leftRedRightSpike, leftRedLeftSpike, leftRedMidSpike, leftRedMidDrop, leftRedLeftDrop, leftRedRightDrop; // left red

    public Action parkRight, parkLeft;



    public AutoActions(HardwareMap hardwareMap, Pose2d startPose, Action action) {
        drive = new MecanumDrive(hardwareMap, startPose);

        initBlueActions(startPose, action);
        initRedActions(startPose, action);
    }

    private void initBlueActions(Pose2d startPose, Action action) {
        // right
        rightBlueRightSpike = drive.actionBuilder(startPose)
                .setTangent(Math.toRadians(280))
                .splineToSplineHeading(new Pose2d(-35.5, 33, Math.toRadians(180)), Math.toRadians(270))
                .build();

        rightBlueRightDrop = drive.actionBuilder(new Pose2d(-35.5, 33, Math.toRadians(180)))
                .setTangent(Math.toRadians(270))
                .splineToConstantHeading(new Vector2d(59, 24.25), Math.toRadians(45))
                .build();

        rightBlueLeftSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-36, 33))
                .turnTo(Math.toRadians(0))
                .build();

        rightBlueLeftDrop = drive.actionBuilder(new Pose2d(-36, 33, Math.toRadians(0)))
                .strafeTo(new Vector2d(-39,33))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-39 , 8))
                .setTangent(Math.toRadians(350))
                .splineToConstantHeading(new Vector2d(58.75, 38), Math.toRadians(90))
                .build();

        rightBlueMidSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-38,3))
                .strafeTo(new Vector2d(-38,11))
                .build();

        rightBlueMidDrop = drive.actionBuilder(new Pose2d(-38, 11, Math.toRadians(90)))
                .setTangent(Math.toRadians(320))
                .splineToSplineHeading(new Pose2d(51.5, 34, Math.toRadians(180)), Math.toRadians(60))
                .build();


        // left
        leftBlueRightSpike = drive.actionBuilder(startPose)
                .strafeToSplineHeading(new Vector2d(10.75, 25),Math.toRadians(180))
                .build();

        leftBlueRightDrop = drive.actionBuilder(new Pose2d(10.75, 25, Math.toRadians(180)))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(56, 24.75), Math.toRadians(0))
                .stopAndAdd(action)
                .strafeTo(new Vector2d(54,56))
                .build();

        leftBlueLeftSpike = drive.actionBuilder(startPose)
                .strafeToSplineHeading(new Vector2d(33.3, 25),Math.toRadians(180))
                .build();

        leftBlueLeftDrop = drive.actionBuilder(new Pose2d(33.3, 25, Math.toRadians(180)))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(55.5 , 38), Math.toRadians(40))
                .stopAndAdd(action)
                .strafeTo(new Vector2d(53, 56))
                .build();

        leftBlueMidSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(13, 11))
                .build();

        leftBlueMidDrop = drive.actionBuilder(new Pose2d(13, 11, Math.toRadians(90)))
                .setTangent(Math.toRadians(330))
                .splineToSplineHeading(new Pose2d(55.2, 31.15, Math.toRadians(182)), Math.toRadians(45))
                .stopAndAdd(action)
                .strafeTo(new Vector2d(54,56))
                .build();
    }

    private void initRedActions(Pose2d startPose, Action action) {
        // left
        leftRedLeftSpike = drive.actionBuilder(startPose)
                .setTangent(Math.toRadians(100))
                .splineToSplineHeading(new Pose2d(-35.5, -29, Math.toRadians(180)), Math.toRadians(90))
                .build();

        leftRedLeftDrop = drive.actionBuilder(new Pose2d(-35.5, -29, Math.toRadians(180)))
                .setTangent(Math.toRadians(90))
                .splineToSplineHeading(new Pose2d(49, -31.75, Math.toRadians(176)), Math.toRadians(315))
                .build();

        leftRedRightSpike = drive.actionBuilder(startPose)
                .setTangent(Math.toRadians(110))
                .splineToSplineHeading(new Pose2d(-36, -30, Math.toRadians(0)), Math.toRadians(0))
                .build();

        leftRedRightDrop = drive.actionBuilder(new Pose2d(-35, -30, Math.toRadians(0)))
                .setTangent(Math.toRadians(160))
                .splineToSplineHeading(new Pose2d(-35 , 0, Math.toRadians(180)), Math.toRadians(0))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(48 , -31), Math.toRadians(300))
                .build();

        leftRedMidSpike = drive.actionBuilder(startPose)
                //.splineToSplineHeading(new Pose2d(-36, -14, Math.toRadians(270)), Math.toRadians(90))
                .strafeTo(new Vector2d(-38,-3))
                .strafeTo(new Vector2d(-38,-11))
                .build();

        leftRedMidDrop = drive.actionBuilder(new Pose2d(-38, -11, Math.toRadians(270)))
                .strafeTo(new Vector2d(-38,-2))
                .turnTo(Math.toRadians(180))
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(46, -43.5, Math.toRadians(170)), Math.toRadians(300))
                .build();

        // right

        rightRedLeftSpike = drive.actionBuilder(startPose)
                .strafeToSplineHeading(new Vector2d(10, -29),Math.toRadians(180))
                .build();

        rightRedLeftDrop = drive.actionBuilder(new Pose2d(10, -29, Math.toRadians(180)))
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(54, -26.75, Math.toRadians(177)), Math.toRadians(0))
                .stopAndAdd(action)
                .strafeTo(new Vector2d(51.6, -58.25))
                .build();

        rightRedRightSpike = drive.actionBuilder(startPose)
                .strafeToSplineHeading(new Vector2d(32, -29),Math.toRadians(180))
                .build();

        rightRedRightDrop = drive.actionBuilder(new Pose2d(32, -29, Math.toRadians(180)))
                .setTangent(Math.toRadians(0))
                .splineToSplineHeading(new Pose2d(54 , -41.5, Math.toRadians(179)), Math.toRadians(310))
                .stopAndAdd(action)
                .strafeTo(new Vector2d(50, -58))
                .build();

        rightRedMidSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(13, -12))
                .build();

        rightRedMidDrop = drive.actionBuilder(new Pose2d(13, -12, Math.toRadians(270)))
                .setTangent(Math.toRadians(30))
                .splineToSplineHeading(new Pose2d(54, -34, Math.toRadians(178)), Math.toRadians(315))
                .stopAndAdd(action)
                .strafeTo(new Vector2d(50, -58))
                .build();
    }
}
