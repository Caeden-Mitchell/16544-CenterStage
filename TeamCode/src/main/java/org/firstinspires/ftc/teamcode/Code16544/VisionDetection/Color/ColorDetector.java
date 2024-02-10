package org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color;

import com.acmerobotics.dashboard.FtcDashboard;
import com.acmerobotics.dashboard.config.Config;
import com.acmerobotics.dashboard.telemetry.MultipleTelemetry;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

@Config
public class ColorDetector extends OpenCvPipeline {
    Telemetry telemetry;
    Mat mat = new Mat();
    public static int highH=220, highS=255, highV=200, lowH=150, lowS=100, lowV=60;
    public enum Location{
        LEFT,
        RIGHT,
        CENTRE
    }

    public enum Color {
        BLUE,
        RED
    }

    private Location location;
    private Color colorChoice;

    // A Rect in OpenCV can  be defined using 2 opposite vertices of a
    // triangle, which are connected by the diagonals
    // We need to create Regions Of Interest where the camera will be
    // looking for the items colors being searched for
    // 800x400
    static final Rect LEFT_ROI = new Rect(new Point(200, 250), new Point(475, 400));
    static final Rect RIGHT_ROI = new Rect(new Point(575, 250), new Point(700, 400));
    // define the threshold
    static double PERCENT_COLOR_THRESHOLD = 0.015;
    public ColorDetector(Telemetry t) {
        telemetry = t;
    }
    public Mat processFrame(Mat input){
        //Telemetry to DashBoard
        telemetry = new MultipleTelemetry(telemetry, FtcDashboard.getInstance().getTelemetry());

        //Convert from RGB to HSV
        Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2HSV);
        Scalar lowHSV = null;
        Scalar highHSV = null;

        if(colorChoice == Color.BLUE) {
            //Create a range for the camera to detect blue
            lowHSV = new Scalar(78, 158, 124);
            highHSV = new Scalar(138, 255, 255);
        } else if(colorChoice == Color.RED){
            //Create a range for the camera to detect red
            lowHSV = new Scalar(160, 100, 20);
            highHSV = new Scalar(180, 255, 255);
        }

        //Apply thresholding, which outlines the area of the image that is of the colour you want
        //In this case, OpenCV will change the colours being searched for to white, while the rest
        //will be black/greyscale
        Core.inRange(mat, //source matrix
                lowHSV, highHSV, //lower and upper boundraries
                mat //destination matrix
        );

        // create the submats
        // submat = submatrix, which is a portion of the original matrix
        Mat left = mat.submat(LEFT_ROI);
        Mat right = mat.submat(RIGHT_ROI);

        //check percentage of the image that became white
        // We do this by adding all the pixels together, dividing by its area,
        // the dividing by 255, which is the max value for a greyscale pixel
        // here we take the first element of the sum result, because there
        // is only one channel in a greyscale image
        double leftValue = Core.sumElems(left).val[0] / LEFT_ROI.area() / 255;
        double rightValue = Core.sumElems(right).val[0] / RIGHT_ROI.area() / 255;

        //make sure to release the submatricies
        left.release();
        right.release();

        // To help with debugging, you can use telemetry to
        // display the values used in the calculation
        telemetry.addData("Left percentage", Math.round(leftValue * 100) + "%");
        telemetry.addData("Right percentage", Math.round(rightValue * 100) + "%");

        boolean isCentre = ((leftValue > PERCENT_COLOR_THRESHOLD) && (leftValue > rightValue));
        boolean isRight = (rightValue > PERCENT_COLOR_THRESHOLD) && (rightValue > leftValue);

        //get location of game element
        if(isCentre){
            location = Location.CENTRE;
        } else if(isRight) {
            location = Location.RIGHT;
        } else {
            location = Location.LEFT;
        }
        telemetry.addData("Location", location);
        telemetry.update();

        //Convert from greyscale to rgb
        Imgproc.cvtColor(mat, mat, Imgproc.COLOR_GRAY2RGB);

        //sets border colours for submatricies
        Scalar Red = new Scalar(255, 0, 0);
        Scalar Green = new Scalar(0, 255, 0);

        // use a ternary operator, which is basically a mini if statement to choose
        // which color to use based on the loction of the skystone
        Imgproc.rectangle(mat, LEFT_ROI, location == Location.CENTRE ? Green : Red);
        Imgproc.rectangle(mat, RIGHT_ROI, location == Location.RIGHT ? Green : Red);

        //return matrix so image can be drawn
        return mat;
    }

    public void setColor(Color color){
        colorChoice = color;
    }

    public Color getColor(){
        return colorChoice;
    }

    public Location getLocation(){
        return location;
    }
}
