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
        int antiNodeCount = getAntiNodeCount(cityAntennaMap);

        System.out.println(antiNodeCount);
    }

    public static int getAntiNodeCount(List<String> cityAntennaMap) {
        int count = 0;
        char antenna = '#';
        char blankArea = '.';

            /*
                Create a custom data structure for mapping antinodes and use
                that to ignore nodes that have already been found (this will
                make it easier for the loops to work with)
             */
        List<String> antiNodeMap = new ArrayList<>();

        for (String s: cityAntennaMap) {
            antiNodeMap.add(".".repeat(s.length()));
        }

        for (int i = 0; i < cityAntennaMap.size(); i++) {
            /*
                1. Find a node
                2. Determine whether it is a lowercase character, an uppercase
                    character, or a digit
                2. Look at all similar nodes after that node
                3. Calculate whether an antinode can be created between them

                Do not have to do this if only later nodes are looked at
                4. Mark the node as read (so that other later nodes cannot
                    create duplicate counts when they see it)
             */

            for (int j = 0; j < cityAntennaMap.get(i).length(); j++) {
                char antennaA = cityAntennaMap.get(i).charAt(j);

                if (Character.isLetter(antennaA) || Character.isDigit(antennaA)) {
                    for (int i2 = i; i2 < cityAntennaMap.get(i).length(); i2++) {
                        for (int j2 = j + 1; j2 < cityAntennaMap.get(i).length(); j2++) {
                            if (cityAntennaMap.get(i2).charAt(j2) == antennaA) {
                                char antennaB = cityAntennaMap.get(i2).charAt(j2);
                                // Check the antimap to ensure that a new
                                // antinode antinode can be placed at a desired
                                // position
                                if (antennaB == antennaA && i != i2 && j != j2) {
                                    // Do calculations to determine antinode
                                    // positions, if either are valid
                                    int[] antiNodeAPosition = new int[2];
                                    int[] antiNodeBPosition = new int[2];

                                    int antennaYDifference = Math.abs(i - i2);
                                    int antennaXDifference = Math.abs(j - j2);

                                    antiNodeAPosition[0] =
                                            j - antennaXDifference;
                                    antiNodeAPosition[1] =
                                            i - antennaYDifference;

                                    antiNodeBPosition[0] =
                                            j2 + antennaXDifference;
                                    antiNodeBPosition[1] =
                                            i2 + antennaYDifference;

                                    // Assuming all lines on the map are the
                                    // same length

                                    /*
                                        i == y so [0] == y
                                        j == x so [1] == x
                                     */

                                    if (
                                            (antiNodeAPosition[0] >= 0
                                                    && antiNodeAPosition[0]
                                                    < antiNodeMap.getFirst().length())
                                                    && (antiNodeAPosition[1] >= 0
                                                    && antiNodeAPosition[1]
                                                    < antiNodeMap.size())
                                    ) {
                                        String line1 = antiNodeMap.get(antiNodeAPosition[1]);
                                        line1 =
                                                line1.substring(0, antiNodeAPosition[0])
                                                        + '#'
                                                        + line1.substring(antiNodeAPosition[0] + 1);
                                        antiNodeMap.set(antiNodeAPosition[1], line1);
                                        count++;
                                    }

                                    if (
                                            (antiNodeBPosition[0] >= 0
                                                    && antiNodeBPosition[0]
                                                    < antiNodeMap.getFirst().length())
                                                    && (antiNodeBPosition[1] >= 0
                                                    && antiNodeBPosition[1]
                                                    < antiNodeMap.getLast().length())
                                    ) {
                                        String line2 = antiNodeMap.get(antiNodeBPosition[1]);
                                        line2 =
                                                line2.substring(0, antiNodeBPosition[0])
                                                        + '#'
                                                        + line2.substring(antiNodeBPosition[0] + 1);
                                        antiNodeMap.set(antiNodeBPosition[1], line2);
                                        count++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }

        for (String s: antiNodeMap) {
            System.out.println(s);
        }

        return count;
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
