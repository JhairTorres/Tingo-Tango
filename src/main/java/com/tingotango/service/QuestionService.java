package com.tingotango.service;

import com.tingotango.exceptions.GameExceptions;
import com.tingotango.model.City;
import com.tingotango.model.ListQuestion;
import com.tingotango.model.Question;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
@Data
@Service
public class QuestionService {
    private ListQuestion questions;
    public QuestionService() {
        questions = new ListQuestion();

        try (BufferedReader reader = new BufferedReader(new FileReader("BancoDePreguntasreal.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split("\\|");

                String questionText = parts[0];
                String[] options = parts[1].split(",");
                Byte correctPos = Byte.parseByte(parts[2]);
                String id = parts[3];

                String[] cityInfo = parts[4].split(",");
                String cityName = cityInfo[0];
                String cityId = cityInfo[1];
                City questionCity = new City(cityName, cityId);

                Question question = new Question(questionText, Arrays.asList(options), correctPos, id, questionCity);
                questions.addQuestion(question);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    public String addNewQuestion(Question newQuestion){
        questions.addQuestion(newQuestion);
        return "Adicionado";
    }
    public String deleteQuestionById(String questionId)throws GameExceptions{
        boolean output = questions.deleteQuestion(questionId);
        if (output){
            return "Eliminado";
        }
        else{
            throw new GameExceptions("No se encontro la id");
        }
    }
    public String updateQuestion(String questionId,Question updatedQuestion) throws GameExceptions{
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
    public Question getQuestionById(String questionId) throws GameExceptions{
        try {
            return questions.getQuestionById(questionId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
