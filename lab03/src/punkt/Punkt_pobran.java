package punkt;

import java.io.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Punkt_pobran implements PacjentDao {
    private List<Pacjent> pacjenci = new LinkedList<Pacjent>();
    private String zatrzymaj;

    public void wpisz_pacenta() {
        Pacjent pacjent = new Pacjent();
        System.out.println("Identyfikator: ");
        pacjent.setIdentyfikator(sprawdz());
        System.out.println("Zakres badania(prosze podac jedno i zatwierdzic 'enter'): ");
        pacjent.setZakres_badania(sprawdz());
        System.out.println("Prosze podac kolejne badanie jesli to wszystko prosze wpisac 'koniec' ");
        zatrzymaj = sprawdz();
        while (!zatrzymaj.equals("koniec")) {
            pacjent.setZakres_badania(zatrzymaj);
            System.out.println("Prosze podac kolejne badanie jesli to wszystko prosze wpisac 'koniec'");
            zatrzymaj = sprawdz();
        }
        pacjenci.add(pacjent);
    }

    @Override
    public void zobacz_wyniki() {
        try {
            ObjectInputStream o = new ObjectInputStream(new FileInputStream("Dane_pacjentow.txt"));
            //wczytuje dane z pliku
            pacjenci = (List<Pacjent>) o.readObject();
            o.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("Identyfikator: ");
        zatrzymaj = sprawdz();
        for (Pacjent pacjent : pacjenci) {
            if (zatrzymaj.equals(pacjent.getIdentyfikator())) {
                if (!pacjent.isStatus()) {
                    System.out.println("Jeszcze nie ma wynikow");
                    return;
                } else {
                    System.out.println(pacjent.getWynik_badania());
                    System.out.println(pacjent.getWykonwaca_badania());
                    return;
                }
            }
        }
        System.out.println("Bledny identyfikator");
    }

    @Override
    public void pobierz_probki() {
        int oznakowanie = 0;
        for (Pacjent pacjent : pacjenci) {
            pacjent.setOznaczenie_probki(oznacz_probki(pacjent.getZakres_badania()));
            pacjent.setOznakowanie(oznakowanie);
            oznakowanie++;
        }
        System.out.println("Probki pobrane !");
    }

    @Override
    public void przeslij_probki() {
        try {
            ObjectOutputStream o = new ObjectOutputStream(new FileOutputStream("Dane_pacjentow"));
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


    private String sprawdz() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean poprawna = false;
        String informacja = null;
        while (!poprawna) {
            try {
                informacja = reader.readLine();
            } catch (NumberFormatException n) {
                System.out.println("Wprowadzono niedozwolone znaki lub zły format, podaj dane jeszcze raz:");
                continue;
            } catch (IOException e) {
                System.out.println("Blad! Podaj dane jeszcze raz:");
                continue;
            }
            poprawna = true;
        }
        return informacja;
    }

    public List oznacz_probki(List zakres_badan) {
        List<String> oznaczenia_tymczasowe = new ArrayList<>();
        for (int i = 0; i < zakres_badan.size(); i++) {
            if (zakres_badan.get(i).equals("hematologia")) {
                oznaczenia_tymczasowe.add("morfologia");
                oznaczenia_tymczasowe.add("eozynofolia");
            } else if (zakres_badan.get(i).equals("koagulogia")) {
                oznaczenia_tymczasowe.add("PT");
                oznaczenia_tymczasowe.add("APTT");
                oznaczenia_tymczasowe.add("fibrynogen");
                oznaczenia_tymczasowe.add("D-dimery");
            } else if (zakres_badan.get(i).equals("kal")) {
                oznaczenia_tymczasowe.add("krew utajona");
                oznaczenia_tymczasowe.add("pasozyty");
            } else if (zakres_badan.get(i).equals("tarczyca")) {
                oznaczenia_tymczasowe.add("TSH");
                oznaczenia_tymczasowe.add("T3");
                oznaczenia_tymczasowe.add("T4");
                oznaczenia_tymczasowe.add("Ft3");
            }
        }
        return oznaczenia_tymczasowe;
    }
}
