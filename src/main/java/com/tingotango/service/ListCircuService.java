package com.tingotango.service;
import com.tingotango.exceptions.GameExceptions;
import com.tingotango.model.City;
import com.tingotango.model.Kid;
import com.tingotango.model.ListDECircular;
import com.tingotango.model.NodeDeCircular;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Data
@Service
public class ListCircuService {
    private ListDECircular kids;
    @Autowired
    private CityService cityService;
    public ListCircuService(){
        kids = new ListDECircular();
        kids.addToEnd(new Kid("John Jaime","01",new City("Manizales","04")));
        kids.addToEnd(new Kid("Jhair Torres","02",new City("Florencia","03")));
        kids.addToEnd(new Kid("Sergio Sneyder","03",new City("Pitalito","01")));
        kids.addToEnd(new Kid("Valeria Osorio","04",new City("Pereira","02")));
        kids.addToStart(new Kid("Rugeles","05",new City("Manizales","04")));
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
    public String insertInPos(int pos, Kid kid) throws GameExceptions{

        try {
            cityService.getCityById(kid.getKidCity().getCityId());
            kids.insertInPos(pos,kid);
            return "Adicionado";
        } catch (GameExceptions e) {
            throw new GameExceptions("La ciudad ingresada no se encuentra en la lista");
        }


    }
    public String addToEnd(Kid kid)throws GameExceptions{
        try {
            cityService.getCityById(kid.getKidCity().getCityId());
            kids.addToEnd(kid);
            return "Adicionado";
        } catch (GameExceptions e) {
            throw new GameExceptions("la ciudad ingresada no se encuentra en la lista global");
        }
    }

    public String addToStart(Kid kid) throws GameExceptions{
        try {
            cityService.getCityById(kid.getKidCity().getCityId());
            kids.addToStart(kid);
            return "Adicionado";
        } catch (GameExceptions e) {
            throw new GameExceptions("La ciudad ingresada no esta en lista global");
        }
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