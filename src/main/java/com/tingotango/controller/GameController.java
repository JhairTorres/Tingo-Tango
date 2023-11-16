package com.tingotango.controller;

import com.tingotango.controller.DTO.ResponseDTO;
import com.tingotango.controller.DTO.StructureDTO;
import com.tingotango.exceptions.GameExceptions;
import com.tingotango.model.Kid;
import com.tingotango.model.Question;
import com.tingotango.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path="tingotango")
public class GameController {
    @Autowired
    private GameService tingoTangoService;

    @PostMapping(path = "/addquestion")
    public ResponseEntity<ResponseDTO> addQuestion(@RequestBody Question newQuestion){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                tingoTangoService.addNewQuestion(newQuestion),null),HttpStatus.OK);
    }

    @DeleteMapping(path = "/deletequestionbyid/{id}")
    public ResponseEntity<ResponseDTO> deleteQuestion(@PathVariable String id){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.deleteQuestionById(id),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @PutMapping(path = "/updatequestionbyid/{id}")
    public ResponseEntity<ResponseDTO> updateQuestionById(@PathVariable String id, @RequestBody Question newQuestion){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.updateQuestionById(id,newQuestion),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null,errors),HttpStatus.OK);
        }
    }

    @GetMapping(path = "/getquestions")
    public ResponseEntity<ResponseDTO> getQuestions(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                tingoTangoService.getQuestions(),null),HttpStatus.OK);
    }

    @GetMapping(path = "/getquestionbyid/{id}")
    public ResponseEntity<ResponseDTO> getQuestionById(@PathVariable String id){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.getQuestionById(id),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    null,errors),HttpStatus.OK);
        }
    }

    @GetMapping(path = "/getkids")
    public ResponseEntity<ResponseDTO> getAll(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.getAllKids(),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }

    @PutMapping(path = "/movekid/{pos}/{kidid}")
    public ResponseEntity<ResponseDTO> moveKid(@PathVariable int pos, @PathVariable String kidid){
        try{
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.moveKid(pos,kidid),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }

    @PostMapping(path = "/addkidtostart")
    public ResponseEntity<ResponseDTO> addToStart (@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                tingoTangoService.addToStart(kid),null),HttpStatus.OK);
    }

    @PostMapping(path = "/addkidtoend")
    public ResponseEntity<ResponseDTO> addToEnd(@RequestBody Kid kid){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                tingoTangoService.addKidToEnd(kid),null),HttpStatus.OK);
    }

    @GetMapping(path = "/rolegame")
    public ResponseEntity<ResponseDTO> roleGame(){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.roleGame(),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
    @GetMapping(path = "/getawaitingquestion")
    public ResponseEntity<ResponseDTO> getAwaitingQuestion(){
        return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                tingoTangoService.getQuestion(),null),HttpStatus.OK);
    }

    @GetMapping(path = "/answerquestion")
    public ResponseEntity<ResponseDTO> answerQuestion(@RequestBody StructureDTO response){

        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.answerQuestion(response),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }

    @DeleteMapping(path = "/deletekidinpos/{pos}")
    public ResponseEntity<ResponseDTO> deleteKidInPos(@PathVariable int pos){
        try {
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.OK.value(),
                    tingoTangoService.deleteKidInPos(pos),null),HttpStatus.OK);
        } catch (GameExceptions e) {
            List<String> errors = new ArrayList<>();
            errors.add(e.getMessage());
            return new ResponseEntity<>(new ResponseDTO(HttpStatus.BAD_REQUEST.value(),
                    null,errors),HttpStatus.OK);
        }
    }
}

