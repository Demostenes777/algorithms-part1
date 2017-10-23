import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alaitz Mendiola on 21/10/2016.
 */
public class MinimumCutTest {

    @Test
    public void testReadFile() {
        MinimumCut mc = new MinimumCut();
        mc.readFile();
        int v12 = 4;
        assertEquals(mc.getElement(1,2), v12);
    }

    @Test
    public void testContractLink() {
        MinimumCut mc = new MinimumCut();
        mc.readFile();
        int u = mc.pickRandomVertex();
        int v = mc.pickRandomVertex();

        System.out.println(u);
        System.out.println(v);
    }

    @Test
    public void testRunAlgorithm() {
        int min = 0;
        for (int i = 0; i < 200; i++) {
            MinimumCut mc = new MinimumCut();
            mc.readFile();
            int a = mc.applyAlgorithm();
            if (min == 0) {
                min = a;
            }
            if(min > a) {
                min = a;
            }
        }
        System.out.println(min);

    }
}