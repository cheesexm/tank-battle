import java.awt.*;

public class Bullet {
    private int x,y;
    public static final int SPEED=8;
    private Dir dir;
    private Group group;
    private boolean live=true;
    public Bullet(int x, int y, Dir dir, Group group) {
        this.x = x;
        this.y = y;
        this.dir = dir;
        this.group=group;
    }
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }

    public void move(){

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
        boundsCheck();
    }
    public void collidesWithTank(Tank tank){
        if(!this.isLive()||!tank.isLive())return;
        if(this.group==tank.getGroup())return;
        Rectangle rect=new Rectangle(x,y, ResourceMgr.bulletU.getWidth(), ResourceMgr.bulletU.getHeight());
        Rectangle rectTank=new Rectangle(tank.getX(),tank.getY(),
                ResourceMgr.badTankU.getWidth(), ResourceMgr.badTankU.getHeight());
        if(rect.intersects(rectTank)){
            this.Die();
            tank.Die();
        }
    }
    private void boundsCheck() {
      //  System.out.println(x+"--"+y);
        if(x<0||y<30||x> TankFrame.GAME_WIDTH||y> TankFrame.GAME_HEIGHT){
            this.Die();

        }
    }
    public void Die(){
        setLive(false);
    }

    public void paint(Graphics g) {


            switch(dir){
                case L:
                    g.drawImage(ResourceMgr.bulletL,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.bulletU,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.bulletR,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.bulletD,x,y,null);
                    break;

            }





            move();

        }


}
