package com.tingotango.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ListQuestion {
    private String question;
    private String poscorrecta;
    private List<String> opciones;
}
