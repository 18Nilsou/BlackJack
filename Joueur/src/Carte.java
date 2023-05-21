public class Carte {
    private String nom;
    private int valeur;
    private String couleur;

    public Carte(String nom, String couleur) {
        this.nom = nom;
        this.couleur = couleur;
        this.setValeur();
    }
    public Carte(){

    }

    public void setNom(String nom) {
        this.nom = nom;
        this.setValeur();
    }

    public void setCouleur(String couleur) {
        this.couleur = couleur;
    }

    public int getValeur() {
        return valeur;
    }

    public String getNom() {
        return nom;
    }

    public String getCouleur() {
        return couleur;
    }

    public String toString() {
        return this.nom + " de " + this.couleur;
    }

    private void setValeur() {
        switch (this.nom) {
            case "AS":
                this.valeur = 11;
                break;
            case "DEUX":
                this.valeur = 2;
                break;
            case "TROIS":
                this.valeur = 3;
                break;
            case "QUATRE":
                this.valeur = 4;
                break;
            case "CINQ":
                this.valeur = 5;
                break;
            case "SIX":
                this.valeur = 6;
                break;
            case "SEPT":
                this.valeur = 7;
                break;
            case "HUIT":
                this.valeur = 8;
                break;
            case "NEUF":
                this.valeur = 9;
                break;
            default:
                this.valeur = 10;
                break;
        }
    }
}
