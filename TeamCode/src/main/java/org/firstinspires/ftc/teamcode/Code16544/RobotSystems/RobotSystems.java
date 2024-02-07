package org.firstinspires.ftc.teamcode.Code16544.RobotSystems;

import static android.os.SystemClock.sleep;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.controller.PIDController;
import com.qualcomm.robotcore.hardware.*;
import com.qualcomm.robotcore.util.ElapsedTime;

@Config

public class RobotSystems {
    public DcMotorEx linearSlideLeft, linearSlideRight; // lifts the pixels using linear slide
    public DcMotorEx intakeMotor; // intake motor

    public Servo rotateArm, rotateHopper, droneLauncher;
    public DistanceSensor distance;

    public static double armNum = 0.045, hopperNum = 0;

    private final double pixP = 0.008, pixI = 0.0002, pixD = 0.0001; // pid coefficients for pixel lift

    private final double robP = 0, robI = 0, robD = 0; // pid coefficients for robot lift

    private final PIDController pixPIDController;
    private final PIDController robPIDController;

    public RobotSystems(HardwareMap hardwareMap) {
        pixPIDController = new PIDController(pixP, pixI, pixD);
        robPIDController = new PIDController(robP, robI, robD);

        linearSlideLeft = hardwareMap.get(DcMotorEx.class, "linearSlideLeft");
        linearSlideRight = hardwareMap.get(DcMotorEx.class, "linearSlideRight");

        intakeMotor = hardwareMap.get(DcMotorEx.class, "intakeMotor");

        distance = hardwareMap.get(DistanceSensor.class, "distance");

        rotateArm = hardwareMap.get(Servo.class, "rotateArm");
        rotateHopper = hardwareMap.get(Servo.class, "rotateHopper");
        droneLauncher = hardwareMap.get(Servo.class, "droneLauncher");

        linearSlideLeft.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        linearSlideRight.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);

        linearSlideLeft.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
        linearSlideLeft.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        linearSlideRight.setMode(DcMotor.RunMode.RUN_TO_POSITION);
        linearSlideLeft.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        linearSlideRight.setDirection(DcMotorSimple.Direction.REVERSE);


        intakeMotor.setZeroPowerBehavior(DcMotorEx.ZeroPowerBehavior.BRAKE);
        intakeMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        rotateArm.setDirection(Servo.Direction.REVERSE);
    }

    public void setLineLeftHeight(int target) {
        pixPIDController.setPID(pixP, pixI, pixD);
        double power1 = pixPIDController.calculate(linearSlideLeft.getCurrentPosition(), target);

        linearSlideLeft.setPower(power1);
    }

    public void setLinRightHeight(int target) {
        pixPIDController.setPID(pixP, pixI, pixD);
        double power2 = pixPIDController.calculate(linearSlideRight.getCurrentPosition(), target);

        linearSlideRight.setPower(power2);
    }

    public void setLinearSlideLeft(int targetPos){
        linearSlideLeft.setTargetPosition(targetPos);
        linearSlideLeft.setPower(1);
    }

    public void setLinearSlideRight(int targetPos){
        linearSlideRight.setTargetPosition(targetPos);
        linearSlideRight.setPower(1);
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
        setServos(0.012, 0.1, 500, false);
    }

    public void servoToZero(){
        setServos(0,0,600, false);
    }

    public void preDrop(){
        setServos(0.13, 0.9, 1750, true);
    }

    public void autoDrop(){
        setServos(0.185, 0.87, 1750, true);
    }

    public void DCDrop(){
        setServos(0.137, 0.5, 0, false);
    }

    public void DCLiftHopper(){
            setServos(0, 0, 0, false);
            setServos(0.13, 0.9, 0, false);

        //servoToZero();
        //preDrop();
    }

    public void DCLowerHopper(){
        setServos(0,0,0, false);
        setServos(0.012, 0.1, 0, false);
        //servoToZero();
        //deadState();
    }

    public void liftHopper(){
        servoToZero();
        preDrop();
    }

    public void lowerHopper(){
        servoToZero();
        sleep(400);
        deadState();
    }

    public void ejectPixelFromIntake() {
        intakeMotor.setPower(0.9);
        sleep(250);
        intakeMotor.setPower(0);
    }

    public void runIntake() {
        intakeMotor.setPower(-0.9);
        sleep(250);
        intakeMotor.setPower(0);
    }
}
