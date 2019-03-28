package cn.itcase.snake.view;

import cn.itcase.snake.entities.Food;
import cn.itcase.snake.entities.Ground;
import cn.itcase.snake.entities.Snake;
import cn.itcase.snake.entities.Snake2;
import cn.itcase.snake.util.Global;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel {

    private Snake snake;
    private Snake2 snake2;
    private Food food;
    private Ground ground;

    public void display(Snake snake, Food food, Ground ground) {

        this.snake = snake;
        this.food = food;
        this.ground = ground;

//        System.out.println("display GamePanel");
        this.repaint();
    }

    public void display(Snake2 snake2, Food food, Ground ground) {

        this.snake2 = snake2;
        this.food = food;
        this.ground = ground;

//        System.out.println("display GamePanel");
        this.repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {

        g.setColor(new Color(0xfcfcfc));
        g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE, Global.HEIGHT * Global.CELL_SIZE);
        if (snake != null && ground != null && food != null) {
            this.snake2.drawMe(g);
//            this.ground.drawMe(g);//显示石头
            this.snake.drawMe(g);
            this.food.drawMe(g);
        }
    }


}
