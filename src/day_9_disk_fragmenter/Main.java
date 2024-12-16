package day_9_disk_fragmenter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello Advent of Code 2024!");
        String filename = "./src/day_9_disk_fragmenter/input.txt";
        String diskMap = getDiskMap(filename);

        System.out.println(diskMap);
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
}
