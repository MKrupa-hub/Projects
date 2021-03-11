package punkt;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Pacjent implements Serializable {
    public Date data;
    private String identyfikator;
    private List<String> zakres_badania = new ArrayList<String>();
    private List<String> oznaczenie_probki = new ArrayList<String>();
    private List<String> wynik_badania = new ArrayList<String>();
    private List<String> wykonwaca_badania = new ArrayList<String>();
    private boolean status = false;
    private int oznakowanie;


    //  @Override
//public String toString() {
//    return identyfikator + ";" + zakres_badania + ";" + oznaczenia + ";" + oznakowanie;
//}
    public List<String> getOznaczenie_probki() {
        return oznaczenie_probki;
    }

    public void setOznaczenie_probki(List<String> oznaczenie_probki) {
        this.oznaczenie_probki = oznaczenie_probki;
    }

    public void dodaj_Wykonwaca_badania(String wykonwaca_badania) {
        this.wykonwaca_badania.add(wykonwaca_badania);
    }

    public List<String> getWynik_badania() {
        return wynik_badania;
    }

    public void dodaj_Wynik_badania(String wynik_badania) {
        this.wynik_badania.add(wynik_badania);
    }

    public List<String> getWykonwaca_badania() {
        return wykonwaca_badania;
    }

    public String getIdentyfikator() {
        return identyfikator;
    }

    public void setIdentyfikator(String identyfikator) {
        this.identyfikator = identyfikator;
    }

    public List<String> getZakres_badania() {
        return zakres_badania;
    }

    public void setZakres_badania(String zakres_badania) {
        this.zakres_badania.add(zakres_badania);
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public void setOznakowanie(int oznakowanie) {
        this.oznakowanie = oznakowanie;
    }
}
