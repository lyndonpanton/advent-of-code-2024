package day_8_resonant_collinearity;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
//        String filename = "./src/day_8_resonant_collinearity/input.txt";
        String filename = "./src/day_8_resonant_collinearity/custom-input.txt";

        List<String> cityAntennaMap = getCityAntennaMap(filename);
        int antiNodeCount = getAntiNodeCount(cityAntennaMap);
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
//                                    System.out.println(
//                                            "Node A position: "
//                                                    + i
//                                                    + ", "
//                                                    + j
//                                    );
//                                    System.out.println(
//                                            "Node B position: "
//                                                    + i2
//                                                    + ", "
//                                                    + j2
//                                    );
                                    // Do calculations to determine antinode
                                    // positions, if either are valid
                                    int[] antiNodeAPosition = new int[2];
                                    int[] antiNodeBPosition = new int[2];

                                    int antennaXDifference = Math.abs(i - i2);
                                    int antennaYDifference = Math.abs(j - j2);

                                    antiNodeAPosition[0] =
                                            i - antennaXDifference;
                                    antiNodeAPosition[1] =
                                            j - antennaYDifference;

                                    antiNodeBPosition[0] =
                                            i2 + antennaXDifference;
                                    antiNodeBPosition[1] =
                                            j2 + antennaYDifference;

                                    System.out.println(
                                            "Anti node A position: "
                                            + antiNodeAPosition[0]
                                            + ", "
                                            + antiNodeAPosition[1]
                                    );

                                    System.out.println(
                                            "Anti node B position: "
                                                    + antiNodeBPosition[0]
                                                    + ", "
                                                    + antiNodeBPosition[1]
                                    );

                                    String line1 = antiNodeMap.get(antiNodeAPosition[0]);
                                    String line2 = antiNodeMap.get(antiNodeBPosition[0]);

                                    line1 =
                                            line1.substring(0, antiNodeAPosition[1])
                                            + '#'
                                            + line1.substring(antiNodeAPosition[1] + 1);

                                    line2 =
                                            line2.substring(0, antiNodeBPosition[1])
                                            + '#'
                                            + line2.substring(antiNodeBPosition[1] + 1);

                                    antiNodeMap.set(antiNodeAPosition[0], line1);
                                    antiNodeMap.set(antiNodeBPosition[0], line2);

                                    // Add check to see if the position of the
                                    // antenna to be added is inside the
                                    // boundary

                                    /*
                                        Referencing the second code block in
                                        the problem brief...

                                        n1: (3, 4)
                                        n2: (5, 5)

                                        xDifference: 2
                                        yDifference: 1

                                        a1: (1, 3)
                                        a2: (7, 6)
                                     */

                                    // Mark the valid antinodes on the antinode
                                    // map
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
