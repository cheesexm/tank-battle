import java.awt.*;

public class Explode {
    private int x,y;
    private static int count=0;
    private int width,height;


    private boolean live=true;
    public Explode(int x, int y) {
        this.x = x;
        this.y = y;
        this.width= ResourceMgr.explodes[0].getWidth();
        this.height= ResourceMgr.explodes[0].getHeight();

    }
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }


    public void Die(){
        setLive(false);
    }

    public void paint(Graphics g) {

//System.out.println(count);

        if(count>= ResourceMgr.explodes.length){
            count=0;
            Die();

        }
        g.drawImage(ResourceMgr.explodes[count],x,y,null);
        count++;


    }










}
