import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by bgpmeala on 14/10/2016.
 */
public class QuickSort {

    private final static String FILE_PATH = "C:\\Users\\bgpmeala\\IdeaProjects\\algorithms2\\src\\file";
    private int comparissons = 0;
    private int[] intA;

    public QuickSort(){
    }

    public void readFile() {

        try (Stream<String> stream = Files.lines(Paths.get(FILE_PATH))){
            intA = Stream.of(stream.toArray(String[]::new)).mapToInt(Integer::parseInt).toArray();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void sort() {
        sort(0, intA.length-1);
    }
    public void sort(int lo, int hi) {
        if (hi <= lo) {
            return;
        }
        else {
            int p = partition(lo, hi);
            sort(lo, p-1);
            sort(p+1, hi);
        }

    }

    private int partition(int lo, int hi) {

        // p representa la posicion del pivote, no su valor
        int p = choosePivot3(lo, hi);
        int temp;
        int j;
        int i = lo + 1;

        // If the pivot is not the first element, swap it.
        if (p != lo) {
            temp = intA[lo];
            intA[lo] = intA[p];
            intA[p] = temp;
        }

        for (j = lo + 1; j <= hi; j++ ) {
            if (intA[j] < intA[lo]) {
                temp = intA[j];
                intA[j] = intA[i];
                intA[i] = temp;
                i++;
            }
        }

        temp = intA[lo];
        intA[lo] = intA[i-1];
        intA[i-1] = temp;

        comparissons = comparissons +  hi - lo;

        return i-1;
    }

    private int choosePivot1(int lo, int hi) {
        return lo;
    }

    private int choosePivot2(int lo, int hi) {return hi;}

    private int choosePivot3(int lo, int hi) {

        int a = (lo + hi) / 2;

        int c = chooseMedian(hi, a, lo);
        return c;
    }

    private int chooseMedian(int hi, int b, int lo) {
        int va = intA[lo];
        int vb = intA[b];
        int vc = intA[hi];

        if (va < vb && vb < vc || vc < vb && vb < va) {
            return b;
        }
        if (va < vc && vc < vb || vb < vc && vc < va) {
            return hi;
        }
        return lo;

    }

    public int getComparissons() {
        return comparissons;
    }
}

