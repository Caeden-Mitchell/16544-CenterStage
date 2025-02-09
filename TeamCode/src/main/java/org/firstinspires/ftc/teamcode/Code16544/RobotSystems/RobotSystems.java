package org.firstinspires.ftc.teamcode.Code16544.RobotSystems;

import static android.os.SystemClock.sleep;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.RoadRunner.Drive.MecanumDrive;

@Config

public class RobotSystems {
    public DcMotorEx linearSlideLeft, linearSlideRight; // lifts the pixels using linear slide
    public DcMotorEx intakeMotor; // intake motor

    public Servo rotateArm, rotateHopper, droneLauncher;
    public DistanceSensor distanceLeft, distanceRight;


    public RobotSystems(HardwareMap hardwareMap) {
        linearSlideLeft = hardwareMap.get(DcMotorEx.class, "linearSlideLeft");
        linearSlideRight = hardwareMap.get(DcMotorEx.class, "linearSlideRight");

        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");

        distanceLeft = hardwareMap.get(DistanceSensor.class, "distanceLeft");
        distanceRight = hardwareMap.get(DistanceSensor.class, "distanceRight");

        rotateArm = hardwareMap.get(Servo.class, "rotateArm");
        rotateHopper = hardwareMap.get(Servo.class, "rotateHopper");
        droneLauncher = hardwareMap.get(Servo.class, "droneLauncher");

        linearSlideLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        linearSlideRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);


        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setTargetPosition(0);
        linearSlideLeft.setTargetPosition(0);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);


        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);
        linearSlideLeft.setDirection(DcMotorSimple.Direction.REVERSE);


        intakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rotateArm.setDirection(Servo.Direction.REVERSE);
    }


    public void setLinearSlideLeft(int targetPos){
        linearSlideLeft.setTargetPosition(targetPos);
        linearSlideLeft.setPower(1);
    }

    public void setLinearSlideRight(int targetPos){
        linearSlideRight.setTargetPosition(targetPos);
        linearSlideRight.setPower(1);
    }

    public void setMotorPower(double drive, double strafe, double rotate, MecanumDrive mecanumDrive){
        mecanumDrive.leftFront.setPower(drive + strafe + rotate);
        mecanumDrive.leftBack.setPower(drive - strafe + rotate);
        mecanumDrive.rightFront.setPower(drive - strafe - rotate);
        mecanumDrive.rightBack.setPower(drive + strafe - rotate);
    }

    public void setServos(double pos1, double pos2, int time, boolean isDelayed){
        ElapsedTime elapsedTime = new ElapsedTime();
        rotateArm.setPosition(pos1);
        if(isDelayed){
            sleep(200);
        }
        rotateHopper.setPosition(pos2);

        sleep(time);
    }

    public void holdIntake () {
        rotateArm.setPosition(0);
        rotateHopper.setPosition(0);
    }

    public void deadState(){
        setServos(0.1,0.889,0,false);
    }

    public void underBarState() {
        setServos(0.47,0.91,0,false);
    }

    public void DCPreDrop(){
        setServos(0.3,0.88,0,false);
    }

    public void DCDrop(){
        setServos(0.44,0.845,0,false);
    }

    public void liftServoSequence(){
        setServos(0.5, 0.87,0,false);
    }

    public void autoPreDrop(){
        setServos(0.47,0.91,1000,true);
    }

    public void autoDrop(){
        setServos(0.47,0.85,2500,false);
    }

    public void ejectPixelFromIntake(double power) {
        intakeMotor.setPower(power);
        sleep(350);
        intakeMotor.setPower(0);
    }

    public void runIntake(double power) {
        intakeMotor.setPower(power);
        sleep(250);
        intakeMotor.setPower(0);
    }
}
