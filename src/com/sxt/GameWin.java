package com.sxt;
import com.sxt.obj.*;
import com.sxt.utils.GameUtils;
import com.sxt.utils.UserDatabaseExample;
import com.sxt.utils.loginFrame;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
public class GameWin extends JFrame {
    public static int sum=0;//������¼��ǰҳ����boss�������涨ҳ���в�����ͬʱ������̨boss
    //�жϵ�ǰ�ҷ��ɻ��Ƿ����
    private static boolean planex = true;
    //���嵱ǰ�û�
    public static String username = null;
    private static boolean isLoginFrameVisible = false;
    //��ȡ���ݿ�����
    public static Connection connection;
    /* 1.���ô������� */
    //��¼��Ϸ״̬�ı���
    //0δ��ʼ 1��Ϸ�� 2��ͣ 3ʧ�� 4ͨ�� 5���� 6�̵� 7����
    public static int state = 10;
    //��ӱ���ͼ���������
    BgObj bgObj = new BgObj(GameUtils.bdImg,0,-1800,2); //ʵ����Ϸ����ͼ���ƶ�
    //����ͼƬ����
    Image offScreenImage = null;
    //�����ҷ��ɻ��Ķ���
    PlaneObj planeObj = new PlaneObj(GameUtils.planeImg,37,41,290,550,0,this);
    //��ȡ�ҷ��ӵ��Ķ���
//    ShellObj shellObj = new ShellObj(GameUtils.shellImg,14,29, planeObj.getX(), planeObj.getY(), 5,this);
    //��¼��Ϸ���ƵĴ���
    int count=1;
    //��ȡ��ʾ��ʶ�Ķ�������ֻ����һ�Σ����Բ��ô����ö���ļ���
    WarningObj warningObj = new WarningObj(GameUtils.warningImg,599,90,0,350,0,this);
    //����һ����������¼�ҷ��ɻ�����
    public static int planeindex = 0;
    //����һ����������¼��ǰ�÷�
    public static int score = 0;
    //��Ϸ��ʼ����
    public void launch(){
        this.setVisible(true);                                  //�����Ƿ�ɼ�
        this.setSize(600,800);                      //���ô��ڴ�С
        this.setLocationRelativeTo(null);                       //���ڵ�λ������Ϊ������ʾ
        this.setTitle("�ɻ���ս");                               //���ô��ڱ���
//        this.setBackground(Color.black);                        //���ô��ڱ�����ɫ
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //�رմ����Զ���������
        //������Ҫ���Ƶ���Ϸ����ȫ����������Ԫ�ؼ����н��л���
        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);


        planeindex = GameUtils.gameObjList.indexOf(planeObj);//�����õ����ҷ��ɻ�������ֵ
        //������¼�
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // �ж��������λ���Ƿ��������������򣬿�ʼ��Ϸ
                if (mouseX >= 248 && mouseX <= 375 && mouseY >= 550 && mouseY <= 580 && state == 0) {
                    // ִ�е���¼��Ĳ���
                    System.out.println("�������������ʼ��Ϸ");
                    state=1;//��Ϸ��ʼ
                }
                // �ж��������λ���Ƿ��������������򣬲鿴����
                if (mouseX >= 10 && mouseX <= 70 && mouseY >= 750 && mouseY <= 800) {
                    // ִ�е���¼��Ĳ���
                    System.out.println("���������");
                    state = 5;

                }
                // �ж��������λ���Ƿ��������������򣬲鿴�̵�
                if (mouseX >= 530 && mouseX <= 600 && mouseY >= 750 && mouseY <= 800) {
                    // ִ�е���¼��Ĳ���
                    System.out.println("������̵�");
                    state = 6;
                }

                // �ж��������λ���Ƿ��������������򣬲鿴����
                if (mouseX >= 540 && mouseX <= 580 && mouseY >= 50 && mouseY <= 80) {
                    // ִ�е���¼��Ĳ���
                    System.out.println("���������");
                    state = 7;

                }
                // �ж��������λ���Ƿ��������������򣬷�����ҳ
                if (mouseX >= 10 && mouseX <= 70 && mouseY >= 50 && mouseY <= 80 && state!=0) {
                    //��ҳ��boss������,��ֹ�ҷ��ɻ�������ʱҳ����������boss������ҳ�����޷�������boss
                    sum=0;
                    // ִ�е���¼��Ĳ���
                    System.out.println("����˷���");
                    //�����Ӧ��Ԫ��
                    GameUtils.gameObjList.clear();
                    GameUtils.removeList.clear();
                    //�з�Ԫ��
                    GameUtils.explodeObjList.clear();
                    GameUtils.enemy1ObjList.clear();
                    GameUtils.enemy2ObjList.clear();
                    GameUtils.littleBoss1List.clear();
                    GameUtils.littleBoss2List.clear();
                    GameUtils.bossObjList.clear();
                    //�ҷ��ӵ�
                    GameUtils.shellObjList.clear();
                    GameUtils.doubleShellObjList.clear();
                    GameUtils.tripleShellObjList.clear();
                    GameUtils.giftObjsList.clear();
                    //�з��ӵ�
                    GameUtils.littleBoss1BulletList.clear();
                    GameUtils.littleBoss2BulletList.clear();
                    GameUtils.bossBulletList.clear();
                    GameUtils.enemy2BulletObjList.clear();
                    //�������Ԫ��
                    GameUtils.gameObjList.add(bgObj);
                    //��������ҷ�С�ɻ�
                    planex = false;
                    createObj();
                    //���õ÷�
                    score=0;
                    //�����ӵ�
                    planeObj.times=0;
                    //�ػ�
                    count=1;
                    state = 0;
                    //���»�ȡ�ҷ��ɻ�������ֵ
                    planeindex = GameUtils.gameObjList.indexOf(GameUtils.planeObjList.get(GameUtils.planeObjList.size()-1));//�����õ����ҷ��ɻ�������ֵ
                }

//                if(e.getButton()==3&&state==0){//��������Ϸ����δ��ʼ��״̬�µ������������Ϸ��ʼ
//                    state=1;//��Ϸ��ʼ
//                }
            }
        });
        //��Ӽ��̼����¼�
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                if(e.getKeyCode()==32){
                    if(state==1){
                        state=2;
                    } else if (state==2) {
                        state=1;
                    }
                }
            }

        });
        while(true){

            createObj();
            repaint();//�ػ���Ϸҳ�棬����ִ��paint
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //����ͼ�ν��棬��ͼ�ν�����Ԫ��
    @Override
    public void paint(Graphics g) {                                 //��дpaint����
        //��ʼ��˫����ͼƬ����
        if(offScreenImage == null){
            offScreenImage = createImage(600,800); //��СҪ����Ϸ���ڴ�С��ͬ
        }
        //��ȡ˫����ͼƬ����Ļ���
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0,0,600,800);
        if (username == null&&(!isLoginFrameVisible)) {//��¼ҳ��
            isLoginFrameVisible=true;
            loginFrame loginFrame = new loginFrame();
            loginFrame.showLoginFrame();

        }

        if(state ==0){//��Ϸδ��ʼ����
            // ����Ҫ�رմ��ڵĵط����� LoginFrame ���󣬲����� closeLoginFrame() ����

            gImage.drawImage(GameUtils.bdImg, 0, 0,  null);     //������ͼƬ��ӵ�������
            gImage.drawImage(GameUtils.explodeImg, 280, 330,  null);
            gImage.drawImage(GameUtils.planeImg, 290, 470,  null);
            gImage.drawImage(GameUtils.bossImg, 190, 70,  null);
            gImage.drawImage(GameUtils.setImg, 540, 50,  null);
            //������Ϸ��ʼ���������
            gImage.setColor(Color.BLUE);      //����������ɫ
            gImage.setFont(new Font("����",Font.BOLD,30));  //�������塢�Ӵ֡���С
            gImage.drawString("��ʼ��Ϸ",248,570);       //��������
            //������Ϸ���а�����
            gImage.setColor(Color.RED);      //����������ɫ
            gImage.setFont(new Font("����",Font.BOLD,30));  //�������塢�Ӵ֡���С
            gImage.drawString("����",10,780);       //��������
            //������Ϸ�̵�����
            gImage.setColor(Color.GREEN);      //����������ɫ
            gImage.setFont(new Font("����",Font.BOLD,30));  //�������塢�Ӵ֡���С
            gImage.drawString("�̵�",530,780);       //��������

        }
        if(state==1){  //��Ϸ��ʼ����
//            bgObj.paintSelf(gImage);//������Ϸ��ʼ����
////            planeObj.paintSelf(gImage); //�����ҷ��ɻ�
////            shellObj.paintSelf(gImage); //�����ҷ��ӵ�
            //����ը������ӵ�����Ԫ�ؼ�����
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);
            //���ٵ�������ĳ����ϷԪ�أ���Ϊ������ϷԪ�ض�����������Ԫ�ؼ����У�����ֻ��Ҫ������������Ԫ�ر���������Ȼ�����������
           for(int i=0;i<GameUtils.gameObjList.size();i++){
               GameUtils.gameObjList.get(i).paintSelf(gImage);
           }//GameUtils.gameObjList.size()����ֻ���������������ΪGameUtils.gameObjList.size()-1��ᵼ�±�ըЧ���ӳٷ���
           //��Ҫ�Ƴ���Ԫ�ش�����Ԫ�ؼ�����ɾ��
            GameUtils.gameObjList.removeAll(GameUtils.removeList);
            count++;
        }
        if(state==2){
            gImage.drawImage(GameUtils.bdImg,0,0,null);
            GameUtils.drawWord(gImage,"����",Color.GREEN,30,10,70);
            GameUtils.drawWord(gImage,"��Ϸ��ͣ",Color.YELLOW,30,240,340);
        }
        if(state==3){
            gImage.drawImage(GameUtils.bdImg,0,0,null);
            GameUtils.gameObjList.removeAll(GameUtils.removeList);//��Ҫ�Ƴ���Ԫ�ش�����Ԫ�ؼ�����ɾ��
            GameUtils.drawWord(gImage,"����",Color.GREEN,30,10,70);
            GameUtils.drawWord(gImage,"��Ϸʧ��",Color.RED,30,240,340);
            try {
                UserDatabaseExample.updateRecord(connection,username,score);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(state==4){
            gImage.drawImage(GameUtils.bdImg,0,0,null );
            GameUtils.drawWord(gImage,"����",Color.GREEN,30,10,70);
            GameUtils.drawWord(gImage,"��Ϸ�ɹ�",Color.GREEN,30,240,340);
            try {
                UserDatabaseExample.updateRecord(connection,username,score);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(state==1 || state==2 || state==3 || state==4){
            //������Ϸ�Ļ������
            GameUtils.drawWord(gImage,score+"��",Color.GREEN,40,30,130);
        }

        if (state == 5) {
            // ������������λ�úʹ�С
            Rectangle leaderboardRect = new Rectangle(100, 250, 400, 450);//����λ������
            // ����Graphics2D����
            Graphics2D g2d = (Graphics2D) gImage;
            // ���Ʊ���
            g2d.drawImage(GameUtils.bdImg, 0, 0, null);
            // ���ƾ��α���
            gImage.setColor(new Color(255, 255, 255, 128)); // ���ñ�����ɫ��͸����
            gImage.fillRect(leaderboardRect.x, leaderboardRect.y, leaderboardRect.width, leaderboardRect.height);
            gImage.drawImage(GameUtils.setImg, 540, 50, null); // ����

            // ������Ϸ�̵�����
            gImage.setColor(Color.GREEN); // ����������ɫ
            gImage.setFont(new Font("����", Font.BOLD, 30)); // �������塢�Ӵ֡���С
            gImage.drawString("�̵�", 530, 780); // ��������

            GameUtils.drawWord(gImage, "����", Color.GREEN, 30, 10, 70);
            GameUtils.drawWord(gImage, "���а�", Color.RED, 30, 255, 200);

            // ������ҵ÷��б�
            gImage.setFont(new Font("����", Font.BOLD, 25)); // �������塢��С
            gImage.setColor(Color.GREEN); // ����������ɫ

            int startY = leaderboardRect.y + 50; // �б����ʼy����
            int lineHeight = 40; // ÿ�еĸ߶�
            gImage.drawString("���"+ "                "+"�÷�", leaderboardRect.x + 50, startY);
            try {
                // ��ȡǰ��λ�÷���ߵ��û�
                List<UserDatabaseExample.ScoreDataUtil.UserScore> topUserScores = UserDatabaseExample.ScoreDataUtil.getTopUserScores(connection);
                // ��������ӡ�û��÷�
                int i =0;
                for (UserDatabaseExample.ScoreDataUtil.UserScore userScore : topUserScores) {
                    String username = userScore.getUsername();
                    int score = userScore.getScore();
                    String scoreText = username+ ":" + score;
                    gImage.drawString(scoreText, leaderboardRect.x + 50, startY + (i + 1) * lineHeight);
                    i++;
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(state ==6){
            gImage.drawImage(GameUtils.bdImg,0,0,null );
            GameUtils.drawWord(gImage,"����",Color.GREEN,30,10,70);
            gImage.drawImage(GameUtils.weixinImg, 100,100,null);
            GameUtils.drawWord(gImage,"΢�� of ˧��",Color.GREEN,30,220,700);
        }
        if(state ==7){
            gImage.drawImage(GameUtils.bdImg,0,0,null );
            GameUtils.drawWord(gImage,"����",Color.GREEN,30,10,70);
            GameUtils.drawWord(gImage,"�ʽ���-��������",Color.GREEN,30,190,380);
        }
        //��˫����ͼƬ��������Ϸ������
        g.drawImage(offScreenImage,0,0,null);
        //��ʾ����Ԫ�ؼ��ϴ�С
        System.out.println(GameUtils.gameObjList.size());

    }
    //��������������������������
    void createObj(){
        if(!planex){
            GameUtils.planeObjList.add(new PlaneObj(GameUtils.planeImg,37,41, 290, 550, 0,this));
            GameUtils.gameObjList.add(GameUtils.planeObjList.get(GameUtils.planeObjList.size()-1));//��ӵ�����Ԫ�ؼ����еĶ�������new�������ӵ����󣬲����������ӵ�����
            planex = true;//���������µ��ҷ��ɻ�
        }
        if(count%10==0){   //��ҳ���ػ�10�Σ�����һ���ӵ�
            if(planeObj.times==0){//1���ӵ�
                GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg,14,29, planeObj.getX()+12, planeObj.getY()-20, 5,this));
                GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size()-1));//��ӵ�����Ԫ�ؼ����еĶ�������new�������ӵ����󣬲����������ӵ�����
            }
            if(planeObj.times==1){//2���ӵ�
                GameUtils.doubleShellObjList.add(new DoubleShellObj(GameUtils.doubleShellImg,32,64, planeObj.getX()+5, planeObj.getY()-20, 8,this));
                GameUtils.gameObjList.add(GameUtils.doubleShellObjList.get(GameUtils.doubleShellObjList.size()-1));//��ӵ�����Ԫ�ؼ����еĶ�������new�������ӵ����󣬲����������ӵ�����
            }
            if(planeObj.times==2){//3���ӵ�
                GameUtils.tripleShellObjList.add(new TripleShellObj(GameUtils.tripleShellImg,64,182, planeObj.getX()-5, planeObj.getY()-100, 15,this));
                GameUtils.gameObjList.add(GameUtils.tripleShellObjList.get(GameUtils.tripleShellObjList.size()-1));//��ӵ�����Ԫ�ؼ����еĶ�������new�������ӵ����󣬲����������ӵ�����
            }

        }
        //���ֵз��ɻ�
        if(count%13==0){//���Ƶз�С�ɻ��Ĳ����ٶ�
            GameUtils.enemy1ObjList.add(new Enemy1Obj(GameUtils.enemy1Img , 32, 24, (int)(Math.random()*10)*60, 0,5, this
            ));
            GameUtils.gameObjList.add(GameUtils.enemy1ObjList.get(GameUtils.enemy1ObjList.size()-1));
        }
        if(count%50==0){
            if(GameUtils.enemy2ObjList.size()>0){//���Ƶз���ɻ��ӵ�������
                //�����x��y�������²����ĵз���ɻ������λ�ã�����Ҫ�����λ���������з���ɻ����ӵ�
                int x = (GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size()-1)).getX();
                int y = (GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size()-1)).getY();
                GameUtils.enemy2BulletObjList.add(new Enemy2BulletObj(GameUtils.enemy2BulletImg,14,25,x+17,y+55,8,this));
                GameUtils.gameObjList.add(GameUtils.enemy2BulletObjList.get(GameUtils.enemy2BulletObjList.size()-1));
            }
        }
        if(count%100==0){//���Ƶз���ɻ��Ĳ����ٶ�
            GameUtils.enemy2ObjList.add(new Enemy2Obj(GameUtils.enemy2Img , 37, 50, (int)((Math.random()*10)*60)-12, 0,3, this
            ));
            GameUtils.gameObjList.add(GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size()-1));
        }
        if((count%500 == 0)&&(sum<2)){//��ҳ���ػ�500��ʱ��Сboss1����
            GameUtils.littleBoss1List.add(new LittleBoss1(GameUtils.littleboss1Img , 172, 112, -200, 350,3, this));
            GameUtils.gameObjList.add(GameUtils.littleBoss1List.get(GameUtils.littleBoss1List.size()-1));
            sum++;
        }
        if((count%1000 == 0)&&(sum<2)){
            GameUtils.littleBoss2List.add(new LittleBoss2(GameUtils.littleboss2Img , 172, 112, 300, -150,2, this
            ));
            GameUtils.gameObjList.add(GameUtils.littleBoss2List.get(GameUtils.littleBoss2List.size()-1));
            sum++;
        }
        if((count%1250==0)&&(sum<2)){//���ƾ�ʾ��־���ֵ�ʱ��
            GameUtils.gameObjList.add(warningObj);
        }
        if(count==1280){//�þ�ʾ��־��ʧ
            GameUtils.removeList.add(warningObj);
        }
        if(count%1500==0&&sum<2){//���µ�boss����ʱ���ҷ��ɻ��ӵ���һ��
            sum++;
            if(PlaneObj.times>1){
                PlaneObj.times--;
            }
//            GameUtils.gameObjList.add(bossObj);
            GameUtils.bossObjList.add(new BossObj(GameUtils.bossImg , 172, 112, 300, -150,2, this
            ));
            GameUtils.gameObjList.add(GameUtils.bossObjList.get(GameUtils.bossObjList.size()-1));
        }
        if(count%30==0&&GameUtils.littleBoss1List.size()>0){   //boss1�����ӵ�
            int x = (GameUtils.littleBoss1List.get(GameUtils.littleBoss1List.size()-1)).getX();
            int y = (GameUtils.littleBoss1List.get(GameUtils.littleBoss1List.size()-1)).getY();
            GameUtils.littleBoss1BulletList.add(new LittleBoss1Bullet(GameUtils.littleBoss1BulletImg,20,25, x+75, y+100, 4,this));
            GameUtils.gameObjList.add(GameUtils.littleBoss1BulletList.get(GameUtils.littleBoss1BulletList.size()-1));//��ӵ�����Ԫ�ؼ����еĶ�������new�������ӵ����󣬲����������ӵ�����
        }
        //boss2�����ӵ�
        if(count%50==0&&GameUtils.littleBoss2List.size()>0){
            int x = (GameUtils.littleBoss2List.get(GameUtils.littleBoss2List.size()-1)).getX();
            int y = (GameUtils.littleBoss2List.get(GameUtils.littleBoss2List.size()-1)).getY();
            GameUtils.littleBoss2BulletList.add(new LittleBoss2Bullet(GameUtils.littleBoss2BulletImg,21,50, x+75, y+100, 4,this));
            GameUtils.gameObjList.add(GameUtils.littleBoss2BulletList.get(GameUtils.littleBoss2BulletList.size()-1));//��ӵ�����Ԫ�ؼ����еĶ�������new�������ӵ����󣬲����������ӵ�����
        }
        //boss�����ӵ�
        if(count%20==0){
            if(GameUtils.bossObjList.size()>0){//boss1���ӵ�
                int x = (GameUtils.bossObjList.get(GameUtils.bossObjList.size()-1)).getX();
                int y = (GameUtils.bossObjList.get(GameUtils.bossObjList.size()-1)).getY();
                GameUtils.littleBoss1BulletList.add(new LittleBoss1Bullet(GameUtils.littleBoss1BulletImg,20,25, x+10, y+130, 6,this));
                GameUtils.gameObjList.add(GameUtils.littleBoss1BulletList.get(GameUtils.littleBoss1BulletList.size()-1));//��ӵ�����Ԫ�ؼ����еĶ�������new�������ӵ����󣬲����������ӵ�����
                if(count%40==0){//2���ӵ�
                    GameUtils.littleBoss2BulletList.add(new LittleBoss2Bullet(GameUtils.littleBoss2BulletImg,21,50, x+220, y+130, 10,this));
                    GameUtils.gameObjList.add(GameUtils.littleBoss2BulletList.get(GameUtils.littleBoss2BulletList.size()-1));//��ӵ�����Ԫ�ؼ����еĶ�������new�������ӵ����󣬲����������ӵ�����
                }
                //boss�ӵ�
                GameUtils.bossBulletList.add(new BossBullet(GameUtils.bossBulletImg,30,50, x+70, y+100, 9,this));
                GameUtils.gameObjList.add(GameUtils.bossBulletList.get(GameUtils.bossBulletList.size()-1));
            }
        }
        //���¼���
        if(count > 60000){
            count =1 ;//��ֹͬһʱ�̳���Ԫ�ع���
        }
    }
    //���ֲ���,��Ŀ����Ϊ��ʵ�ֵ��з��ɻ���ըʱ������Ч������Ȥζ��
    public static void playMusicInBackground(String filePath,float volume) {
        // ����һ���µ��߳�����������
        Thread musicThread = new Thread(() -> {
            try {
                playMusic(filePath,volume);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        musicThread.start();
    }
    public static void playMusic(String filePath, float volume) throws Exception {
        File file = new File(filePath);
        // ��ȡ��Ƶ������
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

        // ��ȡ��Ƶ��ʽ
        AudioFormat audioFormat = audioInputStream.getFormat();
        // ������ƵԴ������
        SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        // ����ƵԴ������
        sourceDataLine.open(audioFormat);

        // ��������
        if (sourceDataLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        }

        // ��ʼ������Ƶ
        sourceDataLine.start();

        int bufferSize = 320;
        byte[] buffer = new byte[bufferSize];
        int bytesRead;
        // ����Ƶ�������ж�ȡ���ݣ���д����ƵԴ�����н��в���
        while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
            sourceDataLine.write(buffer, 0, bytesRead);
        }

        // ���Ž�������ջ������͹ر���ƵԴ�����к���Ƶ������
        sourceDataLine.drain();
        sourceDataLine.close();
        audioInputStream.close();
    }
    //ѭ�����ű�������
    public static void playBackgroundMusic(String musicFilePath) {
        // ����һ��File���󣬱�ʾ�����ļ�
        File musicFile = new File(musicFilePath);

        try {
            // ����һ����Ƶ������
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            // ��ȡ��Ƶ��ʽ
            AudioFormat audioFormat = audioInputStream.getFormat();
            // ����һ����������Ϣ����
            DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat);
            // ���ϵͳ�Ƿ�֧��ָ������������Ϣ
            if (!AudioSystem.isLineSupported(dataLineInfo)) {
                System.out.println("��֧�ֲ��Ŵ������ļ���ʽ");
                return;
            }
            // ����һ��Clip�������ڲ�������
            Clip clip = (Clip) AudioSystem.getLine(dataLineInfo);
            // ����Ƶ������
            clip.open(audioInputStream);
            // �����Ƶ�м�����
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
//                    System.out.println("���ֲ������");
                    clip.setFramePosition(0); // ������λ������Ϊ��Ƶ����ʼλ��
                    clip.start(); // ���²�������
                }
            });

            //��ȡ��������
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            // ��ȡ���������ֵ����Сֵ
            float maxVolume = gainControl.getMaximum();
            float minVolume = gainControl.getMinimum();
            // ����10%��Ӧ������ֵ
            float volume = minVolume + (maxVolume - minVolume) * 0.6f;
            // ��������
            gainControl.setValue(volume);

            // ѭ����������
            clip.loop(Clip.LOOP_CONTINUOUSLY);
//            System.out.println("���ֿ�ʼ����");
            // ֹͣ���ֲ���
            clip.stop();
            // �ر���Ƶ������
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    //���г���
    public static void main(String[] args) throws Exception {
        //�������ݿ�
        String DB_URL = "jdbc:mysql://localhost:3306/plane_ranking";
        String DB_USERNAME = "root";
        String DB_PASSWORD = "123456";
        try {
            // ����MySQL��������
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 1) �������ݿⲢ�����쳣����
           connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("���ݿ����ӳɹ���");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //ѭ�����ű�������
        playBackgroundMusic("music/�ɻ���ս.wav");
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
