package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

@TeleOp
@Config
public class ServoTestSimple extends LinearOpMode {

    public static double position = 0;
    public static double postion1 = 0;
    public static double power = 0.15;
    public static int time = 150;

    private Servo servo, servo1, servo2;
    private DcMotorEx intakeMotor;
    @Override
    public void runOpMode() throws InterruptedException {
        servo = hardwareMap.get(Servo.class, "rotateArm");
        servo1 = hardwareMap.get(Servo.class, "rotateHopper");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");

        servo.setDirection(Servo.Direction.REVERSE);

        waitForStart();

        while (opModeIsActive()) {
            servo.setPosition(position);
            servo1.setPosition(postion1);

        }
    }
}
