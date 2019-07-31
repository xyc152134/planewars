package com.xyc.util;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class ImageMap {
    private static final Map<String, Image> map=new HashMap<>();
     static {
         map.put("bg01",ImageUtil.getImage("com\\xyc\\imgs\\bg\\bg01.png"));

         map.put("my01",ImageUtil.getImage("com\\xyc\\imgs\\plane\\my_1.png"));

         map.put("myb01",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\myb01.png"));

         map.put("ep01",ImageUtil.getImage("com\\xyc\\imgs\\plane\\ep_1.png"));

         map.put("epd01",ImageUtil.getImage("com\\xyc\\imgs\\bullet\\epb_1.png"));

     }
     public static Image get(String key){
         return map.get(key);
     }


}

