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
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Classe qui représente le serveur lors d'un échange d'information
 * @author Baudroit Leila, Boyer Djedline, Briot Nael, Catala-Bailly,
 * 		   Cheikh-Boukal Léo  
 */
public class Serveur {
	
	/** socket de connexion lors du démarrage du client et serveur */
	static ServerSocket conn;
	
	/** socket qui permet la communication entre le serveur et le client */
	static Socket comm;
	
	/**
	 * 
	 * @param args
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws InterruptedException {
		preparerServeur();
		accepterConnexion(); // bloquante : attend que le client se connecte
		String reponse = "";
		
		reponse = recevoirEtAnalyser();
		envoyerReponse(reponse);
		
		System.out.println("FERMETURE DU SERVEUR");
		try {
			conn.close();
		} catch (IOException e) {
			System.err.println("Impossible de fermer la socket serveur.");
			e.printStackTrace();
		}
		
	}
	
	/**
	 * prépare le serveur en démarrant la socket conn
	 */
	public static void preparerServeur() {
		System.out.println("CREATION DU SERVEUR");
		try {
			conn = new ServerSocket(6666);
			System.out.println("La inet Adress : " + conn.getInetAddress());
		} catch (IOException e) {
			System.err.println("Impossible de créer la Socket serveur.");
			e.printStackTrace();
		}
	}
	
	/**
	 * attend qu'un client demande une connexion et l'accepte
	 */
	public static void accepterConnexion(){
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
	 * Permet de recevoir la requete du client et de l'analyser pour 
	 * construire le contenu de la reponse
	 * @return text : la reponse a la requete
	 */
	public static String recevoirEtAnalyser() {
		String text = "";
		
		System.out.println("RECEPTION DE LA REPONSE");
		try {
			if (comm != null) {
				InputStream is = comm.getInputStream();
				boolean test = true;
				while (test) {
					if (is.available() != 0) {
						System.out.println("Le serveur a recu : " + is.available() + " octets.");
						test = false;	
					}
				};
				while (is.available() != 0) {
					text += Character.toString(is.read());
				}
				System.out.println("Le serveur a reçu : " + text);
				File aEcrire = new File("iut/sae/modele/reseau/tests/fichierRecu.txt");
				System.out.println(aEcrire.getAbsolutePath());
				if (!aEcrire.exists()) {
					aEcrire.createNewFile();
				}
				FileWriter fw = new FileWriter(aEcrire);
				fw.append(text);
				fw.flush();
				fw.close();
				
				FileReader fr = new FileReader(aEcrire);
				String lu = "";
				while(fr.ready()){
		    		lu += Character.toString(fr.read());
		    	}
				fr.close();
				System.out.println("le serveur a écrit : " + lu);
			}
		} catch (Exception e) {
			System.err.println("Impossible de recevoir la requête.");
			e.printStackTrace();
		}
		
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
}