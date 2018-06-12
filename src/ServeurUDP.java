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
		
		//Cr�ation de la socket de r�ception qui �coute sur le port 4567
		DatagramSocket socketServeur = new DatagramSocket(4567);
		
		while(true) {
	
		byte[] receiveData = new byte [1024];
		//C'est important que �a soit de la m�me longueur
		byte[]sendData=new byte[receiveData.length];
		//On cr�er le packet des donn�es des voix par exemple qu'on va recevoir par le cable
		
	
		//
		DatagramPacket receivePacket = new DatagramPacket(receiveData,receiveData.length);
		try {
			//Dans ma socket, je re�ois le tableau de paquets
			socketServeur.receive(receivePacket);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		//Cr�ation de la socket r�ception: Ma chaine de caract�re prend en entr�e un tableau de bytes
		String line = new String(receivePacket.getData());
		System.out.println(line);

		
		//Datagram � envoyer au client
		//On cr�e un objet qui cr�e une adresse Ip, on r�cup�re l'adresse ip qui a envoy� les donn�es
		InetAddress adresseIP = receivePacket.getAddress();
		//Tu r�cup�re le port du paquet que tu as re�u
		int port = receivePacket.getPort();
		//On va r�pondre
		String upperCaseLine = line.toUpperCase();
		//On met tout en majuscule dans sendData et on traduit en bytes
		sendData = upperCaseLine.getBytes();
		//On recr�er un nouveau paquet avec les lettes en majuscule sous format byte, �a contient les �l�ments o� on doit l'envoyer
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
