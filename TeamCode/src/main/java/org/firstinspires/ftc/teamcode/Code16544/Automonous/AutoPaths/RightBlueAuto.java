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
public class RightBlueAuto extends LinearOpMode {
    public static double startingY = 64.0;
    public static double startingX = -33;

    public static int target = 200;

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
            case 1:
        Actions.runBlocking(new SequentialAction(
                autoActions.rightBlueRightSpike
                ,new RobotActions(hardwareMap, RobotActions.SystemType.INTAKE_MOTOR)
                //,autoActions.rightBlueRightDrop
                //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, target)
                //,new RobotActions(hardwareMap, RobotActions.System.SERVO)
        ));
                break;
            case 2:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightBlueMidSpike
                        ,new RobotActions(hardwareMap, RobotActions.SystemType.INTAKE_MOTOR)
                        //,autoActions.rightBlueMidDrop
                        //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, target)
                         //,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                ));
                break;
            case 3:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightBlueLeftSpike
                        ,new RobotActions(hardwareMap, RobotActions.SystemType.INTAKE_MOTOR)
                        //,autoActions.rightBlueLeftDrop
                        //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, target)
                        //,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                ));
                break;
            default:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightBlueRightSpike
                        ,new RobotActions(hardwareMap, RobotActions.SystemType.INTAKE_MOTOR)
                        //,autoActions.rightBlueRightDrop
                        //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, target)
                        //,new RobotActions(hardwareMap, RobotActions.System.SERVO)

                ));
                break;
        }

    }
}
