package com.sxt.obj;

import com.sxt.GameWin;
import com.sxt.utils.GameUtils;

import java.awt.*;

public class BossObj extends GameObj{
    private boolean ex = true;//���ɻ��Ƿ����
    int health = 30;
    public BossObj() {
        super();
    }

    public BossObj(Image img, int x, int y, double speed) {
        super(img, x, y, speed);
    }

    public BossObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        super(img, width, height, x, y, speed, frame);
    }

    public BossObj(int x, int y) {
        super(x, y);
    }

    @Override
    public void paintSelf(Graphics g) {
        super.paintSelf(g);
        if(y<40){
            y+=speed;
        }else{
            x+=speed;
            if(x<50||x>360){
                speed=-speed;
            }
        }
        //boss��һ���ӵ�����ײ
        for(ShellObj shellObj : GameUtils.shellObjList){
            if(this.getRec().intersects(shellObj.getRec())&&health>0){
                shellObj.setX(-100);
                shellObj.setY(-100);
                GameUtils.removeList.add(shellObj);
                health--;
                if(health<=0&&ex){
                    ex=false;
                    //���Ʊ�ը
                    ExplodeObj explodeObj=new ExplodeObj(x,y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    this.setX(-200);
                    this.setY(-200);
                    GameUtils.removeList.add(shellObj);
//                    GameWin.state = 4;
                    GameWin.score +=30;
                    GameWin.playMusicInBackground("music/boss12.wav",(float)1);
                    GameWin.sum--;//boss�����٣����ٵ�ǰҳ����boss����
                }
            }
        }
        //boss�Ͷ����ӵ�����ײ
        for(DoubleShellObj doubleShellObj : GameUtils.doubleShellObjList){
            if(this.getRec().intersects(doubleShellObj.getRec())&&health>0){
                doubleShellObj.setX(-100);
                doubleShellObj.setY(-100);
                GameUtils.removeList.add(doubleShellObj);
                health-=2;
                if(health<=0&&ex){
                    ex=false;
                    //���Ʊ�ը
                    ExplodeObj explodeObj=new ExplodeObj(x,y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    this.setX(-200);
                    this.setY(-200);
                    GameUtils.removeList.add(doubleShellObj);
//                    GameWin.state = 4;
                    GameWin.score +=30;
                    GameWin.playMusicInBackground("music/boss12.wav",(float)1);
                    GameWin.sum--;
                }
            }
        }
        //boss�������ӵ�����ײ
        for(TripleShellObj tripleShellObj : GameUtils.tripleShellObjList){
            if(this.getRec().intersects(tripleShellObj.getRec())&&health>0){
                tripleShellObj.setX(-100);
                tripleShellObj.setY(-100);
                GameUtils.removeList.add(tripleShellObj);
                health-=3;
                if(health<=0&&ex){
                    ex=false;
                    //���Ʊ�ը
                    ExplodeObj explodeObj=new ExplodeObj(x,y);
                    GameUtils.explodeObjList.add(explodeObj);
                    GameUtils.removeList.add(explodeObj);
                    this.setX(-200);
                    this.setY(-200);
                    GameUtils.removeList.add(tripleShellObj);
//                    GameWin.state = 4;//��boss��������Ϸ�������������Ϸ�޸�Ϊ�޾�ģʽ
                    GameWin.score +=30;
                    GameWin.playMusicInBackground("music/boss12.wav",(float)1);
                    GameWin.sum--;
                }
            }
        }
        if(GameUtils.bossObjList.size()>0&&health>0){
            //���ư�ɫ����
            g.setColor(Color.WHITE);
            g.fillRect(200,40,200,10);
            //���ƺ�ɫ����
            g.setColor(Color.RED);
            g.fillRect(200,40,health*200/30,10);
        }

    }

    @Override
    public Rectangle getRec() {
        return super.getRec();
    }
}
