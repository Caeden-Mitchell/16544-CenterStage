package org.firstinspires.ftc.teamcode.Code16544.DriverControl;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.PoseVelocity2d;
import com.acmerobotics.roadrunner.Vector2d;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests.DCDrop;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

import java.util.Objects;

@TeleOp
public class DC1 extends LinearOpMode {
    MecanumDrive drive;
    RobotSystems robot;
    int isOn = 0;
    int pixelOn = 0;

    private enum Height{
        DEAD_STATE,
        LOW,
        MID,
        HIGH
    }



    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        robot = new RobotSystems(hardwareMap);
        Height height = Height.DEAD_STATE;
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        double y = 0.0; //left stick y
        double x = 0.0; //left stick x
        double rx = 0.0; //right stick x
        double denominator = 0.0;

        telemetry.addData("Position", robot.pixelLift.getCurrentPosition());
        telemetry.update();

        robot.deadState();

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (gamepad1.left_bumper) {
                //slow mode
                y = gamepad1.left_stick_y / 7; // Y Stick is reversed
                x = gamepad1.left_stick_x * 1.1 / 7; //counters imperfect strafing
                rx = gamepad1.right_stick_x / 7;
            } else {
                //fast mode
                y = gamepad1.left_stick_y; // Y Stick is reversed
                x = gamepad1.left_stick_x * 1.1; //counters imperfect strafing
                rx = gamepad1.right_stick_x;
            }

           //if(!robot.pixelLift.isBusy()) {
                //set height for pixel lift
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
            }



            placePixel();

            if(gamepad1.b){
                robot.intakeMotor.setPower(0.08);
            } else if(gamepad1.a){
                robot.intakeMotor.setPower(0);
            } else if (gamepad1.x){
                robot.intakeMotor.setPower(-0.08);
            }

            //robot.intakeMotor.setPower(gamepad1.right_trigger/6);
            //robot.intakeMotor.setPower(-gamepad1.left_trigger/6);




            // Denominator is the largest motor power (absolute value) or 1
            // This ensures all the powers maintain the same ratio, but only when
            // at least one is out of the range [-1, 1]
            denominator = Math.max(Math.abs(y) + Math.abs(x) + Math.abs(rx), 1);
            //denominator = 1;

            drive.setDrivePowers(new PoseVelocity2d(new Vector2d(y, x), rx));

           // setDrivePower((y - x + rx) / denominator, (y + x + rx) / denominator, (y + x - rx) / denominator, (y - x - rx) / denominator);


            if (gamepad1.right_trigger > 0.5) {
                robot.liftRobot(500);
            } else {
                robot.liftRobot(0);
            }

            telemetry.addData("Position", robot.pixelLift.getCurrentPosition());
            telemetry.update();

        }
    }

    private void setDrivePower(double lf, double lb, double rf, double rb) {
        drive.leftFront.setPower(lf);
        drive.leftBack.setPower(lb);
        drive.rightFront.setPower(rf);
        drive.rightBack.setPower(rb);
    }

    private void placePixel(){
        if(gamepad2.a){
            robot.DCDrop();
        }
        if(gamepad2.x)
            robot.servoToZero();
        if(gamepad2.b)
            robot.deadState();
    }
}
