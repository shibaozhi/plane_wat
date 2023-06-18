package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneObj extends GameObj{

    //记录我方飞机碰撞敌方补给的次数
    public static int times = 0;
//    LittleBoss1 littleBoss1 = new LittleBoss1();
//    LittleBoss2 littleBoss2 = new LittleBoss2();
    public PlaneObj() {
        super();
    }

    public PlaneObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public PlaneObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
        //添加鼠标的移动事件
        this.frame.addMouseMotionListener(new MouseAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                PlaneObj.super.x = e.getX()-19;
                PlaneObj.super.y = e.getY()-20;
            }
        });
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        //进行碰撞检测
        //我方飞机和敌方小飞机碰撞之后，敌方小飞机消失，我方飞机也消失
        for (Enemy1Obj enemy1Obj:GameUtils.enemy1ObjList){
            if(this.getRec().intersects(enemy1Obj.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                enemy1Obj.setX(-100);
                enemy1Obj.setY(-100);
                this.x = 1000;
                this.y = 1000;
                GameUtils.removeList.add(enemy1Obj);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
        //我方飞机和敌方大飞机碰撞之后，两者都消失
        for (Enemy2Obj enemy2Obj:GameUtils.enemy2ObjList){
            if(this.getRec().intersects(enemy2Obj.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);

                enemy2Obj.setX(-100);
                enemy2Obj.setY(-100);
                this.x = 1000;
                this.y = 1000;
                GameUtils.removeList.add(enemy2Obj);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
        //我方飞机和敌方大飞机子弹碰撞之后，两者都消失
        for (Enemy2BulletObj enemy2BulletObj:GameUtils.enemy2BulletObjList){
            if(this.getRec().intersects(enemy2BulletObj.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                enemy2BulletObj.setX(-100);
                enemy2BulletObj.setY(-100);
                this.x = 1000;
                this.y = 1000;
                GameUtils.removeList.add(enemy2BulletObj);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
        //我方飞机和敌方1号和2号boss碰撞
        for (LittleBoss1 littleBoss1:GameUtils.littleBoss1List){
            if(this.getRec().intersects(littleBoss1.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                littleBoss1.setX(-100);
                littleBoss1.setY(-100);
                this.x = 1000;
                this.y = 1000;
                GameUtils.removeList.add(littleBoss1);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
        for (LittleBoss2 littleBoss2:GameUtils.littleBoss2List){
            if(this.getRec().intersects(littleBoss2.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                littleBoss2.setX(-100);
                littleBoss2.setY(-100);
                this.x = 1000;
                this.y = 1000;
                GameUtils.removeList.add(littleBoss2);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
        //我方飞机和敌方boss碰撞
        for (BossObj bossObj:GameUtils.bossObjList){
            if(this.getRec().intersects(bossObj.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                bossObj.setX(-100);
                bossObj.setY(-100);
                this.x = 1000;
                this.y = 1000;
                GameUtils.removeList.add(bossObj);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
        //当我方飞机和boss2号子弹碰撞之后，两者都消失
        for (LittleBoss2Bullet littleBoss2Bullet: GameUtils.littleBoss2BulletList){
            if(this.getRec().intersects(littleBoss2Bullet.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                littleBoss2Bullet.setX(-100);
                littleBoss2Bullet.setY(-100);
                this.x = 1000;
                this.y = 1000;
                GameUtils.removeList.add(littleBoss2Bullet);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
        //当我方飞机和boss1号子弹碰撞之后，两者都消失
        for (LittleBoss1Bullet littleBoss1Bullet: GameUtils.littleBoss1BulletList){
            if(this.getRec().intersects(littleBoss1Bullet.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                littleBoss1Bullet.setX(-100);
                littleBoss1Bullet.setY(-100);
                this.x = 1000;
                this.y = 1000;
                GameUtils.removeList.add(littleBoss1Bullet);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
        //我方飞机碰撞补给品之后，补给品消失，我方飞机不消失
        for(GiftObj giftObj:GameUtils.giftObjsList){
            if(this.getRec().intersects(giftObj.getRec())){
                giftObj.setX(-100);
                giftObj.setY(-100);
                GameUtils.removeList.add(giftObj);
                if(times<2){//当前子弹可以升级
                    times++;
                }
            }
        }
        //我方飞机和boss子弹的碰撞效果
        for(BossBullet bossBullet:GameUtils.bossBulletList){
            if(this.getRec().intersects(bossBullet.getRec())){
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                bossBullet.setX(-100);
                bossBullet.setY(-100);
                this.setX(1000);
                this.setY(1000);
                GameUtils.removeList.add(bossBullet);
                GameUtils.removeList.add(this);
                GameWin.state = 3;
            }
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
