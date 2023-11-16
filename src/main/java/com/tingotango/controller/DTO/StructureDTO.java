package com.tingotango.controller.DTO;

import com.tingotango.model.Kid;
import com.tingotango.model.Question;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StructureDTO {
    private Kid kidData;
    private Question questionData;
}
