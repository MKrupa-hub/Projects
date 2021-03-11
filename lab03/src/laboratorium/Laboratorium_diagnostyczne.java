package laboratorium;

import punkt.Pacjent;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Laboratorium_diagnostyczne implements BadanyDao {
    private List<punkt.Pacjent> pacjenci = new LinkedList<punkt.Pacjent>();

    @Override
    public void pobierz_probki() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("Dane_pacjentow"));
            //wczytuje dane z pliku
            pacjenci = (List<Pacjent>) o.readObject();

            o.close();


            System.out.println("Dane zostały wczytane !");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void przeprowadz_badanie() {
        for (Pacjent pacjent: pacjenci) {
            new Badanie(pacjent);
        }
    }

    @Override
    public void odeslij_wyniki() {
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Dane_pacjentow.txt"));
            // wpisuje dane do pliku
            o.writeObject(pacjenci);
            o.close();

        } catch (FileNotFoundException e) {
            System.out.println("File not found");
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Dane zostały wpisane !");

    }
}
