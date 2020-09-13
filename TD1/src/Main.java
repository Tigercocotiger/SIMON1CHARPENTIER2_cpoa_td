import java.util.*;

public class Main {
	static int idcateg = 0;
	static ArrayList<?> categ ;



public static void main(String[]args) throws Exception {
		menu();
		
			}


public static void menu() {
		Scanner sc = new Scanner(System.in);
		System.out.println("\r\n" + 
				"  __  __                  \r\n" + 
				" |  \\/  |                 \r\n" + 
				" | \\  / | ___ _ __  _   _ \r\n" + 
				" | |\\/| |/ _ \\ '_ \\| | | |\r\n" + 
				" | |  | |  __/ | | | |_| |\r\n" + 
				" |_|  |_|\\___|_| |_|\\__,_|\r\n");
		System.out.println(
				"[1]-----------CLIENT-----------[1]"+"\n"
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

	
	

	
		
	   
