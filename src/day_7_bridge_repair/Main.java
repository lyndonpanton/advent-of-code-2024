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
        long totalEvaluationResult = getTotalEvaluationResult(testValues);

//        System.out.println(totalEvaluationResult);
    }

    public static List<List<Character>> getPossibleOperators(int numberOfOperators) {
        // Use binary???
        List<List<Character>> operators = new ArrayList<>();

        int minValue = 0;
        int maxValue = (int) Math.pow(2, numberOfOperators);

        while (minValue < maxValue) {
            List<Character> currentOperators = new ArrayList<>();
            String binaryString = Integer.toBinaryString(minValue);

            for (int i = 0; i < binaryString.length(); i++) {
                if (binaryString.charAt(i) == '0') {
                    currentOperators.add('+');
                } else {
                    currentOperators.add('*');
                }
            }

            operators.add(currentOperators);

            minValue++;
        }

        return operators;
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

    public static long getTotalEvaluationResult(List<List<Long>> testValues) {
        long result = 0;
        List<Integer> validTestValues = getValidTestValues(testValues);

        for (Integer value: validTestValues) {
            result += value;
        }

        return result;
    }

    public static List<Integer> getValidTestValues(List<List<Long>> testValues) {
        List<Integer> validTestValues = new ArrayList<>();

        for (List<Long> testValue: testValues) {
            long target = testValue.getFirst();
            List<Long> operands = testValue.subList(1, testValue.size());
            List<List<Character>> operators =
                    getPossibleOperators(operands.size() - 1);

            System.out.print("Target: " + target + ", ");
            System.out.println("Operands: " + operands);

            // Recursion?
            for (int i = 0; i < operands.size() - 2; i++) {

            }
        }

        return validTestValues;
    }
}
