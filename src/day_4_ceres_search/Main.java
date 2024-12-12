package day_4_ceres_search;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> rows = getWordsearch(
                "./src/day_4_ceres_search/input.txt"
        );

        int occurrencesOfXMas = getWordOccurrences(rows, "XMAS");

        System.out.println(occurrencesOfXMas);
    }

    public static int getWordOccurrences(List<String> rows, String word) {
        int occurrencesOfXMas = 0;

        for (int i = 0; i < rows.size(); i++) {
            for (int j = 0; j < rows.get(i).length(); j++) {
                if (rows.get(i).charAt(j) == 'X') {
                    boolean canCheckLeft = false;
                    boolean canCheckRight = false;
                    boolean canCheckDown = false;
                    boolean canCheckUp = false;

                    // Can check left
                    if (j >= (word.length() - 1)) {
                        canCheckLeft = true;

                        if (
                                rows.get(i).charAt(j - 1) == 'M'
                                    && rows.get(i).charAt(j - 2) == 'A'
                                    && rows.get(i).charAt(j - 3) == 'S'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    // Can check right
                    if (j < rows.size() - (word.length() - 1)) {
                        canCheckRight = true;

                        if (
                                rows.get(i).charAt(j + 1) == 'M'
                                    && rows.get(i).charAt(j + 2) == 'A'
                                    && rows.get(i).charAt(j + 3) == 'S'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    // Can check down
                    if (i < rows.size() - (word.length() - 1)) {
                        canCheckDown = true;

                        if (
                                rows.get(i + 1).charAt(j) == 'M'
                                    && rows.get(i + 2).charAt(j) == 'A'
                                    && rows.get(i + 3).charAt(j) == 'S'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    // Can check up
                    if (i >= (word.length() - 1)) {
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
                        if (
                                rows.get(i + 1).charAt(j - 1) == 'M'
                                    && rows.get(i + 2).charAt(j - 2) == 'A'
                                    && rows.get(i + 3).charAt(j - 3) == 'S'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    if (canCheckLeft && canCheckUp) {
                        if (
                                rows.get(i - 1).charAt(j - 1) == 'M'
                                    && rows.get(i - 2).charAt(j - 2) == 'A'
                                    && rows.get(i - 3).charAt(j - 3) == 'S'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    if (canCheckRight && canCheckDown) {
                        if (
                                rows.get(i + 1).charAt(j + 1) == 'M'
                                    && rows.get(i + 2).charAt(j + 2) == 'A'
                                    && rows.get(i + 3).charAt(j + 3) == 'S'
                        ) {
                            occurrencesOfXMas++;
                        }
                    }

                    if (canCheckRight && canCheckUp) {
                        if (
                                rows.get(i - 1).charAt(j + 1) == 'M'
                                    && rows.get(i - 2).charAt(j + 2) == 'A'
                                    && rows.get(i - 3).charAt(j + 3) == 'S'
                        ) {
                            occurrencesOfXMas++;
                        }
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
                rows.add(line);

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return rows;
    }
}
