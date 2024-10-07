package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagDetection;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class AprilTagDetector extends SubsystemBase {
    public AprilTagProcessor processor;
    public VisionPortal portal;

    public AprilTagDetector(HardwareMap hwMap) {
        this.processor = AprilTagProcessor.easyCreateWithDefaults();
        this.portal = VisionPortal.easyCreateWithDefaults(hwMap.get(WebcamName.class, "cam"), this.processor);
    }

    public static class DefaultCommand extends CommandBase {
        private final AprilTagDetector mDetector;
        private final Telemetry telemetry;

        public DefaultCommand(AprilTagDetector detector, Telemetry telemetry) {
            this.mDetector = detector;
            this.telemetry = telemetry;

            addRequirements(this.mDetector);
        }

        @Override
        public void execute() {
            for (AprilTagDetection detection: this.mDetector.processor.getDetections()) {
                telemetry.addData("x %f", detection.ftcPose.x);
                telemetry.addData("y %f", detection.ftcPose.y);
                telemetry.addData("z %f", detection.ftcPose.z);
                telemetry.addData("yaw %f", detection.ftcPose.yaw);
                telemetry.addData("id %d", detection.id);
            }
        }
    }
}
