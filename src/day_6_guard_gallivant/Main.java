package day_6_guard_gallivant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
        List<String> map = getMapInformation("./src/day_6_guard_gallivant/input.txt");

    }

    public static List<String> getMapInformation(String filename) {
        BufferedReader br;
        List<String> map = new ArrayList<>();

        try {
            br = new BufferedReader(new FileReader(filename));
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
