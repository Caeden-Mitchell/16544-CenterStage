package org.firstinspires.ftc.teamcode.Code16544.MotorSystems;

import com.arcrobotics.ftclib.controller.PController;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ElevatorFeedforward;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class MotorSystems {
    DcMotorEx pixelLift; // lifts the pixels using linear slide
    DcMotorEx robotLift; // lifts the robot in endgame
    DcMotorEx intakeMotor; // intake motor

    // parameters for pixel lift feedforward
    final double kS = 0.0;
    final double kG = 0.0;
    final double kV = 0.0;
    final double kA = 0.0;


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
    }

    public void pixelFeedforward() {
        ElevatorFeedforward feedforward = new ElevatorFeedforward(
                kS, kG, kV, kA
        );
    }

    public void PIDF(DcMotorEx motor, double kP, double kI, double kD, double kF) {

        PIDFController pidf = new PIDFController(kP, kI, kD, kF);
        // We set the setpoint here.
        pidf.setSetPoint(1200);

        while (!pidf.atSetPoint()) {
            // Calculates the output of the PIDF algorithm based on sensor
            // readings. Requires both the measured value and the desired setpoint
            double output = pidf.calculate(
                    motor.getCurrentPosition()
            );
            motor.setVelocity(output);
        }

        motor.setPower(0); // stop the motor

        // NOTE: motors have internal PID control
    }
}
