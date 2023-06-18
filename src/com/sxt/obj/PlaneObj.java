package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class PlaneObj extends GameObj{

    //��¼�ҷ��ɻ���ײ�з������Ĵ���
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
        //��������ƶ��¼�
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
        //������ײ���
        //�ҷ��ɻ��͵з�С�ɻ���ײ֮�󣬵з�С�ɻ���ʧ���ҷ��ɻ�Ҳ��ʧ
        for (Enemy1Obj enemy1Obj:GameUtils.enemy1ObjList){
            if(this.getRec().intersects(enemy1Obj.getRec())){
                //���Ʊ�ը
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
        //�ҷ��ɻ��͵з���ɻ���ײ֮�����߶���ʧ
        for (Enemy2Obj enemy2Obj:GameUtils.enemy2ObjList){
            if(this.getRec().intersects(enemy2Obj.getRec())){
                //���Ʊ�ը
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
        //�ҷ��ɻ��͵з���ɻ��ӵ���ײ֮�����߶���ʧ
        for (Enemy2BulletObj enemy2BulletObj:GameUtils.enemy2BulletObjList){
            if(this.getRec().intersects(enemy2BulletObj.getRec())){
                //���Ʊ�ը
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
        //�ҷ��ɻ��͵з�1�ź�2��boss��ײ
        for (LittleBoss1 littleBoss1:GameUtils.littleBoss1List){
            if(this.getRec().intersects(littleBoss1.getRec())){
                //���Ʊ�ը
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
                //���Ʊ�ը
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
        //�ҷ��ɻ��͵з�boss��ײ
        for (BossObj bossObj:GameUtils.bossObjList){
            if(this.getRec().intersects(bossObj.getRec())){
                //���Ʊ�ը
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
        //���ҷ��ɻ���boss2���ӵ���ײ֮�����߶���ʧ
        for (LittleBoss2Bullet littleBoss2Bullet: GameUtils.littleBoss2BulletList){
            if(this.getRec().intersects(littleBoss2Bullet.getRec())){
                //���Ʊ�ը
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
        //���ҷ��ɻ���boss1���ӵ���ײ֮�����߶���ʧ
        for (LittleBoss1Bullet littleBoss1Bullet: GameUtils.littleBoss1BulletList){
            if(this.getRec().intersects(littleBoss1Bullet.getRec())){
                //���Ʊ�ը
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
        //�ҷ��ɻ���ײ����Ʒ֮�󣬲���Ʒ��ʧ���ҷ��ɻ�����ʧ
        for(GiftObj giftObj:GameUtils.giftObjsList){
            if(this.getRec().intersects(giftObj.getRec())){
                giftObj.setX(-100);
                giftObj.setY(-100);
                GameUtils.removeList.add(giftObj);
                if(times<2){//��ǰ�ӵ���������
                    times++;
                }
            }
        }
        //�ҷ��ɻ���boss�ӵ�����ײЧ��
        for(BossBullet bossBullet:GameUtils.bossBulletList){
            if(this.getRec().intersects(bossBullet.getRec())){
                //���Ʊ�ը
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
