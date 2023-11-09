package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.roadrunner.Action;
import com.acmerobotics.roadrunner.Pose2d;
import com.acmerobotics.roadrunner.SequentialAction;
import com.acmerobotics.roadrunner.ftc.Actions;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@Autonomous
public class AutoExample extends LinearOpMode {
    MecanumDrive mecanumDrive = new MecanumDrive(hardwareMap, new Pose2d(0,0,Math.toRadians(0)));
    DcMotorEx pixelLift;
    @Override
    public void runOpMode() throws InterruptedException {

        initialize();

        // This is how trajectories will be built from now on using RR 1.0
        // This traj will drive forward to a point of 10
        Action trajAction = mecanumDrive.actionBuilder(mecanumDrive.pose)
                .lineToX(10)
                .build();

        waitForStart();

        Actions.runBlocking(new SequentialAction( // sequential action runs actions one after another
                trajAction, // here it is running the trajectory trajAction
                telemetryPacket -> { // and then it is lifting the pixel lift
                    pixelLift.setTargetPosition(600);
                    pixelLift.setPower(0.6);
                    sleep(1000);
                    pixelLift.setPower(0);
                    return false; // Returning true causes the action to run again, returning false causes it to cease
                }
        ));
    }
    public void initialize() {
        pixelLift = hardwareMap.get(DcMotorEx.class, "pixelLift");

        pixelLift.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        pixelLift.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        pixelLift.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        pixelLift.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
