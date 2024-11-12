package com.example.demo.controller;

import com.example.demo.model.Greeting;
import com.example.demo.model.questions.ArrayQuestionsTrueFalse;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



import java.util.Map;

@Controller
public class HomeController {

    private ArrayQuestionsTrueFalse arrayQuestionsTrueFalse = new ArrayQuestionsTrueFalse();
    private int currentQuestionIndex = 0;

    @GetMapping("/")
    public String home() {
        return "home"; // for home.html
    }

    @GetMapping("/greeting")
    public String greetingForm(Model model) {
        model.addAttribute("greeting", new Greeting());
        return "greeting";
    }

    @PostMapping("/greeting")
    public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
        if (!isPasswordStrong(greeting.getPassword())) {
            return "improper-password";  // Return the improper password view
        } else if (!isUserNameValid(greeting.getUsername())) {
            return "improper-password"; // Return back to greeting form with error message
        }

        model.addAttribute("greeting", greeting);
        model.addAttribute("questions", arrayQuestionsTrueFalse.getAllQuestions());
        model.addAttribute("username", greeting.getUsername());
        return "quiz";
    }

    @PostMapping("/submit-quiz")
    public String submitQuiz(@RequestParam Map<String, String> answers, Model model) {
        try {
            if (answers.isEmpty()) {
                model.addAttribute("error", "No answers selected.");
                return "error";  // Return to error page if no answers are selected
            }

            double grade = calculateGrade(answers);

            model.addAttribute("grade", String.format("%.2f", grade));  // Only grade added to model
            return "results";  // Return results page
        } catch (Exception e) {
            e.printStackTrace();  // Print stack trace for debugging
            model.addAttribute("error", "An unexpected error occurred: " + e.getMessage());
            return "error";  // Show error page
        }
    }

    public double calculateGrade(Map<String, String> answers) {
        int correctAnswers = 0;
        int totalQuestions = arrayQuestionsTrueFalse.getAllQuestions().size();  // Get the total number of questions
    
        if (totalQuestions == 0) {
            throw new IllegalArgumentException("Total number of questions cannot be zero.");
        }
    
        for (int i = 0; i < totalQuestions; i++) {
            boolean answer = Boolean.parseBoolean(answers.get("answers[" + i + "]"));
            if (answer == arrayQuestionsTrueFalse.getAllQuestions().get(i).getAnswer()) {
                correctAnswers++;
            }
        }
    
        return (correctAnswers / (double) totalQuestions) * 100;
    }

    public boolean isPasswordStrong(String password) {
        String passwordPattern = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).{8,}$";
        return password != null && password.matches(passwordPattern);
    }

    public boolean isUserNameValid(String username) {
        return !username.isEmpty() && username.contains("@");
    }
}