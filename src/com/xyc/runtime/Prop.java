package com.xyc.runtime;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;
import com.xyc.base.Moveable;
import com.xyc.constant.FrameConstant;
import com.xyc.main.GameFrame;
import com.xyc.util.DataStore;
import com.xyc.util.ImageMap;


import java.awt.*;

import java.util.Random;


public class Prop extends BaseSpite implements Moveable, Drawable {

    private int type;
    private int speed = FrameConstant.GAME_SPEED * 3;
    private Random random = new Random();
    private boolean right;
    private Image image;
    private Image image1;

    public Prop() {

    }

    public Prop(int x, int y, int type) {
        super(x, y);
        this.type = type;
        this.image = ImageMap.get("prop0");
        this.image1 = ImageMap.get("prop00");
    }

    int index = 0;

    public void draw(Graphics g) {
        if (type == 1) {
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        } else if (type == 2) {
            g.drawImage(image1, getX(), getY(), image1.getWidth(null), image1.getHeight(null), null);
        }
        borderTesting();
        move();


    }

    @Override
    public void move() {
        if (!right) {

            setY(getY() + speed);
        } else if (right) {
            setY(getY() - speed);
        }


    }
    public void borderTesting() {
        GameFrame gameFrame = DataStore.get("gameFrame");
            if (getY() >= FrameConstant.FRAME_HEIGHT) {
                gameFrame.propList.remove(this);
        }
    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.propList.remove(this);
           if(type==1){
               gameFrame.hp++;
               System.out.println("111");
           }
           if(type==2){
               System.out.println("222");
               plane.setSeed(plane.getSeed()+FrameConstant.GAME_SPEED*3);
           }

        }
    }
}

