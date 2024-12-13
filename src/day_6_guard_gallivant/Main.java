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
        List<Character> guardIcons = Arrays.asList('^', '>', 'v', '<');
        Guard guardInformation = getGuardInformation(map, guardIcons);
        int distinctLocations = getDistinctLocations(map, guardInformation);
    }

    public static int getDistinctLocations(List<String> map, Guard guard) {

//        System.out.println("Guard location: "
//                + "("
//                + guard.getLocation()[0] + ", " + guard.getLocation()[1]
//                + ")"
//        );
//
//        System.out.println("Guard direction: " + guard.getDirection());

        int locations = 0;
        char newLocationIcon = '.';
        char oldLocationIcon = 'X';
        char obstacleIcon = '#';
        boolean isInsideMap = true;

        while (isInsideMap) {
            int currentLocationX = guard.getLocation()[0];
            int currentLocationY = guard.getLocation()[1];

            if (guard.getDirection() == GuardDirection.Up) {
                // Check if the guard can go up (is there an obstacle above? is
                // there the boundary of a map above?)
                if (currentLocationX - 1 < 0) {
                    isInsideMap = false;
                }
//                char aboveIcon = map.get(current)
//                switch ()
//                if (map.get(currentLocationX - 1).charAt(currentLocationY) == ) {
//
//                }

            } else if (guard.getDirection() == GuardDirection.Right) {
                if (currentLocationY + 1 > map.size() - 1) {
                    isInsideMap = false;
                }
            } else if (guard.getDirection() == GuardDirection.Down) {
                if (currentLocationX + 1 > map.size() - 1) {
                    isInsideMap = false;
                }
            } else {
                if (currentLocationY - 1 < 0) {
                    isInsideMap = false;
                }
            }
        }

        return locations;
    }

    public static Guard getGuardInformation(List<String> map, List<Character> icons) {
        Guard guard = new Guard();

//        int[] currentGuardLocation = new int[2];
        int currentGuardIcon = 0;

        for (int i = 0; i < map.size(); i++) {
            boolean guardFound = false;

            for (Character c: icons) {
                if (map.get(i).contains(c.toString())) {
                    guardFound = true;
                    guard.setLocation(i, map.get(i).indexOf(c));

                    switch (icons.indexOf(c)) {
                        case 0:
                            guard.setDirection(GuardDirection.Up);
                            break;
                        case 1:
                            guard.setDirection(GuardDirection.Right);
                            break;
                        case 2:
                            guard.setDirection(GuardDirection.Down);
                            break;
                        case 3:
                            guard.setDirection(GuardDirection.Left);
                            break;
                    }
                }

                if (guardFound) {
                    break;
                }
            }
        }

        return guard;
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

class Guard {
    private final int[] location;
    private GuardDirection direction;

    public Guard() {
        location = new int[2];
    }

    public void setDirection(GuardDirection direction) {
        this.direction = direction;
    }

    public void setLocation(int row, int column) {
        location[0] = row;
        location[1] = column;
    }

    public GuardDirection getDirection() {
        return direction;
    }

    public int[] getLocation() {
        return location;
    }
}

enum GuardDirection {
    Up, Right, Down, Left
}
