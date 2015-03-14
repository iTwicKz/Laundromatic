package com.example.juliantolentino.laundromatic_2.Clothes;

import java.util.*;

public class Clothes {
    private int type; //int reference
    private int color; // int reference
    String name;
    int timesWorn;
    float price;
    Date lastWorn;
    int sweatValue;
    int washing;
    int drying;
    //int ironing;
    //int dryCleaning;

    int getType(){
        return type;
    }

    void setType(int type){
        this.type = type;
    }

    int getColor(){
        return color;
    }

    void setColor(int color){
        this.color = color;
    }

    String getName(){
        return name;
    }

    void setName(String name){
        this.name = name;
    }

    int getTimesWorn(){
        return timesWorn;
    }

    void hasBeenWorn(){
        this.timesWorn++;
    }

    float getPrice(){
        return price;
    }

    void setPrice(float price){
        this.price = price;
    }

    void justWorn(){
        lastWorn = new Date();
    }

    int getTimeSinceWash(){
        Date current = new Date();
        return lastWorn.compareTo(current);
    }

    int getSweatValue(){
        return sweatValue;
    }

    void setSweatValue(int sweatValue){
        this.sweatValue = sweatValue;
    }

    int getWashing(){
        return washing;
    }

    void setWashVar(int washing){
        this.washing = washing;
    }

    int getDrying(){
        return drying;
    }

    void setDryVar(int drying){
        this.drying = drying;
    }
}
