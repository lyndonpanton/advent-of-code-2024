package day_1_historian_hysteria;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;

        List<Integer> leftNumbers = new ArrayList<>();
        List<Integer> rightNumbers = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(
                    "src/day_1_historian_hysteria/input.txt"
            ));
            String line = reader.readLine();

            while (line != null) {
                String[] numbers = line.split(" +");
                leftNumbers.add(Integer.parseInt(numbers[0]));
                rightNumbers.add(Integer.parseInt(numbers[1]));

                line = reader.readLine();
            }

            reader.close();
        } catch (IOException e){
            e.printStackTrace();
        }

        Collections.sort(leftNumbers);
        Collections.sort(rightNumbers);

        int totalDistance = 0;

        for (int i = 0; i < leftNumbers.size(); i++) {
            totalDistance += Math.abs(leftNumbers.get(i) - rightNumbers.get(i));
        }

        System.out.println(totalDistance);
    }
}
