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

public class Bullet extends BaseSpite implements Drawable, Moveable {
    private int type;
    private Image image;
    private int speed = FrameConstant.GAME_SPEED * 5;

    public Bullet() {
        this(0, 0, ImageMap.get("myb01"), 0);
    }

    public Bullet(int x, int y, Image image, int type) {
        super(x, y);
        this.image = image;
        this.type = type;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY() - speed);

    }

    public void borderTesting() {
        if (getY() < 30 - image.getHeight(null)) {
            GameFrame gameFrame = DataStore.get("gameFrame");
            gameFrame.bulletList.remove(this);
        }


    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    public void collisionTesting(List<EnemyPlane> enemyPlanesList) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlanesList) {
            if (enemyPlane.getRectangle().intersects(this.getRectangle())) {
                if (type == 1) {
                    enemyPlane.setHp(enemyPlane.getHp() - 1);

                } else if (type == 2) {
                    enemyPlane.setHp(enemyPlane.getHp() - 2);

                }


                if (enemyPlane.getHp() <= 0) {
                    if (enemyPlane.getType() == 1) gameFrame.sore += 4;
                    if (enemyPlane.getType() == 2) gameFrame.sore += 2;
                    if (enemyPlane.getType() == 3) gameFrame.sore++;
                    enemyPlanesList.remove(enemyPlane);
                }
                gameFrame.bulletList.remove(this);


            }

        }

    }

    public void collisionTesting(Boss boss) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (boss.getRectangle().intersects(this.getRectangle())) {
            if (type == 1) {
                gameFrame.bosshp--;
            } else if (type == 2) {
                gameFrame.bosshp -= 2;
            }
            gameFrame.bulletList.remove(this);
            if(gameFrame.bosshp<0){
                gameFrame.killboss=true;

            }
        }
    }
}
