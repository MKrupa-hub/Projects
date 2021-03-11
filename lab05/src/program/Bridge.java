package program;

public class Bridge {
    public boolean isup = false;
    private int amountinwater = 0;
    private int criticalamount = 8;
    private int done = 0;


    public synchronized void bridge_up() {

        while (isup == false) {
            try {
                amountinwater++;
                System.out.println(amountinwater);
                wait();
            } catch (InterruptedException e) {
                System.err.println("Przerwano watek");
            }
        }


    }
    public synchronized void bringgodown(){
        amountinwater--;
        if (amountinwater <= 0) {
            amountinwater=0;
            isup = false;
            notifyAll();
        }
    }

    public synchronized void bridge_down() {

        while (isup == true) {
            try {
                System.out.println("czekam az przeplyna");
                wait();
            } catch (InterruptedException e) {
                System.err.println("Przerwano watek");
            }
        }
    }
    public synchronized void bridgegoup(){
        done++;
        if (amountinwater >= 2 || done == criticalamount) {
            done = 0;
            isup = true;
            notifyAll();
        }
    }
}
