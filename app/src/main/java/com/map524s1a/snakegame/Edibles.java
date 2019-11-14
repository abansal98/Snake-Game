package com.map524s1a.snakegame;

import java.util.Random;

public class Edibles {
    private double bobX;
    private double bobY;

    public Edibles(int width, int height, int partSize) {
        Random random = new Random();
        bobX = random.nextInt((width - 1)/partSize) + 1;
        bobY = random.nextInt((height - 1)/partSize) + 1;
        bobX = bobX * partSize;
        bobY = bobY * partSize;
    }

    public double getBobX(){
        return bobX;
    }

    public double getBobY(){
        return bobY;
    }

}
