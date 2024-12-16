package day_10_hoof_it;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
        String filename = "./src/day_10_hoof_it/input.txt";
        
        List<List<Integer>> topologyMap = getTopologyMap(filename);
        List<List<Integer>> trailHeadPositions =
                getTrailHeadPositions(topologyMap);

        for (List<Integer> trailHeadPosition : trailHeadPositions) {
            System.out.print(
                    "(" + trailHeadPosition.getFirst()
                    + ", " + trailHeadPosition.get(1)
                    + "), "
            );
        }
    }

    public static List<List<Integer>> getTopologyMap(String file) {
        List<List<Integer>> map = new ArrayList<>();

        BufferedReader br;

        try {
            br = new BufferedReader(new FileReader(file));
            String line = br.readLine();

            while (line != null) {
                String[] rowString = line.split("");
                List<Integer> rowInt = new ArrayList<>();
                
                for (String position: rowString) {
                    rowInt.add(Integer.parseInt(position));
                }
                
                map.add(rowInt);

                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return map;
    }

    public static List<List<Integer>> getTrailHeadPositions(List<List<Integer>> topologyMap) {
        List<List<Integer>> positions = new ArrayList<>();

        for (int i = 0; i < topologyMap.size(); i++) {
            for (int j = 0; j < topologyMap.get(i).size(); j++) {
                if (topologyMap.get(i).get(j) == 0) {
                    List<Integer> position = new ArrayList<>();
                    position.add(i);
                    position.add(j);

                    positions.add(position);
                }
            }
        }

        return positions;
    }
}
