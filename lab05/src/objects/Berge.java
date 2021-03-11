package objects;

import program.Bridge;

public class Berge extends MyThread implements Runnable {

    public Berge(String name, Bridge bridge) {

        super(name, bridge);
    }

    @Override
    public void run() {
        while (true) {
            firstsleeep();
            show.show(situation = "left");
            bridge.bridge_up();
            sleeepconstant(5000);
            show.show(situation = "middle");
            sleeep(1500);
            show.show(situation = "right");
            sleeepconstant(1000);
            bridge.bringgodown();
            sleeepconstant(1000);
            show.show(situation = "waitright");
            firstsleeep();
            show.show(situation = "right");
            bridge.bridge_up();
            sleeepconstant(5000);
            show.show(situation = "middle");
            sleeep(1500);
            show.show(situation = "left");
            sleeepconstant(1000);
            bridge.bringgodown();
            sleeepconstant(1000);
            show.show(situation = "waitleft");


        }
    }

    public void sleeep(int time) {
        try {
            sleep(time + (int) (Math.random() * 1000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void sleeepconstant(int time) {
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void firstsleeep() {
        try {
            sleep(10000 + (int) (Math.random() * 15000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
