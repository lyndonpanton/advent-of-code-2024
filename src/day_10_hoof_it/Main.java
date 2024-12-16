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
        
        for (List<Integer> row : topologyMap) {
            for (Integer position: row) {
                System.out.print(position + ",");
            }
            
            System.out.println();
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
}
