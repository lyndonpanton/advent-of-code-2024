package day_9_disk_fragmenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
        String filename = "./src/day_9_disk_fragmenter/input.txt";
        String diskMap = getDiskMap(filename);

        // Change id map and free space map to a list (use the list indexes for
        // ids)
        Map<Integer, Integer> idMap = getIDMap(diskMap);
        Map<Integer, Integer> freeSpaceMap = getFreeSpaceMap(diskMap);

        char freeSpaceCharacter = '.';
        String individualBlockRepresentation = getIndividualBlockRepresentation(
                idMap,
                freeSpaceMap,
                freeSpaceCharacter
        );

        System.out.println(individualBlockRepresentation);

        // Use a boolean and keep switching between values when getting output
    }

    public static String getDiskMap(String filename) {
        BufferedReader br;
        String map = "";

        try {
            br = new BufferedReader(new FileReader(filename));

            map = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static Map<Integer, Integer> getFreeSpaceMap(String diskMap) {
        Map<Integer, Integer> map = new HashMap<>();

        int j = 1;
        int i = 0;

        while (j < diskMap.length()) {
            map.put(i, Integer.parseInt(String.valueOf(diskMap.charAt(j))));

            j += 2;
            i++;
        }

        return map;
    }

    public static Map<Integer, Integer> getIDMap(String diskMap) {
        Map<Integer, Integer> map = new HashMap<>();

        int j = 0;
        int i = 0;

        while (j < diskMap.length()) {
            map.put(i, Integer.parseInt(String.valueOf(diskMap.charAt(j))));

            j += 2;
            i++;
        }

        return map;
    }

    public static String getIndividualBlockRepresentation(
            Map<Integer, Integer> idMap,
            Map<Integer, Integer> freeSpaceMap,
            char freeSpaceCharacter
    ) {
        StringBuilder representation = new StringBuilder();

        for (int i = 0; i < idMap.size(); i++) {
            representation.append(String.valueOf(i).repeat(idMap.get(i)));

            // Id map will either be the same length or 1 longer than the free
            // space map
            if (i < freeSpaceMap.size()) {
                representation.append(
                        String.valueOf(freeSpaceCharacter)
                                .repeat(freeSpaceMap.get(i))
                );
            }
        }

        return representation.toString();
    }
}
