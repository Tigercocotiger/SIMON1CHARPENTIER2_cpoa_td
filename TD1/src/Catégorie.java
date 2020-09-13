import java.sql.*;
import java.util.*;

public class Cat�gorie extends Connexion {
	
public static void MenuCateg(){
	Scanner sc = new Scanner(System.in);
	System.out.println(
			"\n"
		  +	"             CAT�GORIE            "+"\n"+"\n"
		  +	"[1]----Ajout d'une cat�gorie---[1]"+"\n"
		  + "[2]Modification d'une cat�gorie[2]"+"\n"
		  + "[3]Suppression d'une cat�gorie [3]"+"\n"
	      + "[4]--Affichage des cat�gories--[4]"+"\n"
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
		Affiche_Categ();
		break;
	case 5:
		Main.menu();
		break;
	default:
	System.out.println("Mauvaise entr�e veuillez recommencer ");
	MenuCateg();
		}
	}	
public static void AjoutCateg() {
	
	try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant de la cat�gorie : ");
		int id = sc.nextInt();
		System.out.println("Entrer le nom de la cat�gorie : ");
		String titre = sc.next();
		System.out.println("Entrer l'image de la cat�gorie : ");
		String visuel = sc.next();
		sc.close();
		
		Connexion c1 = new Connexion();
		Connection laConnexion = c1.creeConnexion();
		PreparedStatement req = laConnexion.prepareStatement("INSERT INTO Categorie  VALUES ('"+id+"', '"+titre+"', '"+visuel+"')");
		req.executeUpdate();
	}catch (SQLException sqle) {
		System.out.println("Pb dans select " + sqle.getMessage());
		} 		
	
	
}

public static void ModifCateg(){
	try {
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant de la cat�gorie � modifier : ");
		int ancienid = sc.nextInt();
		System.out.println("Entrer le nouvel identifiant de la cat�gorie : ");
		int id = sc.nextInt();
		System.out.println("Entrer le nom de la cat�gorie : ");
		String titre = sc.next();
		System.out.println("Entrer l'image de la cat�gorie : ");
		String visuel = sc.next();
		sc.close();

		Connexion c1 = new Connexion();
		Connection laConnexion = c1.creeConnexion();
		PreparedStatement req = laConnexion.prepareStatement("UPDATE Categorie SET id_categorie='"+id+"',titre='"+titre+"',visuel='"+visuel+"' WHERE id_categorie='"+ancienid+"'");
		req.executeUpdate();
	}catch (SQLException sqle) {
		System.out.println("Pb dans select " + sqle.getMessage());
		} 	
	
}

public static void SupprCateg() {
	try {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer l'identifiant de la cat�gorie � suprrimer : ");
		int id = sc.nextInt();
		Connexion c1 = new Connexion();
		Connection laConnexion = c1.creeConnexion();
		PreparedStatement req = laConnexion.prepareStatement("DELETE FROM Categorie WHERE id_categorie = '"+id+"'");
		req.executeUpdate();
;
	} catch (SQLException sqle) {
		System.out.println("Pb selection " + sqle.getMessage());
	}
	}

public static void Affiche_Categ() {
    try {
   ArrayList<String> ligne = new ArrayList<String>();
   Connexion c1 = new Connexion();
   Connection laConnexion = c1.creeConnexion();
   Statement requete = laConnexion.createStatement();
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
   if (laConnexion != null)
   laConnexion.close();
   } catch (SQLException sqle) {
   System.out.println("Pb dans select " + sqle.getMessage());
   }
   }

}

