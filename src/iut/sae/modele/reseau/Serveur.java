/* Serveur.java												16/10/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni de "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Classe qui représente le serveur lors d'un échange d'information
 * 
 * @author Baudroit Leila, Boyer Djedline, Briot Nael, Catala-Bailly,
 *         Cheikh-Boukal Léo
 */
public class Serveur {

	/** Numéro du port choisi pour la connexion entre les 2 machines */
	private static final int NUMERO_DE_PORT = 6666;

	/** Socket de connexion lors du démarrage du client et serveur */
	private static ServerSocket conn;

	/** Socket qui permet la communication entre le serveur et le client */
	private static Socket comm;

	/**
	 * Recoit un fichier qu'un utilisateur aurait envoyer a cette machine serveur
	 * 
	 * @param args
	 * @throws InterruptedException
	 * @throws IOException 
	 */
	public static void main(String[] args) throws InterruptedException, IOException {
		preparerServeur();
		accepterConnexion(); // bloquante : attend que le client se connecte
		String reponse = "";

		reponse = recevoirEtAnalyser();
		envoyerReponse(reponse);

		fermerSocket();

	}

	/**
	 * Prépare le serveur en démarrant la socket conn qui va ecouter un port déjà
	 * choisi
	 * @throws UnknownHostException 
	 */
	public static void preparerServeur() throws UnknownHostException {
		InetAddress ip = InetAddress.getLocalHost();
		System.out.println("CREATION DU SERVEUR");
		try {
			conn = new ServerSocket(NUMERO_DE_PORT);
			System.out.println("L'adresse IP de la machine est : " + ip.getHostAddress());
		} catch (IOException e) {
			System.err.println("Impossible de créer la Socket serveur.");
			e.printStackTrace();
		}
	}

	/**
	 * Attend qu'un client demande une connexion et l'accepte
	 */
	public static void accepterConnexion() {
		
		System.out.println("ACCEPTATION");
		try {
			comm = conn.accept();
			System.out.println("La inet Adress conn : " + conn.getInetAddress());
			System.out.println("La inet Adress comm : " + comm.getInetAddress());
		} catch (IOException e) {
			System.err.println("Impossible d'accepter la connection.");
			e.printStackTrace();
		}
	}

	/**
	 * Permet de recevoir la requete du client et de l'analyser pour construire le
	 * contenu de la reponse
	 * 
	 * @return text : la reponse a la requete
	 */
	public static String recevoirEtAnalyser() {
		String text = "";

		System.out.println("RECEPTION DE LA REPONSE");
		try {
			if (comm != null) {
				InputStream is = comm.getInputStream();
				boolean messageRecu = true;
				while (messageRecu) {
					if (is.available() != 0) {
						System.out.println("Le serveur a recu : " + is.available() + " octets.");
						messageRecu = false;
					}
				}
				while (is.available() != 0) {
					text += Character.toString(is.read());
				}
				System.out.println("Le serveur a reçu : " + text);
				File aEcrire = new File("iut/sae/modele/reseau/tests/fichierRecu.txt");
				System.out.println(aEcrire.getAbsolutePath());
				
				if (!aEcrire.exists()) {
					aEcrire.createNewFile();
				}
				// Ecrit dans le fichier le contenu qu'on lui a envoyer
				FileWriter fw = new FileWriter(aEcrire);
				fw.append(text);
				fw.flush();
				fw.close();

				// Verifie qu'est ce qu'il a écrit dans le fichier  
				FileReader fr = new FileReader(aEcrire);
				String lu = "";
				while (fr.ready()) {
					lu += Character.toString(fr.read());
				}
				fr.close();
				System.out.println("le serveur a écrit : " + lu);
				
			}
		} catch (Exception e) {
			System.err.println("Impossible de recevoir la requête.");
			e.printStackTrace();
		}
		System.out.println("text : " + text);
		return text;
	}

	/**
	 * Permet d'envoyer la reponse au client en retour d'une requete
	 * @param rep : la reponse a envoyer
	 */
	public static void envoyerReponse(String rep) {
		System.out.println("ENVOI DE LA REPONSE");
		System.out.println("Le serveur est : " + comm.getLocalSocketAddress());
		System.out.println("Le client est : " + comm.getRemoteSocketAddress());
		try {
			if (comm != null) {
				OutputStream os = comm.getOutputStream();
				os.write(rep.getBytes());
				System.out.println("Le serveur a envoyé " + rep);
			}
		} catch (Exception e) {
			System.err.println("Impossible de répondre au client.");
			e.printStackTrace();
		}
	}
	
	/**
	 * Ferme la socket courante.
	 * 
	 * @throws IOException si la socket ne peut être fermée
	 */
	public static void fermerSocket() throws IOException {
		System.out.println("FERMETURE DU CLIENT");
		try {
			conn.close();
		} catch (IOException e) {
			throw new IOException("Impossible de fermer la Socket client.");
		}

	}
}