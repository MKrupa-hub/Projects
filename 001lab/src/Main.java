import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
/*
Autor Mateusz Krupa
C:\Users\mateu\Desktop\001lab\out\artifacts\001lab_jar\001lab.jar
 */

public class Main {


    public static void main(String[] args) {
        int dlugosc_lamanej_gora = 0;
        int ilosc_przerw = 0;
        int koniec;
        int dlugosc_lamanej_dol;
        boolean answer = false;
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (!answer) {
            try {

                dlugosc_lamanej_gora = Integer.parseInt(reader.readLine());
            } catch (NumberFormatException n) {
                System.out.println("Zmienna musi miec wartosc integer");
                continue;
            } catch (IOException e) {
                System.out.println("Blad! Podaj dane jeszcze raz:");
                continue;
            }
            if (dlugosc_lamanej_gora == (int) dlugosc_lamanej_gora && dlugosc_lamanej_gora >= 0) {
                answer = true;
            }
            else System.out.println("Prosze podac dane ponownie");
        }
        koniec = dlugosc_lamanej_gora;
        dlugosc_lamanej_dol = 2 * dlugosc_lamanej_gora + 1;
        for (int i = dlugosc_lamanej_gora; i >= 0; i--, dlugosc_lamanej_gora--, ilosc_przerw++) {
            for (int j = dlugosc_lamanej_gora; j > 0; j--) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int j = ilosc_przerw; j > 0; j--) {
                System.out.print(" ");
            }
            if (ilosc_przerw != 0) {
                System.out.print("*");
                ilosc_przerw++;
            }
            if (dlugosc_lamanej_gora == 0) {
                for (int j = ilosc_przerw - 1; j > 0; j--) {
                    System.out.print(" ");
                }
                System.out.print("*");
            }
            System.out.println();
        }
        ilosc_przerw = ilosc_przerw - 4;
        for (int i = dlugosc_lamanej_dol; i <= 3 * koniec; i++, dlugosc_lamanej_dol++, ilosc_przerw--) {
            for (int j = dlugosc_lamanej_dol; j > 0; j--) {
                System.out.print(" ");
            }
            System.out.print("*");
            for (int j = ilosc_przerw; j > 0; j--) {
                System.out.print(" ");
            }
            if (ilosc_przerw >= 0) {
                System.out.print("*");
                ilosc_przerw--;
            }
            System.out.println();
        }
    }
}


