import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bgpmeala on 14/10/2016.
 */
public class quickSortTest {

    @Test
    public void shouldSort() {
        QuickSort qs = new QuickSort();
        qs.readFile();
        qs.sort();
        System.out.println(qs.getComparissons());

        // first 162085
        // second 164123
        // third 296238
    }

}