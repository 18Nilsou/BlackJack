import java.util.ArrayList;

public class Paquet {
    private ArrayList<Carte> paquet = new ArrayList<>();

    public Paquet() {
        initPaquet();
    }

    private void initPaquet() {
        this.paquet.clear();
        for (Noms nom : Noms.values()) {
            for (Couleurs couleur : Couleurs.values()) {
                paquet.add(new Carte(nom, couleur));
            }
        }
    }

    public Carte tirerCarte() {
        if (paquet.size() == 0) {
            initPaquet();
        }
        Carte c = this.paquet.get((int)(Math.random() * paquet.size()));
        this.paquet.remove(c);
        return c;
    }

}
