import java.util.*;

public class Main {
	static int idcateg = 0;
	static ArrayList<?> categ ;



public static void main(String[]args) throws Exception {
		Connexion test = new Connexion();
		menu();
			}


public static void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println(
				"\n"
			  +	"              MENU              "+"\n"+"\n"
			  +	"[1]-----------CLIENT-----------[1]"+"\n"
			  + "[2]-----------PRODUIT----------[2]"+"\n"
			  + "[3]----------CATEGORIE---------[3]"+"\n"
		      + "[4]----------QUITTER-----------[4]");
		int id = sc.nextInt();
		switch(id) {
		case 1:
			Client.MenuClient();
			break;
		case 2:
			Produit.MenuProd();			
			break;
		case 3:
			Catégorie.MenuCateg();
			break;
		case 4:
			System.exit(0);
			break;
		default:
		System.out.println("Mauvaise entrée veuillez recommencer ");
		menu();
			}
		}
	
}

	
	

	
		
	   
