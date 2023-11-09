package org.firstinspires.ftc.teamcode.Code16544.DriverControl.DriveTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

public class IntakeTest extends LinearOpMode {
    private DcMotor intake = null;

    @Override
    public void runOpMode() throws InterruptedException {
        intake = hardwareMap.get(DcMotor.class, "intake");
        intake.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intake.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.b) {
                intakeOn();
            } else if (gamepad1.a) {
                intakeReverse();
            } else {
                intakeOff();
            }
        }
    }

    private void intakeOn() {
        intake.setPower(0.1);
    }

    private void intakeReverse() {
        intake.setPower(-0.1);
    }

    private void intakeOff() {
        intake.setPower(0);
    }
}
