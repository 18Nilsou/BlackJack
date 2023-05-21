import java.util.ArrayList;

public class Joueur {
    private String nom;
    private int score = 0;
    private ArrayList<Carte> main = new ArrayList<>();

    private int nbrCartes;

    public Joueur(String nom) {
        this.nom = nom;
        for (int i = 0; i < 14; i++){
            main.add(new Carte());
        }
        this.nbrCartes = 0;
    }

    public void recevoirCarte(Carte c) {
        main.get(nbrCartes).setNom(c.getNom());
        main.get(nbrCartes).setCouleur(c.getCouleur());
        ++this.nbrCartes;
        this.score += c.getValeur();
    }

    public String montrerMain(){
        StringBuilder mainString = new StringBuilder();
        mainString.append("Votre main : ");
        for (int i = 0; i < this.nbrCartes; ++i) {
            mainString.append(this.main.get(i).toString()).append(", ");
        }
        mainString.append("Valeur de la main: ").append(this.score);
        return mainString.toString();
    }

    public void nouvelleMain() {
        this.main.clear();
        for (int i = 0; i < 14; i++){
            main.add(new Carte());
        }
        this.nbrCartes = 0;
        this.score = 0;
        System.out.println('\n'+"Nouvelle main" + '\n'+ '\n'+ '\n');
    }

    public int getScore() {
        return this.score;
    }
}
