import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alaitz Mendiola on 12/11/2016.
 */
public class TwoSumAlgorithmTest {

    @Test
    public void test1() {
        TwoSumAlgorithm tsa = new TwoSumAlgorithm();
        tsa.readFile("sum2");
        assertEquals(tsa.compute(4, 5), 2);
    }

    @Test
    public void test2() {
        TwoSumAlgorithm tsa = new TwoSumAlgorithm();
        tsa.readFile("sum1");
        int result = tsa.compute(-10000,10000);
        System.out.println(result);
        //427
    }

}