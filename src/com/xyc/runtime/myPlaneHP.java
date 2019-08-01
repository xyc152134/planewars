package com.xyc.runtime;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;

import com.xyc.util.ImageMap;

import java.awt.*;


public class myPlaneHP extends BaseSpite implements Drawable {
    private Image image;
    private Image image2;

    public myPlaneHP(int x, int y, Image image, Image image2) {
        super(x, y);
        this.image = image;
        this.image2 = image2;
    }

    public myPlaneHP() {
        this(0,0, ImageMap.get("mphp"),ImageMap.get("mphp1"));


    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image2, getX(), getY(), image2.getWidth(null), image2.getHeight(null), null);

    }

}
