import dane.Dane;
import dane.ModelTabeliKlienta;
import dane.ModelTabeliTaksowkarza;
import operacje.MojSluchacz;
import operacje.Odbieracz;
import operacje.Wysylacz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class Centrala extends JFrame implements MojSluchacz {
    List<Dane> bazaklientow = new ArrayList<>();
    List<Dane> bazataksowkarzy = new ArrayList<>();
    private JPanel panel;
    private JButton wyslij;
    private JTable taksowkarze;
    private JTable klienci;
    private JButton wlacznik;
    private JButton aktualizuj;
    private JTextField zlecenie_nr_kleint;
    private JTextField zlecenie_nr_taks;
    private Odbieracz r = null;
    private int numer_klienta = 0;
    private int numer_taksowkarza = 0;
    private int port;
    private String identyfikator;
    private int zmienna_do_porownania;



    public Centrala() {

        stworzTabele();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 700, 400);


        wlacznik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        aktualizuj.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (int i = 0; i < bazaklientow.size(); i++) {
                    try {
                        new Wysylacz().send(bazaklientow.get(i).status_zlecenie, "localhost", bazaklientow.get(i).getNumer_identyfikacyjny());
                    } catch (NumberFormatException es) {
                        es.printStackTrace();
                    }
                }
            }
        });
        wlacznik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                r = new Odbieracz(60000);
                r.addMyListener(Centrala.this);
                r.start();
            }
        });
        wyslij.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    Scanner s = new Scanner(zlecenie_nr_taks.getText());
                    s.useDelimiter("t");
                    port = s.nextInt();
                    Scanner r = new Scanner(zlecenie_nr_kleint.getText());
                    zmienna_do_porownania = r.nextInt();
                    for (int i = 0; i < bazaklientow.size(); i++) {

                        if (bazaklientow.get(i).getNumer_identyfikacyjny() == zmienna_do_porownania) {
                            identyfikator = bazaklientow.get(i).identyfikator;
                            System.out.println(identyfikator);
                        }
                    }
                    new Wysylacz().send(identyfikator, "localhost", port);
                    return;
                } catch (NumberFormatException es) {
                    es.printStackTrace();
                } catch (NoSuchElementException err) {

                    throw new NoSuchElementException("nie wpisanu numeru taksowki");

                }
            }
        });
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    Centrala ramka = new Centrala();
                    ramka.setContentPane(new Centrala().panel);
                    ramka.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void stworzTabele() {

        klienci.setModel(new ModelTabeliKlienta(bazaklientow));

        taksowkarze.setModel(new ModelTabeliTaksowkarza(bazataksowkarzy));

    }


    @Override
    public void otrzymanaWiadomosc(String theLine) {
        Scanner s = new Scanner(theLine);
        char test = theLine.charAt(0);
        //zgloszone
        if (test == 122) {
            s.useDelimiter("z");
            zmienna_do_porownania = s.nextInt();
            for (int i = 0; i < bazaklientow.size(); i++) {

                if (bazaklientow.get(i).getNumer_identyfikacyjny() == zmienna_do_porownania) {
                    bazaklientow.get(i).setStatus_zlecenie("zgloszone do realizacji");
                    klienci.updateUI();
                }
            }
            return;
        }
        //przyjmuje
        if (test == 112) {
            s.useDelimiter("p");
            zmienna_do_porownania = s.nextInt();
            for (int i = 0; i < bazaklientow.size(); i++) {

                if (bazaklientow.get(i).getNumer_identyfikacyjny() == zmienna_do_porownania) {
                    bazaklientow.get(i).setStatus_zlecenie("zlecenie przyjete");
                    klienci.updateUI();
                }
            }
            return;
        }
        //wykonalem
        if (test == 119) {
            s.useDelimiter("w");
            zmienna_do_porownania = s.nextInt();
            for (int i = 0; i < bazaklientow.size(); i++) {

                if (bazaklientow.get(i).getNumer_identyfikacyjny() == zmienna_do_porownania) {
                    bazaklientow.get(i).setStatus_zlecenie("zlecenie wykonane");
                    klienci.updateUI();
                }
            }
            return;
        }
        //odrzucam
        if (test == 111) {
            s.useDelimiter("o");
            zmienna_do_porownania = s.nextInt();
            for (int i = 0; i < bazaklientow.size(); i++) {

                if (bazaklientow.get(i).getNumer_identyfikacyjny() == zmienna_do_porownania) {
                    bazaklientow.get(i).setStatus_zlecenie("zlecenie odrzucone");
                    klienci.updateUI();
                }
            }
            return;
        }
//wpisanie taksowki do tabeli
        if (test == 116) {
            ((ModelTabeliTaksowkarza) (taksowkarze.getModel())).add(new Dane(theLine, "czeka na zlecenie"));
            s.useDelimiter("t");
            bazataksowkarzy.get(numer_taksowkarza).setNumer_identyfikacyjny(s.nextInt());


            return;
        }
        if (theLine.equals("sprawdz")) {

            aktualizuj.doClick();
            return;
        }
        //wpisanie klienta do tabeli
        else {
            ((ModelTabeliKlienta) (klienci.getModel())).add(new Dane(theLine, "przyjęte do centrali"));
            s.useDelimiter(",");
            bazaklientow.get(numer_klienta).setNumer_identyfikacyjny(s.nextInt());
            numer_klienta++;
            return;
        }

    }
}






