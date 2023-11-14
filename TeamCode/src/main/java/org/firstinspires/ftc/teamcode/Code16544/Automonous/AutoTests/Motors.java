package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Motors extends LinearOpMode {

    private DcMotorEx leftWheel = null;
    private DcMotorEx rightWheel = null;
    @Override
    public void runOpMode() throws InterruptedException {
        leftWheel = hardwareMap.get(DcMotorEx.class, "launchMotorRight");
        rightWheel = hardwareMap.get(DcMotorEx.class, "launchMotorLeft");

        waitForStart();

        leftWheel.setPower(-1);
        rightWheel.setPower(1);
        sleep(15000);
        leftWheel.setPower(0);
        rightWheel.setPower(0);
        sleep(500);
    }
}
