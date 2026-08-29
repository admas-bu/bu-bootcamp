import java.io.*; 
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
 
public class GradeAnalyzer {
 
    public static void main(String[] args) {
        int highest = Integer.MIN_VALUE;
        int lowest = Integer.MAX_VALUE;

        // Step 1: read scores from file
        ArrayList<Integer> scores = readScores("scores.txt");
        // Step 2: calculate statistics
        double average = calculateAverage(scores);

        for (int score : scores) {
            if (score > highest) {
                highest = score;
            }
            if (score < lowest) {
                lowest = score;
            }
        }
        // Step 3: write and print report
        writeReport(scores, average, highest, lowest, "report.txt");
    } 
 
    // Returns a list of valid scores read from the file
    public static ArrayList<Integer> readScores(String filename) {
        ArrayList<Integer> validScores = new ArrayList<>();

    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.trim();
            if (line.isEmpty()) {
                continue;
            }
            try {
                validScores.add(Integer.parseInt(line));
            } catch (NumberFormatException e) {
                System.out.println("Warning: skipping invalid score \"" + line + "\"");
            }
        }
    } catch (IOException e) {
        System.out.println("Error reading file: " + e.getMessage());
    }

    return validScores;
    }

    // Returns a map of letter grade to the number of scores in that band
    public static Map<String, Integer> countGradeBands(ArrayList<Integer> scores) {
        Map<String, Integer> counts = new LinkedHashMap<>();
        counts.put("A", 0);
        counts.put("B", 0);
        counts.put("C", 0);
        counts.put("D", 0);
        counts.put("F", 0);

        for (int score : scores) {
            String grade;
            if (score >= 90) {
                grade = "A";
            } else if (score >= 80) {
                grade = "B";
            } else if (score >= 70) {
                grade = "C";
            } else if (score >= 60) {
                grade = "D";
            } else {
                grade = "F";
            }
            counts.put(grade, counts.get(grade) + 1);
        }

        return counts;
    }
 
    // Returns the average of a list of scores, or 0.0 if the list is empty
    public static double calculateAverage(ArrayList<Integer> scores) {
        if (scores.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        for (int score : scores) {
            sum += score;
        }
        return (double) sum / scores.size();
    } 
    
    private static void emit(BufferedWriter writer, String line) throws IOException {
    writer.write(line);
    writer.newLine();
    System.out.println(line);
    }
    // Writes and prints the report
    public static void writeReport(ArrayList<Integer> scores,
                                   double avg, int high, int low,
                                   String outputFile) {

        Map<String, Integer> countsByGrade = countGradeBands(scores);

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
        emit(writer, "=== Grade Analysis Report ===");
        emit(writer, String.format("Total scores processed: %4d", scores.size()));
        emit(writer, "");
        emit(writer, String.format("Average score: %11.2f", avg));
        emit(writer, String.format("Highest score: %8d", high));
        emit(writer, String.format("Lowest score: %9d", low));
        emit(writer, "");
        emit(writer, "Grade distribution:");
        emit(writer, String.format("  A (90-100):   %3d", countsByGrade.get("A")));
        emit(writer, String.format("  B (80-89):    %3d", countsByGrade.get("B")));
        emit(writer, String.format("  C (70-79):    %3d", countsByGrade.get("C")));
        emit(writer, String.format("  D (60-69):    %3d", countsByGrade.get("D")));
        emit(writer, String.format("  F (below 60): %3d", countsByGrade.get("F")));
    } catch (IOException e) {
        System.out.println("Error writing report: " + e.getMessage());
    }

    }
} 
