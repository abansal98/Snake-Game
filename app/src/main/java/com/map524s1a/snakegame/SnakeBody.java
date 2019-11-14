package com.map524s1a.snakegame;

import java.util.List;

public class SnakeBody {
    private double positionX;
    private double positionY;

    SnakeBody(double x, double y){
        positionX = x;
        positionY = y;
    }

    public double getX() {
        return positionX;
    }

    public double getY() {
        return positionY;
    }

    public void setX(double x) {
        positionX = x;
    }

    public void setY(double y) {
        positionY = y;
    }
}