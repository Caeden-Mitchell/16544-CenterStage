package org.firstinspires.ftc.teamcode.Code16544.DriverControl;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
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

    boolean isUp = false;

    private enum Height {
        DEAD_STATE,
        LOW,
        MID,
        HIGH
    }


    private enum TwoLift {
        ON,
        OFF
    }

    Gamepad.RumbleEffect customRumbleEffect;

    public Height height;
    public TwoLift twoLift;

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        robot = new RobotSystems(hardwareMap);
        height = Height.DEAD_STATE;
        twoLift = TwoLift.OFF;

        customRumbleEffect = new Gamepad.RumbleEffect.Builder()
                .addStep(0.0,1.0,500) // rumble both for 10ms
                //.addStep(0.0, 1.0, 500)  //  Rumble right motor 100% for 500 mSec
                //.addStep(0.0, 0.0, 300)  //  Pause for 300 mSec
                //.addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                //.addStep(0.0, 0.0, 250)  //  Pause for 250 mSec
                //.addStep(1.0, 0.0, 250)  //  Rumble left motor 100% for 250 mSec
                .build();


        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        telemetry.addData("Position lrft", robot.linearSlideLeft.getCurrentPosition());
        telemetry.addData("Position right", robot.linearSlideRight.getCurrentPosition());
        telemetry.update();

        //robot.deadState();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            runGamepad1();
            runGamepad2();
            telemetry.addData("Position lrft", robot.linearSlideLeft.getCurrentPosition());
            telemetry.addData("Position right", robot.linearSlideRight.getCurrentPosition());
            telemetry.addData("Lift Function", twoLift);
            telemetry.addData("Distance", robot.distanceLeft.getDistance(DistanceUnit.CM));
            telemetry.update();
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
        liftBot();
    }

    private void dropPixel() {
        if (gamepad2.right_bumper) {
            //if (approachBoard()) {
                robot.DCDrop();
            //}
        }
    }

    private boolean approachBoard() {
        double speed = 0.15;
        switch (height) {
            case MID:
                while (robot.distanceLeft.getDistance(DistanceUnit.CM) > 4.25) {
                    //move forwards
                    drive.leftFront.setPower(-speed);
                    drive.leftBack.setPower(-speed);
                    drive.rightFront.setPower(-speed);
                    drive.rightBack.setPower(-speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                while (robot.distanceLeft.getDistance(DistanceUnit.CM) < 4.25) {
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
                while (robot.distanceLeft.getDistance(DistanceUnit.CM) > 11.6) {
                    //move forwards
                    drive.leftFront.setPower(-speed);
                    drive.leftBack.setPower(-speed);
                    drive.rightFront.setPower(-speed);
                    drive.rightBack.setPower(-speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                while (robot.distanceLeft.getDistance(DistanceUnit.CM) < 11.6) {
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
                while (robot.distanceLeft.getDistance(DistanceUnit.CM) > 3) {
                    //move forwards
                    drive.leftFront.setPower(-speed);
                    drive.leftBack.setPower(-speed);
                    drive.rightFront.setPower(-speed);
                    drive.rightBack.setPower(-speed);
                    if (gamepad2.a) {
                        return false;
                    }
                }
                while (robot.distanceLeft.getDistance(DistanceUnit.CM) < 3) {
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
        //robot.setMotorPower(y,x,rx,drive);

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
    private void setLift() {

        if (gamepad2.dpad_down) {
            height = Height.DEAD_STATE;
            robot.setLinearSlideRight(0);
            robot.setLinearSlideLeft(0);

            robot.deadState();
        }
        if (gamepad2.dpad_left) {
            height = Height.LOW;
            robot.setLinearSlideRight(750);
            robot.setLinearSlideLeft(750);

            robot.DCPreDrop();
        }
        if (gamepad2.dpad_right) {
            height = Height.MID;
            robot.setLinearSlideRight(1500);
            robot.setLinearSlideLeft(1500);

            robot.DCPreDrop();
        }
        if (gamepad2.dpad_up) {
            height = Height.HIGH;
            robot.setLinearSlideRight(3000);
            robot.setLinearSlideLeft(3000);

            robot.DCPreDrop();
        }
        if(gamepad2.b){
            robot.underBarState();
        }
    }

    //uses left bumper to launch airplane
    private void launchPlane(){
        if(gamepad2.left_bumper){
            robot.droneLauncher.setPosition(0.4);
            robot.droneLauncher.setPosition(0.3);
        }
    }
    public void setPowerZero() {
        drive.leftFront.setPower(0);
        drive.leftBack.setPower(0);
        drive.rightFront.setPower(0);
        drive.rightBack.setPower(0);
    }

    public void liftBot(){
        if(gamepad2.y){
            twoLift = TwoLift.ON;
            robot.liftServoSequence();
            gamepad2.rumble(Gamepad.RUMBLE_DURATION_CONTINUOUS);
        } else if(gamepad2.x){
            twoLift = TwoLift.OFF;
            gamepad2.stopRumble();
        }
        if(twoLift == TwoLift.ON){
            if(gamepad2.right_trigger>0.5){
                robot.setLinearSlideLeft(3000);
                robot.setLinearSlideRight(3000);
            } else if(gamepad2.left_trigger > 0.5){
                robot.setLinearSlideRight(1000);
                robot.setLinearSlideLeft(1000);
            } else {
                robot.setLinearSlideLeft(0);
                robot.setLinearSlideRight(0);
            }
        }
    }
}
