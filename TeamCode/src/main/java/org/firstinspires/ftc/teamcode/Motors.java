package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

@Autonomous
public class Motors extends LinearOpMode {

    private DcMotorEx leftWheel = null;
    @Override
    public void runOpMode() throws InterruptedException {
        leftWheel = hardwareMap.get(DcMotorEx.class, "launchMotorRight");

        waitForStart();

        leftWheel.setPower(1);
        sleep(10000);
        leftWheel.setPower(0);
        sleep(500);
    }
}
