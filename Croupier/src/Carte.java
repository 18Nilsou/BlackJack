public class Carte {
    private Noms nom;
    private int valeur;
    private Couleurs couleur;

    public Carte(Noms nom, Couleurs couleur) {
        this.nom = nom;
        this.couleur = couleur;
        this.setValeur();
    }

    public int getValeur() {
        return valeur;
    }

    public Noms getNom() {
        return nom;
    }

    public Couleurs getCouleur() {
        return couleur;
    }

    public String toString() {
        return this.nom + " " + this.couleur;
    }

    private void setValeur() {
        switch (this.nom) {
            case AS:
                this.valeur = 11;
                break;
            case DEUX:
                this.valeur = 2;
                break;
            case TROIS:
                this.valeur = 3;
                break;
            case QUATRE:
                this.valeur = 4;
                break;
            case CINQ:
                this.valeur = 5;
                break;
            case SIX:
                this.valeur = 6;
                break;
            case SEPT:
                this.valeur = 7;
                break;
            case HUIT:
                this.valeur = 8;
                break;
            case NEUF:
                this.valeur = 9;
                break;
            default:
                this.valeur = 10;
                break;
        }
    }
}
