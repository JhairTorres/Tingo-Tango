package com.tingotango.service;
import com.tingotango.controller.DTO.StructureDTO;
import com.tingotango.exceptions.GameExceptions;
import com.tingotango.model.Game;
import com.tingotango.model.Kid;
import com.tingotango.model.NodeDeCircular;
import com.tingotango.model.Question;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Random;
import java.io.*;
@Data
@Service
public class GameService {
    // Dependencies
    private final QuestionService questionService;
    private final ListCircuService listDEService;
    //Tingo Tango object
    private Game game;
    @Autowired
    public GameService(QuestionService questionService,ListCircuService listDEService){
        this.listDEService = listDEService;
        this.questionService = questionService;

        this.game = new Game(listDEService.getKids(),
                questionService.getAll(),false,false,
                null,null,null);
    }

    public String addNewQuestion (Question newQuestion){
        questionService.addNewQuestion(newQuestion);
        return "Pregunta adicionada";
    }

    public String deleteQuestionById(String questionId) throws GameExceptions{
        if(!game.isGameState()){
            try {
                return questionService.deleteQuestionById(questionId);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        else{
            throw new GameExceptions("No se puede eliminar una pregunta si el juego esta en curso");
        }
    }

    public String updateQuestionById(String questionId,Question updQuestion)throws GameExceptions{
        if (!game.isGameState()) {
            try {
                return questionService.updateQuestion(questionId, updQuestion);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            throw new GameExceptions("No se puede actualizar una pregutna con el juego en curso");
        }
    }

    public List<Question> getQuestions(){
        return questionService.getAll();
    }

    public Question getQuestionById(String questionId) throws GameExceptions{
        try {
            return questionService.getQuestionById(questionId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String addKidToEnd (Kid kid){
        return listDEService.addToEnd(kid);
    }

    public String addToStart (Kid kid){
        return listDEService.addToStart(kid);
    }

    public String deleteKidInPos(int pos)throws GameExceptions{
        if(!game.isGameState()) {
            try {
                return listDEService.deleteInPos(pos);
            } catch (GameExceptions e) {
                throw new GameExceptions(e.getMessage());
            }
        }
        else {
            throw new GameExceptions("No se puede eliminar un participante " +
                    "mientras el juego esta en curso");
        }
    }

    public List<Kid> getAllKids () throws GameExceptions{
        try {
            return listDEService.getAll();
        } catch (GameExceptions e) {
            throw new GameExceptions(e.getMessage());
        }
    }

    public String moveKid (int pos, String kidId) throws GameExceptions{
        if (game.getAwaitingKid()==null || !kidId.equals(game.getAwaitingKid().getId())) {
            try {
                listDEService.moveKid(pos,kidId);
                return "Niño movido";
            } catch (GameExceptions e) {
                throw new GameExceptions(e.getMessage());
            }
        } else {
            throw new GameExceptions("No se puede mover puesto que no se ha respondido");
        }
    }

    public StructureDTO roleGame(String direction) throws GameExceptions{
        if(listDEService.getKids().getSize()==1){
            game.setGameState(false);
            throw new GameExceptions("El juego termino puesto que solo hay un participante"+ game.getAwaitingNode().getData().getName()+"Gano el juego");
        }

        else if(game.getAwaitingKid()==null) {
            Random rand = new Random();
            int randomPosition = rand.nextInt(2000);
            int actualKidPosition = randomPosition % listDEService.getKids().getSize();
            int actualQuestionPos = randomPosition % questionService.getAll().size();

            NodeDeCircular temp;
            if (game.getAwaitingKid() == null) {
                temp = listDEService.getKids().getHead();
            } else {
                temp = game.getAwaitingNode();
            }
            while (actualKidPosition > 0) {
                if(direction.equals("r")){
                    temp = temp.getNext();
                }
                else if(direction.equals("l")){
                    temp = temp.getPrevious();
                }
                actualKidPosition--;
            }
            Question question = questionService.getAll().get(actualQuestionPos);
            Question newQuestion = new Question(question.getQuestion(),question.getOptions(),
                    null,question.getId());

            game.setAnswerState(true);
            game.setAwaitingKid(temp.getData());
            game.setGameState(true);
            game.setAwaitingNode(temp);
            game.setAwaitingQuestion(new StructureDTO(temp.getData(),newQuestion));

            return new StructureDTO(temp.getData(), newQuestion);
        }
        else {
            throw new GameExceptions("No se puede jugar si todavia no se ha respondido");
        }

    }
    public StructureDTO getQuestion (){
        return game.getAwaitingQuestion();
    }

    public String answerQuestion(StructureDTO response)throws GameExceptions{

        if (game.getAwaitingQuestion()==null){
            throw new GameExceptions("No hay una pregunta por responder");
        }
        else if(response.getKidData().getId().equals(game.getAwaitingQuestion().getKidData().getId())){

            Question question = null;
            try {
                question = questionService.getQuestionById(response.getQuestionData().getId());
            } catch (Exception e) {
                throw new RuntimeException(e);
            }

            if(question.getCorrectPos().equals(response.getQuestionData().getCorrectPos())){
                game.setAnswerState(false);
                game.setAwaitingKid(null);
                String name = game.getAwaitingQuestion().getKidData().getName();
                game.setAwaitingQuestion(null);
                return "Respuesta correcta, continua " + name;
            }
            else {
                //Change parameters
                game.setAwaitingNode(game.getAwaitingNode().getNext());
                game.setAnswerState(false);
                listDEService.getKids().deleteById(game.getAwaitingKid().getId());
                game.setAwaitingKid(null);
                game.setAwaitingQuestion(null);
                return "Jugador eliminado continua " +game.getAwaitingNode().getData().getName();
            }
        }
        else{
            throw new GameExceptions("Debe respodner el jugador preguntado");
        }
    }

}