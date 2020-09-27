import java.sql.*;
import java.util.*;

public class Produit extends Connexion {

	
public static void MenuProd(){
	Scanner sc = new Scanner(System.in);
	System.out.println("\r\n" + 
			"  _____               _       _ _   \r\n" + 
			" |  __ \\             | |     (_) |  \r\n" + 
			" | |__) | __ ___   __| |_   _ _| |_ \r\n" + 
			" |  ___/ '__/ _ \\ / _` | | | | | __|\r\n" + 
			" | |   | | | (_) | (_| | |_| | | |_ \r\n" + 
			" |_|   |_|  \\___/ \\__,_|\\__,_|_|\\__|\r\n" );
	System.out.println(
		  "[1]----Ajout d'une Produit-----[1]"+"\n"
		  + "[2]-Modification d'un produit--[2]"+"\n"
		  + "[3]--Suppression d'un produit--[3]"+"\n"
	      + "[4]---Affichage des produits---[4]"+"\n"
	      + "[5]-----------Retour-----------[5]");
		int id = sc.nextInt();
		switch(id) {
		case 1:
			AjoutProd();
			break;
		case 2:
			ModifProd();
			break;
		case 3:
			SupprProd();
			break;
		case 4:
			Afficher_Prod();
			break;
		case 5:
			Main.menu();
			break;
		default:
		System.out.println("Mauvaise entrée veuillez recommencer ");
		MenuProd();
			}
		}

public static void AjoutProd() {
	
	
	try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant du produit : ");
		int id = sc.nextInt();
		System.out.println("Entrer le nom du produit : ");
		String nom = sc.next();
		System.out.println("Entrer la description du produit : ");
		String des = sc.next();
		System.out.println("Entrer le prix du produit : ");
		Double prix = sc.nextDouble();
		System.out.println("Veuillez décrire le produit : ");
		String visuel = sc.next();
		System.out.println("Entrer l'identifiant de la catégorie : ");
		int id_categ = sc.nextInt();
		
		
		sc.close();
		Connexion c1 = new Connexion();
		Connection con = c1.creeConnexion();
		PreparedStatement req = con.prepareStatement("INSERT INTO Produit  VALUES ('"+id+"', '"+nom+"', '"+des+"', '"+prix+"', '"+visuel+"', '"+id_categ+"')");
		req.executeUpdate();
	}catch (SQLException sqle) {
		System.out.println("Le problème -> " + sqle.getMessage());
		} 		
	finally {
		System.out.println("\r\n" + 
				"   _____ _           _      __      _ _     _ \r\n" + 
				"  / ____( )         | |    / _|    (_) |   | |\r\n" + 
				" | |    |/  ___  ___| |_  | |_ __ _ _| |_  | |\r\n" + 
				" | |       / _ \\/ __| __| |  _/ _` | | __| | |\r\n" + 
				" | |____  |  __/\\__ \\ |_  | || (_| | | |_  |_|\r\n" + 
				"  \\_____|  \\___||___/\\__| |_| \\__,_|_|\\__| (_)\r\n" + 
				"                                              \r\n" + 
				"                                              \r\n" + 
				"");
		Main.menu();
	}
	
}


public static void ModifProd(){
	try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant du produit à modifier : ");
		int ancienid = sc.nextInt();
		System.out.println("Entrer le nouvel identifiant du produit : ");
		int id = sc.nextInt();
		System.out.println("Entrer le nouveau nom du produit : ");
		String nom = sc.next();
		System.out.println("Entrer la nouvelle description du produit : ");
		String des = sc.next();
		System.out.println("Entrer le nouveau prix du produit : ");
		float prix = sc.nextFloat();
		System.out.println("Veuillez de nouveau décrire le produit : ");
		String visuel = sc.next();
		sc.close();
		
		Connexion c1 = new Connexion();
		Connection con = c1.creeConnexion();
		PreparedStatement state= con.prepareStatement("UPDATE Produit SET id_produit='"+id+"',nom='"+nom+"',description='"+des+"',tarif='"+prix+"',description='"+visuel+"' WHERE id_categorie='"+ancienid+"'");
		state.executeUpdate();
	}catch (SQLException sqle) {
		System.out.println("Le problème -> " + sqle.getMessage());
		} 	
	finally {
		System.out.println("\r\n" + 
				"   _____ _           _      __      _ _     _ \r\n" + 
				"  / ____( )         | |    / _|    (_) |   | |\r\n" + 
				" | |    |/  ___  ___| |_  | |_ __ _ _| |_  | |\r\n" + 
				" | |       / _ \\/ __| __| |  _/ _` | | __| | |\r\n" + 
				" | |____  |  __/\\__ \\ |_  | || (_| | | |_  |_|\r\n" + 
				"  \\_____|  \\___||___/\\__| |_| \\__,_|_|\\__| (_)\r\n" + 
				"                                              \r\n" + 
				"                                              \r\n" + 
				"");
		Main.menu();
	}
}


public static void SupprProd() {
	try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant de la catégorie à suprrimer : ");
		int id = sc.nextInt();
		Connexion c1 = new Connexion();
		Connection con = c1.creeConnexion();
		PreparedStatement requete = con.prepareStatement("DELETE FROM Produit WHERE id_produit = '"+id+"'");
		requete.executeUpdate();
	} catch (SQLException sqle) {
		System.out.println("Le problème -> " + sqle.getMessage());
	}finally {
		System.out.println("\r\n" + 
				"   _____ _           _      __      _ _     _ \r\n" + 
				"  / ____( )         | |    / _|    (_) |   | |\r\n" + 
				" | |    |/  ___  ___| |_  | |_ __ _ _| |_  | |\r\n" + 
				" | |       / _ \\/ __| __| |  _/ _` | | __| | |\r\n" + 
				" | |____  |  __/\\__ \\ |_  | || (_| | | |_  |_|\r\n" + 
				"  \\_____|  \\___||___/\\__| |_| \\__,_|_|\\__| (_)\r\n" + 
				"                                              \r\n" + 
				"                                              \r\n" + 
				"");
		Main.menu();
	}
	}

public static void Afficher_Prod() {
    try {
   ArrayList<String> ligne = new ArrayList<String>();
   Connexion c1 = new Connexion();
   Connection con = c1.creeConnexion();
   Statement requete = con.createStatement();
   ResultSet res = requete.executeQuery("select * from Produit");

   while (res.next()) {
       int no = res.getInt(1);
       String titre = res.getString("nom");
       String desc = res.getString("description");
       float prix = res.getFloat("tarif");
       String visuel = res.getString("visuel");
       int noc = res.getInt("id_categorie");
       String L="Identifiant produit: "+no+"  Titre: "+titre+"  Description: "+desc+"  Tarif: "+prix+"  Visuel: "+visuel+"  Identifiant catégorie: "+noc ;
       ligne.add(L);
       

   }
   for (int i = 0; i < ligne.size(); i++) {
	      System.out.println(ligne.get(i));
	    }
   if (res != null)
   res.close();
   if (requete != null)
   requete.close();
   if (con != null)
   con.close();
   } catch (SQLException sqle) {
   System.out.println("Le problème -> " + sqle.getMessage());
   }finally {
		Main.menu();
	}
   }

}


