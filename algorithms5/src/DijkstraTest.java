import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by Alaitz Mendiola on 06/11/2016.
 */
public class DijkstraTest {

    @Test
    public void shouldReadFile() {
        Dijkstra dj = new Dijkstra();
        dj.readFile("file2");

        System.out.println(dj.getFirstNode());
        System.out.println(dj.getFirstLength());
    }

    @Test
    public void shouldComputeSPF() {
        Dijkstra dj = new Dijkstra();
        dj.readFile("file2");

        dj.getShortestDistance(4);
    }
}