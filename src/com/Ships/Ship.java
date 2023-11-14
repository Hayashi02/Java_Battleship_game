package com.Ships;
import java.util.HashMap;

public class Ship {

    HashMap<String, Boolean> shipStatus = new HashMap<>();

    public void addCell(String cell){
        shipStatus.put(cell, false);
    }
    public String cellStatus(String in){
        if(isDestroyed() || shipStatus.get(in)) return "Miss";
        shipStatus.put(in, true);  
        return "Hit!";
    }
    public boolean isDestroyed(){
        if(shipStatus.containsValue(false)) return false;
        return true;
    }

    public boolean containsCell(String input){
        return shipStatus.containsKey(input);
    }
}
