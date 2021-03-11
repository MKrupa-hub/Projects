import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class DataSetter {
    private List<ClientDataBase> copyofclients = new LinkedList<ClientDataBase>();
    private List<ClientDataBase> clients = new LinkedList<ClientDataBase>();
    private List<PizzaDataBase> pizzas = new LinkedList<PizzaDataBase>();
    private int maxpizzas = 3;
    private int currentpizzas = 0;

    public DataSetter(Scanner s) {

        while (s.hasNext()) {
            ClientDataBase client = new ClientDataBase();
            PizzaDataBase pizza = new PizzaDataBase();
            client.setClient_number(s.nextInt());

            String[] TopingList = s.next().split(",");
            for (String str : TopingList) {
                client.getTopingsList().add(Integer.parseInt(str.trim()));


            }
            client.getTopingsList().sort(Comparator.naturalOrder());
            copyofclients.add(client);
            clients.add(client);
            pizzas.add(pizza);

        }

        int amount = clients.size();

        for (int i = 0; i < amount; i++) {
            for (int y = 0; y < copyofclients.size(); ) {
                if (i == 1) {
                    i--;
                }

                if (copyofclients.get(y).getTopingsList().toString().equals(pizzas.get(i).getPizzanumber())) //sprawdzenie czy pierwsza pozycja jest rowna
                {
                    pizzas.get(i).addPizzzaslizestobake();
                    clients.get(copyofclients.get(y).getClient_number()-1).addPizza_ordered();
                    pizzas.get(i).getClients().add(copyofclients.get(y).getClient_number());
                    copyofclients.remove(y);

                    y = 0;
                } else {
                    while (pizzas.get(i).getPizzanumber() != null) {            //przesuniecie na kolejna pusta pozycje
                        i++;
                        if (copyofclients.size() == 0) {
                            continue;
                        } else if (copyofclients.get(y).getTopingsList().toString().equals(pizzas.get(i).getPizzanumber())) {  //sprawdzenie czy jakas pozycja po drodze nie jest rowna
                            pizzas.get(i).addPizzzaslizestobake();
                            clients.get(copyofclients.get(y).getClient_number()-1).addPizza_ordered();
                            pizzas.get(i).getClients().add(copyofclients.get(y).getClient_number());
                            copyofclients.remove(y);


                            i = 0;
                            y = 0;
                            if (copyofclients.size() == 0) {
                                continue;
                            }
                            continue;
                        }
                    }
                    if (copyofclients.size() == 0) {
                        continue;
                    }

                    pizzas.get(i).setPizzanumber(copyofclients.get(y).getTopingsList().toString());                //zapisanie nowego przypadku na nowym miejscu
                    clients.get(copyofclients.get(y).getClient_number()-1).addPizza_ordered();
                    pizzas.get(i).addPizzzaslizestobake();
                    pizzas.get(i).getClients().add(copyofclients.get(y).getClient_number());
                    copyofclients.remove(y);
                    y = 0;
                    i = 0;
                }
            }
        }


        for (int i = 0; i < clients.size(); i++)                      //porzadkowanie pustych miejsc
        {
            if (clients.get(i).getPizzaordered() == 0) {
                clients.remove(i);
                i--;
            }
        }
        for (int i = 0; i < clients.size(); i++)                     //uporzadkowanie numerow klientow
        {
            clients.get(i).setClient_number(i + 1);
        }
        for (int i = 0; i < pizzas.size(); i++)                     //uporzadkowanie pizz
        {
            if (pizzas.get(i).getPizzzaslizestobake()==0) {
                pizzas.remove(i);
                i--;
            }
        }

for(int l=0;l<maxpizzas;l++) {
    for (PizzaDataBase pizza : pizzas)                           //pieczenie
    {

        if (pizza.getPizzzaslizestobake() >= 8) {

            pizza.add_pizza_baked();

            for (int i = 0; i < 8; i++) {
                for (int k = 0; k < clients.size(); k++) {                   //szukanie klienta
                    if (pizza.getClients().get(0).equals(clients.get(k).getClient_number()-1)) {
                        clients.get(k).addPizzareceived();
                        pizza.getClientsdone().add(pizza.getClients().get(0));
                        pizza.minusPizzzaslizestobake();


                    }
                }

                pizza.getClients().remove(0);
            }

        }


    }
}
        for (PizzaDataBase piza : pizzas)                        //obsluzone zamowienia
        {
            if (piza.getActualpizzabaked() != 0) {
                System.out.println(piza);
            }
        }
        for (PizzaDataBase piza : pizzas)                        //nie obsluzone zamowienia
        {
            if (!piza.getClients().equals(0)) {
                System.out.println(piza.getClients() + ";" + piza.getPizzanumber());
            }
        }


    }

}

