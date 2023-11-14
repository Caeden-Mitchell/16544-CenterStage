package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
@Config
public class Servotest extends LinearOpMode {

    public static double position = 0;
    public static double postion1 = 0;

    private Servo servo, servo1;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "rotateArm");
        servo1 = hardwareMap.get(Servo.class, "rotateHopper");
        servo.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        /*while (opModeIsActive()) {

            servo.setPosition(position);
            servo1.setPosition(postion1);

        }*/


        servo1.setPosition(0);
        servo.setPosition(0);
        sleep(1000);
        servo1.setPosition(.14);
        servo.setPosition(.122);
        sleep(4000);
        servo1.setPosition(0.1);
        servo.setPosition(.15);
        sleep(2000);
        servo1.setPosition(.14);
        servo.setPosition(0.122);
        sleep(2000);
    }
}
