import org.junit.jupiter.api.Test; 
import static org.junit.jupiter.api.Assertions.*; 
import java.util.ArrayList; 
import java.util.Arrays; 
 
public class GradeAnalyzerTest { 
 
    @Test
    void calculateAverage_returnsZero_whenListIsEmpty() { 
        ArrayList<Integer> scores = new ArrayList<>(); 
        assertEquals(0.0, GradeAnalyzer.calculateAverage(scores)); 
    } 
 
    @Test
    void calculateAverage_returnsCorrectAverage_forTypicalScores() { 
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(80, 90, 100)); 
        assertEquals(90.0, GradeAnalyzer.calculateAverage(scores));
    }
 
    @Test
    void calculateAverage_returnsSingleValue_whenListHasOneItem() { 
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(75)); 
        assertEquals(75.0, GradeAnalyzer.calculateAverage(scores));
    } 
 
    @Test
    void calculateAverage_returnsDouble_notInteger() { 
        // 1 + 2 = 3, divided by 2 = 1.5, not 1
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(1, 2)); 
        assertEquals(1.5, GradeAnalyzer.calculateAverage(scores)); 
    } 
 
    @Test 
    void calculateAverage_handlesAllSameValues() { 
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(88, 88, 88)); 
        assertEquals(88.0, GradeAnalyzer.calculateAverage(scores)); 
    }

    @Test
    void calculateAverageWithTenScores_returnsPreciseAverage() {
        // Create a list of 10 specific scores
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(
            85, 92, 78, 88, 95, 81, 87, 90, 76, 84
        ));
        
        // Calculate expected average: sum = 856, average = 856 / 10 = 85.6
        double expectedAverage = 85.6;
        double actualAverage = GradeAnalyzer.calculateAverage(scores);
        
        assertEquals(expectedAverage, actualAverage, 0.01,
                     "Average of 10 scores [85,92,78,88,95,81,87,90,76,84] should be 85.6");
    }

    @Test
    void countGradeBands_withMixedScores_categorizeCorrectly() {
        // Create a list of scores across different grade bands
        ArrayList<Integer> scores = new ArrayList<>(Arrays.asList(
            95, 88, 75, 65, 55, 92, 82, 72  // A, B, C, D, F, A, B, C
        ));
        
        var gradeBands = GradeAnalyzer.countGradeBands(scores);
        
        // Verify counts in each grade band
        assertEquals(2, gradeBands.get("A"), "Should have 2 A grades (95, 92)");
        assertEquals(2, gradeBands.get("B"), "Should have 2 B grades (88, 82)");
        assertEquals(2, gradeBands.get("C"), "Should have 2 C grades (75, 72)");
        assertEquals(1, gradeBands.get("D"), "Should have 1 D grade (65)");
        assertEquals(1, gradeBands.get("F"), "Should have 1 F grade (55)");
    }
}