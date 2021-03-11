import java.util.ArrayList;
import java.util.List;

public class PizzaDataBase {

    private String pizzanumber;
    private int actualpizzabaked;
    private int pizzzaslizestobake = 0;
    private List<Integer> clients = new ArrayList<Integer>();
    private List<Integer> clientsdone = new ArrayList<Integer>();

    public List<Integer> getClientsdone() {
        return clientsdone;
    }

        @Override
        public String toString() {
            return actualpizzabaked + ";" + pizzanumber + ";" + clientsdone;
        }

    public void addPizzzaslizestobake() {
        pizzzaslizestobake ++;
    }
    public void minusPizzzaslizestobake() {
        pizzzaslizestobake --;
    }

    public String getPizzanumber() {
        return pizzanumber;
    }

    public void setPizzanumber(String pizzanumber) {
        this.pizzanumber = pizzanumber;
    }

    public int getPizzzaslizestobake() {
        return pizzzaslizestobake;
    }

    public List<Integer> getClients() {
        return clients;
    }

    public int getActualpizzabaked() {
        return actualpizzabaked;
    }

    public void add_pizza_baked() {
        this.actualpizzabaked++;
    }
}
