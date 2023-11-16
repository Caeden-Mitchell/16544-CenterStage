package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

public class AutoActions {

    MecanumDrive drive;

    public Action rightSpike;
    public Action leftSpike;
    public Action midSpike;
    public Action midDrop;
    public Action leftDrop;
    public Action rightDrop;


    public AutoActions(HardwareMap hardwareMap, Pose2d startPose) {
        drive = new MecanumDrive(hardwareMap, startPose);

        rightSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-36.5, 35))
                .build();

         leftSpike = drive.actionBuilder(startPose)
                //.strafeToLinearHeading(new Vector2d(-33.41, 36), Math.toRadians(180))
                .strafeTo(new Vector2d(-33.41, 36))
                .turnTo(Math.toRadians(0))
                .build();

         midSpike = drive.actionBuilder(startPose)
                .strafeToLinearHeading(new Vector2d(-36, 34), Math.toRadians(270))
                //.strafeTo(new Vector2d(-36, 34))
                .turnTo(Math.toRadians(270))
                .build();

         midDrop = drive.actionBuilder(new Pose2d(-36, 34, Math.toRadians(270)))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-33.41, 36))
                .strafeTo(new Vector2d(45.0, 36))
                .build();

         leftDrop = drive.actionBuilder(new Pose2d(-33.41, 36, Math.toRadians(0)))
                .turnTo(Math.toRadians(180))
                .strafeToConstantHeading(new Vector2d(8.92, 360))
                .splineToSplineHeading(new Pose2d(45, 43.3, Math.toRadians(180)), Math.toRadians(180))
                .build();

         rightDrop = drive.actionBuilder(new Pose2d(-36.5, 35, Math.toRadians(180)))
                .strafeToConstantHeading(new Vector2d(8.92, 360))
                .splineToSplineHeading(new Pose2d(45, 29.0, Math.toRadians(180)), Math.toRadians(180))
                .build();

    }
}
