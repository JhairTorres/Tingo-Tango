package com.tingotango.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class Question {
    private String question;
    private List<String> options;
    private Byte correctPos;
    private String id;
    private City questionCity;
}
