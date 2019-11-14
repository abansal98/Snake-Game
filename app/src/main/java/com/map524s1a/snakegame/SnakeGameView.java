package com.map524s1a.snakegame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

public class SnakeGameView extends View {

    private int height, width = 0;
    Snake snake;
    Paint paint;
    int partSize;
    Edibles edible;
    boolean inited;
    private long nextFrameTime;
    private long nextAgeFrameTime;
    ImageButton btnUp;
    ImageButton btnLeft;
    ImageButton btnRight;
    ImageButton btnDown;
    boolean uneaten;

    public SnakeGameView(Context context) {
        super(context);
        btnUp = findViewById(R.id.UP);
        btnDown = findViewById(R.id.DOWN);
        btnLeft = findViewById(R.id.LEFT);
        btnRight = findViewById(R.id.RIGHT);

        OnTouchListener directionListener = new OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN ) {
                    snake.updateDirection(v.getContentDescription().toString().toUpperCase());
                    System.out.println(v.getContentDescription());
                    return true;
                }

                return false;
            }
        };

        btnUp.setOnTouchListener(directionListener);
        btnDown.setOnTouchListener(directionListener);
        btnLeft.setOnTouchListener(directionListener);
        btnRight.setOnTouchListener(directionListener);
    }

    public SnakeGameView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SnakeGameView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private void initGame(){
        height = getHeight();
        width = getWidth();
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(20);
        partSize = width / 40 ;
        snake = new Snake(width/2, height/2, partSize);
        this.setViewParams(this.getRootView());
        inited = true;
        nextFrameTime = System.currentTimeMillis();
        nextAgeFrameTime = System.currentTimeMillis();
        uneaten = false;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if(!inited){
            initGame();
            inited = true;
        }
        if(updateRequired()){
            snake.updatePositions(partSize);
            checkBoundaryCollision();
            checkSelfCollision();
        }
        drawSnake(canvas);
        drawBoundary(canvas);
        if(!uneaten) {
            edible = new Edibles(width, width, partSize);
        }
        if(snakeAge()){
            snake.growSnake();
        }
        drawEdible(canvas);
        invalidate();
    }

    private void drawSnake(Canvas canvas){
        snake.getLength();
        Paint body = new Paint();
        body.setColor(Color.RED);
        body.setStyle(Paint.Style.FILL);

        Paint bodyOutline = new Paint();
        bodyOutline.setStyle(Paint.Style.STROKE);
        bodyOutline.setColor(Color.WHITE);

        canvas.drawRect((float)snake.snakeHead.getX(), (float)snake.snakeHead.getY() + partSize, (float)snake.snakeHead.getX() + partSize, (float) snake.snakeHead.getY(), body);

        for(int i = 0; i < snake.getLength() - 2; i++){
            SnakeBody temp = snake.snakeBody.get(i);
            Rect tempRect = new Rect();
            tempRect.set((int)temp.getX(), (int)temp.getY() + partSize, (int)temp.getX() + partSize, (int) temp.getY());
            canvas.drawRect(tempRect, body);
            //canvas.drawRect(tempRect, bodyOutline);
        }
    }

    public void setViewParams(View view){
        ViewGroup.LayoutParams params = view.getLayoutParams();
        params.width = view.getWidth() - 10;
        params.height = view.getWidth() - 10;
        view.setLayoutParams(params);
        Rect rect = new Rect();
        //ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(view.getWidth(), view.getWidth());
        //this.setLayoutParams(layoutParams);
    }

    public void drawBoundary(Canvas canvas){
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.BLACK);
        canvas.drawRect(this.getLeft(), this.getTop(),this.getRight(), this.getBottom(), paint);
    }

    private void drawEdible(Canvas canvas){
        Paint ediblePaint = new Paint();
        ediblePaint.setColor(Color.GREEN);
        ediblePaint.setStyle(Paint.Style.FILL);
        canvas.drawCircle((float)edible.getBobX(), (float)edible.getBobY(),5, paint);
        uneaten = true;
    }

    private void checkBoundaryCollision(){
        if(this.getLeft() == snake.snakeHead.getX() || this.getRight() == snake.snakeHead.getX() ||
        this.getTop() >= snake.snakeHead.getY() || this.getBottom() <= snake.snakeHead.getY()) {
            inited = false;
        }
    }

    private void checkSelfCollision(){
        for(int i = 0; i < snake.snakeBody.size(); i++){
            if(snake.snakeHead.getY() == snake.snakeBody.get(i).getY() &&
            snake.snakeHead.getX() == snake.snakeBody.get(i).getY()){
                inited = false;
                break;
            }
        }
    }

    public boolean updateRequired() {

        // Are we due to update the frame
        if(nextFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            nextFrameTime =System.currentTimeMillis() + 1000/5;

            // Return true so that the update and draw
            // functions are executed
            return true;
        }

        return false;
    }

    public boolean snakeAge() {

        // Are we due to update the frame
        if(nextAgeFrameTime <= System.currentTimeMillis()){
            // Tenth of a second has passed

            // Setup when the next update will be triggered
            nextAgeFrameTime =System.currentTimeMillis() + 2;

            // Return true so that the update and draw
            // functions are executed
            return true;
        }

        return false;
    }



}
