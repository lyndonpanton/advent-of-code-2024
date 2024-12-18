package day_11_plutonian_pebbles;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
        String filename = "./src/day_11_plutonian_pebbles/input.txt";

        List<Long> stoneArrangement = getInitialStoneArrangement(filename);
        List<Long> newStoneArrangement =
                getStonesAfterBlinking(stoneArrangement, 25);
        int newStoneArrangementSize = newStoneArrangement.size();

        System.out.println(
                "Stone arrangement length: " + newStoneArrangementSize
        );
    }

    public static List<Long> getInitialStoneArrangement(String file) {
        List<Long> stoneArrangement = new ArrayList<>();

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
            String[] line = br.readLine().split(" ");

            for (String stone: line) {
                stoneArrangement.add(Long.parseLong(stone));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return stoneArrangement;
    }

    public static List<Long> getStonesAfterBlinking(
            List<Long> initialStoneArrangement, int numberOfBlinks
    ) {
        for (int i = 0; i < numberOfBlinks; i++) {
            int j = 0;

            while (j < initialStoneArrangement.size()) {
                if (initialStoneArrangement.get(j) == 0) {
                    initialStoneArrangement.set(j, 1L);
                } else if (
                        String.valueOf(initialStoneArrangement.get(j)).length() % 2
                                == 0) {
                    String currentStone =
                            String.valueOf(initialStoneArrangement.get(j));
                    String leftStone =
                            currentStone.substring(0, currentStone.length() / 2);
                    String rightStone =
                            currentStone.substring(currentStone.length() / 2);

                    long left = Integer.parseInt(leftStone);
                    long right = Integer.parseInt(rightStone);

                    initialStoneArrangement.set(j, left);
                    initialStoneArrangement.add(j + 1, right);
                    j++;
                } else {
                    initialStoneArrangement.set(j, initialStoneArrangement.get(j) * 2024);
                }

                j++;
            }
        }

        return initialStoneArrangement;
    }
}
