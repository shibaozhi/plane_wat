package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class LittleBoss2 extends GameObj{
    private boolean ex = true;//检测飞机是否存在
    //boss2血量
    int health = 15;
    public LittleBoss2() {
        super();
    }

    public LittleBoss2(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public LittleBoss2(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public LittleBoss2(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if(y<150){
            y+=2;
        }else{
            x+=speed;
            if(x>400||x<10){
                speed=-speed;
            }
        }
        //敌方2号小boss和我方子弹碰撞之后，我方子弹消失，当2号boss血量为0的时候，2号boss消失，并产生爆炸
        for(ShellObj shellObj: GameUtils.shellObjList){ //一级子弹和敌方Boss碰撞检测
            if(this.getRec().intersects(shellObj.getRec())){
                shellObj.setX(-100);
                shellObj.setY(-100);
                GameUtils.removeList.add(shellObj);
                health--;
                if(health<0&&ex){
                    ex=false;
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
                    GameWin.playMusicInBackground("music/boss12.wav",(float)1);
                    GameWin.sum--;
                }
            }

        }
        for(DoubleShellObj doubleShellObj: GameUtils.doubleShellObjList) { //二级子弹和敌方Boss碰撞检测
            if (this.getRec().intersects(doubleShellObj.getRec())) {
                doubleShellObj.setX(-100);
                doubleShellObj.setY(-100);
                GameUtils.removeList.add(doubleShellObj);
                health -= 2;
                if (health <=0&&ex) {
                    ex=false;
                    //绘制爆炸
                    ExplodeObj explodeObj = new ExplodeObj(x, y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    //当敌方boss被击毁时出现补给
                    GiftObj giftObj = new GiftObj(this.x, this.y);
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
        for(TripleShellObj tripleShellObj: GameUtils.tripleShellObjList){ //二级子弹和敌方Boss碰撞检测
            if(this.getRec().intersects(tripleShellObj.getRec())){
                tripleShellObj.setX(-100);
                tripleShellObj.setY(-100);
                GameUtils.removeList.add(tripleShellObj);
                health-=3;
                if(health<=0&&ex){
                    ex=false;
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
