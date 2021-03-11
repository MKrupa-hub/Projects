package objects;

import program.Bridge;

abstract public class MyThread extends Thread {
    protected Bridge bridge;
    protected Update show;
    protected String situation;

    public MyThread(String name, Bridge bridge) {
        super(name);
        this.bridge = bridge;
    }

    public void setshow(Update show) {
        this.show = show;
    }
}
