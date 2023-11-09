package org.firstinspires.ftc.teamcode.Code16544.MotorSystems;

import com.qualcomm.robotcore.hardware.*;

public class MotorSystems {
    DcMotorEx pixelLift; // lifts the pixels using linear slide
    DcMotorEx robotLift; // lifts the robot in endgame
    DcMotorEx intakeMotor; // intake motor

    PIDFCoefficients pixPID = new PIDFCoefficients(0, 0, 0, 0);
    PIDFCoefficients robPID = new PIDFCoefficients(0, 0, 0, 0);
    PIDFCoefficients inPID = new PIDFCoefficients(0, 0, 0, 0);


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

        pixelLift.setPIDFCoefficients(pixelLift.getMode(), pixPID);
        robotLift.setPIDFCoefficients(robotLift.getMode(), robPID);
        intakeMotor.setPIDFCoefficients(intakeMotor.getMode(), inPID);
    }

    public void setPixelLiftHeight(int height, int tolerance) {
        pixelLift.setTargetPositionTolerance(tolerance);
        pixelLift.setTargetPosition(height);
        pixelLift.setPower(0.6);
    }

    public void liftRobot(int height) {
        robotLift.setTargetPosition(height);
        robotLift.setPower(0.4);
    }
}
