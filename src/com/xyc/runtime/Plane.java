package com.xyc.runtime;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;
import com.xyc.base.Moveable;
import com.xyc.constant.FrameConstant;
import com.xyc.main.GameFrame;
import com.xyc.util.DataStore;
import com.xyc.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Plane extends BaseSpite implements Drawable, Moveable {
    private Image image;
    private boolean up, right, down, left, fire;
    private int seed = FrameConstant.GAME_SPEED * 3;

    public Plane() {
        this((FrameConstant.FRAME_WIDEH - ImageMap.get("my01").getWidth(null)) / 2,
                FrameConstant.FRAME_HEIGHT - ImageMap.get("my01").getHeight(null),
                ImageMap.get("my01"));
    }

    public Plane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
//      i++;   控制射速
//      if(i>30){
//          i=0;
//          fire();
//      }

    }

    private  int i=0;
    /**
     * 开火方法
     */
    public void fire() {
        if (fire) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.add(new Bullet(
                   getX() + image.getWidth(null) / 2 - ImageMap.get("myb01").getWidth(null) / 2,
                    getY() - ImageMap.get("myb01").getHeight(null),
                    ImageMap.get("myb01")

            ));
        }

    }


    @Override
    public void move() {
        if (up) {
            setY(getY() - seed);
        }
        if (right) {
            setX(getX() + seed);
        }
        if (down) {
            setY(getY() + seed);
        }
        if (left) {
            setX(getX() - seed);
        }
        borderTesting();

    }

    public void borderTesting() {
        if (getX() < 0) {
            setX(0);
        }
        if (getX() > FrameConstant.FRAME_WIDEH - image.getWidth(null)) {
            setX(FrameConstant.FRAME_WIDEH - image.getWidth(null));
        }
        if (getY() < 30) {
            setY(30);
        }
        if (getY() > FrameConstant.FRAME_HEIGHT - image.getHeight(null)) {
            setY(FrameConstant.FRAME_HEIGHT - image.getHeight(null));
        }
    }


    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = true;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_W) {
            up = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_D) {
            right = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_S) {
            down = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_A) {
            left = false;
        }
        if (e.getKeyCode() == KeyEvent.VK_J) {
            fire();
            fire = false;
        }
    }
    @Override
    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }

}
