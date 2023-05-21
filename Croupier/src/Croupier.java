import java.util.ArrayList;

public class Croupier {
    private final String nom = "Croupier";
    private int score = 0;
    private ArrayList<Carte> main = new ArrayList<>();
    private Paquet paquet = new Paquet();

    public Croupier() {
    }

    public Carte donnerCarte() {
        return paquet.tirerCarte();
    }

    public void tirerCarte(){
        Carte c = paquet.tirerCarte();
        main.add(c);
        score += c.getValeur();
    }

    public String montrerMain(){
        String main = "Main du croupier: ";
        for (Carte c : this.main) {
            main += c.toString() + " ";
        }
        main += "Valeur de la main: " + this.score;
        return main;
    }

    public void nouvelleMain() {
        this.main.clear();
        this.score = 0;
    }

    public int getScore() {
        return this.score;
    }
}
