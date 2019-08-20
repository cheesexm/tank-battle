package com.xm194.tank.strategy;

import com.xm194.tank.Bullet;
import com.xm194.tank.Group;
import com.xm194.tank.Player;
import com.xm194.tank.TankFrame;

public class RoundFireStrategy implements FireStrategy {
    @Override
    public void fire(Player p) {
        TankFrame.instance.addButtle(new Bullet(p.getX(),p.getY(),p.getDir(), Group.GOOD));
    }
}
