/*
 * ClientSocket.java                                    24 oct. 2023
 * IUT Rodez, info1 2022-2023, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

/**
 * Représente le client dans les échanges de données via le réseau.
 */
public class Client {

	/**
	 * La Socket client utilisée pour échanger.
	 */
	private static Socket sock;

	private static final File FICHIER_RECEPTION 
		= new File("src/iut/sae/modele/reseau/tests/fichRecu.txt");

	/**
	 * Méthode de test des sockets.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			creerClient("127.0.0.1", 6666);

			String cle = "";
			String recu = "";

			while (recu.isEmpty()) {
				System.out.print("Génération et envoi de la clé");
				cle = construireMessage();
				envoyerMessage(cle);
				recu = recevoirMessage(FICHIER_RECEPTION, cle);
			}

			fermerSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param host l'adresse ou le nom du serveur
	 * @param port le port du serveur
	 * @throws IOException si la socket client ne peut être crée
	 */
	public static void creerClient(String host, int port) throws IOException {
		System.out.println("CREATION DU CLIENT");
		try {
			sock = new Socket(host, port);
		} catch (IOException e) {
			throw new IOException("Impossible de créer la Socket client.");
		}
	}

	/**
	 * Méthode qui crée la clé a envoyer au serveur a partir d'un fichier
	 * 
	 * @return renvoie une chaine avec la clé à envoyer
	 * @throws IOException si le message n'a pas pu être construit
	 */
	public static String construireMessage() throws IOException {
		return Cryptage.genereCle();
	}

	/**
	 * @param data les données à envoyer
	 * @throws IOException si les données ne sont pas envoyées.
	 */
	public static void envoyerMessage(String data) throws IOException {
		System.out.println("ENVOI DES DONNEES");
		try {
			OutputStream os = sock.getOutputStream();
			BufferedWriter bf = new BufferedWriter(new OutputStreamWriter(os, "UTF-16"));
			bf.write(data);
			bf.flush();
			bf.close();
			System.out.println("Le client a envoyé : " + data.toString());
		} catch (IOException e) {
			throw new IOException("Impossible d'envoyer le message au serveur.");
		}
	}

	/**
	 * Saisie le message reçu dans un fichier.
	 * 
	 * @return le message reçu
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static String recevoirMessage(File fichRecu, String cle) throws InterruptedException, IOException {
		try {
			System.out.println("RECEPTION DES DONNEES");

			// Création des canaux de communication
			InputStream is = sock.getInputStream();
			BufferedReader bf = new BufferedReader(new InputStreamReader(is, "UTF-16"));
			boolean test = true;
			while (test) {
				if (is.available() != 0) {
					System.out.println("En attente...");
					test = false;
				}
			}

			// Lecture du fichier
			String recu = "";
			while (bf.ready()) {
				recu += Character.toString(bf.read());
			}
			bf.close();
			System.out.println("Le client a reçu : " + recu);

			// Vérification de l'existence du fichier d'écriture
			if (!fichRecu.exists()) {
				fichRecu.createNewFile();
			}

			Cryptage.dechiffrer(recu, cle);

			// Ecriture dans le fichier
			FileWriter fw = new FileWriter(fichRecu);
			fw.append(recu);
			fw.flush();
			fw.close();

			System.out.println("Le client a reçu : " + recu);
			return recu;
		} catch (IOException e) {
			e.printStackTrace();
			throw new IOException("Impossible de recevoir le message du serveur.");
		} catch (Exception e) {
			e.printStackTrace();
			throw new InterruptedException("La connection a été interrompue");
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
			sock.close();
		} catch (IOException e) {
			throw new IOException("Impossible de fermer la Socket client.");
		}

	}
}
