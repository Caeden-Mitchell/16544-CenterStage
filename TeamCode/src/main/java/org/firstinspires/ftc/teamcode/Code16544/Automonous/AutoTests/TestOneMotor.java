package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
@Autonomous
public class TestOneMotor extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        DcMotorEx test = hardwareMap.get(DcMotorEx.class, "pixelLift");
        waitForStart();
        test.setPower(0.05);
        sleep(1000);
        test.setPower(0);
    }
}
