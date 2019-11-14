package com.map524s1a.snakegame;

import android.view.MotionEvent;

import java.util.LinkedList;
import java.util.List;

public class Snake {
    public List<SnakeBody> snakeBody;
    public SnakeBody snakeHead;
    private int length;
    private static String direction;

    //Constructor
    Snake(double x, double y, double partSize){
        length = 3;
        snakeBody = new LinkedList<SnakeBody>();
        snakeHead = new SnakeBody(x, y);
        snakeBody.add(0, new SnakeBody(x, y + partSize));
        snakeBody.add(1, new SnakeBody(x, y + partSize + partSize));
        direction = "UP";
    }

    //methods
    private SnakeBody getHeadLocation(){
        return snakeHead;
    }

    public void growSnake(){
        SnakeBody temp = snakeBody.get(snakeBody.size() - 1);
        snakeBody.add(length - 2, new SnakeBody(temp.getX()
                , temp.getY()));
        length++;
        System.out.println("Body part was added!!\n" + snakeBody.size() + "\n" + length);
    }

    public static void updateDirection(String dir){
        direction = dir;
    }

    public void updatePositions(double partSize){
        SnakeBody first = new SnakeBody(snakeBody.get(0).getX(), snakeBody.get(0).getY());
        snakeBody.get(0).setX(snakeHead.getX());
        snakeBody.get(0).setY(snakeHead.getY());
        switch (direction) {
            case "UP":
                snakeHead.setY(snakeHead.getY() + partSize);
                break;

            case "DOWN":
                snakeHead.setY(snakeHead.getY() - partSize);
                break;

            case "LEFT":
                snakeHead.setX(snakeHead.getX() - partSize);
                break;

            case "RIGHT":
                snakeHead.setX(snakeHead.getX() + partSize);
                break;
        }
        for(int i = snakeBody.size() - 1; i >= 1; i--){
            SnakeBody temp = snakeBody.get(i);
            temp.setX(snakeBody.get(i - 1).getX());
            temp.setY(snakeBody.get(i - 1).getY());
        }
        snakeBody.get(0).setX(first.getX());
        snakeBody.get(0).setY(first.getY());
    }

    public int getLength(){
        return length;
    }

}

