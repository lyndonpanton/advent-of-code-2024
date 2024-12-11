package day_4_ceres_search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");

        List<String> rows = getWordsearch(
                "./src/day_4_ceres_search/input.txt"
        );

        for (String row: rows) {
            System.out.println(row);
        }

        int occurrencesOfXMas = getWordOccurrences(rows, "xmas");
    }

    public static int getWordOccurrences(List<String> rows, String word) {


        return 0;
    }

    public static List<String> getWordsearch(String filename) {
        BufferedReader br;
        List<String> rows = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            while (line != null) {
                rows.add(line.toLowerCase());

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
