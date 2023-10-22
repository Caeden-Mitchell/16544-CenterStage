package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

import org.firstinspires.ftc.robotcore.external.Telemetry;

/**
 * This opMode will turn each motor in the launcher prototype
 * clockwise and then counter-clockwise.
 */
@Autonomous
public class launcherMotorTest extends LinearOpMode {
    public DcMotorEx leftMotor, rightMotor;

    @Override
    public void runOpMode() {
       Telemetry telemetry = new MultipleTelemetry(this.telemetry, FtcDashboard.getInstance().getTelemetry());

        leftMotor = hardwareMap.get(DcMotorEx.class, "launchMotorLeft");
        rightMotor = hardwareMap.get(DcMotorEx.class, "launchMotorRight");

        int leftMotorTicks = 0;
        int rightMotorTicks = 0;

        waitForStart();

        runMotorsClockwise(leftMotor, leftMotorTicks);
        runMotorsClockwise(rightMotor, rightMotorTicks);

        //runMotorsCounterClockwise(leftMotor, leftMotorTicks);
       // runMotorsCounterClockwise(rightMotor, rightMotorTicks);

        telemetry.addData("left motor ticks:", leftMotorTicks);
        telemetry.addData("right motor ticks:", rightMotorTicks);
        telemetry.update();
    }

    public void runMotorsClockwise(DcMotorEx motor, int ticks) {
        motor.setPower(1);

        sleep(3000);
        while(motor.isBusy()){
            ticks += motor.getCurrentPosition();
        }
        motor.setPower(0);
        sleep(500);
    }

    public void runMotorsCounterClockwise(DcMotorEx motor, int ticks) {
        motor.setPower(-1);
        sleep(3000);
        while(motor.isBusy()){
            ticks += motor.getCurrentPosition();
        }
        motor.setPower(0);
        sleep(500);
    }
}
