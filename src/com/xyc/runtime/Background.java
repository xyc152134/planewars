package com.xyc.runtime;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;
import com.xyc.base.Moveable;
import com.xyc.util.ImageMap;
import com.xyc.util.ImageUtil;

import java.awt.*;

public class Background extends BaseSpite implements Moveable, Drawable {

    private Image bgImage;

    public Background() {
        this(0, 0, ImageMap.get("bg01"));

    }

    public Background(int x, int y, Image bgImage) {
        super(x, y);
        this.bgImage = bgImage;
    }

    @Override
    public void move() {
        setY(getY() - 1);

    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(bgImage, getX(), getY(), bgImage.getWidth(null), bgImage.getHeight(null), null);
        move();
    }
}

