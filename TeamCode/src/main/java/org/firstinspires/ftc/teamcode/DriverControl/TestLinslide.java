package org.firstinspires.ftc.teamcode.DriverControl;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;

@TeleOp
public class TestLinslide extends LinearOpMode {
    private DcMotorEx linslide = null;
    @Override
    public void runOpMode() throws InterruptedException {
        linslide = hardwareMap.get(DcMotorEx.class, "linslide");
        initialize();

        waitForStart();
        while(opModeIsActive()){
            if(gamepad1.a){
                linslide.setTargetPosition(linslide.getTargetPosition()+10);
            } else if(gamepad1.b){
                linslide.setTargetPosition(linslide.getTargetPosition()-10);
            }
            linslide.setPower(0.3);
        }
    }

    public void initialize(){
        linslide.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        linslide.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linslide.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linslide.setMode(DcMotor.RunMode.RUN_TO_POSITION);
    }
}
