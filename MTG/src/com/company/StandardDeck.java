package com.company;

import org.jetbrains.annotations.NotNull;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public class StandardDeck {
    Card[] decklist;

    StandardDeck(Card[] decklist){
        this.decklist=decklist.clone();
    }

    public Boolean isEligible(@NotNull StandardDeck prospect){
        HashMap<String,Integer> map=new HashMap<String,Integer>();
        int count;
        for(Card c: prospect.decklist){
            if(c.getType().contains("Basic Land")!=true) {
                if (map.get(c.getName()) == null) {
                    map.put(c.getName(), 1);
                } else {
                    count = map.get(c.getName());
                    count++;
                    map.put(c.getName(), count);
                }
            }
        }
        Set<String> keys = map.keySet();
        Iterator s = keys.iterator();
        while(s.hasNext()){
            String c= (String) s.next();
            if(map.get(c).intValue()>4) {
                return false;
            }
        }
        return true;
    }

}
