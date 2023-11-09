package org.firstinspires.ftc.teamcode.Code16544.MotorSystems;

import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.*;

public class MotorSystems {
    DcMotorEx pixelLift; // lifts the pixels using linear slide
    DcMotorEx robotLift; // lifts the robot in endgame
    DcMotorEx intakeMotor; // intake motor

    PIDController pid;

    double kP = 0.0;
    double kI = 0.0;
    double kD = 0.0;
    double kF = 0.0;

    int target = 0;


    public MotorSystems(HardwareMap hardwareMap) {
        pixelLift = hardwareMap.get(DcMotorEx.class, "pixelLift");
        robotLift = hardwareMap.get(DcMotorEx.class, "robotLift");
        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");

        pixelLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        robotLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        intakeMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        pixelLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        robotLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        pixelLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        robotLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        intakeMotor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);

        pixelLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        robotLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        intakeMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        pid = new PIDController(0,0,0);

    }

    public void setPixelLiftHeight(int height, int tolerance) {
        pixelLift.setTargetPositionTolerance(tolerance);
        pixelLift.setTargetPosition(height);
        pixelLift.setPower(0.6);
    }

    public void pixelLiftPID() {

    }

    public void liftRobot(int height) {
        robotLift.setTargetPosition(height);
        robotLift.setPower(0.4);
    }
}
