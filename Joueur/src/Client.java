import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;

public class Client {
   String ip;
    int port;

    public Client(String ip, int port){
        this.ip = ip;
        this.port = port;
    }

    public void lancer() throws UnknownHostException, IOException{
        Socket socket = new Socket(ip, port);
        String bufferEnvoyer;
        //recevoir les messages du croupier
        String bufferRecevoir = "";

        //lire les entrer clavier du joueur
        BufferedReader clavier = new BufferedReader(new InputStreamReader(System.in));
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));

        Joueur joueur = new Joueur("J1");
        Carte c = new Carte();
        while(true){

            /*
                FLAG :
                joueur -> on donne une ou plusieurs cartes au joueur
                joueurFin -> on a fini de donner des cartes au joueur
                croupier -> on montrer les cartes du croupier
             */


            bufferRecevoir = in.readLine();
            //System.out.println(bufferRecevoir);

            //tirer des cartes
            if(bufferRecevoir.equals("Voulez-vous tirer une carte ? (oui/non)")){
                System.out.println("Voulez-vous tirer une carte ? (oui/non)");
                bufferEnvoyer = clavier.readLine();
                out.write(bufferEnvoyer);
                out.newLine();
                out.flush();
            }

            // montrer les cartes du croupier
            if(bufferRecevoir.equals("croupier")){
                bufferRecevoir = in.readLine();
                System.out.println(bufferRecevoir);
            }

            if(bufferRecevoir.equals("Nouvelle Main")){
                joueur.nouvelleMain();
            }

            // montrer les cartes du joueur
            if(bufferRecevoir.equals("joueur")){
                bufferRecevoir = in.readLine();
                while(!(bufferRecevoir.equals("joueurFin"))){
                    String[] param =  bufferRecevoir.split(" ");
                    c.setNom(param[0]);
                    c.setCouleur(param[1]);
                    joueur.recevoirCarte(c);
                    bufferRecevoir = in.readLine();
                }
                System.out.println(joueur.montrerMain());
                out.write(String.valueOf(joueur.getScore()));
                out.newLine();
                out.flush();
            }

            if(bufferRecevoir.equals("*** Vous avez gagné ***")){
                System.out.println("*** Vous avez gagné ***");
                joueur.nouvelleMain();
            }
            if(bufferRecevoir.equals("*** Vous avez perdu ***")){
                System.out.println("*** Vous avez perdu ***");
                joueur.nouvelleMain();
            }
            if(bufferRecevoir.equals("*** Egalité ***")){
                System.out.println("*** Egalité ***");
                joueur.nouvelleMain();
            }
            if(bufferRecevoir.equals("Nouvelle partie ? (oui/non)")){
                System.out.println(bufferRecevoir);
                bufferEnvoyer = clavier.readLine();
                out.write(bufferEnvoyer);
                out.newLine();
                out.flush();
            }
            if(bufferRecevoir.equals("fin")){
                break;
            }
        }
    }

    public static void main(String[] args) throws UnknownHostException, IOException {
        Client client = new Client("127.0.0.1", 5000);
        client.lancer();
    }
}