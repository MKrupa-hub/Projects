import operacje.MojSluchacz;
import operacje.Odbieracz;
import operacje.Wysylacz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.util.Scanner;

public class TerminalTaksowkarz extends JFrame implements MojSluchacz {
    private JPanel panel;
    private JButton zgloszone;
    private JButton przyjete;
    private JButton wykonane;
    private JButton odrzucone;
    private JLabel zgloszenie;
    private JButton wpisz_do_centrali;
    private JTextField numer_taksowki;
    private Odbieracz r = null;


    public TerminalTaksowkarz() {

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 700, 600, 300);


        wpisz_do_centrali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    new Wysylacz().send("t" + numer_taksowki.getText(), "localhost", 60000);
                } catch (NumberFormatException es) {
                    es.printStackTrace();
                } catch (IllegalArgumentException err) {
                    if (numer_taksowki.getText().equals(null)) {
                        throw new IllegalArgumentException("nie wpisanu numeru taksowki");
                    }
                }
                Scanner s = new Scanner(numer_taksowki.getText());
                r = new Odbieracz(s.nextInt());
                r.addMyListener(TerminalTaksowkarz.this);
                r.start();
            }
        });
        zgloszone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Scanner s = new Scanner(zgloszenie.getText());
                    s.useDelimiter(",");
                    new Wysylacz().send("z" + s.nextInt(), "localhost",60000);
                } catch (NumberFormatException es) {
                    es.printStackTrace();
                }
            }
        });
        przyjete.addComponentListener(new ComponentAdapter() {
        });
        przyjete.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Scanner s = new Scanner(zgloszenie.getText());
                    s.useDelimiter(",");
                    new Wysylacz().send("p" + s.nextInt(), "localhost", 60000);
                } catch (NumberFormatException es) {
                    es.printStackTrace();
                }
            }
        });
        wykonane.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Scanner s = new Scanner(zgloszenie.getText());
                    s.useDelimiter(",");
                    new Wysylacz().send("w" + s.nextInt(), "localhost", 60000);
                    System.exit(0);
                } catch (NumberFormatException es) {
                    es.printStackTrace();
                }
            }
        });
        odrzucone.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Scanner s = new Scanner(zgloszenie.getText());
                    s.useDelimiter(",");
                    new Wysylacz().send("o" + s.nextInt(), "localhost", 60000);
                    System.exit(0);
                } catch (NumberFormatException es) {
                    es.printStackTrace();
                }
            }
        });
    }


    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    TerminalTaksowkarz ramka = new TerminalTaksowkarz();
                    ramka.setContentPane(new TerminalTaksowkarz().panel);
                    ramka.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

    }

    @Override
    public void otrzymanaWiadomosc(String theLine) {
        zgloszenie.setText(theLine);
    }
}
