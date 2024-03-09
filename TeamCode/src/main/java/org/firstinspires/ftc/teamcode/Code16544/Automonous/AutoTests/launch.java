package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous
public class launch extends LinearOpMode {
    private Servo launcher = null;
    @Override
    public void runOpMode() throws InterruptedException {
        launcher = hardwareMap.get(Servo.class, "droneLauncher");

        double position1 = 0.22;
        double position2 = 0.34;

        waitForStart();

        launcher.setPosition(position1);
        sleep(2000);
        launcher.setPosition(position2);
        sleep(1000);
    }
}
