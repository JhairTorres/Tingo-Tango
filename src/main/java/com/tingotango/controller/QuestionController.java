package com.tingotango.controller;

import com.tingotango.controller.DTO.ResponseDTO;
import com.tingotango.exceptions.GameExceptions;
import com.tingotango.model.Question;
import com.tingotango.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "questionlist")
public class QuestionController {
    @Autowired
    private QuestionService questionListService;

    @GetMapping
    public ResponseEntity<ResponseDTO> getAll(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                questionListService.getAll(),null),HttpStatus.OK);
    }
    @GetMapping(path="/getquestionbyid/{questionid}")
    public ResponseEntity<ResponseDTO> getQuestionById(@PathVariable String questionid){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    questionListService.getQuestionById(questionid),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @PutMapping(path = "/updatequestion/{questionid}")
    public ResponseEntity<ResponseDTO> updateQuestion(@PathVariable String questionid, @RequestBody Question updatedQuestion){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    questionListService.updateQuestion(questionid,updatedQuestion),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @DeleteMapping(path = "/deletequestionbyid/{questionid}")
    public ResponseEntity<ResponseDTO> deleteQuestionById(@PathVariable String questionid){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    questionListService.deleteQuestionById(questionid),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @PostMapping(path = "/addquestion")
    public ResponseEntity<ResponseDTO> addQuestion(@RequestBody Question newQuestion){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(), questionListService.addNewQuestion(newQuestion),null),HttpStatus.OK);
    }
}

