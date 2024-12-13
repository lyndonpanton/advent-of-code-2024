package day_6_guard_gallivant;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
        List<String> map = getMapInformation("./src/day_6_guard_gallivant/input.txt");
        int distinctLocations = getDistinctLocations(map);
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

    public static int getDistinctLocations(List<String> map) {
        int locations = 0;
        char newLocationIcon = '.';
        char oldLocationIcon = 'X';
        char obstacleIcon = '#';
        List<Character> guardIcons = Arrays.asList('^', '>', 'v', '<');

        int[] currentGuardLocation = new int[2];
        int currentGuardIcon = 0;

        for (int i = 0; i < map.size(); i++) {
            boolean guardFound = false;

            for (Character c: guardIcons) {
                if (map.get(i).contains(c.toString())) {
                    currentGuardLocation[0] = i;
                    currentGuardLocation[1] = map.get(i).indexOf(c);

                    currentGuardIcon = guardIcons.indexOf(c);
                }
            }
        }

        System.out.println(
                "Guard location: ("
                        + currentGuardLocation[0] + ", "
                        + currentGuardLocation[1]
                + ")"
        );

        System.out.println("Current guard icon: " + currentGuardIcon + " ("
                + guardIcons.get(currentGuardIcon) + ")"
        );

        return locations;
    }
}
