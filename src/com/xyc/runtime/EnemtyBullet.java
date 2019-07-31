package com.xyc.runtime;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;
import com.xyc.base.Moveable;
import com.xyc.constant.FrameConstant;
import com.xyc.main.GameFrame;
import com.xyc.util.DataStore;

import java.awt.*;

public class EnemtyBullet extends BaseSpite implements Drawable, Moveable {
    private  Image image;
    private  int speed= FrameConstant.GAME_SPEED*2;
    public EnemtyBullet() {
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
        move();

    }

    public EnemtyBullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void move() {
        setY(getY()+speed);
        borderTesting();

    }
    public void borderTesting() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if (getY() > FrameConstant.FRAME_HEIGHT||gameFrame.a) {
            gameFrame.enemtyBullets.remove(this);
        }
    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public void  collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if(plane.getRectangle().intersects(this.getRectangle())){
            gameFrame.enemtyBullets.remove(this);
            gameFrame.gameOver=true;
        }

    }
}
