import java.sql.*;
import java.util.*;

public class Catégorie extends Connexion {
	
public static void MenuCateg(){
	Scanner sc = new Scanner(System.in);
	System.out.println("\r\n" + 
			"   _____      _    __                  _      \r\n" + 
			"  / ____|    | |  /_/                 (_)     \r\n" + 
			" | |     __ _| |_ ___  __ _  ___  _ __ _  ___ \r\n" + 
			" | |    / _` | __/ _ \\/ _` |/ _ \\| '__| |/ _ \\\r\n" + 
			" | |___| (_| | ||  __/ (_| | (_) | |  | |  __/\r\n" + 
			"  \\_____\\__,_|\\__\\___|\\__, |\\___/|_|  |_|\\___|\r\n");
	System.out.println(
		  	"[1]----Ajout d'une catégorie---[1]"+"\n"
		  + "[2]Modification d'une catégorie[2]"+"\n"
		  + "[3]Suppression d'une catégorie [3]"+"\n"
	      + "[4]--Affichage des catégories--[4]"+"\n"
	      + "[5]-----------Retour-----------[5]");
	int id = sc.nextInt();
	switch(id) {
	case 1:
		AjoutCateg();
		break;
	case 2:
		ModifCateg();
		break;
	case 3:
		SupprCateg();
		break;
	case 4:
		Afficher_Categ();
		break;
	case 5:
		Main.menu();
		break;
	default:
	System.out.println("Mauvaise entrée veuillez recommencer ");
	MenuCateg();
		}
	}	
public static void AjoutCateg() {
	
	try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant de la catégorie : ");
		int id = sc.nextInt();
		System.out.println("Entrer le nom de la catégorie : ");
		String titre = sc.next();
		System.out.println("Entrer l'image de la catégorie : ");
		String visuel = sc.next();
		sc.close();
		
		Connexion c1 = new Connexion();
		Connection con = c1.creeConnexion();
		PreparedStatement req = con.prepareStatement("INSERT INTO Categorie  VALUES ('"+id+"', '"+titre+"', '"+visuel+"')");
		req.executeUpdate();
	}catch (SQLException sqle) {
		System.out.println("Pb dans select " + sqle.getMessage());
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

public static void ModifCateg(){
	try {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant de la catégorie à modifier : ");
		int ancienid = sc.nextInt();
		System.out.println("Entrer le nouvel identifiant de la catégorie : ");
		int id = sc.nextInt();
		System.out.println("Entrer le nom de la catégorie : ");
		String titre = sc.next();
		System.out.println("Entrer l'image de la catégorie : ");
		String visuel = sc.next();
		sc.close();

		Connexion c1 = new Connexion();
		Connection con = c1.creeConnexion();
		PreparedStatement req = con.prepareStatement("UPDATE Categorie SET id_categorie='"+id+"',titre='"+titre+"',visuel='"+visuel+"' WHERE id_categorie='"+ancienid+"'");
		req.executeUpdate();
	}catch (SQLException sqle) {
		System.out.println("Pb dans select " + sqle.getMessage());
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

public static void SupprCateg() {
	try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant de la catégorie à suprrimer : ");
		int id = sc.nextInt();
		Connexion c1 = new Connexion();
		Connection con = c1.creeConnexion();
		PreparedStatement req = con.prepareStatement("DELETE FROM Categorie WHERE id_categorie = '"+id+"'");
		req.executeUpdate();
;
	} catch (SQLException sqle) {
		System.out.println("Pb selection " + sqle.getMessage());
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

public static void Afficher_Categ() {
    try {
   ArrayList<String> ligne = new ArrayList<String>();
   Connexion c1 = new Connexion();
   Connection con = c1.creeConnexion();
   Statement requete = con.createStatement();
   ResultSet res = requete.executeQuery("select * from Categorie");

   while (res.next()) {
       int no = res.getInt(1);
       String titre = res.getString("titre");
       String visuel = res.getString("visuel");
       String L=no+" "+titre+"     "+visuel ;
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
   System.out.println("Pb dans select " + sqle.getMessage());
   }finally {
		Main.menu();
	}
   }

}

