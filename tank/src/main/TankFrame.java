import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class TankFrame extends Frame {
    public static final TankFrame instance = new TankFrame();
    private Player myTank;
    private Tank enny;
    private Bullet bullet;
   // private Explode explodes;
    private ArrayList<Bullet> bulletlist;
    private ArrayList<Tank> tanks;
    private ArrayList<Explode> explodes;
    public static final int GAME_WIDTH = 800, GAME_HEIGHT = 600;

    private TankFrame() {
        this.setTitle("Tank Fight");
        this.setLocation(400, 100);
        this.setSize(GAME_WIDTH, GAME_HEIGHT);
        this.addKeyListener(new TankKetListener());
        initGameObjects();


    }

    private void initGameObjects() {
        myTank = new Player(100, 200, Dir.R, Group.GOOD);
        enny = new Tank(50, 50, Dir.U, Group.BAD);
        //explode=new Explode(50,50);
        bulletlist = new ArrayList<>();
        tanks=new ArrayList<>();
        explodes=new ArrayList<>();
        for(int i=0;i<10;i++){
            tanks.add( new Tank(50+50*i,50,Dir.U, Group.BAD));
        }
    }

    @Override
    public void setIgnoreRepaint(boolean ignoreRepaint) {
        super.setIgnoreRepaint(ignoreRepaint);
    }

    @Override
    public void paint(Graphics g) {
        Color c = g.getColor();
        g.setColor(Color.white);
        g.drawString("bullerts" + bulletlist.size(), 0, 50);
        g.drawString("tanks" + tanks.size(), 0, 80);
        g.setColor(c);
        myTank.paint(g);
        for(int i=0;i<tanks.size();i++){
            if(tanks.get(i).isLive()){
                tanks.get(i).paint(g);
            }else{
                tanks.remove(i);;
            }

        }
        for(int i=0;i<explodes.size();i++){
            if(explodes.get(i).isLive()){
                explodes.get(i).paint(g);
            }else{
                explodes.remove(i);
            }
        }

        //   bullet.paint(g);
        for (int i = 0; i < bulletlist.size(); i++) {

            for(int j=0;j<tanks.size();j++){
                bulletlist.get(i).collidesWithTank(tanks.get(j));

            }

            if (!bulletlist.get(i).isLive()) {
                bulletlist.remove(i);

            } else {
                bulletlist.get(i).paint(g);
            }

        }


    }

    public void addExplode(Explode explode) {
        explodes.add(explode);
    }


    private class TankKetListener extends KeyAdapter {


        @Override
        public void keyPressed(KeyEvent e) {
            myTank.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    myTank.keyReleased(e);
                }
            }).start();


        }
    }

    Image offScreenImage = null;

    @Override
    public void update(Graphics g) {
        if (offScreenImage == null) {
            offScreenImage = this.createImage(GAME_WIDTH, GAME_HEIGHT);
        }
        Graphics gOffScreen = offScreenImage.getGraphics();
        Color c = gOffScreen.getColor();
        gOffScreen.setColor(Color.BLACK);
        gOffScreen.fillRect(0, 0, GAME_WIDTH, GAME_HEIGHT);
        gOffScreen.setColor(c);
        paint(gOffScreen);
        g.drawImage(offScreenImage, 0, 0, null);
    }

    public void addButtle(Bullet bullet) {
        bulletlist.add(bullet);
        this.bullet = bullet;
    }
}

