package org.firstinspires.ftc.teamcode.Code16544.Automonous;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.Camera;
import org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color.ColorDetector;

public class RightBlueAuto extends LinearOpMode {
    Camera colorDetection;
    @Override
    public void runOpMode() throws InterruptedException {
        colorDetection = new Camera(hardwareMap, telemetry, ColorDetector.Color.BLUE);


    }
}
