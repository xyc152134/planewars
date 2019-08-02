package com.xyc.runtime;

import java.util.List;

import com.xyc.base.BaseSpite;
import com.xyc.base.Drawable;
import com.xyc.util.ImageMap;

import java.util.concurrent.CopyOnWriteArrayList;
import java.awt.*;

public class GameOver extends BaseSpite implements Drawable {
    private List<Image> imageList = new CopyOnWriteArrayList<Image>();

    public GameOver() {
        for (int i = 1; i < 25; i++) {
            imageList.add(ImageMap.get("gameover" + i));
        }
    }

    int index = 0;

    @Override
    public void draw(Graphics g) {
        g.drawImage(imageList.get(index++ / 5), 140, 390, imageList.get(0).getWidth(null), imageList.get(0).getHeight(null), null);
        if (index > 230/2) index = 0;
    }
}
