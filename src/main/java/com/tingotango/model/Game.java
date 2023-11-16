package com.tingotango.model;

import com.tingotango.controller.DTO.StructureDTO;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;
@Data
@AllArgsConstructor
public class Game {
    private ListDECircular participants;
    private List<Question> questions;
    private boolean gameState;
    private boolean answerState;
    private Kid awaitingKid;
    private NodeDeCircular awaitingNode;
    private StructureDTO awaitingQuestion;
}
