package org.firstinspires.ftc.teamcode.Code16544.DriverControl.DriveTests;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.robotcore.internal.camera.delegating.DelegatingCaptureSequence;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@TeleOp
@Config
public class TestDistance extends LinearOpMode {

    public static double speed = 0.15;
    public static double distance = 0;
    public static int liftHeight = 0;

    MecanumDrive drive;
    RobotSystems robot;
    @Override
    public void runOpMode() throws InterruptedException {
        robot = new RobotSystems(hardwareMap);
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));

        waitForStart();

        robot.setLinearSlideRight(liftHeight);
        robot.setLinearSlideLeft(liftHeight);
        robot.DCPreDrop();
        sleep(2000);


        moveBack();
        turn();
        moveBack();
        robot.DCDrop();
        sleep(2000);
    }

    void stopRobot(){
        drive.leftFront.setPower(0);
        drive.leftBack.setPower(0);
        drive.rightFront.setPower(0);
        drive.rightBack.setPower(0);
    }

    //turn while the robot is not straight
    void turn(){
        while(getAverage()>distance+1 || getAverage()<distance-1){
            if(rightOrLeft()){
                drive.leftFront.setPower(-speed);
                drive.leftBack.setPower(-speed);
                drive.rightFront.setPower(speed);
                drive.rightBack.setPower(speed);
            } else {
                drive.leftFront.setPower(speed);
                drive.leftBack.setPower(speed);
                drive.rightFront.setPower(-speed);
                drive.rightBack.setPower(-speed);
            }
        }
    }

    //return true if right side is further away
    //return false if left side is further away
    boolean rightOrLeft(){
        if(robot.distanceLeft.getDistance(DistanceUnit.CM) < robot.distanceRight.getDistance(DistanceUnit.CM)) {
            return true;
        } else {
            return false;
        }
    }

    void moveBack(){
        while(robot.distanceLeft.getDistance(DistanceUnit.CM) > distance && robot.distanceRight.getDistance(DistanceUnit.CM) > distance){
            telemetry.addData("Right", robot.distanceRight.getDistance(DistanceUnit.CM));
            telemetry.addData("Left", robot.distanceLeft.getDistance(DistanceUnit.CM));
            telemetry.update();
            //move backwards
            drive.leftFront.setPower(-speed);
            drive.leftBack.setPower(-speed);
            drive.rightFront.setPower(-speed);
            drive.rightBack.setPower(-speed);

        }
        stopRobot();
    }
    void moveForward(){

    }

    double getAverage(){
        return (robot.distanceLeft.getDistance(DistanceUnit.CM)+robot.distanceRight.getDistance(DistanceUnit.CM))/2;
    }
}
