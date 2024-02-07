package org.firstinspires.ftc.teamcode.Code16544.DriverControl;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@TeleOp
public class SingleController extends LinearOpMode {

    MecanumDrive drive;
    RobotSystems robot;
    int isOn = 0;
    int pixelOn = 0;
    public static double intakePower = 1;

    private int initialPixelPos = 0;

    private enum Height{
        DEAD_STATE,
        LOW,
        MID,
        HIGH
    }

    public Height height;
    @Override
    public void runOpMode() throws InterruptedException {

        telemetry.addData("Got ","Here");
        telemetry.update();
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        robot = new RobotSystems(hardwareMap);
        height = Height.DEAD_STATE;

        initialPixelPos = robot.linearSlideLeft.getCurrentPosition();

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        double y = 0.0; //left stick y
        double x = 0.0; //left stick x
        double rx = 0.0; //right stick x
        double denominator = 0.0;

        telemetry.addData("Position", robot.linearSlideLeft.getCurrentPosition());
        telemetry.update();

        robot.deadState();

        waitForStart();

        if (isStopRequested()) return;



        while(opModeIsActive()){
            runController();
        }
    }

    /**
     * TESTING FOR SINGLE CONTROLLER
     */
    private void runController(){
        setDrivePower();
        setIntakePower();
        setLift();
        dropPixel();
        launchPlane();
    }

    //uses right bumper to drop pixel
    private void dropPixel(){
        if(gamepad1.right_bumper) {
            //approachBoard();
            robot.DCDrop();
        }
    }

    //uses dpad to control lift height
    private void setLift(){
        //linear slide
        if (gamepad1.dpad_down) {
            height = Height.DEAD_STATE;
            robot.DCLowerHopper();
        }
        if (gamepad1.dpad_left) {
            height = Height.LOW;
           // robot.DCLiftHopper();
        }
        if (gamepad1.dpad_right) {
            height = Height.MID;
           // robot.DCLiftHopper();
        }
        if (gamepad1.dpad_up) {
            height = Height.HIGH;
           // robot.DCLiftHopper();
        }

        switch(height){
            case DEAD_STATE:
                robot.setLinearSlideRight(initialPixelPos);
                break;
            case LOW:
                robot.setLinearSlideRight(750 + initialPixelPos);
                break;
            case MID:
                robot.setLinearSlideRight(1500 + initialPixelPos);
                break;
            case HIGH:
                robot.setLinearSlideRight(3000 + initialPixelPos);
                break;
        }
    }

    //uses left and right triggers to control intake direction
    private void setIntakePower(){
        if(gamepad1.right_trigger > 0.1){
            robot.intakeMotor.setPower(intakePower);
        } else if (gamepad1.left_trigger > 0.1){
            robot.intakeMotor.setPower(-intakePower);
        } else {
            robot.intakeMotor.setPower(0);
        }
    }

    //uses controller sticks to affect robot direction and speed
    private void setDrivePower(){
        double y = 0.0; //left stick y
        double x = 0.0; //left stick x
        double rx = 0.0; //right stick x
        //drive train
        if (gamepad1.a ) {
            //slow mode
            y = gamepad1.left_stick_y / 7; // Y Stick is reversed
            x = gamepad1.left_stick_x * 1.1 / 7; //counters imperfect strafing
            rx = -gamepad1.right_stick_x / 7;
        } else {
            //fast mode
            y = gamepad1.left_stick_y; // Y Stick is reversed
            x = gamepad1.left_stick_x * 1.1; //counters imperfect strafing
            rx = -gamepad1.right_stick_x;
        }
        drive.setDrivePowers(new PoseVelocity2d(new Vector2d(y, x), rx));
    }

    //uses left bumper to launch airplane
    private void launchPlane(){
        if(gamepad1.left_bumper){
            robot.droneLauncher.setPosition(0);
        }
    }

    /*public void approachBoard(){
        while(robot.distance.getDistance(DistanceUnit.CM) > 5){
            //move forwards
            drive.leftFront.setPower(0.1);
            drive.leftBack.setPower(0.1);
            drive.rightFront.setPower(0.1);
            drive.rightBack.setPower(0.1);
        }
        while(robot.distance.getDistance(DistanceUnit.CM) < 5){
            //move backwards
            drive.leftFront.setPower(-0.1);
            drive.leftBack.setPower(-0.1);
            drive.rightFront.setPower(-0.1);
            drive.rightBack.setPower(-0.1);
        }
        drive.leftFront.setPower(0);
        drive.leftBack.setPower(0);
        drive.rightFront.setPower(0);
        drive.rightBack.setPower(0);
    }*/
}
