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
public class LeftBlueAuto extends LinearOpMode {
    public static double startingY = 63.5;
    public static double startingX = 14.5;

    public static int target = 0;

    LocationFinder locationFinder;
    RobotSystems robot;
    AutoActions autoActions;

    @Override
    public void runOpMode() throws InterruptedException {
        locationFinder = new LocationFinder(hardwareMap, telemetry, ColorDetector.Color.BLUE);

        Pose2d startPose = new Pose2d(startingX, startingY, Math.toRadians(180));

        robot = new RobotSystems(hardwareMap);

        autoActions = new AutoActions(hardwareMap, startPose);

        while (!opModeIsActive() && !isStopRequested()) {
            robot.holdIntake();
            locationFinder.getTrajectory(telemetry);
        }

        robot.rotateArm.setPosition(0.2);
        robot.deadState();

        if(isStopRequested()) return;

        switch (locationFinder.trajType) {
            case 1://right
                Actions.runBlocking(new SequentialAction(
                        autoActions.leftBlueRightSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                        ,autoActions.leftBlueRightDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.PIXEL_LIFT, target)
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new RobotActions(hardwareMap, RobotActions.System.PIXEL_LIFT, 0)
                        ,autoActions.parkLeft
                ));
                break;
            case 3://left
                Actions.runBlocking(new SequentialAction(
                        autoActions.leftBlueLeftSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                        ,autoActions.leftBlueLeftDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.PIXEL_LIFT, target)
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new RobotActions(hardwareMap, RobotActions.System.PIXEL_LIFT, 0)
                        ,autoActions.parkLeft
                ));
                break;
            case 2://middle
                Actions.runBlocking(new SequentialAction(
                        autoActions.leftBlueMidSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                        ,autoActions.leftBlueMidDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.PIXEL_LIFT, target)
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new RobotActions(hardwareMap, RobotActions.System.PIXEL_LIFT, 0)
                        ,autoActions.parkLeft
                ));
                break;
            default:
                Actions.runBlocking(new SequentialAction(
                        autoActions.leftBlueRightSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                        ,autoActions.leftBlueRightDrop
                        ,new RobotActions(hardwareMap, RobotActions.System.PIXEL_LIFT, 2500)
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new RobotActions(hardwareMap, RobotActions.System.PIXEL_LIFT, 0)
                        ,autoActions.parkLeft
                ));
                telemetry.addData("ELEMENT", "NOT FOUND. RUNNING RIGHT TRAJ");
                telemetry.update();
                break;
        }
    }
}
