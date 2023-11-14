package org.firstinspires.ftc.teamcode.Code16544.Automonous.AutoTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@Autonomous
@Config
public class PIDF_LinearSideSimple extends LinearOpMode {
    private PIDController pidController;


    public static double kP = 0.008, kI = 0.0002, kD = 0.0001;

    public static int target = 0;

    private DcMotorEx pixelLift;
    @Override
    public void runOpMode() throws InterruptedException {
        pidController = new PIDController(kP, kI, kD);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        pixelLift = hardwareMap.get(DcMotorEx.class, "intake");

        pixelLift.setMode(DcMotorEx.RunMode.RUN_WITHOUT_ENCODER);

        pixelLift.setMode(DcMotorEx.RunMode.STOP_AND_RESET_ENCODER);

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            setPixelLiftHeight(target);

            telemetry.addData("target ", target);
            telemetry.addData("pos ", pixelLift.getCurrentPosition());
            telemetry.update();
        }
    }

    public void setPixelLiftHeight(int height) {
        pixelLift.setPower(pixelLiftPID(height));
    }

    public double pixelLiftPID(int target) {

        pidController.setPID(kP, kI, kD);

        int slidePos = pixelLift.getCurrentPosition();
        telemetry.addData("Power", pidController.calculate(slidePos, target));

        return pidController.calculate(slidePos, target);
    }
}
