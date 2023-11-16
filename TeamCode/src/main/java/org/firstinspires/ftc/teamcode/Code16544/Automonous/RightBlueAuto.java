package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.roadrunner.Actions;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotActions;
import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.Camera;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.ColorDetector;

public class RightBlueAuto extends LinearOpMode {
    public static double startingY = 63.0;
    public static double startingX = -38.5;

    Camera colorDetection;
    RobotSystems robot;

    AutoActions autoActions;


    @Override
    public void runOpMode() throws InterruptedException {
        ColorDetector colorDetector = new ColorDetector(telemetry);
        Pose2d startPose = new Pose2d(startingX, startingY, Math.toRadians(180));

        colorDetection = new Camera(hardwareMap, telemetry, ColorDetector.Color.BLUE);

        robot = new RobotSystems(hardwareMap);

        autoActions = new AutoActions(hardwareMap, startPose);


        switch (colorDetector.getLocation()) {
            case RIGHT:
                Actions.runBlocking(new SequentialAction(
                        autoActions.rightSpike
                        ,new RobotActions(hardwareMap, RobotActions.System.INTAKE_MOTOR)
                        ,autoActions.rightDrop
                        //,new RobotActions(hardwareMap,RobotActions.System.PIXEL_LIFT, 2500)
                        //,new RobotActions(hardwareMap, RobotActions.System.SERVO)
                ));
                break;
            case LEFT:
                break;
            case CENTRE:
                break;
        }

    }
}
