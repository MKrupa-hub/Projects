package punkt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Menu_punkt {
    public int wybor;
    PacjentDao pacjentDao = new Punkt_pobran();

    public Menu_punkt() {
        while (wybor != 3) {
            System.out.println("1. Badany ");
            System.out.println("2. Rejestrujacy ");
            System.out.println("3. Wyjscie ");
            wybor = sprawdz();

            if (wybor == 1) {
                while (wybor != 4) {
                    System.out.println("1. Zloz skierowanie ");
                    System.out.println("2. Zobacz wyniki ");
                    System.out.println("3. Wyjscie ");
                    System.out.println("4. Powrot ");
                    if (wybor == 4) {
                        break;
                    }
                    wybor = sprawdz();
                    opcja1(wybor);
                }
            }

            if (wybor == 2) {
                while (wybor != 4) {
                    System.out.println("1. Pobierz probki  ");
                    System.out.println("2. Przeslij probki do laboratorium ");
                    System.out.println("3. Wyjscie ");
                    System.out.println("4. Powrot ");
                    if (wybor == 4) {
                        break;
                    }
                    wybor = sprawdz();
                    opcja2(wybor);
                }
            }

        }

    }

    public static void main(String[] args) {
        new Menu_punkt();
    }

    private void opcja1(int wybor) {
        if (wybor == 1) {
            pacjentDao.wpisz_pacenta();
        }
        if (wybor == 2) {
            pacjentDao.zobacz_wyniki();
        }
        if (wybor == 3) {
            System.exit(1);
        }
    }

    private void opcja2(int wybor) {
        if (wybor == 1) {
            pacjentDao.pobierz_probki();
        }
        if (wybor == 2) {
            pacjentDao.przeslij_probki();
        }
        if (wybor == 3) {
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
