package cn.itcase.snake.entities;

import cn.itcase.snake.util.Global;

import java.awt.*;
import java.util.Random;

public class Ground {
    private int[][] roks = new int[Global.WIDTH][Global.HEIGHT];

    public Ground() {
        for (int i = 0; i < Global.HEIGHT; i++) {
            roks[i][0] = 1;
            roks[i][Global.WIDTH - 1] = 1;
        }

        for (int i = 0; i < Global.WIDTH; i++) {
            roks[0][i] = 1;
            roks[Global.HEIGHT - 1][i] = 1;
        }

        for (int x = 1; x < Global.WIDTH - 1; x++) {
            for (int y = 1; y < Global.HEIGHT - 1; y++) {
                roks[x][y] = 0;
            }
        }
    }


    public boolean isSnakeEatRock(Snake snake) {
//        System.out.println("isSnakeEatRock");

//        for (int x = 0; x < Global.WIDTH; x++) {
//            for (int y = 0; y < Global.HEIGHT; y++) {
//                if (roks[x][y] == 1 && x == snake.getHead().x && y == snake.getHead().y) {
//                    return true;
//                }
//            }
//        }

        return false;
    }

    public boolean isSnakeEatRock(Snake2 snake2) {
//        System.out.println("isSnakeEatRock");

//        for (int x = 0; x < Global.WIDTH; x++) {
//            for (int y = 0; y < Global.HEIGHT; y++) {
//                if (roks[x][y] == 1 && x == snake2.getHead().x && y == snake2.getHead().y) {
//                    return true;
//                }
//            }
//        }

        return false;
    }

    public Point getPoint() {
        int x = 0;
        int y = 0;
        Random random = new Random();
        do{
            x = random.nextInt(Global.WIDTH-2)+1;
            y = random.nextInt(Global.HEIGHT-2)+1;

        }while(roks[x][y]==1);

        return new Point(x, y);
    }

    public void drawMe(Graphics g) {
//        System.out.println("Ground's show");
        g.setColor(new Color(0x4c4c4c));



        for (int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (roks[x][y] == 1) {
                    g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
                }
            }
        }
//        show(g);




    }

    public void show(Graphics g){
        g.setColor(new Color(0xffffff));
        for (int x = 1; x < Global.WIDTH-1; x++) {
            for (int y = 1; y < Global.HEIGHT-1; y++) {
                g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE, Global.CELL_SIZE, Global.CELL_SIZE, true);
            }
        }
    }




}
