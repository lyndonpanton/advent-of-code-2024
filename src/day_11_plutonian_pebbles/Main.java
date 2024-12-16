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

        for (Long stone: stoneArrangement) {
            System.out.print(stone + " ");
        }
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
}
