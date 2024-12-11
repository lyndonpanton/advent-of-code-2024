package day_2_red_nosed_reports;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        BufferedReader reader;
        String reportFilePath = "./src/day_2_red_nosed_reports/input.txt";

        List<List<Integer>> reports = getReports(reportFilePath);
        int safeReports = getSafeReportCount(reports);

        System.out.println(safeReports);
    }

    public static List<List<Integer>> getReports(String filePath) {
        BufferedReader reader;
        List<List<Integer>> reports = new ArrayList<>();

        try {
            reader = new BufferedReader(new FileReader(
                    filePath)
            );

            String line = reader.readLine();

            while (line != null) {
                List<Integer> report = new ArrayList<>();

                String[] items = line.split(" +");

                for (String item: items) {
                    report.add(Integer.parseInt(item));
                }

                reports.add(report);

                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return reports;
    }

    public static int getSafeReportCount(List<List<Integer>> reports) {
        int safeReports = 0;

        for (List<Integer> report: reports) {
            boolean safe = true;
            boolean isDecreasing = report.get(0) < report.get(1);

            for (int i = 1; i < report.size(); i++) {
                if (
                        Math.abs(report.get(i) - report.get(i - 1)) >= 1
                                && Math.abs(report.get(i) - report.get(i - 1)) <= 3
                ) {
                    if (isDecreasing) {
                        if (!(report.get(i - 1) < report.get(i))) {
                            safe = false;
                            break;
                        }
                    } else {
                        if (!(report.get(i - 1) > report.get(i))) {
                            safe = false;
                            break;
                        }
                    }
                } else {
                    safe = false;
                    break;
                }
            }

            if (safe) {
                safeReports++;
            }
        }

        return safeReports;
    }
}
