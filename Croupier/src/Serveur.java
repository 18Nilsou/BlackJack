import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;


public class Serveur {

    private int port;
    private int nombreDeJoueurs;

    public Serveur(int port, int nombreDeJoueurs) {
        this.port = port;
        this.nombreDeJoueurs = nombreDeJoueurs;
    }

    public void lancer() throws IOException {
        ServerSocket serveurSocket = new ServerSocket(port);
        System.out.println("Serveur lancé sur le port " + port);

        for (int i = 0; i < nombreDeJoueurs; i++) {

            Socket joueur = serveurSocket.accept();
            System.out.println("Joueur connecté");


            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(joueur.getOutputStream()));
            BufferedReader in = new BufferedReader(new InputStreamReader(joueur.getInputStream()));

            String chaine = "";
            Carte c;
            Croupier croupier = new Croupier();

            while (true) {

                /*
                FLAG :
                joueur -> on donne une ou plusieurs cartes au joueur
                joueurFin -> on a fini de donner des cartes au joueur
                croupier -> on montrer les cartes du croupier
                 */


                out.write("joueur");
                out.newLine();
                out.flush();


                for (int j = 0; j < 2; j++) {
                    c = croupier.donnerCarte();
                    out.write(c.toString());
                    out.newLine();
                    out.flush();
                }

                out.write("joueurFin");
                out.newLine();
                out.flush();
                in.readLine();

                croupier.tirerCarte();

                out.write("croupier");
                out.newLine();
                out.flush();
                out.write(croupier.montrerMain());
                out.newLine();
                out.flush();

                out.write("Voulez-vous tirer une carte ? (oui/non)" + "\n");
                out.newLine();
                out.flush();

                int score = 0;

                while ("oui".equals(in.readLine())) {
                    out.write("joueur");
                    out.newLine();
                    out.flush();

                    c = croupier.donnerCarte();
                    out.write(c.toString());
                    out.newLine();
                    out.flush();

                    out.write("joueurFin");
                    out.newLine();
                    out.flush();
                    chaine = in.readLine();
                    try {
                        score = Integer.parseInt(chaine);
                    } catch (NumberFormatException e) {
                        System.out.println(chaine);
                    }
                    if (score > 21) {
                        break;
                    }
                    out.write("Voulez-vous tirer une carte ? (oui/non)" + "\n");
                    out.newLine();
                    out.flush();
                }

                while (croupier.getScore() < 17) {
                    croupier.tirerCarte();
                }
                out.write("croupier");
                out.newLine();
                out.flush();
                out.write(croupier.montrerMain());
                out.newLine();
                out.flush();


                if (score > 21) {
                    out.write("*** Vous avez perdu ***");
                    out.newLine();
                    out.flush();
                }else if (croupier.getScore() > 21) {
                    out.write("*** Vous avez gagné ***");
                    out.newLine();
                    out.flush();
                } else if (croupier.getScore() > score) {
                    out.write("*** Vous avez perdu ***");
                    out.newLine();
                    out.flush();
                } else if (croupier.getScore() == score) {
                    out.write("*** Egalité ***");
                    out.newLine();
                    out.flush();
                } else{
                    out.write("*** Vous avez gagné ***");
                    out.newLine();
                    out.flush();
                }

                out.write("Nouvelle partie ? (oui/non)");
                out.newLine();
                out.flush();
                if(in.readLine().equals("non")){
                    out.write("fin");
                    out.newLine();
                    out.flush();
                    break;
                }

                croupier.nouvelleMain();
                out.newLine();
                out.flush();
            }
            out.close();
            in.close();
            joueur.close();
        }
        serveurSocket.close();
    }

    public static void main(String[] args) throws IOException {
        Serveur serveur = new Serveur(5000, 100000);
        serveur.lancer();
    }
}