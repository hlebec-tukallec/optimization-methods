package matrixes;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class CommonMatrix {
    public static int[] scanArrayInt(String dirName, String fileName) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of(dirName + fileName + ".txt"));
        return Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
    }

    public static double[] scanArrayDouble(String dirName, String fileName) throws IOException {
        BufferedReader reader = Files.newBufferedReader(Path.of(dirName + fileName + ".txt"));
        return Arrays.stream(reader.readLine().split(" ")).mapToDouble(Double::parseDouble).toArray();
    }
}
