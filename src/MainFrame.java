import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Random;

public class MainFrame extends JFrame {
    private JLabel score1 = new JLabel("╰(*°▽°*)╯ Hit:0");
    private JLabel target1 = new JLabel(" σ`∀´)σ");
    private JLabel line1 = new JLabel();
    private JLabel time1 = new JLabel("time:60");
    private JLabel player1 = new JLabel("  ( ˘•ω•˘ )");
    private JLabel shot1 = new JLabel("   Σ(°Д°;");
    private Timer t1;
    private Timer t2;
    private Timer t3;
    private int count1 =0,rndx=0,rndy=0,time=60;

    private int shotx1=1000,shoty1=1000,playerx=350,playery=490;
//    private int shotx1=370,shoty1=455,playerx=350;
    public MainFrame (){
        init();
    }

    private void init(){
        this.setBounds(800,200,720,720);
        this.setLayout(null);
        Random rnd = new Random();

        score1.setBounds(20,630,160,35);
        this.add(score1);
        score1.setOpaque(true);
        score1.setBackground(Color.CYAN);
        score1.setFont(new   java.awt.Font("Dialog",   1,   16));

        line1.setBounds(0,460,720,10);
        this.add(line1);
        line1.setOpaque(true);
        line1.setBackground(Color.BLACK);

        target1.setBounds(350,100,120,70);
        this.add(target1);
        target1.setOpaque(true);
        target1.setBackground(Color.YELLOW);
        target1.setFont(new   java.awt.Font("Dialog",   1,   30));

        time1.setBounds(630,5,120,70);
        this.add(time1);
        time1.setFont(new   java.awt.Font("Dialog",   1,   16));

        player1.setBounds(playerx,490,120,70);
        this.add(player1);
        player1 .setFont(new   java.awt.Font("Dialog",   1,   21));
        player1.setOpaque(true);
        player1.setBackground(Color.orange);

        shot1.setBounds(shotx1,shoty1,80,35);
        this.add(shot1);
        shot1.setOpaque(true);
        shot1.setBackground(Color.RED);

        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
//                super.keyPressed(e);
                switch(e.getKeyCode())
                {
                    case KeyEvent.VK_LEFT:
                        if(player1.getX()>0){
                            playerx-=10;
                        }
                        player1.setLocation(playerx,playery);
//                        player1.setText(Integer.toString(player1.getX()));
                        break;
                    case KeyEvent.VK_RIGHT:
                        if(player1.getX()<590){
                            playerx+=10;
                        }
                        player1.setLocation(playerx,playery);
//                        player1.setText(Integer.toString(player1.getX()));
                        break;
                    case KeyEvent.VK_SPACE:
                        t1.stop();
                        shot1.setLocation(player1.getX()+20,player1.getY()-35);
//                        if(shot1.getY()==shoty1){
//                            shot1.setLocation(player1.getX()+20,player1.getY()-35);
//                        }
                        t1.start();
                        t2.start();
                        t3.start();
                        break;
                    case KeyEvent.VK_UP:
                        if(player1.getX()>0){
                            playery-=10;
                        }
                        player1.setLocation(playerx,playery);
                        player1.setText(Integer.toString(player1.getY()));
                        break;
                    case KeyEvent.VK_DOWN:
                        if(player1.getX()<590){
                            playery+=10;
                        }
                        player1.setLocation(playerx,playery);
                        player1.setText(Integer.toString(player1.getY()));
                        break;
                }
            }
        });


        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });

        t1=new Timer(10, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                shot1.setText("x="+Integer.toString(shot1.getX())+"y="+Integer.toString(shot1.getY()));
                shot1.setLocation(shot1.getX(),shot1.getY()-10);
                if(shot1.getY()<=5){
                    shot1.setLocation(shotx1,shoty1);
                }else if((shot1.getX()>target1.getX()-80)&&shot1.getX()<target1.getX()+120&&shot1.getY()>=target1.getY()&&shot1.getY()<target1.getY()+70){
                    count1++;
                    score1.setText("╰(*°▽°*)╯ Hit:"+Integer.toString(count1));
                    shot1.setLocation(shotx1,shoty1);
                }
            }
        });

        t2=new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int n = rnd.nextInt(10);
                int n1 = rnd.nextInt(10);
                if(n%2!=0){
                    String str="-"+rnd.nextInt(Math.abs(100));
                     rndx = Integer.parseInt(str);
//                    System.out.println(rndx);
                }else{
                     rndx= rnd.nextInt(100);
//                    System.out.println(rndx);
                }
                if(n1%2!=0){
                    String str1="-"+rnd.nextInt(Math.abs(50));
                     rndy = Integer.parseInt(str1);
//                    System.out.println(rndy);
                }else{
                     rndy= rnd.nextInt(50);
                }
//                System.out.print(rndx);
//                System.out.print(rndy);
                if(target1.getX()+rndx>50&&target1.getX()+rndx<520&&target1.getY()>50&&target1.getY()<200){
                    target1.setLocation(target1.getX()+rndx,target1.getY());
//                    target1.setText("Y="+target1.getY());//"X="+target1.getX()+
                }
                if(target1.getX()>50&&target1.getX()<520&&target1.getY()+rndy>50&&target1.getY()+rndy<200){
                    target1.setLocation(target1.getX(),target1.getY()+rndy);
                }
            }
        });

        t3=new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                time--;
                time1.setText("time:"+time);
                if(time==0){
                    t1.stop();
                    t2.stop();
                    t3.stop();
                    target1.setText("Game");
                    player1.setText("Over!");
                }
            }
        });


    }
}
