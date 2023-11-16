package com.tingotango.service;

import com.tingotango.exceptions.GameExceptions;
import com.tingotango.model.ListQuestion;
import com.tingotango.model.Question;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Data
@Service
public class QuestionService {
    private ListQuestion questions;

    public QuestionService(){
        questions = new ListQuestion();
        // falta leer de archivo preguntas de un txt o de una db
        questions.addQuestion(new Question("What is the capital of France?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 0, "q1"));
        questions.addQuestion(new Question("What is the capital of England?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 1, "q2"));
        questions.addQuestion(new Question("What is the capital of Germany?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 2, "q3"));
        questions.addQuestion(new Question("What is the capital of Spain?",
                Arrays.asList("Paris", "London", "Berlin", "Madrid"), (byte) 3, "q4"));
    }

    public String addNewQuestion(Question newQuestion){
        questions.addQuestion(newQuestion);
        return "Adicionado";
    }
    public String deleteQuestionById(String questionId)throws Exception{
        boolean output = questions.deleteQuestion(questionId);
        if (output){
            return "Eliminado";
        }
        else{
            throw new GameExceptions("No se encontro la id");
        }
    }
    public String updateQuestion(String questionId,Question updatedQuestion) throws Exception{
        try {
            questions.updateQuestion(questionId,updatedQuestion);
            return "Pregunta actualizada";
        } catch (GameExceptions e) {
            throw new GameExceptions(e.getMessage());
        }
    }
    public List<Question> getAll(){
        return questions.getAll();
    }
    public Question getQuestionById(String questionId) throws Exception{
        try {
            return questions.getQuestionById(questionId);
        } catch (GameExceptions e) {
            throw new GameExceptions(e.getMessage());
        }
    }
}

