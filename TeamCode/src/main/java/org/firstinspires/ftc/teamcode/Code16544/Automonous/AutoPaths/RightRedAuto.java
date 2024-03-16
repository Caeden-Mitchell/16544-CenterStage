package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoPaths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoActions;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotActions;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.LocationFinder;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.ColorDetector;

@Config
@Autonomous
public class RightRedAuto extends LinearOpMode {
    public static double startingY = -56;
    public static double startingX = 14.5;

    public static int target = 2;

    LocationFinder locationFinder;
    RobotSystems robot;
    AutoActions autoActions;

    @Override
    public void runOpMode() throws InterruptedException {
        locationFinder = new LocationFinder(hardwareMap, telemetry, ColorDetector.Color.RED, true);

        Pose2d startPose = new Pose2d(startingX, startingY, Math.toRadians(270));

        robot = new RobotSystems(hardwareMap);

        autoActions = new AutoActions(hardwareMap, startPose,new RobotActions(hardwareMap, RobotActions.System.SERVO));

        while (!opModeIsActive() && !isStopRequested()) {
            robot.deadState();
            locationFinder.getTrajectory(telemetry);
        }

        if(isStopRequested()) return;

        switch (locationFinder.trajType) {
            case 1://right
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightRedRightSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.rightRedRightDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)

                ));
                break;
            case 3://left
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightRedLeftSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.rightRedLeftDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                ));
                break;
            case 2://middle
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightRedMidSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.rightRedMidDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                ));
                break;
            default:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightRedLeftSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.rightRedLeftDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                ));
                telemetry.addData("ELEMENT", "NOT FOUND. RUNNING LEFT TRAJ");
                telemetry.update();
                break;
        }

    }
}
