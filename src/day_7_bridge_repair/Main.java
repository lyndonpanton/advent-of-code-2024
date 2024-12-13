package day_7_bridge_repair;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");

        String filename = "./src/day_7_bridge_repair/input.txt";
        List<List<Long>> testValues = getTestValues(filename);

        for (List<Long> testValue: testValues) {
            for (Long item: testValue) {
                System.out.print(item + " ");
            }

            System.out.println();
        }
    }

    public static List<List<Long>> getTestValues(String file) {
        List<List<Long>> testValues = new ArrayList<>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                int colonIndex = line.indexOf(':');

                line = line.substring(0, colonIndex)
                        + line.substring(colonIndex + 1);

                String[] test = line.split(" ");

                testValues.add(new ArrayList<>());

                for (String t: test) {
                    testValues.getLast().add(Long.parseLong(t));
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return testValues;
    }
}
