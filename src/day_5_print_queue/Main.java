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

        List<List<Integer>> pageOrderingRules = getPageOrderingRules(filePath);
        List<List<Integer>> pageUpdates = getPageUpdates(filePath);
        List<List<Integer>> validPageUpdates =
                getValidPageUpdates(pageOrderingRules, pageUpdates);
        int validMiddleNumberCount =
                getPageUpdateMiddleNumberSum(validPageUpdates);
        System.out.println(validMiddleNumberCount);
    }

    private static List<List<Integer>> getPageOrderingRules(String file) {
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

                // Dividing line is blank
                if (Objects.equals(line, "")) {
                    break;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return rules;
    }

    private static int getPageUpdateMiddleNumberSum(List<List<Integer>> updates) {
        int sum = 0;

        for (List<Integer> currentUpdate : updates) {
            sum += currentUpdate.get(currentUpdate.size() / 2);
        }

        return sum;
    }

    private static List<List<Integer>> getPageUpdates(String file) {
        List<List<Integer>> updates = new ArrayList<>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));

            String line = br.readLine();
            boolean blankLineFound = false;

            while (line != null) {
                // Dividing line is blank
                if (blankLineFound) {
                    List<String> stringUpdates = List.of(line.split(","));
                    List<Integer> intUpdates = new ArrayList<>();

                    for (String update: stringUpdates) {
                        intUpdates.add(Integer.parseInt(update));
                    }

                    updates.add(intUpdates);
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

    private static List<List<Integer>> getValidPageUpdates(List<List<Integer>> pageOrderingRules, List<List<Integer>> pageUpdates) {
        List<List<Integer>> validPageUpdates = new ArrayList<>();

        for (List<Integer> update: pageUpdates) {
            List<List<Integer>> relevantRules = new ArrayList<>();

            for (List<Integer> rule: pageOrderingRules) {
                if (
                        update.contains(rule.get(0))
                                && update.contains(rule.get(1))
                ) {
                    relevantRules.add(rule);
                }
            }

            // Loop through the relevant rules and check if the indexes of the
            // updates have the incorrect order (i.e., indexOf(rulesZero) >
            // indexOf(rulesOne), assuming before pageNumbers exist in the
            // update). If the above condition evaluates to false, break and do
            // not add the invalid update. If the loop completes without the
            // above condition evaluating to false, add the valid update

            for (int i = 0; i < relevantRules.size(); i++) {
                if (
                        update.indexOf(relevantRules.get(i).get(0))
                                > update.indexOf(relevantRules.get(i).get(1))) {
                    break;
                }

                if (i == relevantRules.size() - 1) {
                    validPageUpdates.add(update);
                }
            }
        }

        return validPageUpdates;
    }
}
