package org.firstinspires.ftc.teamcode.Code16544.DriverControl.DriveTests;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

import org.firstinspires.ftc.robotcore.external.Telemetry;

@TeleOp
public class TestLinslide extends LinearOpMode {
    private DcMotorEx linslide = null;
    private FtcDashboard dashboard = FtcDashboard.getInstance();
    @Override
    public void runOpMode() throws InterruptedException {
        linslide = hardwareMap.get(DcMotorEx.class, "linslide");
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());
        initialize();
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a){
                linslide.setTargetPosition(linslide.getTargetPosition()+10);
            } else if(gamepad1.b){
                linslide.setTargetPosition(linslide.getTargetPosition()-10);
            }
            linslide.setPower(0.5);
            telemetry.addData("position", linslide.getCurrentPosition());
            telemetry.update();
        }
    }
    public void initialize(){
        linslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linslide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
