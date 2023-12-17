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



    public AutoActions(HardwareMap hardwareMap, Pose2d startPose) {
        drive = new MecanumDrive(hardwareMap, startPose);

        parkRight = drive.actionBuilder(new Pose2d(50.25, -35.25, Math.toRadians(180)))
                .strafeTo(new Vector2d(50.25, -64))
                .build();

        parkLeft = drive.actionBuilder(new Pose2d(50.65, 35.2, Math.toRadians(180)))
                .strafeTo(new Vector2d(50.65, 64))
                .build();

        rightBlueRightSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-32, 34.5))
                .build();

        rightBlueLeftSpike = drive.actionBuilder(startPose)
                //.strafeToLinearHeading(new Vector2d(-33.41, 36), Math.toRadians(180))
                .strafeTo(new Vector2d(-39, 35))
                .turnTo(Math.toRadians(0))
                .strafeTo(new Vector2d(-38.5, 35))
                .build();

        rightBlueMidSpike = drive.actionBuilder(startPose)
                //.strafeToLinearHeading(new Vector2d(-36, 34), Math.toRadians(270))
                .strafeTo(new Vector2d(-36, 9))
                .turnTo(Math.toRadians(90))
                .build();

         rightBlueMidDrop = drive.actionBuilder(new Pose2d(-36, 9, Math.toRadians(90)))
                .turnTo(Math.toRadians(180))
                 .setTangent(Math.toRadians(0))
                 .splineToConstantHeading(new Vector2d(49, 35), Math.toRadians(60))
                 //.strafeToConstantHeading(new Vector2d(50, 19))
                 //.strafeToConstantHeading(new Vector2d(43, 19))
                .build();

        rightBlueLeftDrop = drive.actionBuilder(new Pose2d(-38.5, 35, Math.toRadians(0)))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-39, 15))
                .setTangent(Math.toRadians(-20))
                .splineToConstantHeading(new Vector2d(49 , 42.5), Math.toRadians(90))
                //.strafeToConstantHeading(new Vector2d(50, 19))
                //.strafeToConstantHeading(new Vector2d(43, 19))
                .build();

        rightBlueRightDrop = drive.actionBuilder(new Pose2d(-32, 34.5, Math.toRadians(180)))
                .strafeToConstantHeading(new Vector2d(-32, 12))
                .setTangent(Math.toRadians(-20))
                .splineToConstantHeading(new Vector2d(49, 27.5), Math.toRadians(45))
                //.strafeToConstantHeading(new Vector2d(50, 19))
                //.strafeToConstantHeading(new Vector2d(43, 19))
                .build();

        leftBlueRightSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(15, 36))
                .build();

        leftBlueLeftSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(14, 33))
                .strafeTo(new Vector2d(34.9, 33))
                .build();

        leftBlueMidSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(16, 9))
                .turnTo(Math.toRadians(91))
                .build();

        leftBlueRightDrop = drive.actionBuilder(new Pose2d(15, 36, Math.toRadians(180)))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49, 27.5), Math.toRadians(0))
                .build();

        leftBlueLeftDrop = drive.actionBuilder(new Pose2d(34.9, 33, Math.toRadians(180)))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49 , 42), Math.toRadians(0))
                .build();

        leftBlueMidDrop = drive.actionBuilder(new Pose2d(16, 9, Math.toRadians(91)))
                .turnTo(Math.toRadians(180))
                .setTangent(Math.toRadians(45))
                .splineToConstantHeading(new Vector2d(49, 35.2), Math.toRadians(45))
                .build();



        leftRedLeftSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-32, -34.5))
                .build();

        leftRedRightSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-39, -35))
                .turnTo(Math.toRadians(0))
                .strafeTo(new Vector2d(-38.5, -35))
                .build();

        leftRedMidSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-36, -9))
                .turnTo(Math.toRadians(270))
                .build();

        leftRedLeftDrop = drive.actionBuilder(new Pose2d(-32, -34.5, Math.toRadians(180)))
                .strafeToConstantHeading(new Vector2d(-32, -13))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49, -28.5), Math.toRadians(-45))
                .build();

        leftRedRightDrop = drive.actionBuilder(new Pose2d(-38.5, -35, Math.toRadians(0)))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-39, -15))
                .setTangent(Math.toRadians(20))
                .splineToConstantHeading(new Vector2d(49 , -42.5), Math.toRadians(-90))
                .build();

        leftRedMidDrop = drive.actionBuilder(new Pose2d(-36, -9, Math.toRadians(270)))
                .turnTo(Math.toRadians(180))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49, -35), Math.toRadians(-60))
                .build();

        rightRedLeftSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(15, -36))
                .build();
        
        rightRedRightSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(14, -33))
                .strafeTo(new Vector2d(34.75, -33))
                .build();
        
        rightRedMidSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(16, -9))
                .turnTo(Math.toRadians(269))
                .build();
        
        rightRedLeftDrop = drive.actionBuilder(new Pose2d(15, -36, Math.toRadians(180)))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49  , -27.5), Math.toRadians(0))
                .build();
        
        rightRedRightDrop = drive.actionBuilder(new Pose2d(34.75, -33, Math.toRadians(180)))
                .setTangent(Math.toRadians(0))
                .splineToConstantHeading(new Vector2d(49 , -42), Math.toRadians(0))
                .build();
        
        rightRedMidDrop = drive.actionBuilder(new Pose2d(16, -9, Math.toRadians(269)))
                .turnTo(Math.toRadians(180))
                .setTangent(Math.toRadians(-45))
                .splineToConstantHeading(new Vector2d(49, -35.25), Math.toRadians(-45))
                .build();
        





    }
}
