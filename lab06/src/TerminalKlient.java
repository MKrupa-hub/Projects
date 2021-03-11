import operacje.MojSluchacz;
import operacje.Odbieracz;
import operacje.Wysylacz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;

public class TerminalKlient extends JFrame implements MojSluchacz {
    String sprawdz = "sprawdz";
    private JPanel panel;
    private JButton wyslij_zgloszenie;
    private JButton sprawdz_status;
    private JLabel status;
    private JTextField zgloszenie;
    private JButton wpisz_do_centrali;
    private int port;
    private Odbieracz r = null;


    public TerminalKlient() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 0, 600, 300);
        wyslij_zgloszenie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0) {
                Scanner s = new Scanner(zgloszenie.getText());
                s.useDelimiter(",");
                port = s.nextInt();

                try {
                    new Wysylacz().send(zgloszenie.getText(), "localhost", 60000);
                } catch (NumberFormatException e) {
                    e.printStackTrace();
                }

            }

        });

        sprawdz_status.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Wysylacz().send(sprawdz, "localhost",60000);
                r = new Odbieracz(port);
                r.addMyListener(TerminalKlient.this);
                r.start();
            }
        });
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TerminalKlient ramka = new TerminalKlient();
                    ramka.setContentPane(new TerminalKlient().panel);
                    ramka.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    @Override
    public void otrzymanaWiadomosc(String theLine) {
        status.setText(theLine);
        try {
            if (theLine.equals("zlecenie odrzucone") || theLine.equals("zlecenie wykonane")) {
                Thread.sleep(800);
                System.exit(0);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}