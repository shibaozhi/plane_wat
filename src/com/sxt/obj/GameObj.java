package com.sxt.obj;

import com.sxt.GameWin;

import java.awt.*;

//������ϷԪ�صĸ���
public class GameObj {
    //Ԫ�ص�ͼƬ
    Image img;
    //��ϷԪ�صĴ�С
    int width;
    int height;
    //��ϷԪ�ص�λ��
    int x;
    int y;
    //Ԫ�ص��˶��ٶ�
    double speed;
    //������
    GameWin frame;
    //set��get����
    public Image getImg() {
        return img;
    }

    public void setImg(Image img) {
        this.img = img;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public GameWin getFrame() {
        return frame;
    }

    public void setFrame(GameWin frame) {
        this.frame = frame;
    }
    //���췽��
    public GameObj() {

    }

    public GameObj(Image img, int x, int y, double speed) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.speed = speed;
    }

    public GameObj(Image img, int width, int height, int x, int y, double speed, GameWin frame) {
        this.img = img;
        this.width = width;
        this.height = height;
        this.x = x;
        this.y = y;
        this.speed = speed;
        this.frame = frame;
    }

    public GameObj(int x, int y) {
        this.x = x;
        this.y = y;
    }

    //����Ԫ������ķ���
    public void paintSelf(Graphics g){
        g.drawImage(img,x,y,null);      //����ͼƬλ��,��ͼƬ���Ƶ�������
    }
    //��ȡ������εķ���������������ײ���
    public Rectangle getRec(){
        return new Rectangle(x,y,width,height);
    }
}
