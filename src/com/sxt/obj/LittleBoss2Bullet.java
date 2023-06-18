package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class LittleBoss2Bullet extends GameObj{
    int health=2;
    public LittleBoss2Bullet() {
        super();
    }

    public LittleBoss2Bullet(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public LittleBoss2Bullet(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public LittleBoss2Bullet(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
        //实现追踪功能
        x-=(x- GameUtils.gameObjList.get(GameWin.planeindex).getX())/40;
        for(ShellObj shellObj: GameUtils.shellObjList){

            if(this.getRec().intersects(shellObj.getRec())&&health>0){
                shellObj.setX(-100);
                shellObj.setY(-100);
                GameUtils.removeList.add(shellObj);
                health--;
            } else if (this.getRec().intersects(shellObj.getRec())&&health==0) {
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                shellObj.setX(-100);
                shellObj.setY(-100);
                this.setX(-200);
                this.setY(-200);
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);
            }
        }
        for(DoubleShellObj doubleShellObj: GameUtils.doubleShellObjList){ //二级子弹和敌方子弹碰撞检测
            if(this.getRec().intersects(doubleShellObj.getRec())&&health>0){
                doubleShellObj.setX(-100);
                doubleShellObj.setY(-100);
                GameUtils.removeList.add(doubleShellObj);
                health--;
            } else if (health==0) {
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                doubleShellObj.setX(-100);
                doubleShellObj.setY(-100);
                this.setX(-200);
                this.setY(-200);
                GameUtils.removeList.add(doubleShellObj);
                GameUtils.removeList.add(this);
            }
        }
        for(TripleShellObj tripleShellObj: GameUtils.tripleShellObjList){ //三级子弹和敌方子弹碰撞检测
            if(this.getRec().intersects(tripleShellObj.getRec())&&health>0){
                tripleShellObj.setX(-100);
                tripleShellObj.setY(-100);
                GameUtils.removeList.add(tripleShellObj);
                health--;
            } else if (health==0) {
                //绘制爆炸
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                tripleShellObj.setX(-100);
                tripleShellObj.setY(-100);
                this.setX(-200);
                this.setY(-200);
                GameUtils.removeList.add(tripleShellObj);
                GameUtils.removeList.add(this);
            }
        }
        //越界判断，防止因为所有元素集合中的元素越来越多，导致绘制页面的压力变大，从而影响游戏速度
        if(y>800){
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
