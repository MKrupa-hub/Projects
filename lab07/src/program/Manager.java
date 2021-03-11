package program;

import communication.IControlCenter;
import communication.IManager;
import communication.ISite;
import data.TableModelMixers;
import data.TableModelSites;
import support.Mixer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class Manager extends JFrame implements IManager {


    static IManager im;
    private static IControlCenter ic;
    private static ISite is;
    private static Registry reg;
    List<ISite> sitesList = new ArrayList<>();
    List<Mixer> mixersList = new ArrayList<>();


    private JButton Start;
    private JButton Stop;
    private JButton update;
    private JTable sites;
    private JTable mixers;
    private JPanel panel;


    public Manager() throws RemoteException {


        createTable();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(500, 300, 700, 400);

        Start.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {


                for (ISite sit : sitesList) {
                    try {
                        if (sit.getName().equals(sites.getValueAt(sites.getSelectedRow(), sites.getSelectedColumn()))) {
                            for (Mixer mix : mixersList) {
                                if (mix.getName().equals(mixers.getValueAt(mixers.getSelectedRow(), mixers.getSelectedColumn()))) {
                                    sit.setMixer(mix);
                                    is = sit;
                                    sit.start();


                                }
                            }
                        }
                    } catch (RemoteException | InterruptedException remoteException) {
                        remoteException.printStackTrace();
                    }
                }

            }
        });

        Stop.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (ISite sit : sitesList) {
                    try {
                        if (sit.getName().equals(sites.getValueAt(sites.getSelectedRow(), sites.getSelectedColumn()))) {
                            for (Mixer mix : mixersList) {
                                if (mix.getName().equals(mixers.getValueAt(mixers.getSelectedRow(), mixers.getSelectedColumn()))) {
                                    sit.setMixer(mix);
                                    is = sit;
                                    is.stop();
                                }
                            }
                        }
                    } catch (RemoteException remoteException) {
                        remoteException.printStackTrace();
                    }
                }


            }
        });
    }

    public static void main(String[] args) {
        try {


            reg = LocateRegistry.getRegistry("localhost", 3000);

            ic = (IControlCenter) reg.lookup("ControlCenter");

            Manager m = new Manager();
            im = (IManager) UnicastRemoteObject.exportObject(m, 0);
            reg.rebind("Manager", m);
            ic.subscribe(im);

            m.setContentPane(m.panel);
            m.setVisible(true);


        } catch (RemoteException | NotBoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    public void createTable() throws RemoteException {
        sitesList = ic.getAllSites();
        mixersList = ic.getAllMixers();
        mixers.setModel(new TableModelMixers(mixersList));
        sites.setModel(new TableModelSites(sitesList));

    }


    @Override
    public void notify(ISite is, Boolean s) throws RemoteException {

        ((TableModelSites) sites.getModel()).add(is);
    }

    @Override
    public void notify(Mixer mi, Boolean s) throws RemoteException {
        if (s == true) {
            ((TableModelMixers) (mixers.getModel())).add(mi);
        } else if (s == false) {
            for (Mixer mix : mixersList) {
                if (mix.getName().equals(mi.getName())) {
                    ((TableModelMixers) (mixers.getModel())).remove(mix);
                }
            }
        }


    }


}
