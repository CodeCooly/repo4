package cn.itcase.snake.thread;

import cn.itcase.snake.entities.Snake;
import cn.itcase.snake.entities.Snake2;
import cn.itcase.snake.listener.SnakeListener;

import java.util.HashSet;
import java.util.Set;

public class SnakeDriver implements Runnable {
    private Snake snake;
    private Snake2 snake2;
    private Set<SnakeListener> listeners = new HashSet<SnakeListener>();


    public SnakeDriver(Snake snake , Snake2 snake2){
        this.snake = snake;
        this.snake2 = snake2;

    }

    Object o = new Object();
    private boolean life;
    @Override
    public void run() {
        while (life) {
            synchronized (o) {
                snake.move();
                snake2.move();
                for (SnakeListener l : listeners) {
//                    l.snakeMoved(Snake.this);
                }

                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
