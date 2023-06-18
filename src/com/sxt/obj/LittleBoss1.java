package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class LittleBoss1 extends GameObj {
    private boolean ex = true;
    int health = 10;
    public LittleBoss1() {
        super();
    }

    public LittleBoss1(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public LittleBoss1(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public LittleBoss1(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics g) {

        super.paintSelf(g);
        x+=speed;
        if(x>400){
            speed=-1;
        }
        for(ShellObj shellObj: GameUtils.shellObjList){ //一级子弹和敌方Boss碰撞检测
            if(this.getRec().intersects(shellObj.getRec())){
                shellObj.setX(-100);
                shellObj.setY(-100);
                GameUtils.removeList.add(shellObj);
                health--;
                if(health<=0&&ex){
                    ex=false;//目前存在问题，当我方飞机与敌方boss相撞时，存在连续加分的情况
                    //绘制爆炸
                    ExplodeObj explodeObj=new ExplodeObj(x,y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    //当敌方boss被击毁时出现补给
                    GiftObj giftObj = new GiftObj(this.x,this.y);
                    GameUtils.giftObjsList.add(giftObj);
                    GameUtils.gameObjList.addAll(GameUtils.giftObjsList);

                    shellObj.setX(-100);
                    shellObj.setY(-100);
                    this.setX(-200);
                    this.setY(-200);
                    GameUtils.removeList.add(this);
                    GameWin.score += 10;
//                    System.out.println("1000000000000");
                    GameWin.playMusicInBackground("music/boss12.wav",(float)1);
                    GameWin.sum--;
                }
            }


        }
        for(DoubleShellObj doubleShellObj: GameUtils.doubleShellObjList){ //二级子弹和敌方Boss碰撞检测
            if(this.getRec().intersects(doubleShellObj.getRec())){
                doubleShellObj.setX(-100);
                doubleShellObj.setY(-100);
                GameUtils.removeList.add(doubleShellObj);
                health-=2;
                if(health<=0&&ex){
                    ex=false;//目前存在问题，当我方飞机与敌方boss相撞时，存在连续加分的情况
                    //绘制爆炸
                    ExplodeObj explodeObj=new ExplodeObj(x,y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    //当敌方boss被击毁时出现补给
                    GiftObj giftObj = new GiftObj(this.x,this.y);
                    GameUtils.giftObjsList.add(giftObj);
                    GameUtils.gameObjList.addAll(GameUtils.giftObjsList);
                    doubleShellObj.setX(-100);
                    doubleShellObj.setY(-100);
                    this.setX(-200);
                    this.setY(-200);
                    GameUtils.removeList.add(this);
                    GameWin.score += 10;
                    GameWin.playMusicInBackground("music/boss12.wav",(float)1);
                    GameWin.sum--;
                }
            }
        }
        for(TripleShellObj tripleShellObj: GameUtils.tripleShellObjList){ //三级子弹和敌方Boss碰撞检测
            if(this.getRec().intersects(tripleShellObj.getRec())&&health>0){
                tripleShellObj.setX(-100);
                tripleShellObj.setY(-100);
                GameUtils.removeList.add(tripleShellObj);
                health-=3;
                if(health<=0&&ex){
                    ex=false;//目前存在问题，当我方飞机与敌方boss相撞时，存在连续加分的情况
                    //绘制爆炸
                    ExplodeObj explodeObj=new ExplodeObj(x,y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    //当敌方boss被击毁时出现补给
                    GiftObj giftObj = new GiftObj(this.x,this.y);
                    GameUtils.giftObjsList.add(giftObj);
                    GameUtils.gameObjList.addAll(GameUtils.giftObjsList);
                    tripleShellObj.setX(-100);
                    tripleShellObj.setY(-100);
                    this.setX(-200);
                    this.setY(-200);
                    GameUtils.removeList.add(this);
                    GameWin.score += 10;
                    GameWin.playMusicInBackground("music/boss12.wav",(float)1);
                    GameWin.sum--;
                }
            }
        }


    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
