package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Code16544.RobotSystems.RobotSystems;

@Config
@Autonomous
public class PIDF_LinearSlide extends LinearOpMode {
    RobotSystems robot;
    public static int target = 0;

    @Override
    public void runOpMode() throws InterruptedException {
        robot = new RobotSystems(hardwareMap);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());
        telemetry.addData("target", target);
        telemetry.addData("pixel pos", robot.linearSlideLeft.getCurrentPosition());
        telemetry.update();

        robot.deadState();

        waitForStart();

        if (isStopRequested()) return;

        robot.setLinearSlideRight(500);

        robot.deadState();
        //robot.servoToZero();
        robot.autoPreDrop();
        robot.autoDrop();
        robot.autoPullOut();
        //robot.servoToZero();
        robot.deadState();
        sleep(1000);

        telemetry.addData("position", robot.linearSlideLeft.getCurrentPosition());
        telemetry.addData("Target ", target);
        telemetry.update();
    }
}
