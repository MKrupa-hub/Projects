import java.util.ArrayList;
import java.util.List;

public class ClientDataBase {
    private int client_number;
    private List<Integer> topingsList = new ArrayList<Integer>();
    private int pizzaordered;
    private int pizzareceived = 0;


    @Override
    public String toString() {
        return client_number + ":" + topingsList;
    }

    public int getPizzareceived() {
        return pizzareceived;
    }


    public int getClient_number() {
        return client_number;
    }

    public void setClient_number(int client_number) {
        this.client_number = client_number;
    }

    public List<Integer> getTopingsList() {
        return topingsList;
    }

    public int getPizzaordered() {
        return pizzaordered;
    }

    public void addPizza_ordered() {
        pizzaordered ++;
    }

    public void addPizzareceived() {
        pizzareceived ++;
    }


}


