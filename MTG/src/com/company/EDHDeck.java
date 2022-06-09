package com.company;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class EDHDeck {
    Card[] decklist=new Card[91];

    EDHDeck(Card[]decklist){
        this.decklist=decklist;
    }

    public boolean isEligable(EDHDeck prospective){
        int nonbasicland=0;
        for(int i=0;i<prospective.decklist.length;i++){
            if(prospective.decklist[i].getType()!="Basic Land"){
                nonbasicland++;
            }
        }
        String[] nonbasiclands=new String[nonbasicland];
        int i=0;
        for(Card c: prospective.decklist){
            if(c.getType()!="Basic Land"){
                nonbasiclands[i]=c.getName();
                i++;
            }
        }
        Set<String> cardlist = new HashSet<String>(Arrays.asList(nonbasiclands));
        return(cardlist.size()==nonbasiclands.length);
    }
}
