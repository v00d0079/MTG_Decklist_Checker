package com.company;
import java.io.*;
import java.util.Scanner;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here

        File edhdecklist = new File("src\\test.txt");
        File standecklist = new File("src\\test2.txt");
        Card[] mm;
        JSONParser parser=new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("src\\default-cards-20220531090359.json"));
        String ln,bannedcard="";
        int cardcnt = 0;

            System.out.println("Enter Desired Format for Your Deck Check!");
            System.out.println("1:Standard 2:Modern 3:Legacy 4:Vintage 5:Commander 6:Pioneer 7:Pauper 0:Exit");
            Scanner sc = new Scanner(System.in);
            int format = sc.nextInt();
            switch (format){
                default -> System.out.println("You did not enter a valid format. Please Try Again!");
                case 0->exit(3);
                case 1->{
                    BufferedReader br = new BufferedReader(new FileReader(standecklist));
                    while ((ln = br.readLine()) != null) {
                        cardcnt += Integer.parseInt(ln.substring(0, ln.indexOf(' ')));
                        System.out.println(cardcnt);
                    }
                    if (cardcnt < 60) {
                        System.out.println("There are not enough cards for the Standard format!");
                        exit(-2);
                    }
                    br.close();
                    mm=new Card[cardcnt];
                    br = new BufferedReader(new FileReader(standecklist));
                    int i=0;
                    while ((ln = br.readLine()) != null) {
                        String cardname = ln.substring(ln.indexOf(" ")+1);
                        int numof = Integer.parseInt(ln.substring(0,ln.indexOf(" ")));
                        System.out.println(numof);
                        for (Object o : a) {
                            JSONObject card = (JSONObject) o;
                            String name = (String) card.get("name");
                            JSONObject leg = (JSONObject) card.get("legalities");
                            String isitlegal = (String) leg.get("standard");
                            if (name.equals(cardname)) {
                                if(isitlegal.equals("banned")||isitlegal.equals("not_legal")){
                                    bannedcard=bannedcard+name+"//";
                                }
                                if (numof > 1) {
                                    for (int j = 0; j < numof; j++) {
                                        mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"),isitlegal);
                                        i++;
                                    }
                                } else {
                                    mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"), isitlegal);
                                    //System.out.println((int) card.get("power")+" "+(int) card.get("toughness"));
                                    //System.out.println((String) card.get("name") +" "+(String) card.get("lang") +" "+ (String) card.get("mana_cost")+" " + (double) card.get("cmc")+" " + (String) card.get("type_line") +" "+ (String) card.get("oracle_text"));
                                    //System.out.println();
                                    i++;
                                }
                                break;
                            }else if(name.contains(cardname)&&!(name.equals(cardname+" // "+cardname))){
                                if(isitlegal.equals("banned")||isitlegal.equals("not_legal")){
                                    bannedcard=bannedcard+name+"//";
                                }
                                if (numof > 1) {
                                    for (int j = 0; j < numof; j++) {
                                        mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"),isitlegal);
                                        i++;
                                    }
                                } else {
                                    mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"), isitlegal);
                                    //System.out.println((int) card.get("power")+" "+(int) card.get("toughness"));
                                    //System.out.println((String) card.get("name") +" "+(String) card.get("lang") +" "+ (String) card.get("mana_cost")+" " + (double) card.get("cmc")+" " + (String) card.get("type_line") +" "+ (String) card.get("oracle_text"));
                                    //System.out.println();
                                    i++;
                                }
                                break;
                            }
                        }
                    }
                    StandardDeck deck = new StandardDeck(mm);
                    if(bannedcard!=""){
                        System.out.println("This is NOT a legal deck list for the Standard Format");
                        System.out.println("There are Banned or nonlegal cards in your deck list including\n"+bannedcard);
                        break;
                    }
                   switch (deck.isEligible(deck) ? 1 : 0) {
                        case 1 -> System.out.println("This is a legal deck list for the Standard Format\n");
                        case 0 -> System.out.println("This is NOT a legal deck list for the Standard Format\n");
                    }
                   // for (Card c : mm) {
                    //    c.printCard();
                   // }*/
                }
                case 2->{}
                case 3->{}
                case 4->{}
                case 5->{
                    BufferedReader br = new BufferedReader(new FileReader(edhdecklist));
                    mm=new Card[100];
                    while ((ln = br.readLine()) != null) {
                        cardcnt += Integer.parseInt(ln.substring(0, ln.indexOf(' ')));
                        if (cardcnt > 100) {
                            System.out.println("There are too many cards for the Commander format!");
                            exit(-1);
                        }
                    }
                    br.close();
                    br = new BufferedReader(new FileReader(edhdecklist));
                    int i=0;
                    while ((ln = br.readLine()) != null) {
                        String cardname = ln.substring(ln.indexOf(" ")+1);
                        int numof = Integer.parseInt(ln.substring(0,ln.indexOf(" ")));
                        for (Object o : a) {
                            JSONObject card = (JSONObject) o;
                            String name = (String) card.get("name");
                            JSONObject leg = (JSONObject) card.get("legalities");
                            String isitlegal = (String) leg.get("commander");
                            if (name.equals(cardname)) {
                                if(isitlegal.equals("banned")||isitlegal.equals("not_legal")){
                                    bannedcard=bannedcard+name+"//";
                                }
                                if (numof > 1) {
                                    for (int j = 0; j < numof; j++) {
                                        mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"),isitlegal);
                                        i++;
                                    }
                                } else {
                                    mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"),isitlegal);
                                    //System.out.println((int) card.get("power")+" "+(int) card.get("toughness"));
                                    //System.out.println((String) card.get("name") +" "+(String) card.get("lang") +" "+ (String) card.get("mana_cost")+" " + (double) card.get("cmc")+" " + (String) card.get("type_line") +" "+ (String) card.get("oracle_text"));
                                    //System.out.println();
                                    i++;
                                }
                                break;
                            }else if(name.contains(cardname)&&!(name.equals(cardname+" // "+cardname))){
                                if(isitlegal.equals("banned")||isitlegal.equals("not_legal")){
                                    bannedcard=bannedcard+name+"//";
                                }
                                if (numof > 1) {
                                    for (int j = 0; j < numof; j++) {
                                        mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"),isitlegal);
                                        i++;
                                    }
                                } else {
                                    mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"), isitlegal);
                                    //System.out.println((int) card.get("power")+" "+(int) card.get("toughness"));
                                    //System.out.println((String) card.get("name") +" "+(String) card.get("lang") +" "+ (String) card.get("mana_cost")+" " + (double) card.get("cmc")+" " + (String) card.get("type_line") +" "+ (String) card.get("oracle_text"));
                                    //System.out.println();
                                    i++;
                                }
                                break;
                            }
                        }
                    }
                    EDHDeck deck = new EDHDeck(mm);
                    if(bannedcard!=""){
                        System.out.println("This is NOT a legal deck list for the Commander Format");
                        System.out.println("There are Banned or nonlegal cards in your deck list including\n"+bannedcard);
                        break;
                    }
                    switch (deck.isEligable(deck) ? 1 : 0) {
                        case 1 -> System.out.println("This is a legal deck list for the Commander Format\n");
                        case 0 -> System.out.println("This is NOT a legal deck list for the Commander Format\n");
                    }
                    for (Card c : mm) {
                        c.printCard();
                    }
                }
                case 6->{}
                case 7->{}

            }
       }
       //while((ln=br.readLine())!=null) {
       //    System.out.println(ln.substring(2, ln.length()));
       //}
    }
