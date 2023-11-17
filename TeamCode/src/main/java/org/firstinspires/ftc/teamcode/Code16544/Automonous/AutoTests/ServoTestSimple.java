package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
@Config
public class ServoTestSimple extends LinearOpMode {

    public static double position = 0;
    public static double postion1 = 0;
    public static double position2 = 0;

    private Servo servo, servo1, servo2;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "rotateArm");
        servo1 = hardwareMap.get(Servo.class, "rotateHopper");
        servo2 = hardwareMap.get(Servo.class, "latch_servo");

        servo.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            servo.setPosition(position);
            servo1.setPosition(postion1);
            servo2.setPosition(position2);

        }
    }
}
