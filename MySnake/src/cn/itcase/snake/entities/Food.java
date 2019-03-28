package cn.itcase.snake.entities;

import cn.itcase.snake.util.Global;

import java.awt.*;

public class Food extends Point {
    public boolean isSnakeEatFood(Snake snake) {

       return this.equals(snake.getHead());

    }

    public boolean isSnakeEatFood(Snake2 snake2) {

        return this.equals(snake2.getHead());

    }
    public void newFood(Point p){
        this.setLocation(p);
    }

    public void drawMe(Graphics g) {
        g.setColor(new Color(0x00ff00));
        g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
//        g.fill3DRect((x+1) * Global.CELL_SIZE, (y+1) * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
    }
}
