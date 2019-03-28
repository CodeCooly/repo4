package cn.itcase.snake.listener;

import cn.itcase.snake.entities.Snake;
import cn.itcase.snake.entities.Snake2;

public interface SnakeListener {
    void snakeMoved(Snake snake);
    void snakeMoved(Snake2 snake);
}
