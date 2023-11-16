package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.ParallelAction;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.Vector2d;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@Config
@Autonomous
public class AutoExample extends LinearOpMode {
    MecanumDrive drive;
    RobotSystems robot;

    public static int target = 2890;

    @Override
    public void runOpMode() throws InterruptedException {
        Pose2d startPose = new Pose2d(41.77, -65.17, Math.toRadians(180.00));

        drive = new MecanumDrive(hardwareMap, startPose);

        robot = new RobotSystems(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());


        // This is how trajectories will be built from now on using RR 1.0
        // This traj will drive forward to a point of 10
        Action trajAction = drive.actionBuilder(startPose)
                .strafeTo(new Vector2d(40,-36))
                .strafeToConstantHeading(new Vector2d(45.5,-36))
                .turnTo(Math.toRadians(183))
                .build();

        telemetry.addData("pixel pos", robot.pixelLift.getCurrentPosition());
        telemetry.update();

        waitForStart();

        robot.servoToZero();

        if(isStopRequested()) return;

        Actions.runBlocking(new SequentialAction(trajAction));

        ElapsedTime delay = new ElapsedTime();

        while (delay.seconds() < 2) {
            robot.setPixelLiftHeight(target);
        }

        robot.preDrop();
        robot.autoDrop();

        delay.reset();

        while (delay.seconds() < 1) {
            robot.setPixelLiftHeight(2500);
        }

        robot.servoToZero();
        robot.deadState();

        delay.reset();
        while (delay.seconds() < 1) {
            robot.setPixelLiftHeight(0);
        }

        telemetry.addData("power", robot.pixelLift.getPower());
        telemetry.addData("pixel pos", robot.pixelLift.getCurrentPosition());
        telemetry.update();
    }
}
