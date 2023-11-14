package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import android.app.appsearch.SearchResult;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@Config
@Autonomous
public class Auto1 extends LinearOpMode {
    MecanumDrive drive;
    RobotSystems robot;

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startPose = new Pose2d(-41.67, 65.52, Math.toRadians(90));

        drive = new MecanumDrive(hardwareMap, startPose);

        robot = new RobotSystems(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        Action rightSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-36.5, 35.00))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-33.5, 35.5))
                .build();

        Action leftSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-33.41, 35.00))
                .turnTo(Math.toRadians(0))
                .turnTo(Math.toRadians(180))
                .build();

        Action midSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-36, 34))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-33.5, 35.5))
                .build();

        Action midDrop = drive.actionBuilder(new Pose2d(-33.5, 35.5, Math.toRadians(0.00)))
                .strafeTo(new Vector2d(45.0, 35.50))
                .build();

        Action leftDrop = drive.actionBuilder(new Pose2d(-33.5, 35.5, Math.toRadians(0.00)))
                .strafeToConstantHeading(new Vector2d(8.92, 35.50))
                .splineToSplineHeading(new Pose2d(45, 43.3, Math.toRadians(180.00)), Math.toRadians(0.00))
                .build();

        Action rightDrop = drive.actionBuilder(new Pose2d(-33.5, 35.5, Math.toRadians(0.00)))
                .strafeToConstantHeading(new Vector2d(8.92, 35.50))
                .splineToSplineHeading(new Pose2d(45, 29.0, Math.toRadians(180.00)), Math.toRadians(0.00))
                .build();

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(
                rightSpike
                ,telemetryPacket -> {
            telemetry.addLine("Action");
            return false;
        }
                // ,midDrop
        ));
    }
}
