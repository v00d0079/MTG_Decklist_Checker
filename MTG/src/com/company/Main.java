package com.company;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;

import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        System.out.println("Fuck this stupid asss game\n");
       /* Card mnt = new Card("Mountain", "Basic Land",0,0,"{}",0,"tap for 1 {R} mana");
        Card gob = new Card("Torbran, Thane of Red Fell","Creature", 2,4,"{1}{R}{R}{R}",4,"if a red source you control would deal damage to an opponent or a permanent an opponent controls, it deals that much damage plus 2 instead.");
        Card[] mm=new Card[99];
        mm[0] = new Card("Torbran, Thane of Red Fell","Creature", 2,4,"{1}{R}{R}{R}",4,"if a red source you control would deal damage to an opponent or a permanent an opponent controls, it deals that much damage plus 2 instead.");
        for(int i=1; i< mm.length-1; i++){
            mm[i]=mnt;
        }
        mm[98] = new Card("Torbran, Thane of Red Fell","Creature", 2,4,"{1}{R}{R}{R}",4,"if a red source you control would deal damage to an opponent or a permanent an opponent controls, it deals that much damage plus 2 instead.");
        EDHDeck test= new EDHDeck(gob,mm);
        test.commander.printCard();
        for(Card c: mm){
            c.printCard();
        }

        System.out.println("The deck given is eligable for the format "+ test.isEligable(test));
        */
        File decklist = new File("src\\test.txt");
        Card[] mm=new Card[91];
        BufferedReader br = new BufferedReader(new FileReader(decklist));
        JSONParser parser=new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("src\\default-cards-20220531090359.json"));
        String ln;
        int i=0;
        while((ln= br.readLine())!=null) {
            String sort = ln.substring(2, ln.length());
            for (Object o : a) {
                JSONObject card = (JSONObject) o;
                String name = (String) card.get("name");
                if (name.equals(sort)) {
                   mm[i]=new Card((String) card.get("name"),(String) card.get("type_line"),(String) card.get("power"),(String) card.get("toughness"),(String) card.get("mana_cost"),(double) card.get("cmc"),(String) card.get("oracle_text"));
                    //System.out.println((int) card.get("power")+" "+(int) card.get("toughness"));
                    //System.out.println((String) card.get("name") +" "+(String) card.get("lang") +" "+ (String) card.get("mana_cost")+" " + (double) card.get("cmc")+" " + (String) card.get("type_line") +" "+ (String) card.get("oracle_text"));
                    //System.out.println();
                    i++;
                    break;
                }
            }
        }
        for(Card c:mm){
            c.printCard();
        }
        EDHDeck test= new EDHDeck(mm);
        int res= test.isEligable(test)? 1:0;
        switch(res){
            case 1:
                System.out.println("This is an eligable deck list for the EDH Format");
                break;
            case 0:
                System.out.println("This is NOT an eligable deck list for the EDH Format");
                break;
        }

       }
       //while((ln=br.readLine())!=null) {
       //    System.out.println(ln.substring(2, ln.length()));
       //}
    }
