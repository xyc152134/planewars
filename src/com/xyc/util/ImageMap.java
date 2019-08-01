package com.xyc.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map=new HashMap<>();
     static {
         map.put("bg02",ImageUtil.getImage("com\\xyc\\imgs\\bg\\bg02.png"));

         map.put("my01",ImageUtil.getImage("com\\xyc\\imgs\\plane\\my_1_gaitubao_119x80.png"));

         map.put("myb01",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\myb01.png"));
         map.put("myb02",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\myb02_01.png"));

         map.put("ep01",ImageUtil.getImage("com\\xyc\\imgs\\plane\\ep_1.png"));
         map.put("ep02",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\enemy_03.png"));
         map.put("ep03",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\enemy_02.png"));

         map.put("epd01",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\epb_1_gaitubao_39x55.png"));
         map.put("epd02",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\enemyBulletBig.png"));
         map.put("epd03",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\missile_1_down.png"));
         map.put("bossb",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\bossbullet_gaitubao_19x56.png"));

         map.put("mphp",ImageUtil.getImage("com\\xyc\\imgs\\hp\\myplane_box.png"));

         map.put("mphp1",ImageUtil.getImage("com\\xyc\\imgs\\hp\\myplane_HP.png"));
         map.put("boss1",ImageUtil.getImage("com\\xyc\\imgs\\Boss\\BOSS01_1.png"));
         map.put("boss2",ImageUtil.getImage("com\\xyc\\imgs\\Boss\\BOSS01_2.png"));
         map.put("boss3",ImageUtil.getImage("com\\xyc\\imgs\\Boss\\BOSS01_3.png"));
         map.put("boss4",ImageUtil.getImage("com\\xyc\\imgs\\Boss\\BOSS01_4.png"));
         map.put("boss5",ImageUtil.getImage("com\\xyc\\imgs\\Boss\\BOSS01_5.png"));
         map.put("boss6",ImageUtil.getImage("com\\xyc\\imgs\\Boss\\BOSS01_6.png"));
         map.put("boss7",ImageUtil.getImage("com\\xyc\\imgs\\Boss\\BOSS01_7.png"));
         map.put("boss8",ImageUtil.getImage("com\\xyc\\imgs\\Boss\\BOSS01_8.png"));

         map.put("prop0",ImageUtil.getImage("com\\xyc\\imgs\\prop\\addHP1.png"));
         map.put("prop1",ImageUtil.getImage("com\\xyc\\imgs\\prop\\addHP2.png"));
         map.put("prop2",ImageUtil.getImage("com\\xyc\\imgs\\prop\\addHP3.png"));
         map.put("prop3",ImageUtil.getImage("com\\xyc\\imgs\\prop\\addHP4.png"));

         map.put("prop00",ImageUtil.getImage("com\\xyc\\imgs\\prop\\addmagic1.png"));
         map.put("prop01",ImageUtil.getImage("com\\xyc\\imgs\\prop\\addmagic2.png"));
         map.put("prop02",ImageUtil.getImage("com\\xyc\\imgs\\prop\\addmagic3.png"));
         map.put("prop03",ImageUtil.getImage("com\\xyc\\imgs\\prop\\addmagic4.png"));

     }
     public static Image get(String key){
         return map.get(key);
     }


}

