package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@Autonomous
public class AutoDrop extends LinearOpMode {
    private Servo rotateArm, rotateHopper;

    @Override
    public void runOpMode() throws InterruptedException {
        RobotSystems robot = new RobotSystems(hardwareMap);

        waitForStart();

        sleep(250);
        robot.deadState();
        robot.servoToZero();
        robot.preDrop();
        robot.autoDrop();
        robot.servoToZero();
        sleep(1000);
        robot.deadState();

    }
}
