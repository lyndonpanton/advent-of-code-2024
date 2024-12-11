package day_3_mull_it_over;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.*;

public class Main {
    public static void main(String[] args) {
        List<String> corruptedMemory = getCorruptedMemory(
                "./src/day_3_mull_it_over/input.txt"
        );

        List<List<Integer>> realInstructions = getRealInstructions(corruptedMemory);

        int result = getRealInstructionsResult(realInstructions);
        System.out.println(result);
    }

    public static List<String> getCorruptedMemory(String filename) {
        BufferedReader br;
        List<String> corruptedMemory = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(filename));
            String line = br.readLine();

            while (line != null) {
                corruptedMemory.add(line);

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return corruptedMemory;
    }

    public static List<List<Integer>> getRealInstructions(List<String> corruptedMemory) {
        List<List<Integer>> realInstructions = new ArrayList<>();

        for (String line: corruptedMemory) {
            Pattern pattern = Pattern.compile("mul[(][0-9]+,[0-9]+[)]");
            Matcher matcher = pattern.matcher(line);

            while (matcher.find()) {
                String group = matcher.group();
                List<Integer> pair = new ArrayList<>();

                int commaIndex = matcher.group().indexOf(',');
                int closingParenthesisIndex = matcher.group().indexOf(')');

                pair.add(Integer.parseInt(group.substring(4, commaIndex)));
                pair.add(Integer.parseInt(
                        group.substring(commaIndex + 1, closingParenthesisIndex)
                ));

                realInstructions.add(pair);
            }
        }

        return realInstructions;
    }

    public static int getRealInstructionsResult(List<List<Integer>> pairs) {
        int sum = 0;

        for (List<Integer> pair: pairs) {
            sum += (pair.get(0) * pair.get(1));
        }

        return sum;
    }
}
