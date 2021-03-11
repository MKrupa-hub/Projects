package laboratorium;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu_laboratorium {
    public int wybor;
    BadanyDao badanyDao = new Laboratorium_diagnostyczne();

    public Menu_laboratorium() {

        while (wybor != 4) {
            System.out.println("1. Pobierz probki ");
            System.out.println("2. Przeprowadz badanie   ");
            System.out.println("3. Odeslij wyniki ");
            System.out.println("4. Wyjscie ");
            wybor = sprawdz();
            opcja1(wybor);
        }
    }

    public static void main(String[] args) {
        new Menu_laboratorium();
    }

    private void opcja1(int wybor) {
        if (wybor == 1) {
            badanyDao.pobierz_probki();
        }
        if (wybor == 2) {
            badanyDao.przeprowadz_badanie();
        }
        if (wybor == 3) {
            badanyDao.odeslij_wyniki();
        }
        if (wybor == 4) {
            System.exit(1);
        }
    }

    private int sprawdz() {

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        boolean poprawna = false;
        int wartosc = 0;
        while (!poprawna) {
            try {
                wartosc = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException n) {
                System.out.println("Wprowadzono niedozwolone znaki lub zły format, podaj dane jeszcze raz:");
                continue;
            } catch (IOException e) {
                System.out.println("Blad! Podaj dane jeszcze raz:");
                continue;
            }
            poprawna = true;
        }
        return wartosc;
    }
}
