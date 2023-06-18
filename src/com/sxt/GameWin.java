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
    public static int sum=0;//用来记录当前页面内boss数量，规定页面中不可以同时出现三台boss
    //判断当前我方飞机是否存在
    private static boolean planex = true;
    //定义当前用户
    public static String username = null;
    private static boolean isLoginFrameVisible = false;
    //获取数据库连接
    public static Connection connection;
    /* 1.设置窗口属性 */
    //记录游戏状态的变量
    //0未开始 1游戏中 2暂停 3失败 4通关 5排行 6商店 7设置
    public static int state = 10;
    //添加背景图对象的属性
    BgObj bgObj = new BgObj(GameUtils.bdImg,0,-1800,2); //实现游戏背景图的移动
    //定义图片变量
    Image offScreenImage = null;
    //引入我方飞机的对象
    PlaneObj planeObj = new PlaneObj(GameUtils.planeImg,37,41,290,550,0,this);
    //获取我方子弹的对象
//    ShellObj shellObj = new ShellObj(GameUtils.shellImg,14,29, planeObj.getX(), planeObj.getY(), 5,this);
    //记录游戏绘制的次数
    int count=1;
    //获取警示标识的对象，由于只出现一次，所以不用创建该对象的集合
    WarningObj warningObj = new WarningObj(GameUtils.warningImg,599,90,0,350,0,this);
    //定义一个变量来记录我方飞机索引
    public static int planeindex = 0;
    //定义一个变量来记录当前得分
    public static int score = 0;
    //游戏开始界面
    public void launch(){
        this.setVisible(true);                                  //窗口是否可见
        this.setSize(600,800);                      //设置窗口大小
        this.setLocationRelativeTo(null);                       //窗口的位置设置为居中显示
        this.setTitle("飞机大战");                               //设置窗口标题
//        this.setBackground(Color.black);                        //设置窗口背景颜色
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   //关闭窗口自动结束进程
        //将所有要绘制的游戏物体全部放入所有元素集合中进行绘制
        GameUtils.gameObjList.add(bgObj);
        GameUtils.gameObjList.add(planeObj);


        planeindex = GameUtils.gameObjList.indexOf(planeObj);//这里拿到了我方飞机的索引值
        //鼠标点击事件
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int mouseX = e.getX();
                int mouseY = e.getY();

                // 判断鼠标点击的位置是否在文字所在区域，开始游戏
                if (mouseX >= 248 && mouseX <= 375 && mouseY >= 550 && mouseY <= 580 && state == 0) {
                    // 执行点击事件的操作
                    System.out.println("点击了鼠标左键开始游戏");
                    state=1;//游戏开始
                }
                // 判断鼠标点击的位置是否在文字所在区域，查看排行
                if (mouseX >= 10 && mouseX <= 70 && mouseY >= 750 && mouseY <= 800) {
                    // 执行点击事件的操作
                    System.out.println("点击了排行");
                    state = 5;

                }
                // 判断鼠标点击的位置是否在文字所在区域，查看商店
                if (mouseX >= 530 && mouseX <= 600 && mouseY >= 750 && mouseY <= 800) {
                    // 执行点击事件的操作
                    System.out.println("点击了商店");
                    state = 6;
                }

                // 判断鼠标点击的位置是否在文字所在区域，查看设置
                if (mouseX >= 540 && mouseX <= 580 && mouseY >= 50 && mouseY <= 80) {
                    // 执行点击事件的操作
                    System.out.println("点击了设置");
                    state = 7;

                }
                // 判断鼠标点击的位置是否在文字所在区域，返回首页
                if (mouseX >= 10 && mouseX <= 70 && mouseY >= 50 && mouseY <= 80 && state!=0) {
                    //将页面boss数置零,防止我方飞机被击毁时页面内有两架boss机导致页面内无法再生成boss
                    sum=0;
                    // 执行点击事件的操作
                    System.out.println("点击了返回");
                    //清除相应的元素
                    GameUtils.gameObjList.clear();
                    GameUtils.removeList.clear();
                    //敌方元素
                    GameUtils.explodeObjList.clear();
                    GameUtils.enemy1ObjList.clear();
                    GameUtils.enemy2ObjList.clear();
                    GameUtils.littleBoss1List.clear();
                    GameUtils.littleBoss2List.clear();
                    GameUtils.bossObjList.clear();
                    //我方子弹
                    GameUtils.shellObjList.clear();
                    GameUtils.doubleShellObjList.clear();
                    GameUtils.tripleShellObjList.clear();
                    GameUtils.giftObjsList.clear();
                    //敌方子弹
                    GameUtils.littleBoss1BulletList.clear();
                    GameUtils.littleBoss2BulletList.clear();
                    GameUtils.bossBulletList.clear();
                    GameUtils.enemy2BulletObjList.clear();
                    //重新添加元素
                    GameUtils.gameObjList.add(bgObj);
                    //重新添加我方小飞机
                    planex = false;
                    createObj();
                    //重置得分
                    score=0;
                    //重置子弹
                    planeObj.times=0;
                    //重绘
                    count=1;
                    state = 0;
                    //重新获取我方飞机的索引值
                    planeindex = GameUtils.gameObjList.indexOf(GameUtils.planeObjList.get(GameUtils.planeObjList.size()-1));//这里拿到了我方飞机的索引值
                }

//                if(e.getButton()==3&&state==0){//当我们游戏处于未开始的状态下点击鼠标左键，游戏开始
//                    state=1;//游戏开始
//                }
            }
        });
        //添加键盘监听事件
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
            repaint();//重绘游戏页面，重新执行paint
            try {
                Thread.sleep(25);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    //绘制图形界面，及图形界面内元素
    @Override
    public void paint(Graphics g) {                                 //重写paint方法
        //初始化双缓存图片对象
        if(offScreenImage == null){
            offScreenImage = createImage(600,800); //大小要和游戏窗口大小相同
        }
        //获取双缓存图片对象的画笔
        Graphics gImage = offScreenImage.getGraphics();
        gImage.fillRect(0,0,600,800);
        if (username == null&&(!isLoginFrameVisible)) {//登录页面
            isLoginFrameVisible=true;
            loginFrame loginFrame = new loginFrame();
            loginFrame.showLoginFrame();

        }

        if(state ==0){//游戏未开始界面
            // 在需要关闭窗口的地方创建 LoginFrame 对象，并调用 closeLoginFrame() 方法

            gImage.drawImage(GameUtils.bdImg, 0, 0,  null);     //将背景图片添加到窗体中
            gImage.drawImage(GameUtils.explodeImg, 280, 330,  null);
            gImage.drawImage(GameUtils.planeImg, 290, 470,  null);
            gImage.drawImage(GameUtils.bossImg, 190, 70,  null);
            gImage.drawImage(GameUtils.setImg, 540, 50,  null);
            //绘制游戏开始界面的文字
            gImage.setColor(Color.BLUE);      //设置文字颜色
            gImage.setFont(new Font("仿宋",Font.BOLD,30));  //设置字体、加粗、大小
            gImage.drawString("开始游戏",248,570);       //设置内容
            //绘制游戏排行榜文字
            gImage.setColor(Color.RED);      //设置文字颜色
            gImage.setFont(new Font("仿宋",Font.BOLD,30));  //设置字体、加粗、大小
            gImage.drawString("排行",10,780);       //设置内容
            //绘制游戏商店文字
            gImage.setColor(Color.GREEN);      //设置文字颜色
            gImage.setFont(new Font("仿宋",Font.BOLD,30));  //设置字体、加粗、大小
            gImage.drawString("商店",530,780);       //设置内容

        }
        if(state==1){  //游戏开始界面
//            bgObj.paintSelf(gImage);//绘制游戏开始界面
////            planeObj.paintSelf(gImage); //绘制我方飞机
////            shellObj.paintSelf(gImage); //绘制我方子弹
            //将爆炸集合添加到所有元素集合中
            GameUtils.gameObjList.addAll(GameUtils.explodeObjList);
            //不再单独绘制某个游戏元素，因为所有游戏元素都放入了所有元素集合中，这里只需要将集合中所有元素遍历出来，然后绘制自身即可
           for(int i=0;i<GameUtils.gameObjList.size();i++){
               GameUtils.gameObjList.get(i).paintSelf(gImage);
           }//GameUtils.gameObjList.size()这里只能是这样，如果换为GameUtils.gameObjList.size()-1则会导致爆炸效果延迟发生
           //将要移除的元素从所有元素集合中删除
            GameUtils.gameObjList.removeAll(GameUtils.removeList);
            count++;
        }
        if(state==2){
            gImage.drawImage(GameUtils.bdImg,0,0,null);
            GameUtils.drawWord(gImage,"返回",Color.GREEN,30,10,70);
            GameUtils.drawWord(gImage,"游戏暂停",Color.YELLOW,30,240,340);
        }
        if(state==3){
            gImage.drawImage(GameUtils.bdImg,0,0,null);
            GameUtils.gameObjList.removeAll(GameUtils.removeList);//将要移除的元素从所有元素集合中删除
            GameUtils.drawWord(gImage,"返回",Color.GREEN,30,10,70);
            GameUtils.drawWord(gImage,"游戏失败",Color.RED,30,240,340);
            try {
                UserDatabaseExample.updateRecord(connection,username,score);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(state==4){
            gImage.drawImage(GameUtils.bdImg,0,0,null );
            GameUtils.drawWord(gImage,"返回",Color.GREEN,30,10,70);
            GameUtils.drawWord(gImage,"游戏成功",Color.GREEN,30,240,340);
            try {
                UserDatabaseExample.updateRecord(connection,username,score);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        if(state==1 || state==2 || state==3 || state==4){
            //绘制游戏的积分面板
            GameUtils.drawWord(gImage,score+"分",Color.GREEN,40,30,130);
        }

        if (state == 5) {
            // 定义矩形区域的位置和大小
            Rectangle leaderboardRect = new Rectangle(100, 250, 400, 450);//矩形位置设置
            // 创建Graphics2D对象
            Graphics2D g2d = (Graphics2D) gImage;
            // 绘制背景
            g2d.drawImage(GameUtils.bdImg, 0, 0, null);
            // 绘制矩形背景
            gImage.setColor(new Color(255, 255, 255, 128)); // 设置背景颜色和透明度
            gImage.fillRect(leaderboardRect.x, leaderboardRect.y, leaderboardRect.width, leaderboardRect.height);
            gImage.drawImage(GameUtils.setImg, 540, 50, null); // 设置

            // 绘制游戏商店文字
            gImage.setColor(Color.GREEN); // 设置文字颜色
            gImage.setFont(new Font("仿宋", Font.BOLD, 30)); // 设置字体、加粗、大小
            gImage.drawString("商店", 530, 780); // 设置内容

            GameUtils.drawWord(gImage, "返回", Color.GREEN, 30, 10, 70);
            GameUtils.drawWord(gImage, "排行榜", Color.RED, 30, 255, 200);

            // 绘制玩家得分列表
            gImage.setFont(new Font("仿宋", Font.BOLD, 25)); // 设置字体、大小
            gImage.setColor(Color.GREEN); // 设置文字颜色

            int startY = leaderboardRect.y + 50; // 列表的起始y坐标
            int lineHeight = 40; // 每行的高度
            gImage.drawString("玩家"+ "                "+"得分", leaderboardRect.x + 50, startY);
            try {
                // 获取前六位得分最高的用户
                List<UserDatabaseExample.ScoreDataUtil.UserScore> topUserScores = UserDatabaseExample.ScoreDataUtil.getTopUserScores(connection);
                // 遍历并打印用户得分
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
            GameUtils.drawWord(gImage,"返回",Color.GREEN,30,10,70);
            gImage.drawImage(GameUtils.weixinImg, 100,100,null);
            GameUtils.drawWord(gImage,"微信 of 帅哥",Color.GREEN,30,220,700);
        }
        if(state ==7){
            gImage.drawImage(GameUtils.bdImg,0,0,null );
            GameUtils.drawWord(gImage,"返回",Color.GREEN,30,10,70);
            GameUtils.drawWord(gImage,"资金不足-开发待定",Color.GREEN,30,190,380);
        }
        //将双缓存图片绘制在游戏窗口中
        g.drawImage(offScreenImage,0,0,null);
        //显示所有元素集合大小
        System.out.println(GameUtils.gameObjList.size());

    }
    //整个方法是用来批量创建物体
    void createObj(){
        if(!planex){
            GameUtils.planeObjList.add(new PlaneObj(GameUtils.planeImg,37,41, 290, 550, 0,this));
            GameUtils.gameObjList.add(GameUtils.planeObjList.get(GameUtils.planeObjList.size()-1));//添加到所有元素集合中的对象，是新new出来的子弹对象，并不是整个子弹集合
            planex = true;//不再生成新的我方飞机
        }
        if(count%10==0){   //当页面重绘10次，生成一个子弹
            if(planeObj.times==0){//1级子弹
                GameUtils.shellObjList.add(new ShellObj(GameUtils.shellImg,14,29, planeObj.getX()+12, planeObj.getY()-20, 5,this));
                GameUtils.gameObjList.add(GameUtils.shellObjList.get(GameUtils.shellObjList.size()-1));//添加到所有元素集合中的对象，是新new出来的子弹对象，并不是整个子弹集合
            }
            if(planeObj.times==1){//2级子弹
                GameUtils.doubleShellObjList.add(new DoubleShellObj(GameUtils.doubleShellImg,32,64, planeObj.getX()+5, planeObj.getY()-20, 8,this));
                GameUtils.gameObjList.add(GameUtils.doubleShellObjList.get(GameUtils.doubleShellObjList.size()-1));//添加到所有元素集合中的对象，是新new出来的子弹对象，并不是整个子弹集合
            }
            if(planeObj.times==2){//3级子弹
                GameUtils.tripleShellObjList.add(new TripleShellObj(GameUtils.tripleShellImg,64,182, planeObj.getX()-5, planeObj.getY()-100, 15,this));
                GameUtils.gameObjList.add(GameUtils.tripleShellObjList.get(GameUtils.tripleShellObjList.size()-1));//添加到所有元素集合中的对象，是新new出来的子弹对象，并不是整个子弹集合
            }

        }
        //两种敌方飞机
        if(count%13==0){//控制敌方小飞机的产生速度
            GameUtils.enemy1ObjList.add(new Enemy1Obj(GameUtils.enemy1Img , 32, 24, (int)(Math.random()*10)*60, 0,5, this
            ));
            GameUtils.gameObjList.add(GameUtils.enemy1ObjList.get(GameUtils.enemy1ObjList.size()-1));
        }
        if(count%50==0){
            if(GameUtils.enemy2ObjList.size()>0){//控制敌方大飞机子弹的速率
                //这里的x和y就是最新产生的敌方大飞机对象的位置，我们要用这个位置来产生敌方打飞机的子弹
                int x = (GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size()-1)).getX();
                int y = (GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size()-1)).getY();
                GameUtils.enemy2BulletObjList.add(new Enemy2BulletObj(GameUtils.enemy2BulletImg,14,25,x+17,y+55,8,this));
                GameUtils.gameObjList.add(GameUtils.enemy2BulletObjList.get(GameUtils.enemy2BulletObjList.size()-1));
            }
        }
        if(count%100==0){//控制敌方大飞机的产生速度
            GameUtils.enemy2ObjList.add(new Enemy2Obj(GameUtils.enemy2Img , 37, 50, (int)((Math.random()*10)*60)-12, 0,3, this
            ));
            GameUtils.gameObjList.add(GameUtils.enemy2ObjList.get(GameUtils.enemy2ObjList.size()-1));
        }
        if((count%500 == 0)&&(sum<2)){//当页面重绘500次时，小boss1出现
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
        if((count%1250==0)&&(sum<2)){//控制警示标志出现的时间
            GameUtils.gameObjList.add(warningObj);
        }
        if(count==1280){//让警示标志消失
            GameUtils.removeList.add(warningObj);
        }
        if(count%1500==0&&sum<2){//当新的boss出现时将我方飞机子弹降一级
            sum++;
            if(PlaneObj.times>1){
                PlaneObj.times--;
            }
//            GameUtils.gameObjList.add(bossObj);
            GameUtils.bossObjList.add(new BossObj(GameUtils.bossImg , 172, 112, 300, -150,2, this
            ));
            GameUtils.gameObjList.add(GameUtils.bossObjList.get(GameUtils.bossObjList.size()-1));
        }
        if(count%30==0&&GameUtils.littleBoss1List.size()>0){   //boss1产生子弹
            int x = (GameUtils.littleBoss1List.get(GameUtils.littleBoss1List.size()-1)).getX();
            int y = (GameUtils.littleBoss1List.get(GameUtils.littleBoss1List.size()-1)).getY();
            GameUtils.littleBoss1BulletList.add(new LittleBoss1Bullet(GameUtils.littleBoss1BulletImg,20,25, x+75, y+100, 4,this));
            GameUtils.gameObjList.add(GameUtils.littleBoss1BulletList.get(GameUtils.littleBoss1BulletList.size()-1));//添加到所有元素集合中的对象，是新new出来的子弹对象，并不是整个子弹集合
        }
        //boss2产生子弹
        if(count%50==0&&GameUtils.littleBoss2List.size()>0){
            int x = (GameUtils.littleBoss2List.get(GameUtils.littleBoss2List.size()-1)).getX();
            int y = (GameUtils.littleBoss2List.get(GameUtils.littleBoss2List.size()-1)).getY();
            GameUtils.littleBoss2BulletList.add(new LittleBoss2Bullet(GameUtils.littleBoss2BulletImg,21,50, x+75, y+100, 4,this));
            GameUtils.gameObjList.add(GameUtils.littleBoss2BulletList.get(GameUtils.littleBoss2BulletList.size()-1));//添加到所有元素集合中的对象，是新new出来的子弹对象，并不是整个子弹集合
        }
        //boss产生子弹
        if(count%20==0){
            if(GameUtils.bossObjList.size()>0){//boss1号子弹
                int x = (GameUtils.bossObjList.get(GameUtils.bossObjList.size()-1)).getX();
                int y = (GameUtils.bossObjList.get(GameUtils.bossObjList.size()-1)).getY();
                GameUtils.littleBoss1BulletList.add(new LittleBoss1Bullet(GameUtils.littleBoss1BulletImg,20,25, x+10, y+130, 6,this));
                GameUtils.gameObjList.add(GameUtils.littleBoss1BulletList.get(GameUtils.littleBoss1BulletList.size()-1));//添加到所有元素集合中的对象，是新new出来的子弹对象，并不是整个子弹集合
                if(count%40==0){//2号子弹
                    GameUtils.littleBoss2BulletList.add(new LittleBoss2Bullet(GameUtils.littleBoss2BulletImg,21,50, x+220, y+130, 10,this));
                    GameUtils.gameObjList.add(GameUtils.littleBoss2BulletList.get(GameUtils.littleBoss2BulletList.size()-1));//添加到所有元素集合中的对象，是新new出来的子弹对象，并不是整个子弹集合
                }
                //boss子弹
                GameUtils.bossBulletList.add(new BossBullet(GameUtils.bossBulletImg,30,50, x+70, y+100, 9,this));
                GameUtils.gameObjList.add(GameUtils.bossBulletList.get(GameUtils.bossBulletList.size()-1));
            }
        }
        //重新计数
        if(count > 60000){
            count =1 ;//防止同一时刻出现元素过多
        }
    }
    //音乐播放,其目的是为了实现当敌方飞机爆炸时产生音效，增加趣味性
    public static void playMusicInBackground(String filePath,float volume) {
        // 创建一个新的线程来播放音乐
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
        // 获取音频输入流
        AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);

        // 获取音频格式
        AudioFormat audioFormat = audioInputStream.getFormat();
        // 创建音频源数据行
        SourceDataLine sourceDataLine = AudioSystem.getSourceDataLine(audioFormat);
        // 打开音频源数据行
        sourceDataLine.open(audioFormat);

        // 调整音量
        if (sourceDataLine.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            FloatControl gainControl = (FloatControl) sourceDataLine.getControl(FloatControl.Type.MASTER_GAIN);
            gainControl.setValue(volume);
        }

        // 开始播放音频
        sourceDataLine.start();

        int bufferSize = 320;
        byte[] buffer = new byte[bufferSize];
        int bytesRead;
        // 从音频输入流中读取数据，并写入音频源数据行进行播放
        while ((bytesRead = audioInputStream.read(buffer, 0, buffer.length)) != -1) {
            sourceDataLine.write(buffer, 0, bytesRead);
        }

        // 播放结束后，清空缓冲区和关闭音频源数据行和音频输入流
        sourceDataLine.drain();
        sourceDataLine.close();
        audioInputStream.close();
    }
    //循环播放背景音乐
    public static void playBackgroundMusic(String musicFilePath) {
        // 创建一个File对象，表示音乐文件
        File musicFile = new File(musicFilePath);

        try {
            // 创建一个音频输入流
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(musicFile);
            // 获取音频格式
            AudioFormat audioFormat = audioInputStream.getFormat();
            // 创建一个数据行信息对象
            DataLine.Info dataLineInfo = new DataLine.Info(Clip.class, audioFormat);
            // 检查系统是否支持指定的数据行信息
            if (!AudioSystem.isLineSupported(dataLineInfo)) {
                System.out.println("不支持播放此音乐文件格式");
                return;
            }
            // 创建一个Clip对象，用于播放音乐
            Clip clip = (Clip) AudioSystem.getLine(dataLineInfo);
            // 打开音频输入流
            clip.open(audioInputStream);
            // 添加音频行监听器
            clip.addLineListener(event -> {
                if (event.getType() == LineEvent.Type.STOP) {
//                    System.out.println("音乐播放完毕");
                    clip.setFramePosition(0); // 将播放位置设置为音频的起始位置
                    clip.start(); // 重新播放音乐
                }
            });

            //获取音量控制
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            // 获取音量的最大值和最小值
            float maxVolume = gainControl.getMaximum();
            float minVolume = gainControl.getMinimum();
            // 计算10%对应的音量值
            float volume = minVolume + (maxVolume - minVolume) * 0.6f;
            // 设置音量
            gainControl.setValue(volume);

            // 循环播放音乐
            clip.loop(Clip.LOOP_CONTINUOUSLY);
//            System.out.println("音乐开始播放");
            // 停止音乐播放
            clip.stop();
            // 关闭音频输入流
            audioInputStream.close();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    //运行程序
    public static void main(String[] args) throws Exception {
        //连接数据库
        String DB_URL = "jdbc:mysql://localhost:3306/plane_ranking";
        String DB_USERNAME = "root";
        String DB_PASSWORD = "123456";
        try {
            // 加载MySQL驱动程序
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 1) 连接数据库并进行异常处理
           connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
            System.out.println("数据库连接成功！");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        //循环播放背景音乐
        playBackgroundMusic("music/飞机大战.wav");
        GameWin gameWin = new GameWin();
        gameWin.launch();
    }
}
