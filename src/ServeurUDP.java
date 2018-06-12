import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

public class ServeurUDP {
	
	private static final InetAddress IPAd = null;

	public static void main(String[] args) throws SocketException {
		
		//Création de la socket de réception qui écoute sur le port 4567
		DatagramSocket socketServeur = new DatagramSocket(4567);
		
		while(true) {
	
		byte[] receiveData = new byte [1024];
		//C'est important que ça soit de la même longueur
		byte[]sendData=new byte[receiveData.length];
		//On créer le packet des données des voix par exemple qu'on va recevoir par le cable
		
	
		//
		DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		try {
			//Dans ma socket, je reçois le tableau de paquets
			socketServeur.receive(receivePacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Création de la socket réception: Ma chaine de caractère prend en entrée un tableau de bytes
		String line = new String(receivePacket.getData());
		System.out.println(line);

		
		//Datagram à envoyer au client
		//On crée un objet qui crée une adresse Ip, on récupère l'adresse ip qui a envoyé les données
		InetAddress adresseIP = receivePacket.getAddress();
		//Tu récupère le port du paquet que tu as reçu
		int port = receivePacket.getPort();
		//On va répondre
		String upperCaseLine = line.toUpperCase();
		//On met tout en majuscule dans sendData et on traduit en bytes
		sendData = upperCaseLine.getBytes();
		//On recréer un nouveau paquet avec les lettes en majuscule sous format byte, ça contient les éléments où on doit l'envoyer
		DatagramPacket sendPacket = new DatagramPacket(sendData,sendData.length, adresseIP, port);
		try {
			socketServeur.send(sendPacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
			
	}

}
