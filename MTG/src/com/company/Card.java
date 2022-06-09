package com.company;

public class Card {
    String name,type,manacost,cardtext,power,toughness;
    double totalcost;


    Card(String name,String type, String power, String toughness, String cost, double totalcost, String text){
        this.name =name;
        this.type =type;
        this.power=power;
        this.toughness=toughness;
        this.manacost=cost;
        this.totalcost=totalcost;
        this.cardtext=text;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getPower() {
        return power;
    }

    public String getToughness() {
        return toughness;
    }

    public String getManacost() {
        return manacost;
    }

    public double getTotalcost() {
        return totalcost;
    }

    public String getCardtext() {
        return cardtext;
    }

    public void printCard(){
        System.out.println("Name: "+getName()
                +" Type: "+getType()
                +" Power: "+ getPower()
                +" Toughness: "+getToughness()
                +" Mana Cost: "+getManacost()
                +" Converted Cost: "+getTotalcost()
                +" Effect: "+getCardtext()+"\n");
    }
}
