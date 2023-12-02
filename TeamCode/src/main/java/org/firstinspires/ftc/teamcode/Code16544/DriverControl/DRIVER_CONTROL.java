package org.firstinspires.ftc.teamcode.Code16544.DriverControl;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@TeleOp
public class DRIVER_CONTROL extends LinearOpMode {
    MecanumDrive drive;
    RobotSystems robot;
    public static double intakePower = 1;

    ElapsedTime elapsedTime = new ElapsedTime();

    private int initialPixelPos = 0;
    boolean isUp = false;

    private enum Height {
        DEAD_STATE,
        LOW,
        MID,
        HIGH
    }

    public Height height;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        robot = new RobotSystems(hardwareMap);
        height = Height.DEAD_STATE;

        initialPixelPos = robot.pixelLift.getCurrentPosition();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Position", robot.pixelLift.getCurrentPosition());
        telemetry.update();

        robot.deadState();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            runGamepad1();
            runGamepad2();
        }

    }

    private void runGamepad1() {
        setDrivePower();
        setIntakePower();
    }

    private void runGamepad2() {
        setLift();
        launchPlane();
        dropPixel();
    }

    private void dropPixel() {
        if (gamepad2.right_bumper) {
            if (approachBoard()) {
                robot.DCDrop();
            }
        }
    }

    private boolean approachBoard() {
        double speed = 0.15;
        switch (height) {
            case MID:
                while (robot.distance.getDistance(DistanceUnit.CM) > 4.5) {
                    //move forwards
                    drive.leftFront.setPower(-speed);
                    drive.leftBack.setPower(-speed);
                    drive.rightFront.setPower(-speed);
                    drive.rightBack.setPower(-speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                while (robot.distance.getDistance(DistanceUnit.CM) < 4.5) {
                    //move backwards
                    drive.leftFront.setPower(speed);
                    drive.leftBack.setPower(speed);
                    drive.rightFront.setPower(speed);
                    drive.rightBack.setPower(speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                break;
            case HIGH:
                while (robot.distance.getDistance(DistanceUnit.CM) > 14) {
                    //move forwards
                    drive.leftFront.setPower(-speed);
                    drive.leftBack.setPower(-speed);
                    drive.rightFront.setPower(-speed);
                    drive.rightBack.setPower(-speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                while (robot.distance.getDistance(DistanceUnit.CM) < 14) {
                    //move backwards
                    drive.leftFront.setPower(speed);
                    drive.leftBack.setPower(speed);
                    drive.rightFront.setPower(speed);
                    drive.rightBack.setPower(speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                break;
            default:
                while (robot.distance.getDistance(DistanceUnit.CM) > 2) {
                    //move forwards
                    drive.leftFront.setPower(-speed);
                    drive.leftBack.setPower(-speed);
                    drive.rightFront.setPower(-speed);
                    drive.rightBack.setPower(-speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                while (robot.distance.getDistance(DistanceUnit.CM) < 2) {
                    //move backwards
                    drive.leftFront.setPower(speed);
                    drive.leftBack.setPower(speed);
                    drive.rightFront.setPower(speed);
                    drive.rightBack.setPower(speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                break;

        }

        drive.leftFront.setPower(0);
        drive.leftBack.setPower(0);
        drive.rightFront.setPower(0);
        drive.rightBack.setPower(0);

        return true;
    }

    //uses controller sticks to affect robot direction and speed
    private void setDrivePower(){
        double y = 0.0; //left stick y
        double x = 0.0; //left stick x
        double rx = 0.0; //right stick x
        //drive train
        if (gamepad1.left_bumper) {
            //slow mode
            y = gamepad1.left_stick_y / 4; // Y Stick is reversed
            x = gamepad1.left_stick_x * 1.1 / 4; //counters imperfect strafing
            rx = -gamepad1.right_stick_x / 4;
        } else {
            //fast mode
            y = gamepad1.left_stick_y; // Y Stick is reversed
            x = gamepad1.left_stick_x * 1.1; //counters imperfect strafing
            rx = -gamepad1.right_stick_x;
        }
        drive.setDrivePowers(new PoseVelocity2d(new Vector2d(y, x), rx));
    }

    //uses left and right triggers to control intake direction
    private void setIntakePower(){
        //spit out
        if(gamepad1.left_trigger > 0.1){
            robot.intakeMotor.setPower(intakePower);
            // pull in
        } else if (gamepad1.right_trigger > 0.1){
            robot.intakeMotor.setPower(-intakePower);
        } else {
            robot.intakeMotor.setPower(0);
        }
    }

    //uses dpad to control lift height
    private void setLift(){

        //linear slide
        if (gamepad2.dpad_down) {
            height = Height.DEAD_STATE;
            robot.DCLowerHopper();
        }
        if (gamepad2.dpad_left) {
            height = Height.LOW;
            robot.DCLiftHopper();
        }
        if (gamepad2.dpad_right) {
            height = Height.MID;
            robot.DCLiftHopper();
        }
        if (gamepad2.dpad_up) {
            height = Height.HIGH;
            robot.DCLiftHopper();
        }

        switch(height){
            case DEAD_STATE:
                robot.setPixelLiftHeight(initialPixelPos);
                break;
            case LOW:
                robot.setPixelLiftHeight(750 + initialPixelPos);
                break;
            case MID:
                robot.setPixelLiftHeight(1500 + initialPixelPos);
                break;
            case HIGH:
                robot.setPixelLiftHeight(3000 + initialPixelPos);
                break;
        }
    }

    //uses left bumper to launch airplane
    private void launchPlane(){
        if(gamepad2.left_bumper){
            robot.droneLauncher.setPosition(0.1);
            robot.droneLauncher.setPosition(0);
        }
    }
    public void setPowerZero() {
        drive.leftFront.setPower(0);
        drive.leftBack.setPower(0);
        drive.rightFront.setPower(0);
        drive.rightBack.setPower(0);
    }

}
