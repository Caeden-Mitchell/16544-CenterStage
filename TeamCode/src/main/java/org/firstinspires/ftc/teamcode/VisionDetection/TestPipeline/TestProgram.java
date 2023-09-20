package org.firstinspires.ftc.teamcode.VisionDetection.TestPipeline;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraFactory;
import org.openftc.easyopencv.OpenCvInternalCamera;

public class TestProgram extends LinearOpMode {
    /**
     * Before you can obtain a camera instance from the camera factory,
     * you must decide whether or not you wish to have a live camera preview
     * displayed on the Robot Controller screen. If so, you can obtain the
     * view ID for the camera monitor container in exactly the same way as is done for Vuforia.
     * */
    int cameraMonitorViewId = hardwareMap.appContext.getResources().getIdentifier("cameraMonitorViewId", "id", hardwareMap.appContext.getPackageName());
    /*Note that even if you choose to use a live viewport, you can programmatically pause
     *  it to reduce CPU/battery load using camera.pauseViewport(); and webcam.resumeViewport();*/

    /**
     * The first parameter specifies the WebcamName of the webcam you wish to use.
     * and the second (optional) parameter specifies the view ID in which to insert the live preview.
     */
    WebcamName webcamName = hardwareMap.get(WebcamName.class, "Webcam1");

    /**Once you've obtained the WebcamName, you can proceed to using the camera factory*/
    // With live preview
    OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName, cameraMonitorViewId);
    // Without a live preview
    //OpenCvCamera camera = OpenCvCameraFactory.getInstance().createWebcam(webcamName);

    @Override
    public void runOpMode() throws InterruptedException {
        
        /*There are two methods for connecting the camera device: Asynchronously and Synchronously*/
        /**
         * When opening asynchronously, your thread is not blocked.
         * Instead, you provide a callback in order to be notified
         * when the opening process has been completed. Usually it
         * is in this callback that you'll want to start streaming from the camera
         */
        camera.openCameraDeviceAsync(new OpenCvCamera.AsyncCameraOpenListener()
        {
            @Override
            public void onOpened()
            {
                // Usually this is where you'll want to start streaming from the camera
            }
            @Override
            public void onError(int errorCode)
            {
                /*
                 * This will be called if the camera could not be opened
                 */
            }
        });
        /**When opening synchronously, your thread is blocked until the operation is complete.**/
        //camera.openCameraDevice();
    }
}
