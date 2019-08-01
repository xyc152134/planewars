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

public class EnemyPlane extends BaseSpite implements Moveable, Drawable {
    private Image image;
    private Image image1;
    private Image image2;
    private int speed = FrameConstant.GAME_SPEED * 2;
    private Random random = new Random();

    private int type;
    private boolean right;

    public int getType() {
        return type;
    }

    private int hp;

    public int getHp() {
        return hp;
    }

    public void setHp(int hp) {
        this.hp = hp;
    }


    public EnemyPlane(int x, int y, int type) {
        super(x, y);
        this.image = ImageMap.get("ep01");
        this.image1 = ImageMap.get("ep02");
        this.image2 = ImageMap.get("ep03");
        this.type = type;
        init();
    }

    void init() {
        if (type == 1) hp = 4;
        if (type == 2) hp = 2;
        if (type == 3) hp = 1;
    }

    public EnemyPlane() {
        this(0, 0, 0);
    }

    @Override
    public void draw(Graphics g) {
        if (type == 1) {
            g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        } else if (type == 2) {
            g.drawImage(image1, getX(), getY(), image1.getWidth(null), image1.getHeight(null), null);

        } else if (type == 3) {
            g.drawImage(image2, getX(), getY(), image2.getWidth(null), image2.getHeight(null), null);
        }
        move();
        fire();

    }

    public void fire() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (type == 1) {
            if (random.nextInt(1000) > 985 && getY() > 0) {
                gameFrame.enemtyBullets.add(new EnemtyBullet(
                        getX() + (image.getWidth(null) / 2) - ImageMap.get("epd01").getWidth(null) / 2,
                        getY() + image.getHeight(null),
                        ImageMap.get("epd01"),1
                ));
            }


        }
        if (type == 2) {
            if (random.nextInt(1000) > 985 && getY() > 0) {
                gameFrame.enemtyBullets.add(new EnemtyBullet(
                        getX() + (image1.getWidth(null) / 2) - ImageMap.get("epd02").getWidth(null) / 2,
                        getY() + image1.getHeight(null),
                        ImageMap.get("epd02"),2
                ));
            }
        }
        if (type == 3) {
            if (random.nextInt(1000) > 985 && getY() > 0) {
                gameFrame.enemtyBullets.add(new EnemtyBullet(
                        getX() + (image2.getWidth(null) / 2) - ImageMap.get("epd03").getWidth(null) / 2,
                        getY() + image2.getHeight(null),
                        ImageMap.get("epd03"),3
                ));
            }
        }

    }


    public void borderTesting() {
        GameFrame gameFrame = DataStore.get("gameFrame");

        if (type == 1) {
            if (getY() >= FrameConstant.FRAME_HEIGHT - image.getWidth(null) || getX() >= FrameConstant.FRAME_WIDEH - image.getWidth(null)) {
               right=true;
            }
            if (getY() < 0 || getX() < 0) {
                right=false;
            }

        }
        if(type==2){
            if (getY() > FrameConstant.FRAME_HEIGHT || getX() > FrameConstant.FRAME_WIDEH) {
                gameFrame.enemyPlaneList.remove(this);
            }
        }
        if(type==3){
        if (getY() > FrameConstant.FRAME_HEIGHT || getX() > FrameConstant.FRAME_WIDEH) {
            gameFrame.enemyPlaneList.remove(this);
            //gameFrame.a = true;
            // gameFrame.enemtyBullets.remove(this);  敌机死亡后移除子弹
        }

    }}

    @Override
    public void move() {
        if (type == 1) {
            if (!right) {
                setX(getX() + speed);
            } else if(right){
                setX(getX() - 3*speed);
            }
        }
        if (type == 2) {
            setY(getY() + 2 * speed);

        }
        if (type == 3) {
            setY(getY() + speed);
        }
        borderTesting();

    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), image.getWidth(null), image.getHeight(null));
    }

    //我方与敌机碰撞
    public void collisionTesting(Plane plane) {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (plane.getRectangle().intersects(this.getRectangle())) {
            gameFrame.enemyPlaneList.remove(this);
            gameFrame.hp--;

        }
    }
}
