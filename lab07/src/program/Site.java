package program;

import communication.IControlCenter;
import communication.ISite;
import support.Mixer;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class Site extends JFrame implements ISite {
    static Site s;
    static IControlCenter ic;
    public int[] lightings = {1, 2, 3, 4, 5, 6};
    boolean i = true;
    private Mixer mixer;
    private JPanel panel;
    private JButton Add;
    private JTextField namefield;
    private JLabel l1;
    private JLabel l2;
    private JLabel l3;
    private JLabel l4;
    private JLabel l5;
    private JLabel l6;
    private String name;

    public Site() {
        updateLightings();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(650, 700, 350, 300);
        Add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                name = namefield.getText();
                ISite is = (s);
                try {
                    ic.add(is);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                } catch (NotBoundException notBoundException) {
                    notBoundException.printStackTrace();
                }
            }
        });
    }

    public static void main(String[] args) {
        try {
            Registry reg = LocateRegistry.getRegistry("localhost", 3000);
            ic = (IControlCenter) reg.lookup("ControlCenter");
            s = new Site();
            ic.add((ISite)UnicastRemoteObject.exportObject(s, 0));
            s.setContentPane(s.panel);
            s.setVisible(true);


        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public void start() {
        i=true;
        new Thread(new Runnable() {
            public void run() {
                while (i) {
                    SwingUtilities.invokeLater(new Runnable() {
                        public void run() {
                            mixer.mix(lightings);
                            updateLightings();

                        }
                    });

                    try {

                        java.lang.Thread.sleep(500);
                    } catch (Exception e) {
                    }
                }
            }
        }).start();

    }

    @Override
    public void stop() {
        i = false;

    }

    @Override
    public void setMixer(Mixer m) throws RemoteException {
        mixer = m;

    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public void updateLightings() {

        setColor(l1, 0);
        setColor(l2, 1);
        setColor(l3, 2);
        setColor(l4, 3);
        setColor(l5, 4);
        setColor(l6, 5);

    }

    public void setColor(JLabel label, int index) {
        label.setText(String.valueOf(lightings[index]));
        if (String.valueOf(lightings[index]).equals("1")) {
            label.setForeground(Color.red);
        } else if (String.valueOf(lightings[index]).equals("2")) {
            label.setForeground(Color.blue);
        } else if (String.valueOf(lightings[index]).equals("3")) {
            label.setForeground(Color.gray);
        } else if (String.valueOf(lightings[index]).equals("4")) {
            label.setForeground(Color.orange);
        } else if (String.valueOf(lightings[index]).equals("5")) {
            label.setForeground(Color.yellow);
        } else if (String.valueOf(lightings[index]).equals("6")) {
            label.setForeground(Color.green);
        }

    }

}
