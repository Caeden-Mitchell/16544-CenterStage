package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.R;
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
                .strafeTo(new Vector2d(-35, 34))
                .build();

        leftSpike = drive.actionBuilder(startPose)
                //.strafeToLinearHeading(new Vector2d(-33.41, 36), Math.toRadians(180))
                .strafeTo(new Vector2d(-35, 36))
                .turn(Math.toRadians(-180))
                .build();

        midSpike = drive.actionBuilder(startPose)
                //.strafeToLinearHeading(new Vector2d(-36, 34), Math.toRadians(270))
                .strafeTo(new Vector2d(-36, 10))
                .turnTo(Math.toRadians(90))
                .build();

         midDrop = drive.actionBuilder(new Pose2d(-36, 10, Math.toRadians(90)))
                .turnTo(Math.toRadians(180))
                .setTangent(Math.toRadians(-15))
                .splineToConstantHeading(new Vector2d(50, 36), Math.toRadians(60))
                 //.strafeToConstantHeading(new Vector2d(50, 19))
                 //.strafeToConstantHeading(new Vector2d(43, 19))
                .build();

        leftDrop = drive.actionBuilder(new Pose2d(-35, 36, Math.toRadians(0)))
                 .turnTo(Math.toRadians(180))
                 .strafeToConstantHeading(new Vector2d(-36, 13))
                 .setTangent(Math.toRadians(0))
                 .splineToConstantHeading(new Vector2d(50 , 43.3), Math.toRadians(90))
                //.strafeToConstantHeading(new Vector2d(50, 19))
                //.strafeToConstantHeading(new Vector2d(43, 19))
                .build();

        rightDrop = drive.actionBuilder(new Pose2d(-35, 34, Math.toRadians(180)))
                .strafeToConstantHeading(new Vector2d(-40, 10))
                .setTangent(Math.toRadians(-20))
                .splineToConstantHeading(new Vector2d(50, 26.75), Math.toRadians(45))
                //.strafeToConstantHeading(new Vector2d(50, 19))
                //.strafeToConstantHeading(new Vector2d(43, 19))
                .build();

    }
}
