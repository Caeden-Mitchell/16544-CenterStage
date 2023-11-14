package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;

@Config
@Autonomous
public class PIDF_LinearSlide extends LinearOpMode {
    RobotSystems robot;
    public static int target = 0;
    public static double delayInSec = 5.0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new RobotSystems(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addData("target", target);
        telemetry.addData("pixel pos", robot.pixelLift.getCurrentPosition());
        telemetry.update();

        waitForStart();

        robot.servoToZero();

        if (isStopRequested()) return;

        ElapsedTime delay = new ElapsedTime();

        while (delay.seconds() < delayInSec) {
            robot.setPixelLiftHeight(target);
        }

        robot.preDrop();
        robot.autoDrop();
        robot.servoToZero();
        sleep(1000);
        robot.servoToZero();
        delay.reset();

        while (delay.seconds() < 2) {
            robot.setPixelLiftHeight(0);
        }

        robot.deadState();

        telemetry.addData("position", robot.pixelLift.getCurrentPosition());
        telemetry.addData("Target ", target);
        telemetry.update();
    }
}
