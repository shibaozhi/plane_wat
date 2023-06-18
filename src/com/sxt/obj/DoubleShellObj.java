package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class DoubleShellObj extends GameObj{
    public DoubleShellObj() {
        super();
    }

    public DoubleShellObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public DoubleShellObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public DoubleShellObj(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y -=speed;
        //越界判断，防止因为所有元素集合中的元素越来越多，导致绘制页面的压力变大，从而影响游戏速度
        if(y<0){
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
