package operacje;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

public class Odbieracz {
    private List<MojSluchacz> ml = new ArrayList<MojSluchacz>();
    private Thread t = null;
    private int port = 0;
    private ServerSocket s = null;
    private boolean end = false;

    public Odbieracz(int port) {
        this.port = port;

    }

    public void stop() {
        t.interrupt();
        try {
            s.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void start() {
        end = false;
        t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    s = new ServerSocket(port);
                    while (true) {
                        Socket sc = s.accept();
                        InputStream is = sc.getInputStream();
                        InputStreamReader isr = new InputStreamReader(is);
                        BufferedReader br = new BufferedReader(isr);
                        String theLine = br.readLine();
                        ml.forEach((item) -> item.otrzymanaWiadomosc(theLine));
                        sc.close();
                    }
                } catch (SocketException e) {

                } catch (IOException e) {

                    e.printStackTrace();
                }
            }
        });
        t.start();
    }

    public void addMyListener(MojSluchacz m) {
        ml.add(m);
    }

    public void removeMyListener(MojSluchacz m) {
        ml.remove(m);
    }

}

