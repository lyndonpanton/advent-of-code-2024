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

        for (Long stone: newStoneArrangement) {
            System.out.print(stone + " ");
        }

        System.out.println();

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
        List<Long> newStoneArrangement = new ArrayList<>(initialStoneArrangement);

        int i = 0;

        while (i < numberOfBlinks) {
            int j = 0;

            while (j < newStoneArrangement.size()) {
                if (newStoneArrangement.get(j) == 0) {
                    newStoneArrangement.set(j, 1L);
                    j++;
                }

                j++;
            }

            i++;
        }

        return newStoneArrangement;
    }
}
