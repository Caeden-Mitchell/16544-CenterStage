package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.arcrobotics.ftclib.controller.PIDController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@Config
@Autonomous
public class PIDF_LinearSlide extends LinearOpMode {
    private PIDController pidController;

    public static double kP = 0, kI = 0, kD = 0;
    public static double kG = 0;

    public static int target = 0;

    private final double TICKS_IN_DEGREES = 1;

    private DcMotorEx pixelLift;
    @Override
    public void runOpMode() throws InterruptedException {

        pidController = new PIDController(kP, kI, kD);

        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        pixelLift = hardwareMap.get(DcMotorEx.class, "pixelLift");

        waitForStart();

        if (isStopRequested()) return;

        while (opModeIsActive()) {

            pidController.setPID(kP, kI, kD);

            int slidePos = pixelLift.getTargetPosition();
            double pid = pidController.calculate(slidePos, target);

            double power = pid + kG;

            pixelLift.setPower(power);

            telemetry.addData("pos ", slidePos);
            telemetry.addData("target ", target);
            telemetry.update();
        }
    }
}
