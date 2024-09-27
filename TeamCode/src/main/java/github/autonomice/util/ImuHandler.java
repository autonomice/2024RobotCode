package github.autonomice.util;

import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.IMU;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;

public class ImuHandler {
    private final IMU mImu;
    private final double angleOffSet;

    public ImuHandler(HardwareMap hwMap, IMU.Parameters params, double angleOffSet) {
        this.angleOffSet = angleOffSet;
        this.mImu = hwMap.get(IMU.class, "imu");
        this.mImu.initialize(params);
        mImu.resetYaw();
    }

    public double getYaw() {
        return this.mImu.getRobotYawPitchRollAngles().getYaw(AngleUnit.DEGREES) + angleOffSet;
    }

    public void reset() {
        mImu.resetYaw();
    }
}
