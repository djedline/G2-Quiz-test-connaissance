/* Serveur.java												16/10/2023
 * IUT Rodez, info2 2023-2024, pas de copyright ni de "copyleft"
 */
package iut.sae.modele.reseau;

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
		while (!reponse.equalsIgnoreCase("stop")) {
			reponse = recevoirEtAnalyser();
			envoyerReponse(reponse);
		}
		Thread.sleep(5000);
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
				while (is.available() == 0) {
					System.out.println("Le serveur a recu : " + is.available() + " octets.");
					Thread.sleep(1000);
				};
				while (is.available() != 0) {
					text += Character.toString(is.read());
				}
				System.out.println("Le serveur a reçu : " + text);
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
		
		/*
		for(int i = 0 ; i < 5 ; i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}*/
	}
}
