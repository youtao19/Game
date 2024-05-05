package GameCenter;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;

public class GameGUI extends JFrame implements KeyListener,ActionListener{
    int [][] data = new int[4][4];
    //空白方块在二维数组中的位置
    int x = 0, y = 0;

    //定义一个变量，记录当前展示图片的路径
    String path = "src/Image/animal/animal3/";

    //定义一个二维数组，储存正确的数据
    int[][] win = {
            {1, 2, 3, 4},
            {5, 6, 7, 8},
            {9, 10, 11, 12},
            {13, 14, 15, 0}
    };

    //统计步数。
    int step = 0;

    JMenuBar menuBar = new JMenuBar();
    JMenu about = new JMenu("关于我们");
    JMenu fun = new JMenu("功能");
    JMenu changePicture = new JMenu("更换图片");

    JMenuItem replayGame = new JMenuItem("重新游戏");
    JMenuItem winGame = new JMenuItem("一键通关");
    JMenuItem diposeGame = new JMenuItem("关闭游戏");
    JMenuItem reLoginGame = new JMenuItem("重新登录");
    JMenuItem helpItem = new JMenuItem("帮助");
    JMenuItem redClother = new JMenuItem("红色衣服");
    JMenuItem girlItem = new JMenuItem("女孩");
    JMenuItem girl2Item = new JMenuItem("女孩2");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem blueHeartItem = new JMenuItem("蓝色爱心");
    JMenuItem blueHairItem = new JMenuItem("蓝色头发");
    JMenuItem blueEyeItem = new JMenuItem("蓝色对视");



    public GameGUI() {
        //主界面UI
        UiFrame();
        //菜单栏目
        menuInit();

        //初始化数据（打乱）
        initData();

        //初始化图片
        imageInit();

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }


    //初始化数据
    private void initData() {

        int[] temp = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};

        Random r = new Random();

        for(int i = 0;i < temp.length; i++){

            int index = r.nextInt(temp.length);
            int t = temp[i];
            temp[i] = temp[index];
            temp[index] = t;
        }

        for(int i = 0;i < temp.length; i++){
            if(temp[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = temp[i];
        }
    }

    //图片初始化
    private void imageInit() {
        //清空原本已经出现的所有图片
        this.getContentPane().removeAll();

        //显示胜利的图标
        if (victory()) {
            JLabel winJlabel = new JLabel(new ImageIcon("src/Image/win.png"));
            winJlabel.setBounds(203,283,197,73);
            this.getContentPane().add(winJlabel);
        }

        JLabel stepJLabel = new JLabel("步数" + step);
        stepJLabel.setBounds(50,30,100,20);
        this.getContentPane().add(stepJLabel);


        for(int i = 0;i < 4;i++){
            for(int j = 0;j < 4;j++){
                int idx = data[i][j];
                JLabel label = new JLabel(new ImageIcon(path + idx +".jpg"));
                label.setBounds(105 * j + 83,105 * i + 134,105,105);
                label.setBorder(new BevelBorder(1));
                this.getContentPane().add(label);
            }
        }

        //添加背景图片
        JLabel bg = new JLabel(new ImageIcon("src/Image/background.png"));
        bg.setBounds(40, 40, 508, 560);
        this.getContentPane().add(bg);

        this.getContentPane().repaint();

    }
    //主界面设置
    private void UiFrame() {
        setTitle("拼图游戏");
        setAlwaysOnTop(true);
        setSize(603,680);
        setLocationRelativeTo(null);
        setLayout(null);
        addKeyListener(this);
    }

    //菜单栏目
    private void menuInit() {

        setJMenuBar(menuBar);

        menuBar.add(fun);
        menuBar.add(about);

        fun.add(changePicture);
        fun.add(replayGame);
        fun.add(winGame);
        fun.add(diposeGame);
        fun.add(reLoginGame);


        changePicture.add(redClother);
        changePicture.add(animalItem);
        changePicture.add(girlItem);
        changePicture.add(blueHeartItem);
        changePicture.add(blueHairItem);
        changePicture.add(blueEyeItem);
        changePicture.add(girl2Item);


        about.add(helpItem);

        replayGame.addActionListener(this);
        diposeGame.addActionListener(this);
        reLoginGame.addActionListener(this);
        helpItem.addActionListener(this);
        redClother.addActionListener(this);
        blueHeartItem.addActionListener(this);
        blueHairItem.addActionListener(this);
        blueEyeItem.addActionListener(this);
        animalItem.addActionListener(this);
        girlItem.addActionListener(this);
        girl2Item.addActionListener(this);
        winGame.addActionListener(this);//一键通关

    }


    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override//按下不松时
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            //把界面中所有的图片全部删除
            this.getContentPane().removeAll();
            //加载第一张完整图片
            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,134,420,420);
            this.getContentPane().add(all);
            //加载背景图片
            JLabel bg = new JLabel(new ImageIcon("src/Image/background.png"));
            bg.setBounds(40, 40, 508, 560);
            this.getContentPane().add(bg);

            this.getContentPane().repaint();

        }
    }

    @Override//松开按键的时候
    public void keyReleased(KeyEvent e) {
        //判断游戏是否胜利，如果胜利，则不能进行下列移动
        if (victory()) {
            return;
        }


        //左：37，上：38，右：39，下：40；
        int code = e.getKeyCode();

        if(code == 37){//左
            if(y == 3){
                return;
            }
            data[x][y] = data[x][y + 1];
            data[x][y + 1] = 0;
            y++;
            step++;
            imageInit();
        }
        else if(code == 38){//上
            //空白方块已经在最下方
            if(x == 3){
                return;
            }
            data[x][y] = data[x + 1][y];
            data[x + 1][y] = 0;
            x++;
            step++;
            imageInit();
        }
        else if(code == 39){//右
            if(y == 0) return;
            data[x][y] = data[x][y - 1];
            data[x][y - 1] = 0;
            y--;
            step++;
            imageInit();
        }
        else if(code == 40){//下
            if(x == 0) return;
            data[x][y] = data[x - 1][y];
            data[x - 1][y] = 0;
            x--;
            step++;
            imageInit();
        }
        else if(code == 65){
            imageInit();
        }
        else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            imageInit();
        }
    }


    //判断data数组中的数据是否根win数组中相同
    //全部相同返回true，否则返回false；
    public boolean victory(){
        for (int i = 0; i < data.length; i++) {
            //i：
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    //有一个不一样，则返回false
                    return false;
                }
            }
        }
        //全部一样
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //获取当前被点击的方法
        Object obj = e.getSource();
        //重新游戏
        if(obj == replayGame){
            System.out.println("重新游戏");

            initData();//重新加载数据
            step = 0;//计步器清零
            imageInit();//重新加载图片
        }
        //重新登陆
        else if(obj == reLoginGame){
            System.out.println("重新登陆");

            this.setVisible(false);//关闭当前窗口
            new Login();//打开登录界面

        }
        //帮助
        else if (obj == helpItem) {
            System.out.println("帮助");
            JDialog jDialog = new JDialog();
            JLabel jLabel = new JLabel("游戏说明：按键盘上下左右移动，按住'a'可查看最终图片，\n" +
                    "按住w直接胜利。");
            jLabel.setBounds(0,0,300,20);
            jDialog.getContentPane().add(jLabel);
            jDialog.setSize(500,200);//设置弹框大小
            jDialog.setAlwaysOnTop(true);//窗口置顶
            jDialog.setLocationRelativeTo(null);//居中
            jDialog.setModal(true);//弹框不关闭，则界面不可操控
            jDialog.setVisible(true);//显示弹框
        }
        //结束游戏
        else if (obj == diposeGame) {
            System.out.println("结束游戏");
            System.exit(0);
        }
        //点击红色衣服
        else if(obj == redClother){
            path = "src/Image/redClother/";
            step = 0;
            initData();
            imageInit();
        }
        //蓝色爱心
        else if(obj == blueHeartItem){
            path = "src\\Image\\bluHeart\\";
            step = 0;
            initData();
            imageInit();
        }
        //蓝色头发
        else if(obj == blueHairItem){
            path = "src/Image/blueHair/";
            step = 0;
            initData();
            imageInit();
        }
        //蓝色眼睛
        else if(obj == blueEyeItem){
            path = "src/Image/blueEye/";
            step = 0;
            initData();
            imageInit();
        }
        //一键通关
        else if (obj == winGame){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            imageInit();
        }
        //动物
        else if (obj == animalItem) {
            path = "F:\\javaProject\\Game\\src\\Image\\animal\\animal3\\";
            step = 0;
            initData();
            imageInit();
        }
        //女孩
        else if (obj == girlItem){
            path = "src\\Image\\girl\\girl1\\";
            step = 0;
            initData();
            imageInit();
        }
        //女孩2
        else if(obj == girl2Item){
            path = "src\\Image\\girl\\girl7\\";
            step = 0;
            initData();
            imageInit();
        }
    }
}
