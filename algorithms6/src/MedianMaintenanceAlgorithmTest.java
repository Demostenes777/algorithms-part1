import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alaitz Mendiola on 12/11/2016.
 */
public class MedianMaintenanceAlgorithmTest {

    @Test
    public void testMedian1() {
        MedianMaintenanceAlgorithm mma = new MedianMaintenanceAlgorithm();
        mma.readFile("median1");
        System.out.println(mma.getMedians());
    }

}