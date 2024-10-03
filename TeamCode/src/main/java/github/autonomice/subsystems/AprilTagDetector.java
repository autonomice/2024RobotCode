package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.vision.VisionPortal;
import org.firstinspires.ftc.vision.apriltag.AprilTagProcessor;

public class AprilTagDetector extends SubsystemBase {
    public AprilTagProcessor processor;
    public VisionPortal portal;

    public AprilTagDetector(HardwareMap hwMap) {
        this.processor = AprilTagProcessor.easyCreateWithDefaults();
        this.portal = VisionPortal.easyCreateWithDefaults(hwMap.get(WebcamName.class, "cam"), this.processor);
        this.portal.setProcessorEnabled(this.processor, true);
    }
}
