package com.company;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import static java.lang.System.exit;

public class Main {

    public static void main(String[] args) throws Exception {
	// write your code here
        File decklist = new File("src\\test.txt");
        Card[] mm=new Card[100];
        BufferedReader br = new BufferedReader(new FileReader(decklist));
        JSONParser parser=new JSONParser();
        JSONArray a = (JSONArray) parser.parse(new FileReader("src\\default-cards-20220531090359.json"));
        String ln;
        int i=0,cardcnt = 0;

        System.out.println("Fuck this stupid asss game\n");

        while((ln=br.readLine())!=null){
            cardcnt+=Integer.parseInt(ln.substring(0,ln.indexOf(' ')));
            if(cardcnt>100){
                System.out.println("There are too many cards for the specified format!");
                exit(-1);
            }
        }
        System.out.println("Number of Cards in deck: "+cardcnt);
        br.close();
        br=new BufferedReader(new FileReader(decklist));
        while((ln= br.readLine())!=null) {
            String cardname = ln.substring(2, ln.length());
            int numof = Character.getNumericValue(ln.charAt(0));
            for (Object o : a) {
                JSONObject card = (JSONObject) o;
                String name = (String) card.get("name");
                if (name.equals(cardname)) {
                    if(numof>1){
                        for(int j=0;j<numof;j++) {
                            System.out.println("Num of = "+numof);
                            mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"));
                            i++;
                        }
                    }else {
                        mm[i] = new Card((String) card.get("name"), (String) card.get("type_line"), (String) card.get("power"), (String) card.get("toughness"), (String) card.get("mana_cost"), (double) card.get("cmc"), (String) card.get("oracle_text"));
                        //System.out.println((int) card.get("power")+" "+(int) card.get("toughness"));
                        //System.out.println((String) card.get("name") +" "+(String) card.get("lang") +" "+ (String) card.get("mana_cost")+" " + (double) card.get("cmc")+" " + (String) card.get("type_line") +" "+ (String) card.get("oracle_text"));
                        //System.out.println();
                        i++;
                    }
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
