package com.xm194.tank.strategy;

import com.xm194.tank.Bullet;
import com.xm194.tank.Group;
import com.xm194.tank.Player;
import com.xm194.tank.TankFrame;

public class DefaultFireStrategy implements FireStrategy{
    @Override
    public void fire(Player p) {
                new Thread(new Runnable(){

            public void run(){
                while(p.isFire()){
                    TankFrame.instance.addButtle(new Bullet(p.getX(),p.getY(),p.getDir(), Group.GOOD));
                    try {
                        Thread.sleep(150);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
