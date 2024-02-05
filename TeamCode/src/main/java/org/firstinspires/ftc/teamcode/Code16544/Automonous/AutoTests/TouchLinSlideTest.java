package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.TouchSensor;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
@Config
@Autonomous
public class TouchLinSlideTest extends LinearOpMode {

    RobotSystems robot;

    public static int target = 3000;

    int intialTarget = 3000;

    TouchSensor touch;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new RobotSystems(hardwareMap);

        touch = hardwareMap.get(TouchSensor.class, "sensor_touch");


        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {
            if (touch.isPressed()) {
                target = robot.linearSlideLeft.getCurrentPosition();

                telemetry.addData("Touch Sensor", "Is Pressed");
            } else {
                target = intialTarget;
            }

            robot.setPixelLiftHeight(target);
            telemetry.addData("pos", robot.linearSlideLeft.getCurrentPosition());
            telemetry.update();

        }
    }
}
