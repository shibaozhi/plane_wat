package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class Enemy2BulletObj extends GameObj{
    public Enemy2BulletObj() {
        super();
    }

    public Enemy2BulletObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public Enemy2BulletObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
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
