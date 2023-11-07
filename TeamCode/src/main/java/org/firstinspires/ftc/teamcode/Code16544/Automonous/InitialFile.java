package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@Autonomous
public class InitialFile extends LinearOpMode {
    MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(0,0,Math.toRadians(90)));
    @Override
    public void runOpMode() throws InterruptedException {
        waitForStart();

        Actions.runBlocking(new SequentialAction(goStraight(10)));
    }

    private Action goStraight(double pos) {
        return mecanumDrive.actionBuilder(mecanumDrive.pose)
                .lineToY(pos)
                .build();
    }
}
