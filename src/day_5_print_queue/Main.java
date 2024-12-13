package day_5_print_queue;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
        String filePath = "./src/day_5_print_queue/input.txt";

        // Dividing line is blank

        List<List<Integer>> pageOrderingRules = getPageOrderingRules(filePath);

        for (List<Integer> rule: pageOrderingRules) {
            System.out.println(rule.get(0) + " and " + rule.get(1));
        }
    }

    public static List<List<Integer>> getPageOrderingRules(String file) {
        List<List<Integer>> rules = new ArrayList<>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));

            String line = br.readLine();

            while (line != null) {
                int pipeIndex = line.indexOf('|');

                int beforeNumber =
                        Integer.parseInt(line.substring(0, pipeIndex));
                int afterNumber =
                        Integer.parseInt(line.substring(pipeIndex + 1));

                List<Integer> rule = new ArrayList<>();
                rule.add(beforeNumber);
                rule.add(afterNumber);
                rules.add(rule);

                line = br.readLine();

                if (Objects.equals(line, "")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rules;
    }

    public static List<List<Integer>> getPageUpdates(String file) {
        List<List<Integer>> updates = new ArrayList<>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));

            String line = br.readLine();
            boolean blankLineFound = false;

            while (line != null) {

                if (blankLineFound) {

                } else if (Objects.equals(line, "")) {
                    blankLineFound = true;
                }

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return updates;
    }
}
