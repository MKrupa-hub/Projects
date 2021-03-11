package dane;

public class Dane {
    public int getNumer_identyfikacyjny() {
        return numer_identyfikacyjny;
    }

    public void setStatus_zlecenie(String status_zlecenie) {
        this.status_zlecenie = status_zlecenie;
    }

    @Override
    public String toString() {
        return identyfikator+ status_zlecenie;
    }
    public String identyfikator;
    public String status_zlecenie;
    private int numer_identyfikacyjny;
    public Dane(String identyfikator, String status_zlecenie) {
        this.identyfikator=identyfikator;
        this.status_zlecenie = status_zlecenie;
    }

    public void setNumer_identyfikacyjny(int numer_identyfikacyjny) {
        this.numer_identyfikacyjny = numer_identyfikacyjny;
    }
}