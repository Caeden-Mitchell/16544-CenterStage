package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoPaths;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.SleepAction;
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
public class RightBlueAuto extends LinearOpMode {
    public static double startingY = 66;
    public static double startingX = -38.4;

    public static int target = 200;
    public static int numer = 2;

    LocationFinder locationFinder;
    RobotSystems robot;
    AutoActions autoActions;

    @Override
    public void runOpMode() throws InterruptedException {
        locationFinder = new LocationFinder(hardwareMap, telemetry, ColorDetector.Color.BLUE,true);

        Pose2d startPose = new Pose2d(startingX, startingY, Math.toRadians(270));

        robot = new RobotSystems(hardwareMap);

        autoActions = new AutoActions(hardwareMap, startPose, new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR));

        while (!opModeIsActive() && !isStopRequested()) {
            robot.underBarState();
            locationFinder.getTrajectory(telemetry);
        }

        robot.underBarState();

        if(isStopRequested()) return;

        switch (locationFinder.trajType) {
            case 1:
        Actions.runBlocking(new SequentialAction(
                autoActions.rightBlueRightSpike
                ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                ,autoActions.rightBlueRightDrop
                //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 250)
                ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                ,new SleepAction(.5)
                ,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 0)
                ,autoActions.parkLeft
        ));
                break;
            case 2:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightBlueMidSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.rightBlueMidDrop
                        //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 250)
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new SleepAction(.5)
                        ,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 0)
                        ,autoActions.parkLeft
                ));
                break;
            case 3:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightBlueLeftSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.rightBlueLeftDrop
                        //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 250)
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new SleepAction(.5)
                        ,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 0)
                        ,autoActions.parkLeft
                ));
                break;
            default:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightBlueRightSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.REVERSE_INTAKE_MOTOR)
                        ,autoActions.rightBlueRightDrop
                        //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 250)
                        ,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                        ,new SleepAction(.5)
                        ,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 0)
                        ,autoActions.parkLeft

                ));
                telemetry.addData("ELEMENT", "NOT FOUND. RUNNING RIGHT TRAJ");
                telemetry.update();
                break;
        }

    }
}
