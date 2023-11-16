package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotActions;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.Camera;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.ColorDetector;

@Config
@Autonomous
public class RightBlueAuto extends LinearOpMode {
    public static double startingY = 63.0;
    public static double startingX = -37;
    public static int target = 1000;

    Camera colorDetection;
    RobotSystems robot;
    AutoActions autoActions;


    @Override
    public void runOpMode() throws InterruptedException {
        ColorDetector colorDetector = new ColorDetector(telemetry);
        Pose2d startPose = new Pose2d(startingX, startingY, Math.toRadians(180));

        //colorDetection = new Camera(hardwareMap, telemetry, ColorDetector.Color.BLUE, colorDetector);

        robot = new RobotSystems(hardwareMap);

        autoActions = new AutoActions(hardwareMap, startPose);

        waitForStart();

        if(isStopRequested()) return;


        //switch (colorDetector.getLocation()) {
            //case RIGHT:
        Actions.runBlocking(new SequentialAction(
                autoActions.rightSpike
                ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                ,autoActions.rightDrop
                //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, target)
                //,new RobotActions(hardwareMap, RobotActions.System.SERVO)
        ));
                /*telemetry.addData("ELEMENT", "RIGHT");
                telemetry.update();
                break;
            case LEFT:
                telemetry.addData("ELEMENT", "LEFT");
                telemetry.update();
                break;
            case CENTRE:
                telemetry.addData("ELEMENT", "CENTRE");
                telemetry.update();
                break;
            default:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                        ,autoActions.rightDrop
                        //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 2500)
                        //,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                ));
                telemetry.addData("ELEMENT", "NOT FOUND. RUNNING RIGHT TRAJ");
                telemetry.update();
                break;

        }*/

    }
}
