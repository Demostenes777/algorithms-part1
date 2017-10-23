import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by bgpmeala on 07/10/2016.
 */
public class IntegerArrayTest {

    @Test
    public void shouldReadFile() {
        IntegerArray integerArray = new IntegerArray();
        integerArray.readFile();
        assertEquals((int)integerArray.getArray().get(0), 54044);
    }

    @Test
    public void shouldCountInversions() {
        IntegerArray integerArray = new IntegerArray();
        integerArray.readFile();
        integerArray.countInversions();
        System.out.println(integerArray.getInversions());
    }

    @Test
    public void shouldCountWithBruteForce() {
        IntegerArray integerArray = new IntegerArray();
        integerArray.readFile();
        integerArray.bruteForce();
        System.out.println(integerArray.getInversions());
    }
}