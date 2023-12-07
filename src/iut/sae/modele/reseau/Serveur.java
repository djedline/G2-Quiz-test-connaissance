/* Serveur.java												16/10/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni de "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Classe qui représente le serveur lors d'un échange d'information
 * @author leila.baudroit
 * @author djedline.boyer
 * @author nael.briot
 * @author tany.catala-bailly
 * @author leo.cheikh-boukal
 */
public class Serveur {

	private static final File FICHIER_A_ENVOYER = 
			new File("src/iut/sae/modele/reseau/tests/fichEnvoi.txt");

	/** socket de connexion lors du démarrage du client et serveur */
	public ServerSocket conn;

	/** socket qui permet la communication entre le serveur et le client */
	private Socket comm;

	private ExecutorService executor;

	/**
	 * Utilitaires réseaux pour envoyer et recevoir facilement les chaînes
	 * de caractères. Lié à la socket.
	 */
	private ReseauUtils util;

	/**
	 * prépare le serveur en démarrant la socket conn.
	 * @throws IOException si la socket serveur ne peut être crée
	 */
	public Serveur() throws IOException {
		System.out.println("CREATION DU SERVEUR");
		try {
			conn = new ServerSocket(6666);
			System.out.println("coucou");
		} catch (UnknownHostException e) {
			throw new IOException("Impossible de trouver l'adresse IP.", e);
		} catch (IOException e) {
			throw new IOException("Impossible de créer la Socket serveur.", e);
		}
	}

	/** 
	 * Envoie les données initiales (P et G de Diffie-Hellman)
	 * @return int
	 * @throws IOException 
	 * @throws InterruptedException 
	 */
	public int envoiDonneesInitiale() throws IOException, InterruptedException {
		int p = DiffieHellman.genererModulo();
		int g = DiffieHellman.genererGenerateur();
		String msgP = Integer.toString(p);
		String msgG = Integer.toString(g);
		int b = DiffieHellman.genererX();

		System.out.println("Envoi de G : ");
		util.envoyerMessage(msgG);
		Thread.sleep(1000);
		System.out.println("Envoi de P : ");
		util.envoyerMessage(msgP);

		String msgGB = Integer.toString((int) Math.pow(g, b));
		Thread.sleep(1000);
		System.out.println("Envoi de GB : ");
		util.envoyerMessage(msgGB);

		System.out.println("Réception de GA : ");
		String msgGA = util.reception();
		try {
			int gA = Integer.parseInt(msgGA);
			int cle = (int) Math.pow(gA, b);
			System.out.println("Clé générée : " + cle);
			return cle;
		} catch (NumberFormatException e) {
			throw new IOException("Données corrompues envoyées par le client.");
		}
	}

	/** 
	 * Receptionne le contenu d'un fichier
	 * @param cle
	 * @return f
	 * @throws IOException
	 */
	public String receptionFichier(int cle) throws IOException {
		String contenuFichCrypte = util.reception();
		return Cryptage.dechiffrer(contenuFichCrypte, 
				Integer.toString(cle));
	}

	/*
	 * 
	 * @param args
	 * @throws InterruptedException
	 *
    public void main(String[] args) throws InterruptedException {
        preparerServeur();
        accepterConnexion(); // bloquante : attend que le client se connecte
        String cle = "";
        String recu ="";
        while (recu.isEmpty()) {
            System.out.print("Génération et envoi de la clé");
            try {
                cle = genererCle();
                System.out.println("Le client a envoyé : la cle)");
                envoyerMessage(cle.getBytes());
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }

            //recu = recevoirMessage(FICHIER_RECEPTION, cle);
        }

       // reponse = recevoirEtAnalyser();
        //envoyerReponse(reponse);

        Thread.sleep(1000);

        System.out.println("FERMETURE DU SERVEUR");
        try {
            conn.close();
        } catch (IOException e) {
            System.err.println("Impossible de fermer la socket serveur.");
            e.printStackTrace();
        }
    }*/


	/**
	 * Ferme le serveur
	 */
	public void fermetureServeur() {
		try {
			if (comm != null) {
				comm.close();
			}
			if (conn != null) {
				conn.close();
			}
		} catch (IOException e) {
			System.err.println("Impossible de fermer la socket serveur.");
			e.printStackTrace();
		}
	}

	/**
	 * attend qu'un client demande une connexion et l'accepte
	 * @param timeout 
	 * @return un boolean
	 */
	public boolean accepterConnexion(int timeout) {

		System.out.println("ACCEPTATION");
		try {
			executor = Executors.newSingleThreadExecutor();

			executor.submit(() -> {
				try {
					comm = conn.accept();
					util = new ReseauUtils(comm);
					System.out.println("La inet Adress conn : " 
							+ conn.getInetAddress());
					System.out.println("La inet Adress comm : " 
							+ comm.getInetAddress());
				} catch (IOException e) {
					System.err.println("Impossible d'accepter la connection.");
					//e.printStackTrace();
				}
			}).get(timeout, TimeUnit.SECONDS);
			return true;
		} catch (Exception e) {
			System.err.println("La connexion n'a pas pu être établie dans le délai spécifié.../nFermeture en cours...");
			//e.printStackTrace();
			return false;
		} finally {
			if (executor != null) {
				executor.shutdownNow(); // Arrête le thread après l'exécution
			}
		}
	}
}