package objects;

import program.Bridge;

public class Vehicle extends MyThread implements Runnable {


    public Vehicle(String name, Bridge bridge) {
        super(name, bridge);

    }

    @Override
    public void run() {
        while (true) {
            firstsleeep();
            show.show(situation = "down");
            bridge.bridge_down();
            sleeepconstant(1000);
            show.show(situation = "middle");
            sleeep(100);
            show.show(situation = "up");
            bridge.bridgegoup();
            sleeep(50);
            show.show(situation = "waitup");
            firstsleeep();
            show.show(situation = "up");
            sleeep(50);
            bridge.bridge_down();
            sleeepconstant(1000);
            show.show(situation = "middle");
            sleeep(100);
            show.show(situation = "down");
            bridge.bridgegoup();
            sleeep(10);
            show.show(situation = "waitdown");
        }
    }

    public void sleeep(int time) {
        try {
            sleep(time + (int) (Math.random() * 3000));
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
            sleep(1000 + (int) (Math.random() * 10000));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
