import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by bgpmeala on 07/10/2016.
 */
public class IntegerArray {

    private List<Integer> fileContent = new ArrayList<>();
    private Integer[] fileContentArray;
    private long inversions = 0;

    public IntegerArray() {

    }

    public List<Integer> getArray() {
        return fileContent;
    }

    public void readFile() {

        String fileName = "C:\\Users\\bgpmeala\\IdeaProjects\\algorithms1\\src\\file";

        try (Stream<String> stream = Files.lines(Paths.get(fileName))) {
            stream.forEach(a -> fileContent.add(Integer.parseInt(a)));
        } catch (IOException e) {
            e.printStackTrace();
        }

        fileContentArray = fileContent.toArray(new Integer[fileContent.size()]);

    }

    public long count(Integer[] intA, int n) {
        long x, y, z;

        if (n == 1) {
            return 0;
        }

        Integer[] A = new Integer[intA.length / 2];
        Integer[] B = new Integer[intA.length - A.length];
        Integer[] C = new Integer[intA.length];

        System.arraycopy(intA, 0, A, 0, A.length);
        System.arraycopy(intA, A.length, B, 0, B.length);
        x = count(A, A.length);
        y = count(B, B.length);
        z = countSplitInv(A, B, C);


        for (int k = 0; k < intA.length; k++){
            intA[k] = C[k];
        }
        return x + y + z;
    }

    private long countSplitInv(Integer[] A, Integer[] B, Integer[] C) {

        int j = 0;
        int i = 0;
        int k = 0;
        long a = 0;

        while (i < A.length && j < B.length) {
                if (A[i] < B[j]) {
                    C[k] = A[i];
                    i++;
                }
                else {
                    C[k] = B[j];
                    j++;
                    a = a +  A.length - i;
                }
            k++;
        }

        while (i < A.length) {
            C[k] = A[i];
            k++;
            i++;
        }
        while (j < B.length) {
            C[k] = B[j];
            k++;
            j++;
        }
        return a;
    }

    public void countInversions() {
        inversions = count(fileContentArray, fileContentArray.length);
    }

    public long getInversions() {
        return inversions;
    }

    public long bruteForce() {
        for (int i = 0; i < fileContentArray.length; i++) {
            for (int j = i+1; j < fileContentArray.length; j++) {
                if (fileContentArray[i] > fileContentArray[j]) {
                    inversions++;

                }
            }
        }
        return inversions;
    }
}
