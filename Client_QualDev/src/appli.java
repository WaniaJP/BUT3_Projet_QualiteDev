import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

import Codage.Encodage;

public class appli {

	public static void main(String[] args) {
		try {
			if(args.length <= 0) {
				System.out.println("Aucun numéro de port passé en paramètre.");
			}
			else if(args[0] == null || !args[0].matches("[0-9]+")) {
				System.out.println("La valeur indiquée n'est pas valide.");
			}
			else {
				int port = Integer.parseInt(args[0]);
				Socket socket = new Socket("localhost", port);
				Scanner sc = new Scanner(System.in);
				System.out.println("Connexion réussie");
				
				PrintWriter sOut = new PrintWriter(socket.getOutputStream(), true);
				BufferedReader sIn = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				while(true) {
					String resultat = Encodage.decodage(sIn.readLine());
					System.out.print(resultat);
					if(resultat.contains("<>"))
						break;
					sOut.println(sc.nextLine());
				}
			}
		} catch (IOException e) {
			System.out.println("Aucune connexion ouverte à ce port.");
		}

	}

}
