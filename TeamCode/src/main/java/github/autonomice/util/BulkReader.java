package github.autonomice.util;

import com.qualcomm.hardware.lynx.LynxModule;
import com.qualcomm.robotcore.hardware.HardwareMap;

import java.util.List;

// Perform Lynx Module Bulk Reads.
public class BulkReader {
    private final List<LynxModule> allHubs;

    public BulkReader(HardwareMap map) {
        allHubs = map.getAll(LynxModule.class);

        for (LynxModule m : allHubs) {
            m.setBulkCachingMode(LynxModule.BulkCachingMode.MANUAL);
        }
    }

    public void bulkRead() {
        for (LynxModule m : allHubs) {
            m.clearBulkCache();
        }
    }
}
