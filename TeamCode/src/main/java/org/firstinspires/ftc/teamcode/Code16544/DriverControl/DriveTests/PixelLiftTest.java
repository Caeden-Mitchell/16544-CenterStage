package org.firstinspires.ftc.teamcode.Code16544.DriverControl.DriveTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;


@TeleOp
@Config
public class PixelLiftTest extends LinearOpMode {
    RobotSystems robot;
    public static int target = 1000;

    private Servo servo, servo1;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new RobotSystems(hardwareMap);

        servo = hardwareMap.get(Servo.class, "rotateArm");
        servo1 = hardwareMap.get(Servo.class, "rotateHopper");
        servo.setDirection(Servo.Direction.REVERSE);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.b) {
                robot.setPixelLiftHeight(target);
            } else if (gamepad1.a) {
                robot.setPixelLiftHeight(0);
            } else {
                robot.linearSlideLeft.setPower(0);
            }
            if(gamepad1.left_bumper) {
                robot.setPixelLiftHeight(target);
                servo1.setPosition(0.16);
                servo.setPosition(0);
                sleep(1000);
                servo1.setPosition(1);
                servo.setPosition(0.95);
                sleep(1500);
                servo1.setPosition(0.65);
                sleep(750);
                servo1.setPosition(0.14);
                servo.setPosition(0);
                sleep(750);
            }
            telemetry.addData("pos ", robot.linearSlideLeft.getCurrentPosition());
            telemetry.update();
        }
    }

}
