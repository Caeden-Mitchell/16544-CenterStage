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

    @Override
    public void runOpMode() throws InterruptedException {
        drive = new MecanumDrive(hardwareMap, new Pose2d(0, 0, 0));
        robot = new RobotSystems(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        double y = 0.0; //left stick y
        double x = 0.0; //left stick x
        double rx = 0.0; //right stick x
        double denominator = 0.0;



        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (gamepad1.left_bumper) {
                //slow mode
                y = -gamepad1.left_stick_y / 3.5; // Y Stick is reversed
                x = -gamepad1.left_stick_x * 1.1 / 3.5; //counters imperfect strafing
                rx = -gamepad1.right_stick_x / 3.5;
            } else {
                //fast mode
                y = -gamepad1.left_stick_y; // Y Stick is reversed
                x = -gamepad1.left_stick_x * 1.1; //counters imperfect strafing
                rx = -gamepad1.right_stick_x;
            }

            //set height for pixel lift
            if (gamepad2.dpad_down) {
                robot.setPixelLiftHeight(0);
            }
            if (gamepad2.dpad_left){
                robot.setPixelLiftHeight(750);
            }
            if (gamepad2.dpad_right) {
                robot.setPixelLiftHeight(1500);
            }
            if (gamepad2.dpad_up) {
                robot.setPixelLiftHeight(3000);
            }
            if (gamepad2.a) {
                drive.leftFront.setPower(0.7);
                drive.leftBack.setPower(0.7);
                drive.rightFront.setPower(0.7);
                drive.rightBack.setPower(0.7);
            }

            if(gamepad1.b){
                isOn = true;
            } else if(gamepad1.a){
                isOn = false;
            }



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

            telemetry.addData("pixel pos ", robot.pixelLift.getCurrentPosition());
            telemetry.addData("right encoder", drive.leftFront.getCurrentPosition());
            telemetry.addData("left encoder", drive.rightBack.getCurrentPosition());
            telemetry.addData("perp encoder", drive.leftBack.getCurrentPosition());
            telemetry.update();
        }
    }

    private void setDrivePower(double lf, double lb, double rf, double rb) {
        drive.leftFront.setPower(lf);
        drive.leftBack.setPower(lb);
        drive.rightFront.setPower(rf);
        drive.rightBack.setPower(rb);
    }

    private void setIntake(){
        if(gamepad1.b){
            isOn = 1;
        } else if(gamepad1.a){
            isOn = 0;
        } else if(gamepad1.x){
            //reverse
            isOn = 2;
        }
        if(isOn == 1){
            robot.intakeMotor.setPower(0.45);
        } else if(isOn == 0){
            robot.intakeMotor.setPower(0);
        } else {
            robot.intakeMotor.setPower(-0.45);
        }
    }
}
