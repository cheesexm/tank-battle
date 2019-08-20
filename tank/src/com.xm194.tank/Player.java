package com.xm194.tank;

import com.xm194.tank.strategy.DefaultFireStrategy;
import com.xm194.tank.strategy.FireStrategy;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Player extends AbstractGameObject {
    private int x,y;
    static final int SPEED=4;
    private Dir dir;
    private boolean bL,bU,bR,bD;
    private boolean stop=true;
    private Group group;
    private boolean isFire;
    private boolean live=true;
    private FireStrategy strategy=null;
    public Player(int x, int y, Dir dir, Group group){
        this.x=x;
        this.y=y;
        this.dir=dir;
        this.group=group;
        this.initFireStrategy();
    }
    public boolean isLive() {
        return live;
    }

    public void setLive(boolean live) {
        this.live = live;
    }
    public Dir getDir(){
        return dir;
    }
    @Override
    public void paint(Graphics g) {
        if(!this.isLive())return;

            switch(dir){
                case L:
                    g.drawImage(ResourceMgr.goodTankL,x,y,null);
                    break;
                case U:
                    g.drawImage(ResourceMgr.goodTankU,x,y,null);
                    break;
                case R:
                    g.drawImage(ResourceMgr.goodTankR,x,y,null);
                    break;
                case D:
                    g.drawImage(ResourceMgr.goodTankD,x,y,null);
                    break;

            }




        move();

    }
    public void move(){
        // System.out.println(bL+""+bU+""+bR+""+bD);
        // System.out.println(stop);
        if(stop)return;
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

    }

    public void keyPressed(KeyEvent e) {
        //   stop=false;
        // System.out.println(e);
        int key=e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT:
                bL=true;
                // dir=Dir.L;
                break;
            case KeyEvent.VK_UP:
                bU=true;
                //  dir=Dir.U;
                break;
            case KeyEvent.VK_RIGHT:
                bR=true;
                // dir=Dir.R;
                break;
            case KeyEvent.VK_DOWN:
                bD=true;
                // dir=Dir.D;
                break;
            case KeyEvent.VK_ALT:
                isFire=true;
                fire();
                break;
        }
        //myTank
        setDir();
    }

    private void setDir() {

        if(!bL&&!bU&&!bR&&!bD){
            stop=true;
        }else{
            stop=false;
        }
        if(bL&&!bU&&!bR&&!bD){
            dir=Dir.L;
        }
        if(!bL&&bU&&!bR&&!bD){
            dir=Dir.U;
        }
        if(!bL&&!bU&&bR&&!bD){
            dir=Dir.R;
        }
        if(!bL&&!bU&&!bR&&bD){
            dir=Dir.D;
        }

    }

    public void keyReleased(KeyEvent e) {
        int key=e.getKeyCode();
        switch(key){
            case KeyEvent.VK_LEFT:
                bL=false;
                break;
            case KeyEvent.VK_UP:
                bU=false;
                break;
            case KeyEvent.VK_RIGHT:
                bR=false;
                break;
            case KeyEvent.VK_DOWN:
                bD=false;
                break;
            case KeyEvent.VK_ALT:

                isFire=false;
               // System.out.println("isFire"+isFire);
                break;

        }
        setDir();
    }
    public void Die(){
        setLive(false);
    }
    public void initFireStrategy(){
        String className=PropertyMgr.get("tankFireStrategy");
        try {
            Class clazz=Class.forName("com.xm194.tank.strategy."+className);
            strategy=(FireStrategy)clazz.getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void fire() {
        strategy.fire(this);

    }
    public int getX(){
        return  x+ResourceMgr.goodTankU.getWidth()/2-ResourceMgr.bulletU.getWidth()/2;

    }
    public int getY(){
        return  y+ResourceMgr.goodTankU.getHeight()/2-ResourceMgr.bulletU.getHeight()/2;
    }

    public boolean isFire() {
        return isFire;
    }

    public void setFire(boolean fire) {
        isFire = fire;
    }
}

