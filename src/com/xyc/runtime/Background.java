package com.xyc.runtime;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;
import com.xyc.base.Moveable;
import com.xyc.constant.FrameConstant;
import com.xyc.util.ImageMap;


import java.awt.*;

public class Background extends BaseSpite implements Moveable, Drawable {

    private Image bgImage;

    public Background() {
        this(0, FrameConstant.FRAME_HEIGHT-ImageMap.get("bg01").getHeight(null),
                ImageMap.get("bg01"));

    }

    public Background(int x, int y, Image bgImage) {
        super(x, y);
        this.bgImage = bgImage;
    }

    @Override
    public void move() {
      if (getY()==0){
          setY(ImageMap.get("bg01").getHeight(null));

      }
        setY(getY() +FrameConstant.GAME_SPEED);

    }


    @Override
    public void draw(Graphics g) {
        g.drawImage(bgImage, getX(), getY(), bgImage.getWidth(null),bgImage.getHeight(null), null);
        move();
    }
}

