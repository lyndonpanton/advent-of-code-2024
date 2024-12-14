package day_8_resonant_collinearity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
        String filename = "./src/day_8_resonant_collinearity/input.txt";

        List<String> cityAntennaMap = getCityAntennaMap(filename);

        for (String line: cityAntennaMap) {
            System.out.println(line);
        }
    }

    public static List<String> getCityAntennaMap(String file) {
        List<String> map = new ArrayList<>();
        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                map.add(line);

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }
}
