package org.firstinspires.ftc.teamcode.Code16544.DriverControl.DriveTests;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp
public class TestRumble extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();
            gamepad2.rumble(5000);

    }
}
