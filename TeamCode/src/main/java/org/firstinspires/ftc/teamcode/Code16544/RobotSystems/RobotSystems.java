package org.firstinspires.ftc.teamcode.Code16544.RobotSystems;

import static android.os.SystemClock.sleep;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotSystems {
    public DcMotorEx pixelLift; // lifts the pixels using linear slide
    public DcMotorEx robotLift; // lifts the robot in endgame
    public DcMotorEx intakeMotor; // intake motor

    public Servo rotateArm, rotateHopper;

    private final double pixP = 0.008, pixI = 0.0002, pixD = 0.0001; // pid coefficients for pixel lift

    private final double robP = 0, robI = 0, robD = 0; // pid coefficients for robot lift


    private final PIDController pixPIDController;
    private final PIDController robPIDController;

    public RobotSystems(HardwareMap hardwareMap) {
        pixPIDController = new PIDController(pixP, pixI, pixD);
        robPIDController = new PIDController(robP, robI, robD);

        pixelLift = hardwareMap.get(DcMotorEx.class, "pixelLift");
        robotLift = hardwareMap.get(DcMotorEx.class, "robotLift");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");


        rotateArm = hardwareMap.get(Servo.class, "rotateArm");
        rotateHopper = hardwareMap.get(Servo.class, "rotateHopper");

        pixelLift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        robotLift.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        robotLift.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);
        intakeMotor.setMode(DcMotorEx.RunMode.RUN_USING_ENCODER);

        rotateArm.setDirection(Servo.Direction.REVERSE);
    }

    public void setPixelLiftHeight(int target) {
        pixPIDController.setPID(pixP, pixI, pixD);
        double power = pixPIDController.calculate(pixelLift.getCurrentPosition(), target);
        pixelLift.setPower(power);
    }


    public void liftRobot(int target) {
        robPIDController.setPID(pixP, pixI, pixD);
        double power = robPIDController.calculate(robotLift.getCurrentPosition(), target);
        robotLift.setPower(power);
    }

    public void setServos(double pos1, double pos2, int time, boolean isDelayed){
        rotateArm.setPosition(pos1);
        if(isDelayed){
            sleep(100);
        }
        rotateHopper.setPosition(pos2);
        sleep(time);
    }

    public void deadState(){
        setServos(0.019, 0.03, 1000, false);
    }

    public void servoToZero(){
        setServos(0,0,600, false);
    }

    public void preDrop(){
        setServos(0.13, 0.15, 1750, true);
    }

    public void autoDrop(){
        setServos(0.18, 0.13, 2500, true);
    }

    public void DCDrop(){
        setServos(0.13, 0.07, 0, false);
    }

    public void DCLiftHopper(){
        setServos(0,0,0,false);
        setServos(0.13, 0.15, 0, false);
    }

    public void DCLowerHopper(){
        setServos(0,0,0, false);
        setServos(0.019, 0.03, 0, false);
    }

    public void liftHopper(){
        servoToZero();
        preDrop();
    }

    public void lowerHopper(){
        servoToZero();
        sleep(400);
        deadState();
    }

    public void ejectPixelFromIntake() {
        intakeMotor.setPower(-0.05);
    }

}
