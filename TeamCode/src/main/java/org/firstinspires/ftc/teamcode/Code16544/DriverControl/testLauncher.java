package org.firstinspires.ftc.teamcode.Code16544.DriverControl;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp
@Config
public class testLauncher extends LinearOpMode {

    private Servo launcher = null;

    public static double position = 0;
    @Override
    public void runOpMode() throws InterruptedException {
        launcher = hardwareMap.get(Servo.class, "droneLauncher");

        waitForStart();

        while(opModeIsActive()) {
            launcher.setPosition(position);
        }

    }
}
