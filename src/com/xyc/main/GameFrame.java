package com.xyc.main;

import com.xyc.constant.FrameConstant;
import com.xyc.runtime.Background;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class GameFrame extends Frame {
  private Background background=new Background();


    @Override
    public void paint(Graphics g) {
        background.draw(g);

    }

    /**
     * 初始化窗口
     */
    public void init() {
        //窗口大小
        setSize(FrameConstant.FRAME_WIDEH, FrameConstant.FRAME_HEIGHT);
        //设置居中
        setLocationRelativeTo(null);
        //屏蔽输入法
        enableInputMethods(false);
        new Thread() {
            @Override
            public void run() {
                while (true) {
                    repaint();
                    try {
                        Thread.sleep(15);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        setVisible(true);

    }
   private Image offScreenImage=null;//创建缓冲区

    @Override
    public void update(Graphics g) {
        if(offScreenImage==null){
            offScreenImage=this.createImage(FrameConstant.FRAME_WIDEH,FrameConstant.FRAME_HEIGHT);
        }
        Graphics goff=offScreenImage.getGraphics();//创建离线图片的实例
        paint(goff);
        g.drawImage(offScreenImage,0,0,null);//将缓冲区图片绘制到窗口目标
    }
}
