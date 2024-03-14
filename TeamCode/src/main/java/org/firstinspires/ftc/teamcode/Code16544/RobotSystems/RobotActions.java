package org.firstinspires.ftc.teamcode.Code16544.RobotSystems;

import androidx.annotation.NonNull;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.TelemetryPacket;
import com.acmerobotics.roadrunner.Action;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.checkerframework.checker.units.qual.C;

@Config
public class RobotActions implements Action {
    RobotSystems robot;
    public static double power = 0.15;
    public static double time = 0.15;
    private int pixelHeight = 500;

    public enum System {
        PIXEL_LIFT,
        REVERSE_INTAKE_MOTOR,
        INTAKE_MOTOR,
        SERVO,
        PARK
    }

    public RobotActions(HardwareMap hardwareMap, System system)  {
        robot = new RobotSystems(hardwareMap);
        this.system = system;
    }

    public RobotActions(HardwareMap hardwareMap, System system, int pixelHeight) {
        robot = new RobotSystems(hardwareMap);
        this.system = system;
        this.pixelHeight = pixelHeight;
    }

    System system;
    @Override
    public boolean run(@NonNull TelemetryPacket telemetryPacket) {
        switch (system) {
            case PIXEL_LIFT:
                ElapsedTime elapsedTime = new ElapsedTime();
                while (elapsedTime.seconds() < 1.5) {
                    robot.setLinearSlideRight(pixelHeight);
                }
                return false;
            case REVERSE_INTAKE_MOTOR:
                ElapsedTime elapsedTime1 = new ElapsedTime();

                while (elapsedTime1.seconds() < 0.5) {
                    robot.ejectPixelFromIntake(0.8);
               }
                return false;
            case INTAKE_MOTOR:
                ElapsedTime elapsedTime2 = new ElapsedTime() ;

                while (elapsedTime2.seconds() < .25) {
                    robot.runIntake();
                }
                return false;
            case SERVO:
                robot.autoPreDrop();
                robot.autoDrop();
                robot.deadState();
                return false;
        }
        return false;
    }
}
