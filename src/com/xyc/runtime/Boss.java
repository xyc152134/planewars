package com.xyc.runtime;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;
import com.xyc.base.Moveable;
import com.xyc.constant.FrameConstant;
import com.xyc.main.GameFrame;
import com.xyc.util.DataStore;
import com.xyc.util.ImageMap;

import java.awt.*;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class Boss extends BaseSpite implements Moveable, Drawable {
    private List<Image> image = new CopyOnWriteArrayList<>();
    private boolean right;
    Random random = new Random();
    private Image image2;
    private int speed = FrameConstant.GAME_SPEED ;
    public Boss() {
        for (int i = 1; i < 9; i++) {
            image.add(ImageMap.get("boss" + i));
        }
        this.image2 = ImageMap.get("bossb");
    }


    int index = 0;

    public void draw(Graphics g) {
        move();
        borderTesting();
        g.drawImage(image.get(index++ / 5), getX(), getY(), image.get(0).getWidth(null), image.get(0).getHeight(null), null);
        if (index > 35) {
            index = 0;
        }
        fire();
    }

    @Override
    public void move() {
        if (!right) {
            setY(getY() + speed);
            setX(getX() +speed);
        } else {
            setY(getY() -speed);
            setX(getX() - speed);
        }
    }

    public void borderTesting() {
        if (getX() > FrameConstant.FRAME_WIDEH - image.get(0).getWidth(null) || getY() > FrameConstant.FRAME_HEIGHT - image.get(0).getHeight(null)) {
            right = true;
        }
        if (getX() < 0 || getY() < 0) {
            right = false;
        }
    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (random.nextInt(1000) > 950) {
            gameFrame.enemtyBullets.add(new EnemtyBullet(
                    getX() + (image2.getWidth(null) / 2) - ImageMap.get("bossb").getWidth(null) / 2 + random.nextInt(15),
                    getY() + image2.getHeight(null) + random.nextInt(15),
                    ImageMap.get("bossb"), 4
            ));
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.get(0).getWidth(null), image.get(0).getHeight(null));
    }


}
