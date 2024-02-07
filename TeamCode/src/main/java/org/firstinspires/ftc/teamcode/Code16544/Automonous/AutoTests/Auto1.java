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
public class Auto1 extends LinearOpMode {
    MecanumDrive drive;
    RobotSystems robot;

    public static double startingY = 63.0;
    public static double startingX = -38.5;
    public static int target = 2890;

    public static int trajType = 1;

    public static double servoPos = 0;


    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startPose = new Pose2d(startingX, startingY, Math.toRadians(180));

        drive = new MecanumDrive(hardwareMap, startPose);

        robot = new RobotSystems(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        Action rightSpike = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(-36.5, 35))
                .build();

        Action leftSpike = drive.actionBuilder(startPose)
                //.strafeToLinearHeading(new Vector2d(-33.41, 36), Math.toRadians(180))
                .strafeTo(new Vector2d(-33.41, 36))
                .turnTo(Math.toRadians(0))
                .build();

        Action midSpike = drive.actionBuilder(startPose)
                .strafeToLinearHeading(new Vector2d(-36, 34), Math.toRadians(270))
                //.strafeTo(new Vector2d(-36, 34))
                .turnTo(Math.toRadians(270))
                .build();

        Action midDrop = drive.actionBuilder(new Pose2d(-33.41, 36, Math.toRadians(180)))
                .strafeTo(new Vector2d(-33.41, 36))
                .strafeTo(new Vector2d(45.0, 36))
                .build();

        Action leftDrop = drive.actionBuilder(new Pose2d(-33.41, 36, Math.toRadians(180)))
                .strafeTo(new Vector2d(-33.41, 36))
                .strafeToConstantHeading(new Vector2d(8.92, 360))
                .splineToSplineHeading(new Pose2d(45, 43.3, Math.toRadians(180)), Math.toRadians(180))
                .build();

        Action rightDrop = drive.actionBuilder(new Pose2d(-33.41, 36, Math.toRadians(180)))
                .strafeToConstantHeading(new Vector2d(8.92, 360))
                .splineToSplineHeading(new Pose2d(45, 29.0, Math.toRadians(180)), Math.toRadians(180))
                .build();



        waitForStart();

        if (isStopRequested()) return;

        switch (trajType) {
            case 1:
                Actions.runBlocking(new SequentialAction(
                        rightSpike
                        ,midDrop
                ));
                break;
            case 2:
                Actions.runBlocking(new SequentialAction(
                        midSpike,
                        telemetryPacket -> {
                            //robot.rotateHopper.setPosition(1);
                            sleep(2000);
                            return false;
                        }
                        //,midDrop
                ));
                break;
            case 3:
                Actions.runBlocking(new SequentialAction(
                        leftSpike,
                        telemetryPacket -> {
                            //robot.rotateHopper.setPosition(1);
                            sleep(2000);
                            return false;
                        }
                        //,midDrop
                ));
                break;

        }

        robot.servoToZero();

        ElapsedTime delay = new ElapsedTime();

        while (delay.seconds() < 2) {
            robot.setLineLeftHeight(target);
        }

        robot.preDrop();
        robot.autoDrop();

        delay.reset();

        while (delay.seconds() < 1) {
            robot.setLineLeftHeight(2500);
        }

        robot.servoToZero();
        robot.deadState();

        delay.reset();
        while (delay.seconds() < 1) {
            robot.setLineLeftHeight(0);
        }
    }
}
