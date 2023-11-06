package org.firstinspires.ftc.teamcode;

import android.annotation.SuppressLint;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.hardware.rev.Rev2mDistanceSensor;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

@TeleOp
public class DistanceTest extends LinearOpMode {
    private DistanceSensor distance = null;

    private FtcDashboard dashboard = FtcDashboard.getInstance();
    @SuppressLint("DefaultLocale")
    @Override
    public void runOpMode() throws InterruptedException {

        distance = hardwareMap.get(DistanceSensor.class, "distance");
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());

        waitForStart();

        while(opModeIsActive()) {
            telemetry.addData("range", String.format("%.01f mm", distance.getDistance(DistanceUnit.MM)));
            telemetry.addData("range", String.format("%.01f cm", distance.getDistance(DistanceUnit.CM)));
            telemetry.addData("range", String.format("%.01f m", distance.getDistance(DistanceUnit.METER)));
            telemetry.addData("range", String.format("%.01f in", distance.getDistance(DistanceUnit.INCH)-0.4));
            telemetry.update();
        }
    }
}
