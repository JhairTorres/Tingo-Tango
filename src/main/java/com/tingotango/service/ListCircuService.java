package com.tingotango.service;

import com.tingotango.exceptions.GameExceptions;
import com.tingotango.model.Kid;
import com.tingotango.model.ListDECircular;
import com.tingotango.model.NodeDeCircular;
import lombok.Data;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
public class ListCircuService {
    private ListDECircular kids;
    public ListCircuService(){
        kids = new ListDECircular();
        kids.addToEnd(new Kid("John Jaime","01"));
        kids.addToEnd(new Kid("Jhair Torres","02"));
        kids.addToEnd(new Kid("Sergio Sneyder","03"));
        kids.addToEnd(new Kid("Valeria Osorio","04"));
        kids.addToStart(new Kid("Rugeles","05"));
    }

    public List<Kid> getAll() throws GameExceptions{
        try {
            return kids.getAll();
        } catch (GameExceptions e) {
            throw new GameExceptions(e.getMessage());
        }
    }
    public String deleteInPos(int pos) throws GameExceptions{
        try {
            kids.deleteByPos(pos);
            return "Eliminado";
        } catch (GameExceptions e) {
            throw new GameExceptions(e.getMessage());
        }
    }
    public String insertInPos(int pos, Kid kid){
        kids.insertInPos(pos,kid);
        return "Adicionado";
    }
    public String addToEnd(Kid kid) {
        kids.addToEnd(kid);
        return "Adicionado";
    }

    public String addToStart(Kid kid){
        kids.addToStart(kid);
        return "Adicionado";
    }

    public String moveKid(int pos, String kidId) throws GameExceptions{
        try {
            kids.moveKid(pos,kidId);
            return "Niño movido";
        } catch (GameExceptions e) {
            throw new GameExceptions(e.getMessage());
        }
    }
}