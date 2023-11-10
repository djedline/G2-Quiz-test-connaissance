/*
 * Client.java                                    24 oct. 2023
 * IUT Rodez, info1 2023-2024, pas de copyright ni "copyleft"
 */
package iut.sae.modele.reseau;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * Classe de représentation d'un client lors d'un échange de données entre deux
 * machines sous le même réseau
 * 
 * @author leila.baudroit,djedline.boyer,nael.briot,tany.catala-bailly,
 *         léo.cheikh-boukal
 */
public class Client {

	/** Numéro du port choisi pour la connexion entre les 2 machines */
	private static final int NUMERO_DE_PORT = 6666;

	/** La Socket client utilisée pour échanger. */
	private static Socket sock;

	/**
	 * Envoie le fichier que l'utilisateur veut transmettre a la machine serveur
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.print("Donnez l'adresse IP du serveur : ");
			creerClient(sc.nextLine(), NUMERO_DE_PORT);

			String message = "";
			File fichierATraiter;
			String messageRecu = "";

			while (messageRecu.isEmpty()) {
				System.out.print("Saisissez le chemin absolu du fichier a envoyer : ");
				fichierATraiter = new File(sc.nextLine());
				message = contruireMessage(fichierATraiter);
				envoyerMessage(message);
				messageRecu = recevoirMessage();
				System.out.println(messageRecu);
			}
			sc.close();

			fermerSocket();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Méthode qui créer la socket de connexion du coté client
	 * 
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
	 * Méthode qui crée le message a envoyer au serveur a partir d'un fichier
	 * 
	 * @param aEnvoyer fichier que l'on va traiter pour etre envoyer sous forme de
	 *                 chaine
	 * @return renvoie une chaine avec le contenu du fichier
	 * @throws IOException si le fichier n'existe pas ou il n'est pas autorisé de
	 *                     lecture
	 */
	public static String contruireMessage(File aEnvoyer) throws IOException {
		System.out.println("Chemin : " + aEnvoyer.getAbsolutePath());
		FileReader fr = new FileReader(aEnvoyer);
		String message = "";
		while (fr.ready()) {
			message += Character.toString(fr.read());
		}
		fr.close();
		return message;
	}

	/**
	 * Méthode qui envoie les données, passées en paramètre, dans la socket qui lie
	 * le client au serveur.
	 * 
	 * @param data les données à envoyer
	 * @throws IOException si les données ne sont pas envoyées.
	 */
	public static void envoyerMessage(String data) throws IOException {
		System.out.println("ENVOI DES DONNEES");
		try {
			OutputStream os = sock.getOutputStream();
			os.write(data.getBytes());
			System.out.println("Le client a envoyé : " + data);
		} catch (IOException e) {
			throw new IOException("Impossible d'envoyer le message au serveur.");
		}
	}

	/**
	 * méthode qui permet de bloquer le programme et d'attendre une réponse du
	 * serveur pour le client
	 * 
	 * @return le message reçu
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public static String recevoirMessage() throws InterruptedException, IOException {
		try {
			System.out.println("RECEPTION DES DONNEES");
			InputStream is = sock.getInputStream();

			// La partie bloquante
			boolean messageBienRecu = false;

			while (!messageBienRecu) {
				messageBienRecu = is.available() != 0;
			}

			// Boucle qui recrée le message caractère par caractère
			String messageRecu = "";

			while (is.available() != 0) {
				messageRecu += Character.toString(is.read());
			}

			System.out.println("Le client a reçu : " + messageRecu);
			return messageRecu;

		} catch (IOException e) {
			throw new IOException("Impossible de recevoir le message du serveur.");
		} catch (Exception e) {
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
