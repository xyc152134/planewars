package com.xyc.runtime;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;
import com.xyc.base.Moveable;
import com.xyc.constant.FrameConstant;
import com.xyc.main.GameFrame;
import com.xyc.util.DataStore;
import com.xyc.util.ImageMap;

import java.awt.*;
import java.util.Collection;
import java.util.Random;

public class EnemyPlane extends BaseSpite implements Moveable, Drawable {
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 1;
    private Random random = new Random();
    private  boolean fire;

    public EnemyPlane(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    public EnemyPlane() {
        this(0, 0, ImageMap.get("ep01"));
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        fire();

    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");

        if (random.nextInt(1000) > 998&&fire) {
            gameFrame.enemtyBullets.add(new EnemtyBullet(
                    getX() + (image.getWidth(null) / 2) - ImageMap.get("epd01").getWidth(null) / 2,
                    getY() + image.getHeight(null),
                    ImageMap.get("epd01")
            ));
        }

    }


    public void borderTesting() {
        if (getY() > FrameConstant.FRAME_HEIGHT) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.enemyPlaneList.remove(this);
            gameFrame.a=true;
           // gameFrame.enemtyBullets.remove(this);  敌机死亡后移除子弹
        }

    }

    @Override
    public void move() {
        setY(getY() + speed);
        if (getY()+700>0){
            fire=true;
        }
        borderTesting();

    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }
}
