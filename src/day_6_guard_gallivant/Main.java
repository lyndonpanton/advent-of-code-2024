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

        System.out.println(distinctLocations);
    }

    public static int getDistinctLocations(List<String> map, Guard guard) {
        char newLocationIcon = '.';
        char visitedLocationIcon = 'X';
        char obstacleIcon = '#';

        int locations = 1;
        boolean isInsideMap = true;

        while (isInsideMap) {
            int currentLocationX = guard.getLocation()[0];
            int currentLocationY = guard.getLocation()[1];
            char nextLocationIcon;

            System.out.println(
                    "Current location: " + currentLocationX + ", "
                            + currentLocationY
            );

            System.out.println("Current direction: " + guard.getDirection());
            System.out.println("Distinct locations: " + locations);

            /*
                Process:
                    1. Is the location the guard is trying to move into inside
                    the mapped area? End the loop)
                    2. Is there an obstacle on the location the guard is trying
                    to move onto? Turn right
                    3. Move onto the next location
                        a. Has this location been visited? Increment the
                        distinct locations variable
             */

            if (guard.getDirection() == GuardDirection.Up) {
                // Check if the guard can go up (is there an obstacle above? is
                // there the boundary of a map above?)
                if (currentLocationX - 1 < 0) {
                    break;
                }

                nextLocationIcon =
                        map.get(currentLocationX - 1).charAt(currentLocationY);

                if (nextLocationIcon == obstacleIcon) {
                    guard.setDirection(GuardDirection.Right);
                } else {
                    // Move onto the next location
                    guard.setLocation(currentLocationX - 1, currentLocationY);
                    // If statement to see if it is a new location, and change
                    // the location's icon if so
                    if (
                            map.get(guard.getLocation()[0])
                                    .charAt(guard.getLocation()[1])
                            == newLocationIcon) {
                        locations++;

                        String newRow = map.get(guard.getLocation()[0]);
                        newRow =
                                newRow.substring(0, guard.getLocation()[1])
                                + visitedLocationIcon
                                + newRow.substring(guard.getLocation()[1] + 1);
                        map.set(guard.getLocation()[0], newRow);
                    }
                }

            } else if (guard.getDirection() == GuardDirection.Right) {
                if (currentLocationY + 1 > map.size() - 1) {
                    break;
                }

                nextLocationIcon =
                        map.get(currentLocationX).charAt(currentLocationY + 1);

                if (nextLocationIcon == obstacleIcon) {
                    guard.setDirection(GuardDirection.Down);
                } else {
                    guard.setLocation(currentLocationX, currentLocationY + 1);

                    if (
                            map.get(guard.getLocation()[0])
                                    .charAt(guard.getLocation()[1])
                            == newLocationIcon) {
                        locations++;

                        String newRow = map.get(guard.getLocation()[0]);
                        newRow =
                                newRow.substring(0, guard.getLocation()[1])
                                        + visitedLocationIcon
                                        + newRow.substring(guard.getLocation()[1] + 1);
                        map.set(guard.getLocation()[0], newRow);
                    }
                }
            } else if (guard.getDirection() == GuardDirection.Down) {
                if (currentLocationX + 1 > map.size() - 1) {
                    isInsideMap = false;
                }

                nextLocationIcon =
                        map.get(currentLocationX + 1).charAt(currentLocationY);

                if (nextLocationIcon == obstacleIcon) {
                    guard.setDirection(GuardDirection.Left);
                } else {
                    guard.setLocation(currentLocationX + 1, currentLocationY);

                    if (
                            map.get(guard.getLocation()[0])
                                    .charAt(guard.getLocation()[1])
                            == newLocationIcon) {
                        locations++;

                        String newRow = map.get(guard.getLocation()[0]);
                        newRow =
                                newRow.substring(0, guard.getLocation()[1])
                                + visitedLocationIcon
                                + newRow.substring(guard.getLocation()[1] + 1);
                        map.set(guard.getLocation()[0], newRow);
                    }
                }
            } else {
                if (currentLocationY - 1 < 0) {
                    break;
                }

                nextLocationIcon =
                        map.get(currentLocationX).charAt(currentLocationY - 1);

                if (nextLocationIcon == obstacleIcon) {
                    guard.setDirection(GuardDirection.Up);
                } else {
                    guard.setLocation(currentLocationX, currentLocationY - 1);

                    if (
                            map.get(guard.getLocation()[0])
                                    .charAt(guard.getLocation()[1])
                            == newLocationIcon) {
                        locations++;

                        String newRow = map.get(guard.getLocation()[0]);
                        newRow =
                                newRow.substring(0, guard.getLocation()[1])
                                + visitedLocationIcon
                                + newRow.substring(guard.getLocation()[1] + 1);
                        map.set(guard.getLocation()[0], newRow);
                    }
                }
            }
        }

        return locations;
    }

    public static Guard getGuardInformation(List<String> map, List<Character> icons) {
        Guard guard = new Guard();

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
