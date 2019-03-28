package cn.itcase.snake.controller;

import cn.itcase.snake.entities.Food;
import cn.itcase.snake.entities.Ground;
import cn.itcase.snake.entities.Snake;
import cn.itcase.snake.entities.Snake2;
import cn.itcase.snake.listener.SnakeListener;
import cn.itcase.snake.view.GamePanel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;


public class Controller3 extends KeyAdapter implements SnakeListener {

    private Snake snake;
    private Snake2 snake2;
    private Food food;
    private Ground ground;
    private GamePanel gamePanel;
    private ServerSocket serverSocket;

    public Controller3(Snake2 snake2, Snake snake, Food food, Ground ground, GamePanel gamePanel) {
        this.snake = snake;
        this.snake2 = snake2;
        this.food = food;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }


    @Override
    public void keyPressed(KeyEvent e) {

        try {
            serverSocket = new ServerSocket(6666);
            Socket socket = serverSocket.accept();
            InputStream inputStream = socket.getInputStream();
            OutputStream outputStream = socket.getOutputStream();
            int read = inputStream.read();
            switch (read) {
                case Snake2.VK_W:
                    snake2.changeDirection(Snake2.VK_W);
                    break;
                case Snake2.VK_A:
                    snake2.changeDirection(Snake2.VK_A);
                    break;
                case Snake2.VK_S:
                    snake2.changeDirection(Snake2.VK_S);
                    break;
                case Snake2.VK_D:
                    snake2.changeDirection(Snake2.VK_D);
                    break;
                default:
                    break;
            }


            snake2.snake = this.snake;
            snake.snake2 = this.snake2;


            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    snake.changeDirection(Snake.UP);
                    outputStream.write(Snake.UP);
                    outputStream.flush();

                    break;
                case KeyEvent.VK_DOWN:
                    snake.changeDirection(Snake.DOWN);
                    outputStream.write(Snake.DOWN);
                    outputStream.flush();
                    break;
                case KeyEvent.VK_LEFT:
                    snake.changeDirection(Snake.LEFT);
                    outputStream.write(Snake.LEFT);
                    outputStream.flush();
                    break;
                case KeyEvent.VK_RIGHT:
                    snake.changeDirection(Snake.RIGHT);
                    outputStream.write(Snake.RIGHT);
                    outputStream.flush();
                    break;
//                case KeyEvent.VK_F:
//                    snake2.changeDirection(Snake2.VK_F);
//                    snake.changeDirection(Snake.VK_F);
//                    break;
//                case KeyEvent.VK_G:
//                    snake2.changeDirection(Snake2.VK_G);
//                    snake.changeDirection(Snake.VK_G);
//                    break;
//            case KeyEvent.VK_W:
//                snake2.changeDirection(Snake2.VK_W);
//                break;
//            case KeyEvent.VK_A:
//                snake2.changeDirection(Snake2.VK_A);
//                break;
//            case KeyEvent.VK_S:
//                snake2.changeDirection(Snake2.VK_S);
//                break;
//            case KeyEvent.VK_D:
//                snake2.changeDirection(Snake2.VK_D);
//                break;
//                case KeyEvent.VK_T:
//                    snake2.changeDirection(Snake2.VK_T);
//                    snake.changeDirection(Snake.VK_T);
//                    break;
//                case KeyEvent.VK_K:
//                    snake2.changeDirection(Snake2.VK_K);
//                    snake.changeDirection(Snake.VK_K);
//                    break;
//                case KeyEvent.VK_J:
//                    newGame();
//                    break;
//                case KeyEvent.VK_0:
//                    Snake.SPEED = 10;
//                    Snake2.SPEED = 10;
//                    break;
//                case KeyEvent.VK_1:
//                    Snake.SPEED = 50;
//                    Snake2.SPEED = 50;
//                    break;
//                case KeyEvent.VK_2:
//                    Snake.SPEED = 100;
//                    Snake2.SPEED = 100;
//                    break;
//                case KeyEvent.VK_3:
//                    Snake.SPEED = 200;
//                    Snake2.SPEED = 200;
//                    break;
//                case KeyEvent.VK_4:
//                    Snake.SPEED = 300;
//                    Snake2.SPEED = 300;
//                    break;
//                case KeyEvent.VK_Q:
//                    Snake2.SPEED -= 10;
//                    if (Snake2.SPEED <= 0) {
//                        Snake2.SPEED = 10;
//                    }
//                    break;
//                case KeyEvent.VK_E:
//                    Snake2.SPEED += 10;
//                    break;
//                case KeyEvent.VK_O:
//                    Snake.SPEED -= 10;
//                    if (Snake.SPEED <= 0) {
//                        Snake.SPEED = 10;
//                    }
//                    break;
//                case KeyEvent.VK_P:
//                    Snake.SPEED += 10;
//                    break;


            }


        } catch (IOException e1) {
            e1.printStackTrace();
        }finally {
            try {
                serverSocket.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }


    }


    @Override
    public void snakeMoved(Snake snake) {

        if (food.isSnakeEatFood(snake)) {
            snake.eatFood();
            food.newFood(ground.getPoint());
        }
        if (ground.isSnakeEatRock(snake)) {
            snake.die();
            snake.init();

        }
        if (snake.isEatBody(snake2.getHead())) {
            snake2.die();
//            snake2.init();
            snake2.res();

        }

//        if(food.isSnakeEatFood(snake2)){
//            snake2.eatFood();
//            food.newFood(ground.getPoint());
//        }
//        if(ground.isSnakeEatRock(snake2)){
//            snake2.die();
//        }
//        if(snake.isEatBody(snake2)){
//            snake.die();
//        }

        gamePanel.display(snake, food, ground);
//        gamePanel.display(snake2, food, ground);
    }

    @Override
    public void snakeMoved(Snake2 snake2) {
        if (food.isSnakeEatFood(snake2)) {
            snake2.eatFood();
            food.newFood(ground.getPoint());
        }
        if (ground.isSnakeEatRock(snake2)) {
            snake2.die();
        }
        if (snake2.isEatBody(snake.getHead())) {
            snake.die();
            snake.res();
        }

        gamePanel.display(snake2, food, ground);
    }

    public void newGame() {
        snake2.start();
        snake.start();
        food.newFood(ground.getPoint());
    }

    public void newGame2() {
        food.newFood(ground.getPoint());
    }
}
