package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@Autonomous
public class DCDrop extends LinearOpMode {
    @Override
    public void runOpMode() throws InterruptedException {
        RobotSystems robot = new RobotSystems(hardwareMap);

        waitForStart();

        sleep(250);
        robot.deadState();
        robot.liftHopper();
        robot.rotateArm.setPosition(0.13);
        robot.rotateHopper.setPosition(0.07);
        sleep(1500);
        robot.lowerHopper();
    }
}
