package cn.itcase.snake.entities;

import cn.itcase.snake.listener.SnakeListener;
import cn.itcase.snake.util.Global;

import java.awt.*;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Random;
import java.util.Set;

public class Snake2 {
    public static final int VK_W = -1;
    public static final int VK_S = 1;
    public static final int VK_A = 2;
    public static final int VK_D = -2;
    public static final int VK_F = 10;
    public static final int VK_G = 11;
    public static final int VK_T = 12;
    public static final int VK_K = 13;
    public static long SPEED = 50;
    private int oldDirection;
    private int newDirection;
    private boolean life;
    public Snake snake;


    Object o = new Object();

    public Snake2() {

        init();
    }

    public void init() {
        int x = Global.WIDTH / 2 + 2;
        int y = Global.HEIGHT / 2 + 2;

        for (int i = 0; i < 3; i++) {
            body.addLast(new Point(x--, y));
        }
        oldDirection = newDirection = VK_D;
        life = true;

    }

    private Point oldTail;
    private int direction;

    public LinkedList<Point> body = new LinkedList<Point>();

    private Set<SnakeListener> listeners = new HashSet<SnakeListener>();

    public Point getHead() {
        return body.getFirst();
    }

    public void move() {

//        if (!(newDirection + oldDirection == 0))
//            oldDirection = newDirection;

        oldTail = body.getLast();
        body.removeLast();
        int x = body.getFirst().x;
        int y = body.getFirst().y;

        switch (direction) {
            case VK_W:
                y--;
                if (y < 0) {
                    y = Global.HEIGHT - 1;
                }
                break;
            case VK_S:
                y++;
                if (y >= Global.HEIGHT) {
                    y = 0;
                }
                break;
            case VK_A:
                x--;
                if (x < 0) {
                    x = Global.WIDTH - 1;
                }
                break;
            case VK_D:
                x++;
                if (x >= Global.WIDTH) {
                    x = 0;
                }
                break;
            case VK_F:
                try {
                    o.wait(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    break;
                }
            case VK_G:
                o.notify();

                break;
            case VK_T:
                SPEED += 20;

                break;
            case VK_K:
                SPEED -= 20;

                if (SPEED <= 0) {
                    SPEED = 1000;
                }
                break;

        }
        Point newHead = new Point(x, y);

        body.addFirst(newHead);
//
//        for (Point point : body) {
//            if(point.equals(snake.getHead())){
//                snake.die();
//            }
//        }

    }

    public void changeDirection(int direction) {
//        if(!(this.direction+direction==0)){
//        newDirection = direction;
        this.direction = direction;
//        }
    }

    public void eatFood() {
//        System.out.println("snake's eatFood");

        body.addLast(oldTail);
    }

    public boolean isEatBody(Point head) {

        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(head)) {
                return true;
            }
        }
//        System.out.println("snake's isEatBody");
        return false;
    }

    public void drawMe(Graphics g) {
//        System.out.println("show shake's me");

        g.setColor(new Color(0xa4a4a4));
        for (Point p : body) {
            if (p.equals(body.getFirst())) {
                g.setColor(new Color(0xff00ba));
            } else {
                g.setColor(new Color(0xff0000));
            }

            g.fill3DRect(p.x * Global.WIDTH, p.y * Global.HEIGHT, Global.CELL_SIZE, Global.CELL_SIZE, true);
        }
    }

    private class SnakeDriver2 implements Runnable {

        @Override
        public void run() {
            while (life) {
                synchronized (o) {
                    move();
                    for (SnakeListener l : listeners) {
                        l.snakeMoved(Snake2.this);
                    }

                    try {
                        Thread.sleep(SPEED);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void start() {
        new Thread(new SnakeDriver2()).start();
    }

    public void addSnakeListener(SnakeListener l) {
        if (l != null) {
            this.listeners.add(l);
        }
    }


    public void die() {
        life = false;
    }


    Point point = new Point();

    public void res() {
        for (int i = 0; i < body.size(); i++) {
            Random random = new Random();
            int inde = random.nextInt(2);
            if (inde % 2 == 0) {
                point.x = body.get(i).x + 1;
                point.y = body.get(i).y - 1;
            } else {
                point.x = body.get(i).x - 1;
                point.y = body.get(i).y + 1;
            }

        }
        life = false;
        body.clear();
        init();
        life = true;
    }


}
