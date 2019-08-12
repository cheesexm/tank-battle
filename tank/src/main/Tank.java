import java.awt.*;
import java.util.Random;

public class Tank extends Frame {
    private int x,y,oldX,oldY,Width,Height;
    static final int SPEED=3;
    private Dir dir;
    private boolean stop;
    private Group group;
    private boolean isFire;
    private boolean live=true;;
    private Explode explode;
    public Tank(int x,int y,Dir dir,Group group){
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.group=group;

        this.Width= ResourceMgr.badTankU.getWidth();
        this.Height=ResourceMgr.badTankU.getHeight();
    }
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
    @Override
    public void paint(Graphics g) {
        if(!this.isLive())return;


            switch(dir){
                case L:
                    g.drawImage(ResourceMgr.badTankL,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.badTankU,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.badTankR,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.badTankD,x,y,null);
                    break;

            }



        move();

    }
    public void move(){
        // System.out.println(bL+""+bU+""+bR+""+bD);
        // System.out.println(stop);
        if(stop)return;
        this.oldX=x;
        this.oldY=y;
        switch(dir){
            case L:
                x-=SPEED;
                break;
            case U:
                y-=SPEED;
                break;
            case R:
                x+=SPEED;
                break;
            case D:
                y+=SPEED;
                break;
        }
        if(r.nextInt(100)>95) {randomDir();}

        if(r.nextInt(100)>90){ fire();}
        boundsCheck();
    }
    private void boundsCheck() {

        if(x<0||y<30||x+Width> TankFrame.GAME_WIDTH||y+Height> TankFrame.GAME_HEIGHT){
           x=oldX;
           y=oldY;

        }
    }



private Random r=new Random();
    public void Die(){
        setLive(false);
        TankFrame.instance.addExplode(new Explode(x,y));
    }
    private void randomDir(){
        this.dir=Dir.randomDir();
    }
    private void fire() {
                    TankFrame.instance.addButtle(new Bullet(getX(),getY(),dir,Group.BAD));



    }
    public int getX(){
        return  x+ResourceMgr.badTankU.getWidth()/2-ResourceMgr.bulletU.getWidth()/2;

    }
    public int getY(){
        return  y+ResourceMgr.badTankU.getHeight()/2-ResourceMgr.bulletU.getHeight()/2;
    }

    public Group getGroup() {
        return group;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

