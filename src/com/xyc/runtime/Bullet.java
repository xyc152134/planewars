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

    private  Image image;
    private int speed= FrameConstant.GAME_SPEED*5;

    public Bullet() {
        this(0,0, ImageMap.get("myb01"));
    }

    public Bullet(int x, int y, Image image) {
        super(x, y);
        this.image = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, getX(), getY(), image.getWidth(null), image.getHeight(null), null);
   move();
        borderTesting();
    }

    @Override
    public void move() {
        setY(getY()-speed);

    }
    public void borderTesting(){
  if(getY()<30-image.getHeight(null)){
     GameFrame gameFrame= DataStore.get("gameFrame");
     gameFrame.bulletList.remove(this);
  }


    }

    public Rectangle getRectangle() {
        return new Rectangle(getX(),getY(),image.getWidth(null),image.getHeight(null));
    }
    public void  collisionTesting(List<EnemyPlane> enemyPlanesList){
        GameFrame gameFrame = DataStore.get("gameFrame");
        for (EnemyPlane enemyPlane : enemyPlanesList) {
            if(enemyPlane.getRectangle().intersects(this.getRectangle())){
                enemyPlanesList.remove(enemyPlane);
                gameFrame.sore++;

            }
            
        }

    }
}
