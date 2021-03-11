package program;

import communication.IControlCenter;
import communication.IManager;
import communication.ISite;
import support.Mixer;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;

public class ControlCenter implements IControlCenter {


    private static IManager im = null;
    private static Registry reg;
    List<ISite> sitesList = new ArrayList<>();
    List<Mixer> mixersList = new ArrayList<>();
    List<IManager> managerList = new ArrayList<>();

    public static void main(String[] args) {
        try {
            reg = LocateRegistry.createRegistry(3000);
            reg.rebind("ControlCenter", UnicastRemoteObject.exportObject(new ControlCenter(), 0));

        } catch (RemoteException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    @Override
    public Boolean add(ISite is) throws RemoteException {
        sitesList.add(is);
        if (im != null) {
            im.notify(is, true);
        }
        return null;
    }

    @Override
    public Boolean remove(ISite is) throws RemoteException {
        sitesList.remove(is);
        im.notify(is, false);
        return null;
    }

    @Override
    public Boolean add(Mixer m) throws RemoteException {
        mixersList.add(m);
        if (im != null) {
            im.notify(m, true);
        }
        return null;
    }

    @Override
    public Boolean remove(Mixer m) throws RemoteException {
        for (Mixer mix : mixersList) {
            if (mix.getName().equals(m.getName())) {
                mixersList.remove(mix);
                im.notify(m, false);
            }
        }

        return null;
    }

    @Override
    public void subscribe(IManager im) throws RemoteException, NotBoundException {
        reg = LocateRegistry.getRegistry("localhost", 3000);
        this.im = (IManager) reg.lookup("Manager");
        managerList.add(im);
    }


    @Override
    public List<ISite> getAllSites() {
        return sitesList;
    }

    @Override
    public List<Mixer> getAllMixers() {
        return mixersList;
    }


}
