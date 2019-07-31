package com.xyc;

import com.xyc.main.GameFrame;
import com.xyc.util.DataStore;

public class GameStat {
    public static void main(String[] args) {
        GameFrame gameFrame=new GameFrame();
        DataStore.put("gameFrame",gameFrame);
        gameFrame.init();
    }
}
