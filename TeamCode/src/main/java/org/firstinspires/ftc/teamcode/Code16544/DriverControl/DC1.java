package org.firstinspires.ftc.teamcode.Code16544.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@TeleOp
public class DC1 extends LinearOpMode {
    DcMotor leftFront = null;
    DcMotor rightFront = null;
    DcMotor leftBack = null;
    DcMotor rightBack = null;
    DcMotorEx pixelLift = null;
    DcMotorEx robotLift = null;

    @Override
    public void runOpMode() throws InterruptedException {
        //declare Motors
        leftFront = hardwareMap.get(DcMotor.class, "leftFront");
        rightFront = hardwareMap.get(DcMotor.class, "rightFront");
        leftBack = hardwareMap.get(DcMotor.class, "leftBack");
        rightBack = hardwareMap.get(DcMotor.class, "rightBack");
        pixelLift = hardwareMap.get(DcMotorEx.class, "pixelLift");
        robotLift = hardwareMap.get(DcMotorEx.class, "robotLift");

        initialize();

        double y = 0.0; //left stick y
        double x = 0.0; //left stick x
        double rx = 0.0; //right stick x
        double denominator = 0.0;

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (gamepad1.left_bumper) {
                //slow mode
                y = -gamepad1.left_stick_y / 3.5; // Y Stick is reversed
                x = -gamepad1.left_stick_x * 1.1 / 3.5; //counters imperfect strafing
                rx = gamepad1.right_stick_x / 3.5;
            } else {
                //fast mode
                y = -gamepad1.left_stick_y; // Y Stick is reversed
                x = -gamepad1.left_stick_x * 1.1; //counters imperfect strafing
                rx = gamepad1.right_stick_x;
            }


            if (gamepad2.dpad_down) {
                setPxlLiftHeight(300);
            }
            if (gamepad2.dpad_left || gamepad2.dpad_right) {
                setPxlLiftHeight(600);
            }
            if (gamepad2.dpad_up) {
                setPxlLiftHeight(900);
            }
            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            //denominator = 1;


            setDrivePower((y - x + rx) / denominator, (y + x + rx) / denominator, (y + x - rx) / denominator, (y - x - rx) / denominator);
            liftRobot();
        }
    }

    private void setPxlLiftHeight(int liftHeight) {
        pixelLift.setTargetPosition(liftHeight);
        pixelLift.setPower(0.6);
    }

    private void liftRobot() {
        if (gamepad1.right_trigger > 0.5) {
            robotLift.setTargetPosition(500);
            robotLift.setPower(0.4);
        } else {
            robotLift.setTargetPosition(0);
            robotLift.setPower(0.4);
        }
    }

    private void setDrivePower(double lf, double lb, double rf, double rb) {
        leftFront.setPower(lf);
        leftBack.setPower(lb);
        rightFront.setPower(rf);
        rightBack.setPower(rb);
    }

    private void initialize() {
        rightFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftFront.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        leftBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        rightBack.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pixelLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robotLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pixelLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robotLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pixelLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robotLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pixelLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robotLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //reverse motors
        leftBack.setDirection(DcMotorSimple.Direction.REVERSE);
        leftFront.setDirection(DcMotorSimple.Direction.REVERSE);
    }
}
