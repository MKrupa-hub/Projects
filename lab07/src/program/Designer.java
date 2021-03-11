package program;

import communication.IControlCenter;
import support.Mixer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class Designer extends JFrame{

    private static IControlCenter ic;
    private JPanel panel;
    private JButton AddMixer1;
    private JButton RemoveMixer1;
    private JButton AddMixer2;
    private JButton RemoveMixer2;


    public static void main(String[] args) {
        try {
            Designer frame = new Designer();
            frame.setContentPane(new Designer().panel);
            frame.setVisible(true);

            Registry reg = LocateRegistry.getRegistry("localhost", 3000);
            ic = (IControlCenter) reg.lookup("ControlCenter");

        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Designer() {

        Mixer m = new MixerFrontBack("FBMixer");
        Mixer mt = new Mixerflip("Mixerflip");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 0, 600, 300);

        AddMixer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ic.add(m);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
        RemoveMixer1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {

                    ic.remove(m);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
        AddMixer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ic.add(mt);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
        RemoveMixer2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    ic.remove(mt);
                } catch (RemoteException remoteException) {
                    remoteException.printStackTrace();
                }
            }
        });
    }





}
