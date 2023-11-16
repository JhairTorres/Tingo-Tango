package com.tingotango.model;
import com.tingotango.exceptions.GameExceptions;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data

public class ListQuestion {
    private List<Question> questionList;

    public ListQuestion(){
        questionList = new ArrayList<>();
    }
    public List<Question> getAll (){
        return questionList;
    }

    public Question getQuestionById (String questionId) throws Exception {
        for(Question question :questionList){
            if(question.getId().equals(questionId)){
                return question;
            }
        }
        throw new GameExceptions("No hay una pregunta con esa id");
    }
    public void addQuestion(Question newQuestion){
        questionList.add(newQuestion);
    }
    public boolean deleteQuestion(String questionId){
        return questionList.removeIf(question -> question.getId().equals(questionId));
    }
    public void updateQuestion(String questionId, Question updatedQuestion) throws GameExceptions{
        boolean matchFound = false;
        for (int i = 0; i < questionList.size(); i++) {
            if (questionList.get(i).getId().equals(questionId)) {
                questionList.set(i, updatedQuestion);
                matchFound = true;
                break;
            }
        }
        if(!matchFound) {
            throw new GameExceptions("La id es incorrecta o no se encontro");
        }
    }

}

