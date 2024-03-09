package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;

@Autonomous
public class DS_to_Pre extends LinearOpMode {

    @Override
    public void runOpMode() throws InterruptedException {
        RobotSystems robot = new RobotSystems(hardwareMap);
        robot.deadState();

        waitForStart();

        robot.DCPreDrop();
        sleep(2000);
        robot.DCDrop();
        sleep(2000);
    }
}
