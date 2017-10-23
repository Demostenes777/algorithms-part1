import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alaitz Mendiola on 29/10/2016.
 */
public class sccTest {



    @Test
    public void testSCC2() {
        StronglyCC scc = new StronglyCC(9);
        scc.readFile("file2");
        // Expected output 3,3,3,0,0
        scc.dfs();
        scc.getSCCs().forEach(x -> System.out.println(x));
    }

    @Test
    public void testFile3() {
        StronglyCC scc = new StronglyCC(8);
        scc.readFile("file3");
        // Expected output 3,3,2,0,0
        scc.dfs();
        scc.getSCCs().forEach(x -> System.out.println(x));
    }

    @Test
    public void testFile1() {
        StronglyCC scc = new StronglyCC(875714);
        scc.readFile("file");
        // Expected output 3,3,2,0,0
        scc.dfs();
        scc.getSCCs().forEach(x -> System.out.println(x));
    }
}