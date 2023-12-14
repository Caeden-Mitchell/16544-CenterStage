package org.firstinspires.ftc.teamcode.Code16544.RobotSystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

public class RobotActions implements Action {
    RobotSystems robot;
    private int pixelHeight = 0;

    public enum SystemType {
        PIXEL_LIFT,
        INTAKE_MOTOR,
        SERVO,
    }

    public RobotActions(HardwareMap hardwareMap, SystemType systemType)  {
        robot = new RobotSystems(hardwareMap);
        this.systemType = systemType;
    }

    public RobotActions(HardwareMap hardwareMap, SystemType systemType, int pixelHeight) {
        robot = new RobotSystems(hardwareMap);
        this.systemType = systemType;
        this.pixelHeight = pixelHeight;
    }

    SystemType systemType;
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        switch (systemType) {
            case PIXEL_LIFT:
                ElapsedTime elapsedTime = new ElapsedTime();
                while (elapsedTime.seconds() < 1.5) {
                    robot.setPixelLiftHeight(pixelHeight);
                }
                return false;
            case INTAKE_MOTOR:
                ElapsedTime elapsedTime1 = new ElapsedTime();
                while (elapsedTime1.seconds() < .75) {
                    robot.ejectPixelFromIntake();
                }
                return false;
            case SERVO:
                robot.deadState();
                robot.servoToZero();
                robot.preDrop();
                robot.autoDrop();
                robot.servoToZero();
                robot.deadState();
                return false;
        }
        return false;
    }
}
