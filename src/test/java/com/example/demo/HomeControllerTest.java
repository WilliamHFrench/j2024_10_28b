import com.example.demo.controller.HomeController;
import com.example.demo.model.questions.ArrayQuestionsTrueFalse;
import com.example.demo.model.questions.QuestionTrueFalse;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class HomeControllerTest {

    @InjectMocks
    private HomeController homeController;

    @Mock
    private ArrayQuestionsTrueFalse arrayQuestionsTrueFalse;

    @Test
    public void testCalculateGrade() {
        // Mock the getAllQuestions method to return a fixed list of questions
        List<QuestionTrueFalse> mockQuestions = Arrays.asList(
            new QuestionTrueFalse("Question 1", true),
            new QuestionTrueFalse("Question 2", false),
            new QuestionTrueFalse("Question 3", true)
        );
        when(arrayQuestionsTrueFalse.getAllQuestions()).thenReturn(mockQuestions);
    
        // Create a map of correct answers
        Map<String, String> correctAnswers = new HashMap<>();
        correctAnswers.put("answers[0]", "true");
        correctAnswers.put("answers[1]", "false");
        correctAnswers.put("answers[2]", "true");
    
        // Calculate the grade with correct answers
        double correctGrade = homeController.calculateGrade(correctAnswers);
    
        // Assert the correct grade
        assertEquals(100.0, correctGrade, 0.01);
    
        // Create a map of incorrect answers
        Map<String, String> incorrectAnswers = new HashMap<>();
        incorrectAnswers.put("answers[0]", "true");
        incorrectAnswers.put("answers[1]", "true");
        incorrectAnswers.put("answers[2]", "false");
    
        // Calculate the grade with incorrect answers
        double incorrectGrade = homeController.calculateGrade(incorrectAnswers);
    
        // Assert the incorrect grade (adjust the expected value based on your calculation logic)
        assertEquals(33.3, incorrectGrade, 0.1);
    }

    @Test
    public void testIsPasswordStrong() {
        assertTrue(homeController.isPasswordStrong("StrongPassword123!"));
        assertFalse(homeController.isPasswordStrong("weakpassword"));
    }

    @Test
    public void testIsUserNameValid() {
        assertTrue(homeController.isUserNameValid("user@example.com"));
        assertFalse(homeController.isUserNameValid("validusername"));
    }
}
