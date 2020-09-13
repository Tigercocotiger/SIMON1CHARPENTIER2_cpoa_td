import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;
public class Client extends Connexion{
	
	public static void MenuClient(){
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"\n"
			  +	"             CLIENT            "+"\n"+"\n"
			  +	"[1]------Ajout d'un client-----[1]"+"\n"
			  + "[2]--Modification d'un client--[2]"+"\n"
			  + "[3]--Suppression d'un client---[3]"+"\n"
		      + "[4]---Affichage des clients----[4]"+"\n"
		      + "[5]-----------Retour-----------[5]");
		int id = sc.nextInt();
		switch(id) {
		case 1:
			AjoutClient();
			break;
		case 2:
			ModifClient();
			break;
		case 3:
			SupprClient();
			break;
		case 4:
			Afficher_Client();
			break;
		case 5:
			Main.menu();
			break;
		default:
		System.out.println("Mauvaise entrée veuillez recommencer ");
		MenuClient();
			}
		}
	public static void AjoutClient() {
		try {
			
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrer l'identifiant du client : ");
			int id = sc.nextInt();
			System.out.println("Entrer le nom du client : ");
			String nom = sc.next();
			System.out.println("Entrer le prénom du client : ");
			String prenom = sc.next();
			System.out.println("Entrer l'identifiant de connexion client : ");
			String idx = sc.next();
			System.out.println("Entrer le mot de passe du client : ");
			String mdp = sc.next();
			System.out.println("Entrer le numéro de ruedu client : ");
			String numéroad = sc.next();
			System.out.println("Entrer la rue du client : ");
			String voie = sc.next();
			System.out.println("Entrer le code postal du client : ");
			int codepost = sc.nextInt();
			System.out.println("Entrer la ville du clientt : ");
			Double ville = sc.nextDouble();
			System.out.println("Entrer le pays du client : ");
			String pays = sc.next();
			
			Connexion c1 = new Connexion();
			Connection laConnexion = c1.creeConnexion();
			Statement requete = laConnexion.createStatement();
			int res = requete.executeUpdate("INSERT INTO Client (id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES ('"+id+"', '"+nom+"', '"+prenom+"', '"+idx+"', '"+mdp+"', '"+numéroad+"', '"+voie+"', '"+codepost+"', '"+ville+"', '"+pays+"')");
		} catch (SQLException sqle) {
			System.out.println("Pb selection " + sqle.getMessage());
		}
}
	
	
	
	public static void SupprClient() {
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrer l'identifiant du client à supprimer : ");
			int id = sc.nextInt();
			Connexion c1 = new Connexion();
			Connection laConnexion = c1.creeConnexion();
			Statement requete = laConnexion.createStatement();
			int res = requete.executeUpdate("DELETE FROM Client WHERE id_client = '"+id+"'");
		} catch (SQLException sqle) {
			System.out.println("Pb selection " + sqle.getMessage());
		}
		}
	
	public static void  ModifClient(){
		try {
			Scanner sc = new Scanner(System.in);
			System.out.println("Entrer l'ancien identifiant du client : ");
			int oldid = sc.nextInt();
			System.out.println("Entrer le nouvel identifiant du client : ");
			int id = sc.nextInt();
			System.out.println("Entrer le nouveau nom du client : ");
			String nom = sc.next();
			System.out.println("Entrer le nouveau prénom du client : ");
			String prenom = sc.next();
			System.out.println("Entrer le nouvel identifiant de connexion client : ");
			String idx = sc.next();
			System.out.println("Entrer le  nouveau mot de passe du client : ");
			String mdp = sc.next();
			System.out.println("Entrer le nouveau numéro de ruedu client : ");
			String numéroad = sc.next();
			System.out.println("Entrer la  nouveau rue du client : ");
			String voie = sc.next();
			System.out.println("Entrer le  nouveau code postal du client : ");
			String codepost = sc.next();
			System.out.println("Entrer la  nouvelle ville du clientt : ");
			Double ville = sc.nextDouble();
			System.out.println("Entrer le  nouveau pays du client : ");
			String pays = sc.next();
			Connexion c1 = new Connexion();
			Connection laConnexion = c1.creeConnexion();
			Statement state= laConnexion.createStatement();
			state.executeUpdate("UPDATE Client SET id_client='"+id+"',nom='"+nom+"',prenom='"+prenom+"',identifiant='"+idx
					+"',mot_de_pass'"+mdp+"',adr_numero='"+numéroad+"',adr_voie='"+voie+"',adr_code_postal='"+codepost+"',adr_ville='"+ville+"',adr_pays='"+pays+"' WHERE id_client='"+oldid+"'");
			state.close();
		}catch (SQLException sqle) {
			System.out.println("Pb selection " + sqle.getMessage());
			} 	
		
	}
	public static void Afficher_Client() {
	    try {
	   ArrayList<String> ligne = new ArrayList<String>();
	   Connexion c1 = new Connexion();
	   Connection laConnexion = c1.creeConnexion();
	   Statement requete = laConnexion.createStatement();
	   ResultSet res = requete.executeQuery("select * from Produit");

	   while (res.next()) {
	       int no = res.getInt(1);
	       String nom = res.getString("nom");
	       String prenom = res.getString("prenom");
	       String id = res.getString("identifiant");
	       String mdp = res.getString("mot_de_passe");
	       String numéroad = res.getString("adr_numero");
	       String voie = res.getString("adr_voie");
	       String codepost = res.getString("adr_code_postal");
	       String ville = res.getString("adr_ville");
	       String pays = res.getString("adr_pays");
	       String L=no+"  "+nom+"   "+prenom+"  "+id+"  "+mdp+"  "+numéroad+"    "+voie+"   "+codepost+"  "+ville+"  "+pays;
	       ligne.add(L);
	       

	   }
	   for (int i = 0; i < ligne.size(); i++) {
		      System.out.println(ligne.get(i));
		    }
	   if (res != null)
	   res.close();
	   if (requete != null)
	   requete.close();
	   if (laConnexion != null)
	   laConnexion.close();
	   } catch (SQLException sqle) {
	   System.out.println("Pb dans select " + sqle.getMessage());
	   }
	   }

}
