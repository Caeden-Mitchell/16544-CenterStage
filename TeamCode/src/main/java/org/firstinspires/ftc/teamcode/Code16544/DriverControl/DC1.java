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
public class DC1 extends LinearOpMode {
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

        while (opModeIsActive()) {

            runGamepad1();
            runGamepad2();

            telemetry.addData("intake power", robot.intakeMotor.getPower());
            telemetry.addData("Position", robot.linearSlideLeft.getCurrentPosition());
            telemetry.update();

            /*if (gamepad1.left_bumper) {
                //slow mode
                y = gamepad1.left_stick_y / 7; // Y Stick is reversed
                x = gamepad1.left_stick_x * 1.1 / 7; //counters imperfect strafing
                rx = -gamepad1.right_stick_x / 7;
            } else {
                //fast mode
                y = gamepad1.left_stick_y; // Y Stick is reversed
                x = gamepad1.left_stick_x * 1.1; //counters imperfect strafing
                rx = -gamepad1.right_stick_x;
            }*/

           //if(!robot.pixelLift.isBusy()) {
                //set height for pixel lift
             /*   if (gamepad2.dpad_down) {
                    height = Height.DEAD_STATE;

                }
                if (gamepad2.dpad_left) {
                    height = Height.LOW;
                }
                if (gamepad2.dpad_right) {
                    height = Height.MID;
                }
                if (gamepad2.dpad_up) {
                    height = Height.HIGH;
                }

                switch(height){
                    case DEAD_STATE:
                        robot.setPixelLiftHeight(0);
                        break;
                    case LOW:
                        robot.setPixelLiftHeight(750);
                        break;
                    case MID:
                        robot.setPixelLiftHeight(1500);
                        break;
                    case HIGH:
                        robot.setPixelLiftHeight(3000);
                        break;
                }

            if(gamepad2.left_bumper)
                robot.DCLowerHopper();
            if (gamepad2.right_bumper){
                robot.DCLiftHopper();
            }*/



            /*if(gamepad1.b){
                robot.intakeMotor.setPower(intakePower);
            } else if(gamepad1.a){
                robot.intakeMotor.setPower(0);
            } else if (gamepad1.x){
                robot.intakeMotor.setPower(-intakePower);
            }*/

            //robot.intakeMotor.setPower(gamepad1.right_trigger/6);
            //robot.intakeMotor.setPower(-gamepad1.left_trigger/6);




            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            //denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            //denominator = 1;

            //drive.setDrivePowers(new PoseVelocity2d(new Vector2d(y, x), rx));

           // setDrivePower((y - x + rx) / denominator, (y + x + rx) / denominator, (y + x - rx) / denominator, (y - x - rx) / denominator);



        }
    }


    /*private void placePixel(){
        if(gamepad2.y){
            robot.DCDrop();
        }
        if(gamepad2.b)
            robot.servoToZero();
        if(gamepad2.a)
            robot.deadState();
    }*/

    private void runGamepad1(){
        double y = 0.0; //left stick y
        double x = 0.0; //left stick x
        double rx = 0.0; //right stick x
        double denominator = 0.0;
        //drive train
        if (gamepad1.left_bumper) {
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

        //intake
        if(gamepad1.b){
            robot.intakeMotor.setPower(intakePower);
        } else if (gamepad1.x){
            robot.intakeMotor.setPower(-intakePower);
        } else {
            robot.intakeMotor.setPower(0);
        }

        if (gamepad1.left_trigger > 0.1) {
            robot.intakeMotor.setPower(gamepad1.left_trigger/1.25);
        }


        //robot lift
        /*
         * WRITE CODE HERE ONCE WE GET LIFT MECHANISM
         */
    }

    private void runGamepad2(){
        //linear slide
        if (gamepad2.dpad_down) {
            height = Height.DEAD_STATE;
        }
        if (gamepad2.dpad_left) {
            height = Height.LOW;
        }
        if (gamepad2.dpad_right) {
            height = Height.MID;
        }
        if (gamepad2.dpad_up) {
            height = Height.HIGH;
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

        //hopper
        if(gamepad2.a) {
            robot.DCLowerHopper();
        }
        if (gamepad2.y){
            //robot.DCLiftHopper();
        }
        if (gamepad2.right_bumper){
            robot. DCDrop();
        }

        //airplane launcher
        if(gamepad2.left_bumper){
            robot.droneLauncher.setPosition(0);
        }
    }
}
