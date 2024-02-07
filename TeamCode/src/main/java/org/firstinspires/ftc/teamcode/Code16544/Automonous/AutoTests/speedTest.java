package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;

@TeleOp
public class speedTest extends LinearOpMode {

    private DcMotorEx linearSlideRight = null;
    private DcMotorEx linearSlideLeft = null;

    @Override
    public void runOpMode() throws InterruptedException {
        linearSlideRight = hardwareMap.get(DcMotorEx.class, "linearSlideRight");
        linearSlideLeft = hardwareMap.get(DcMotorEx.class, "linearSlideLeft");
        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        waitForStart();
        while (opModeIsActive()) {
            if(gamepad2.b) {
                linearSlideRight.setTargetPosition(3000);
                linearSlideLeft.setTargetPosition(3000);
                linearSlideRight.setPower(1);
                //linearSlideLeft.setPower(1);
            } else if (gamepad2.a){
                linearSlideRight.setTargetPosition(0);
                linearSlideLeft.setTargetPosition(0);
                linearSlideRight.setPower(1);
                //linearSlideLeft.setPower(1);
            }
        }
    }
}
