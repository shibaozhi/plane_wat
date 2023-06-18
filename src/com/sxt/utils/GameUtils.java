package com.sxt.utils;
import com.sxt.obj.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
public class GameUtils {
    //΢��ͼƬ
    public static Image weixinImg = Toolkit.getDefaultToolkit().getImage("imgs/tubiao/΢��.jpg");
    //��ȡ����ͼƬ
    public static Image bdImg = Toolkit.getDefaultToolkit().getImage("imgs/bg.jpg");
    //��ȡ����ͼ��
    public static Image setImg = Toolkit.getDefaultToolkit().getImage("imgs/tubiao/����.png");
    //��ȡbossͼƬ
    public static Image bossImg = Toolkit.getDefaultToolkit().getImage("imgs/boss.png");
    //��ȡ��ըͼƬ
    public static Image explodeImg = Toolkit.getDefaultToolkit().getImage("imgs/explode/e6.gif");
    //��ȡ�ҷ��ɻ�ͼƬ
    public static Image planeImg = Toolkit.getDefaultToolkit().getImage("imgs/plane.png");
    //��ȡ�ҷ��ɻ��ӵ���ͼƬ
    public static Image shellImg = Toolkit.getDefaultToolkit().getImage("imgs/shell.png");
    //��ȡ�з�С�ɻ���ͼƬ
    public static Image enemy1Img = Toolkit.getDefaultToolkit().getImage("imgs/enemy1.png");
    //��ȡ�з���ɻ���ͼƬ
    public static Image enemy2Img = Toolkit.getDefaultToolkit().getImage("imgs/enemy2.png");
    //��ȡ�з���ɻ��ӵ���ͼƬ
    public static Image enemy2BulletImg = Toolkit.getDefaultToolkit().getImage("imgs/enemy2bullet.png");
    //��ȡ�з�Boss1��ͼƬ
    public static Image littleboss1Img = Toolkit.getDefaultToolkit().getImage("imgs/littleboss1.png");
    //��ȡ�з�Boss2��ͼƬ
    public static Image littleboss2Img = Toolkit.getDefaultToolkit().getImage("imgs/littleboss2.png");
    //��ȡ�з�1��boss�ӵ���ͼƬ
    public static Image littleBoss1BulletImg = Toolkit.getDefaultToolkit().getImage("imgs/littleboss1bullet.png");
    //��ȡ�з�2��boss�ӵ���ͼƬ
    public static Image littleBoss2BulletImg = Toolkit.getDefaultToolkit().getImage("imgs/littleboss2bullet.png");
    //��ȡ������ͼƬ
    public static Image giftImg = Toolkit.getDefaultToolkit().getImage("imgs/gift.png");
    //��ȡ�����ӵ���ͼƬ
    public static Image doubleShellImg = Toolkit.getDefaultToolkit().getImage("imgs/doubleshell.png");
    //��ȡ�����ӵ���ͼƬ
    public static Image tripleShellImg = Toolkit.getDefaultToolkit().getImage("imgs/tripleshell.png");
    //boss�ӵ���ͼƬ
    public static Image bossBulletImg = Toolkit.getDefaultToolkit().getImage("imgs/bossbullet.png");
    //��ȡ��ʾͼƬ
    public static Image warningImg = Toolkit.getDefaultToolkit().getImage("imgs/warning.gif");
    //�����ҷ��ӵ��ļ���
    public static List<ShellObj> shellObjList = new ArrayList<>();
    //�����ҷ�С�ɻ��ļ���
    public static List<PlaneObj> planeObjList = new ArrayList<>();
    //�����з�С�ɻ��ļ���
    public static List<Enemy1Obj> enemy1ObjList = new ArrayList<>();
    //�����з���ɻ��ļ���
    public static List<Enemy2Obj> enemy2ObjList = new ArrayList<>();
    //�����з�1��boss�ļ���
    public static List<LittleBoss1> littleBoss1List = new ArrayList<>();
    //�����з�2��boss�ļ���
    public static List<LittleBoss2> littleBoss2List = new ArrayList<>();
    //�����з�boss�ļ���
    public static List<BossObj> bossObjList = new ArrayList<>();
    //�з���ɻ��ӵ��ļ���
    public static List<Enemy2BulletObj> enemy2BulletObjList = new ArrayList<>();
    //����Ԫ�صļ���
    public static List<GameObj> gameObjList = new ArrayList<>();

    //�Ƴ���Ϸ����Ԫ�صļ���
    public static List<GameObj> removeList = new ArrayList<>();
    //��ը����
    public static List<ExplodeObj> explodeObjList=new ArrayList<>();
    //1���ӵ��ļ���
    public static List<LittleBoss1Bullet> littleBoss1BulletList=new ArrayList<>();
    //2���ӵ��ļ���
    public static List<LittleBoss2Bullet> littleBoss2BulletList=new ArrayList<>();
    //�����ļ���
    public static List<GiftObj> giftObjsList=new ArrayList<>();
    //�����ӵ��ļ���
    public static List<DoubleShellObj> doubleShellObjList=new ArrayList<>();
    //�����ӵ��ļ���
    public static List<TripleShellObj> tripleShellObjList=new ArrayList<>();
    //boss�ӵ��ļ���
    public static List<BossBullet> bossBulletList=new ArrayList<>();
    //��������������������ֵ�
    public static void drawWord(Graphics gImage,String str,Color color,int size,int x,int y){
        gImage.setColor(color);
        gImage.setFont(new Font("����",Font.BOLD,size));
        gImage.drawString(str,x,y);
    }
}
