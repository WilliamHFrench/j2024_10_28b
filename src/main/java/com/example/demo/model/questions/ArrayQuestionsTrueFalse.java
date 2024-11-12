package com.example.demo.model.questions;

import java.util.ArrayList;
import java.util.List;

public class ArrayQuestionsTrueFalse {
   public int totalQuestions = 0;
    private int currentQuestionIndex = 0;
    private List<QuestionTrueFalse> arrayListQuestionsTF = new ArrayList<>();

  
    public ArrayQuestionsTrueFalse() {
      // Add your questions here
      arrayListQuestionsTF.add(new QuestionTrueFalse("The sky is blue", true));
      arrayListQuestionsTF.add(new QuestionTrueFalse("The sky is red", false));
        arrayListQuestionsTF.add(new QuestionTrueFalse("The moon is cheese", false));
        arrayListQuestionsTF.add(new QuestionTrueFalse("College can be expensive", true));
        arrayListQuestionsTF.add(new QuestionTrueFalse("The earth is flat", false));
      // ... Add more questions ...
      totalQuestions = arrayListQuestionsTF.size();
    }
  
    public QuestionTrueFalse nextQuestion(int i) {
      currentQuestionIndex = (currentQuestionIndex + 1) % totalQuestions;  // Update current index
      return arrayListQuestionsTF.get(currentQuestionIndex);
    }
  
    public List<QuestionTrueFalse> getAllQuestions() {
      return new ArrayList<>(arrayListQuestionsTF); // Return a copy of the list
    }
  }