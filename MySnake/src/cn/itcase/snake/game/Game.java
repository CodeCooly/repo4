package cn.itcase.snake.game;

import cn.itcase.snake.controller.Controller2;
import cn.itcase.snake.controller.Controller3;
import cn.itcase.snake.entities.Food;
import cn.itcase.snake.entities.Ground;
import cn.itcase.snake.entities.Snake;
import cn.itcase.snake.entities.Snake2;
import cn.itcase.snake.util.Global;
import cn.itcase.snake.view.GamePanel;

import javax.swing.*;
import java.awt.*;

public class Game {
    public static void main(String[] args) {
        Snake snake = new Snake();
        Snake2 snake2 = new Snake2();
        Food food = new Food();
        Ground ground = new Ground();
        GamePanel gamePanel = new GamePanel();
        Controller2 controller = new Controller2(snake2,snake, food, ground, gamePanel);


        javax.swing.JFrame frame = new JFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gamePanel.setSize(Global.WIDTH * Global.CELL_SIZE
                , Global.HEIGHT * Global.CELL_SIZE);
        frame.setSize(Global.WIDTH * Global.CELL_SIZE + 10
                , Global.HEIGHT * Global.CELL_SIZE + 35);
        frame.add(gamePanel, BorderLayout.CENTER);

        frame.addKeyListener(controller);

        gamePanel.addKeyListener(controller);
        snake.addSnakeListener(controller);
        snake2.addSnakeListener(controller);

        frame.setVisible(true);
        controller.newGame();


    }
}
