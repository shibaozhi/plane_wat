package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class Enemy1Obj extends GameObj{
    public Enemy1Obj() {
        super();
    }

    public Enemy1Obj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public Enemy1Obj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        y+=speed;
        for (ShellObj shellObj: GameUtils.shellObjList){//һ���ӵ�����ײ���
            if(this.getRec().intersects(shellObj.getRec())){
                //���Ʊ�ը
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);
                shellObj.setX(-100);
                shellObj.setY(-100);
                this.setX(-200);
                this.setY(-200);
                GameUtils.removeList.add(shellObj);
                GameUtils.removeList.add(this);
                GameWin.score += 1;
                GameWin.playMusicInBackground("music/enemy12.wav",(float)1);
            }
        }
        for (DoubleShellObj doubleShellObj: GameUtils.doubleShellObjList){//�����ӵ�����ײ���
            if(this.getRec().intersects(doubleShellObj.getRec())){
                //���Ʊ�ը
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);

                doubleShellObj.setX(-100);
                doubleShellObj.setY(-100);
                this.setX(-200);
                this.setY(-200);
                GameUtils.removeList.add(doubleShellObj);
                GameUtils.removeList.add(this);
                GameWin.score += 1;
                GameWin.playMusicInBackground("music/enemy12.wav",(float)1);
            }
        }
        for (TripleShellObj tripleShellObj: GameUtils.tripleShellObjList){//�����ӵ�����ײ���
            if(this.getRec().intersects(tripleShellObj.getRec())){
                //���Ʊ�ը
                ExplodeObj explodeObj=new ExplodeObj(x,y);
                GameUtils.explodeObjList.add(explodeObj);
                GameUtils.removeList.add(explodeObj);

                tripleShellObj.setX(-100);
                tripleShellObj.setY(-100);
                this.setX(-200);
                this.setY(-200);
                GameUtils.removeList.add(tripleShellObj);
                GameUtils.removeList.add(this);
                GameWin.score += 1;
                GameWin.playMusicInBackground("music/enemy12.wav",(float)1);
            }
        }
        //Խ���жϣ���ֹ��Ϊ����Ԫ�ؼ������е�Ԫ��Խ��Խ�࣬���»���ҳ���ѹ����󣬴Ӷ�Ӱ����Ϸ�ٶ�
        if(y>800){
            GameUtils.removeList.add(this);
        }
    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
