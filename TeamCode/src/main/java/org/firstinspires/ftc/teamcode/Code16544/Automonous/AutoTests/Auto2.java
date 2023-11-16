package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

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
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@Config
@Autonomous
public class Auto2 extends LinearOpMode {
    MecanumDrive drive;
    RobotSystems robot;

    public static double startingY = 59.52;
    public static double startingX = -41.67;
    public static int target = 2890;

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startPose = new Pose2d(startingX, startingY, Math.toRadians(0));

        drive = new MecanumDrive(hardwareMap, startPose);

        robot = new RobotSystems(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        drive.updatePoseEstimate();


        Action rightSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-36.5, 35.00))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-33.5, 36))
                .build();

        Action leftSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-33.41, 36))
                .turnTo(Math.toRadians(0))
                .turnTo(Math.toRadians(180))
                .build();

        Action midSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-36, 34))
                .turnTo(Math.toRadians(180))
                .strafeTo(new Vector2d(-33.5, 36))
                .build();

        Action midDrop = drive.actionBuilder(new Pose2d(-33.5, 36, Math.toRadians(180.00)))
                .strafeTo(new Vector2d(45.0, 36))
                .strafeTo(new Vector2d(45.0, 43))
                .build();

        Action leftDrop = drive.actionBuilder(new Pose2d(-33.5, 36, Math.toRadians(180.00)))
                .strafeToConstantHeading(new Vector2d(8.92, 360))
                .splineToSplineHeading(new Pose2d(45, 43.3, Math.toRadians(180.00)), Math.toRadians(180.00))
                .build();

        Action rightDrop = drive.actionBuilder(new Pose2d(-33.5, 36, Math.toRadians(1800.00)))
                .strafeToConstantHeading(new Vector2d(8.92, 360))
                .splineToSplineHeading(new Pose2d(45, 29.0, Math.toRadians(180.00)), Math.toRadians(180.00))
                .build();

        waitForStart();

        if (isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(
                rightSpike,
                telemetryPacket -> {
                    robot.rotateHopper.setPosition(1);
                    return false;
                }
                //,midDrop
        ));
    }
}
