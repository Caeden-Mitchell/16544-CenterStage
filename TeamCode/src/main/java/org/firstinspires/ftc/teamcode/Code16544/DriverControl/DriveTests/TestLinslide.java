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
    private DcMotorEx linslideLeft = null;
    private DcMotorEx linslideRight = null;
    private FtcDashboard dashboard = FtcDashboard.getInstance();
    @Override
    public void runOpMode() throws InterruptedException {
        linslideLeft = hardwareMap.get(DcMotorEx.class, "linearSlideLeft");
        linslideRight = hardwareMap.get(DcMotorEx.class, "linearSlideRight");
        Telemetry telemetry = new MultipleTelemetry(this.telemetry, dashboard.getTelemetry());
        linslideLeft.setTargetPosition(0);
        linslideRight.setTargetPosition(0);
        initialize();
        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a && linslideLeft.getCurrentPosition()<2900){
                linslideLeft.setTargetPosition(linslideLeft.getTargetPosition()+10);
            } else if(gamepad1.b && linslideLeft.getCurrentPosition()>10){
                linslideLeft.setTargetPosition(linslideLeft.getTargetPosition()-10);
            } else if(gamepad1.x && linslideRight.getCurrentPosition()<10){
                linslideRight.setTargetPosition(linslideRight.getTargetPosition()+10);
            } else if (gamepad1.y && linslideLeft.getCurrentPosition()>-2900){
                linslideRight.setTargetPosition(linslideRight.getTargetPosition()-10);
            }
            linslideLeft.setPower(1);
            linslideRight.setPower(1);
            telemetry.addData("left position", linslideLeft.getCurrentPosition());
            telemetry.addData("right position", linslideRight.getTargetPosition());
            telemetry.update();
        }
    }
    public void initialize(){
        linslideLeft.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linslideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linslideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linslideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linslideRight.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linslideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linslideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linslideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
