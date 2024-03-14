package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoPaths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.robot.Robot;

import org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoActions;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotActions;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.LocationFinder;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.ColorDetector;

@Config
@Autonomous
public class LeftRedAuto extends LinearOpMode {
    public static double startingY = -56;
    public static double startingX = -38.4;

    public static int target = 2;

    LocationFinder locationFinder;
    RobotSystems robot;
    AutoActions autoActions;

    @Override
    public void runOpMode() throws InterruptedException {
        locationFinder = new LocationFinder(hardwareMap, telemetry, ColorDetector.Color.RED, false);

        Pose2d startPose = new Pose2d(startingX, startingY, Math.toRadians(270));

        robot = new RobotSystems(hardwareMap);

        autoActions = new AutoActions(hardwareMap, startPose,new RobotActions(hardwareMap, RobotActions.System.SERVO));

        while (!opModeIsActive() && !isStopRequested()) {
            robot.underBarState();
            locationFinder.getTrajectory(telemetry);
        }

        robot.underBarState();

        if(isStopRequested()) return;

        switch (target) {
            case 1://right
                Actions.runBlocking(new SequentialAction(
                        autoActions.leftRedRightSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.leftRedRightDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                ));
                break;
            case 3://left
                Actions.runBlocking(new SequentialAction(
                        autoActions.leftRedLeftSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.leftRedLeftDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                ));
                break;
            case 2://middle
                Actions.runBlocking(new SequentialAction(
                        autoActions.leftRedMidSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.leftRedMidDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                ));
                break;
            default:
                Actions.runBlocking(new SequentialAction(
                        autoActions.leftRedLeftSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.leftRedLeftDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                ));
                telemetry.addData("ELEMENT", "NOT FOUND. RUNNING LEFT TRAJ");
                telemetry.update();
                break;
        }

    }
}
