package com.xm194.tank;

public class Main {
    public static void main(String[] args){
        TankFrame.instance.setVisible(true);
        for(;;){
            try {
               // TimeUnit.MICROSECONDS.sleep(25);
                Thread.sleep(25);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            TankFrame.instance.repaint();
        }
    }
}
