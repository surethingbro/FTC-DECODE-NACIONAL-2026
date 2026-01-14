    package org.firstinspires.ftc.teamcode.OpenCV;

    import org.opencv.core.Core;
    import org.opencv.core.Mat;
    import org.opencv.core.Rect;
    import org.opencv.core.Scalar;
    import org.opencv.imgproc.Imgproc;
    import org.openftc.easyopencv.OpenCvPipeline;

    public class PipelineImprov extends OpenCvPipeline {

        public String detectedcolor = "NINGUNO";

        Mat hsv = new Mat();
        Mat purpleMask = new Mat();
        Mat greenMask = new Mat();
        Mat combinedMasks = new Mat();
        Mat processedInput = new Mat();
        
        @Override
        public Mat processFrame(Mat input) {

            Imgproc.cvtColor(input, hsv, Imgproc.COLOR_BGR2HSV);

            Scalar lowerPurple = new Scalar(130,50,50);
            Scalar upperPurple = new Scalar(170,255,255);

            Core.inRange(hsv, lowerPurple, upperPurple, purpleMask);

            Scalar lowerGreen = new Scalar(40,50,50);
            Scalar upperGreen = new Scalar(80,255,255);

            Core.inRange(hsv, lowerGreen, upperGreen, greenMask);

            Core.bitwise_or(greenMask, purpleMask, combinedMasks);


            Core.bitwise_and(input, input, processedInput, combinedMasks);

            Rect roi = new Rect(
                    170,
                    120,
                    300,
                    200
            );

            Imgproc.rectangle(
                    processedInput,
                    roi,
                    new Scalar(0, 0, 255),
                    3
            );

            detectedcolor = determineDetectedColor(purpleMask, greenMask, roi);
            return processedInput;
        }

        public static String determineDetectedColor(Mat purpleMask, Mat greenMask, Rect roi) {

            Mat purpleROI = purpleMask.submat(roi);
            Mat greenROI = greenMask.submat(roi);

            int purplePixels = Core.countNonZero(purpleROI);
            int greenPixels = Core.countNonZero(greenROI);

            purpleROI.release();
            greenROI.release();

            int threshold = 500;

            if (purplePixels > greenPixels && purplePixels > threshold) {
                return "MORADO";
            } else if (greenPixels > purplePixels && greenPixels > threshold) {
                return "VERDE";
            } else {
                return "NINGUNO";
            }
        }
    }



