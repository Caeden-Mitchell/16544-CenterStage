package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
@Config
public class TestSlides extends LinearOpMode {
    private DcMotorEx slideLeft = null;
    private DcMotorEx slideRight = null;

    public static double leftPow = 0;
    public static double rightPow = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        slideLeft = hardwareMap.get(DcMotorEx.class, "linearSlideLeft");
        slideRight = hardwareMap.get(DcMotorEx.class, "linearSlideRight");

        slideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        slideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        slideRight.setTargetPosition(0);
        slideLeft.setTargetPosition(0);
        slideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        slideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        slideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        slideLeft.setDirection(DcMotorSimple.Direction.REVERSE);

        waitForStart();

        slideLeft.setTargetPosition(500);
        slideRight.setTargetPosition(500);
        slideRight.setPower(rightPow);
        slideLeft.setPower(leftPow);
        sleep(1500);
        slideRight.setPower(0);
        slideLeft.setPower(0);
        sleep(500);


    }
}
