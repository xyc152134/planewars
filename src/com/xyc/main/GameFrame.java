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
    public static int sore = 0;
    public static boolean a;
    public static int hp = 10;
    public static int bosshp = 50;
    public boolean killboss;
    private Random random = new Random();
    //创建背景对象
    private Background background = new Background();
    //创建飞机对象
    public Plane plane = new Plane();
    //创建我方血量槽
    private myPlaneHP myPlaneHP = new myPlaneHP();
    //创建我方血量 集合
    public final List<myPlaneHP> myPlaneHPList = new CopyOnWriteArrayList<>();
    // 创建子弹集合
    public final List<Bullet> bulletList = new CopyOnWriteArrayList<>();
    //创建敌机
    public final List<EnemyPlane> enemyPlaneList = new CopyOnWriteArrayList<>();
    //创建敌机子弹集合
    public final List<EnemtyBullet> enemtyBullets = new CopyOnWriteArrayList<>();
    //创建boss
    private Boss boss = new Boss();
    //创建道具
    public final List<Prop> propList = new CopyOnWriteArrayList<>();
    public boolean gameOver = false;

    @Override
    public void paint(Graphics g) {
        if (!gameOver) {
            //会有覆盖问题
            background.draw(g);
            plane.draw(g);

            for (Prop prop : propList) {
                prop.draw(g);
            }
            for (myPlaneHP myPlaneHP : myPlaneHPList) {
                myPlaneHP.draw(g);
            }
            for (Bullet bullet : bulletList) {
                bullet.draw(g);

            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.draw(g);
            }
            for (EnemtyBullet enemtyBullet : enemtyBullets) {
                enemtyBullet.draw(g);

            }
            //我方子弹碰敌机
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(enemyPlaneList);

            }
            for (Bullet bullet : bulletList) {
                bullet.collisionTesting(boss);
            }
            //每个敌机子弹碰我方
            for (EnemtyBullet enemtyBullet : enemtyBullets) {
                enemtyBullet.collisionTesting(plane);
            }
            for (EnemyPlane enemyPlane : enemyPlaneList) {
                enemyPlane.collisionTesting(plane);

            }
            for (Prop prop : propList) {
                prop.collisionTesting(plane);
            }
            g.setFont(new Font("楷体", 1, 36));
            g.setColor(Color.green);
            g.drawString("生命---" + hp, 50, 80);
            g.drawString("分数---" + sore, 50, 120);
            g.drawString("Boss---" + propList.size(), 50, 620);

            if (sore>=50) {
            boss.draw(g);
            }
            if (enemyPlaneList.size() == 0) {
                g.setFont(new Font("楷体", 1, 56));
                g.setColor(Color.green);
                g.drawString("恭喜通关", 300, 380);
                gameOver = true;
            }

            if (hp <= 0) {
                g.setFont(new Font("楷体", 1, 56));
                g.setColor(Color.red);
                g.drawString("很遗憾你失败了", 180, 380);
                gameOver = true;
            }
            if (killboss) {
                g.setFont(new Font("楷体", 1, 56));
                g.setColor(Color.green);
                g.drawString("恭喜通关", 300, 380);
                gameOver = true;
            }
            myPlaneHpadd();


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
                        Thread.sleep(10);
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
                    gameOver = !gameOver;
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
        addProp();
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
    public void enemtyPlaneadd() {
        while (true) {
            enemyPlaneList.add(new EnemyPlane(random.nextInt(700), -random.nextInt(4000), random.nextInt(4)));
            if (enemyPlaneList.size() > 50) {
                break;
            }
        }
    }//添加血条

    public void myPlaneHpadd() {
        myPlaneHPList.clear();
        for (int i = 0; i < hp; i++) {
            myPlaneHPList.add(new myPlaneHP(FrameConstant.FRAME_WIDEH - ImageMap.get("mphp").getWidth(null) + 230 + i * 11 + 2, 30 + ImageMap.get("mphp").getHeight(null) + 5, ImageMap.get("mphp"), ImageMap.get("mphp1")));
        }
    }

    public void addProp() {
        while (true) {
            propList.add(new Prop(random.nextInt(750), -random.nextInt(4000), (random.nextInt(5) % 2 + 1)));
            if(propList.size()>20)break;
        }
    }




}

