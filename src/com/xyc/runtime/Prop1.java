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

public class Prop1 extends BaseSpite implements Moveable, Drawable {
    private List<Image> imageList = new CopyOnWriteArrayList<Image>() ;
    private List<Image> imageList2 = new CopyOnWriteArrayList<Image>() ;
    private int type;
    private int speed = FrameConstant.GAME_SPEED * 3;
    private Random random = new Random();
    private boolean right;

    public Prop1() {
        for (int i = 1; i < 5; i++) {
            imageList.add(ImageMap.get("prop" + i));
        }
        for (int i = 1; i < 5; i++) {
            imageList2.add(ImageMap.get("prop0" + i));
        }
    }

    public Prop1(int x, int y, int type) {
        super(x, y);
        this.type = type;
    }

    int index = 0;

    public void draw(Graphics g) {
        //borderTesting();
        move();
        if (type == 1) {
            g.drawImage(imageList.get(index++ / 10), getX(), getY(), imageList.get(0).getWidth(null), imageList.get(0).getHeight(null), null);
            if (index > 30) {
                index = 0;
            }}
        if (type == 2) {
            g.drawImage(imageList2.get(index++ / 10), getX(), getY(), imageList2.get(0).getWidth(null), imageList2.get(0).getHeight(null), null);
            if (index > 30) {
                index = 0;
            }}

    }

    @Override
    public void move() {
        if (!right) {
            setX(getX() + speed);
            setY(getY() + speed);
        }else if(right){
            setX(getX() -speed+2);
            setY(getY() - speed);
        }


    }

    public void borderTesting() {
        GameFrame gameFrame = DataStore.get("gameFrame");
        if(type==1)
            if (getY() >= FrameConstant.FRAME_HEIGHT - imageList.get(0).getWidth(null) || getX() >= FrameConstant.FRAME_WIDEH - imageList.get(0).getWidth(null)) {
                right = true;
            }
        if (getY() < 0 || getX() < 0) {
            right = false;
        }

    }
    public Rectangle getRectangle() {
        return new Rectangle(getX(), getY(), imageList.get(0).getWidth(null), imageList.get(0).getHeight(null));
    }
    public void collisionTesting(Plane plane){
        GameFrame gameFrame = DataStore.get("gameFrame");
        if(plane.getRectangle().intersects(this.getRectangle())){
            gameFrame.hp++;


        }
    }


}
