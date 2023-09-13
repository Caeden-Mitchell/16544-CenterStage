package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.util.ElapsedTime;

@Autonomous
public class runMotor extends LinearOpMode {

    public DcMotorEx random = null;

    @Override
    public void runOpMode() throws InterruptedException {

        random = hardwareMap.get(DcMotorEx.class, "rando");
        random.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        int ticks = 0;

        waitForStart();


        random.setPower(1);
        sleep(3000);
        while(random.isBusy()){
            ticks += random.getCurrentPosition();
        }
        random.setPower(0);
        sleep(500);

        telemetry.addData("ticks:", ticks);
        telemetry.update();
    }
}
