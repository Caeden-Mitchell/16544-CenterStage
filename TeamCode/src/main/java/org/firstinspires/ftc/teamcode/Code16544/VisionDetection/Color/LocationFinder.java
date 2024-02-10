package org.firstinspires.ftc.teamcode.Code16544.VisionDetection.Color;

import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvCameraRotation;

public class LocationFinder {
    private final OpenCvCamera camera;
    int cameraMonitorViewId;
    public int trajType = 0;

    private final ColorDetector colorDetector;


    public LocationFinder(HardwareMap hardwareMap, Telemetry telemetry, ColorDetector.Color color, boolean far) {

        cameraMonitorViewId = hardwareMap.appContext
                .getResources().getIdentifier("cameraMonitorViewId",
                        "id", hardwareMap.appContext.getPackageName());

        WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam");

        camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);

        colorDetector = new ColorDetector(telemetry, far);

        camera.setPipeline(colorDetector);

        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener() {
            @Override
            public void onOpened() {
                camera.startStreaming(800, 448, OpenCvCameraRotation.UPRIGHT);
            }
            @Override
            public void onError(int errorCode) {
                telemetry.addLine("camera not found");
            }
        });

        colorDetector.setColor(color);

    }

    public void getTrajectory(Telemetry telemetry) {
        if (colorDetector.getLocation() == ColorDetector.Location.LEFT) {
            trajType = 3;
        }
        if (colorDetector.getLocation() == ColorDetector.Location.RIGHT) {
            trajType = 1;
        }
        if (colorDetector.getLocation() == ColorDetector.Location.CENTRE) {
            trajType = 2;
        }
        telemetry.addData("ELEMENT", colorDetector.getLocation());
        telemetry.update();
    }
}
