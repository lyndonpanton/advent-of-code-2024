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

        int occurrencesOfXMas = getWordOccurrences(rows, "xmas");

        System.out.println(occurrencesOfXMas);
    }

    public static int getWordOccurrences(List<String> rows, String word) {
        int occurrencesOfXMas = 0;

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).length(); j++) {
                if (rows.get(i).charAt(j) == 'x') {
                    boolean canCheckLeft = false;
                    boolean canCheckRight = false;
                    boolean canCheckDown = false;
                    boolean canCheckUp = false;

                    // Can check left
                    if (j >= word.length() - 1) {
                        canCheckLeft = true;

                        if (
                                rows.get(i).charAt(j - 1) == 'm'
                                    && rows.get(i).charAt(j - 2) == 'a'
                                    && rows.get(i).charAt(j - 3) == 's'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    // Can check right
                    if (j < rows.size() - word.length()) {
                        canCheckRight = true;

                        if (
                                rows.get(i).charAt(j + 1) == 'm'
                                    && rows.get(i).charAt(j + 2) == 'a'
                                    && rows.get(i).charAt(j + 3) == 's'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    // Can check down
                    if (i < rows.size() - word.length()) {
                        canCheckDown = true;

                        if (
                                rows.get(i + 1).charAt(j) == 'm'
                                    && rows.get(i + 2).charAt(j) == 'a'
                                    && rows.get(i + 3).charAt(j) == 's'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    // Can check up
                    if (i >= word.length() - 1) {
                        canCheckUp = true;

                        if (
                                rows.get(i - 1).charAt(j) == 'M'
                                        && rows.get(i - 2).charAt(j) == 'A'
                                        && rows.get(i - 3).charAt(j) == 'S'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    if (canCheckLeft && canCheckDown) {

                    }

                    if (canCheckLeft && canCheckUp) {

                    }

                    if (canCheckRight && canCheckDown) {

                    }

                    if (canCheckRight && canCheckUp) {

                    }
                }
            }
        }

        return occurrencesOfXMas;
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
