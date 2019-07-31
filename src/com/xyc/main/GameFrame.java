package com.xyc.main;

import com.xyc.constant.FrameConstant;
import com.xyc.runtime.*;
import com.xyc.util.ImageMap;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

public class GameFrame extends Frame {
    public static  int sore=0;
    public static  boolean a;
    private Random random=new Random();
    //创建背景对象
    private Background background = new Background();
    //创建飞机对象
    private Plane plane = new Plane();
// 创建子弹集合
   public final List<Bullet> bulletList=new CopyOnWriteArrayList<>();
   //创建敌机
   public final List<EnemyPlane> enemyPlaneList=new CopyOnWriteArrayList<>();
   //创建敌机子弹集合
   public final List<EnemtyBullet> enemtyBullets=new CopyOnWriteArrayList<>();
   public  boolean gameOver=false;
    @Override
    public void paint(Graphics g) {
        if(!gameOver) {
            //会有覆盖问题
            background.draw(g);
            plane.draw(g);
            for (Bullet bullet : bulletList) {
                bullet.draw(g);

            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }
            for (EnemtyBullet enemtyBullet : enemtyBullets) {
                enemtyBullet.draw(g);

            }
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);

            }
            for (EnemtyBullet enemtyBullet : enemtyBullets) {
                enemtyBullet.collisionTesting(plane);
            }
            g.setColor(Color.white);

            g.drawString("得分-------" + sore, 100, 100);
        }
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
                        Thread.sleep(2);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }.start();
        //键盘监听
        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                //游戏暂停
                if (e.getKeyCode() == KeyEvent.VK_L) {
                    gameOver=!gameOver;

                }
                plane.keyPressed(e);

            }

            @Override
            public void keyReleased(KeyEvent e) {
                plane.keyReleased(e);
            }
        });

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        //游戏初始时添加一些敌机
            enemtyPlaneadd();

        setVisible(true);

    }

    private Image offScreenImage = null;//创建缓冲区

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(FrameConstant.FRAME_WIDEH, FrameConstant.FRAME_HEIGHT);
        }
        Graphics goff = offScreenImage.getGraphics();//创建离线图片的实例
        paint(goff);
        g.drawImage(offScreenImage, 0, 0, null);//将缓冲区图片绘制到窗口目标
    }
    //随机添加敌机
    public  void enemtyPlaneadd(){
        while (true) {
            enemyPlaneList.add(new EnemyPlane(random.nextInt(700), -random.nextInt(8000), ImageMap.get("ep01")));

            if(enemyPlaneList.size()>15){
                break;
            }
        }
    }
    }

