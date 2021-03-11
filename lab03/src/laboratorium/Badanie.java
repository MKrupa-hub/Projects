package laboratorium;

import punkt.Pacjent;

import java.util.Random;

public class Badanie {
    Random losuj = new Random();
    int losowa_liczba;
    private Pacjent pacjent;
    private String stan;

    public Badanie(Pacjent pacjent) {
        this.pacjent = pacjent;
        pacjent.setStatus(true);
        wykonaj_badanie();
    }

    public void wykonaj_badanie() {
        for (int i = 0; i < pacjent.getOznaczenie_probki().size(); i++) {
            switch (pacjent.getOznaczenie_probki().get(i)) {
                case "morfologia":
                    pacjent.dodaj_Wykonwaca_badania("Henryk[2]");
                    pacjent.dodaj_Wynik_badania("morfologia " + badaj());
                    break;
                case "eozynofolia":
                    pacjent.dodaj_Wynik_badania("eozynofolia " + badaj());
                    break;
                case "PT":
                    pacjent.dodaj_Wykonwaca_badania("Kamil[4]");
                    pacjent.dodaj_Wynik_badania("PT " + badaj());
                    break;
                case "APTT":
                    pacjent.dodaj_Wynik_badania("APTT " + badaj());
                    break;
                case "fibrynogen":
                    pacjent.dodaj_Wynik_badania("fibrynogen " + badaj());
                    break;
                case "D-dimery":
                    pacjent.dodaj_Wynik_badania("D-dimery " + badaj());
                    break;
                case "krew utajona":
                    pacjent.dodaj_Wykonwaca_badania("Karol[2]");
                    pacjent.dodaj_Wynik_badania("krew utajona " + badaj());
                    break;
                case "pasozyty":
                    pacjent.dodaj_Wynik_badania("pasozyty " + badaj());
                    break;
                case "TSH":
                    pacjent.dodaj_Wykonwaca_badania("Tomasz[4]");
                    pacjent.dodaj_Wynik_badania("TSH " + badaj());
                    break;
                case "T3":
                    pacjent.dodaj_Wynik_badania("T3 " + badaj());
                    break;
                case "T4":
                    pacjent.dodaj_Wynik_badania("T4 " + badaj());
                    break;
                case "Ft3":
                    pacjent.dodaj_Wynik_badania("Ft3 " + badaj());
                    break;
            }
        }
    }

    public String badaj() {

        losowa_liczba = losuj.nextInt(3);
        if (losowa_liczba == 0) {
            stan = "w normie";
        } else if (losowa_liczba == 1) {
            stan = "nadmiar";
        } else if (losowa_liczba == 2) {
            stan = "niedobor";
        }

        return stan;
    }
}
