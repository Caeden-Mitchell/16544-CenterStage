package org.firstinspires.ftc.teamcode.VisionDetection.TestPipeline;

import org.opencv.core.Mat;
import org.openftc.easyopencv.OpenCvPipeline;

public class test extends OpenCvPipeline {
    @Override
    public Mat processFrame(Mat input){
        return input;
    }
}
