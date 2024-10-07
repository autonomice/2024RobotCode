package github.autonomice.subsystems;

import com.arcrobotics.ftclib.command.CommandBase;
import com.arcrobotics.ftclib.command.SubsystemBase;
import com.qualcomm.hardware.rev.RevColorSensorV3;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.NormalizedRGBA;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class ColorSensor extends SubsystemBase {
    private final RevColorSensorV3 mColorSensor;

    public ColorSensor(HardwareMap hwMap) {
        this.mColorSensor = hwMap.get(RevColorSensorV3.class, "colorsensor");
    }

    public SampleColor getColor() {
        NormalizedRGBA colors = this.mColorSensor.getNormalizedColors();

        double[] redReference = {0.01, 0.006, 0.001};
        double[] yellowReference = {0.01, 0.01, 0.003};
        double[] blueReference = {0.003, 0.005, 0.01};

        final double MIN_CONFIDENCE = 0.006; // TODO: tune

        double redDistance = l2Distance(colors, redReference);
        double yellowDistance = l2Distance(colors, yellowReference);
        double blueDistance = l2Distance(colors, blueReference);

        double minDistance = Math.min(redDistance, Math.min(yellowDistance, blueDistance));

        if (minDistance < MIN_CONFIDENCE) {
            if (minDistance == redDistance) return SampleColor.RED;
            if (minDistance == yellowDistance) return SampleColor.YELLOW;
            if (minDistance == blueDistance) return SampleColor.BLUE;
        }

        return SampleColor.EMPTY;
    }

    private double l2Distance(NormalizedRGBA color, double[] reference) {
        double dr = color.red - reference[0];
        double dg = color.green - reference[1];
        double db = color.blue - reference[2];
        return Math.sqrt(dr*dr + dg*dg + db*db);
    }

    public static class DefaultCommand extends CommandBase {
        private final ColorSensor mColorSensor;
        private final Telemetry telemetry;

        public DefaultCommand(ColorSensor colorSensor, Telemetry telemetry) {
            this.mColorSensor = colorSensor;
            this.telemetry = telemetry;

            addRequirements(this.mColorSensor);
        }

        @Override
        public void execute() {
            telemetry.addData("%s", this.mColorSensor.getColor());
        }
    }

    public enum SampleColor {
        RED,
        YELLOW,
        BLUE,
        EMPTY
    }
}
