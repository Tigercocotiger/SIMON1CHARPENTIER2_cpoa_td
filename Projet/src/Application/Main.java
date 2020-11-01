package Application;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import metier.CMCategorie;
import metier.CMClient;
import metier.CMCommande;
import metier.CMLignedeCommande;
import metier.CMProduit;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;
//import SQL.MySQLCategorieDAO;

public class Main {
	public static void main(String[] args) {
		Menu();
	}

	public static void Menu() {
		Scanner sc=new Scanner(System.in);
		DAOFactory daos =DAOFactory.getDAOFactory(Persistance.MYSQL);
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
			  + "[4]----------COMMANDE ---------[4]"+"\n"
			  + "[5]----------LIGNE COM---------[5]"+"\n"
		      + "[6]----------QUITTER-----------[6]");
		int partie = sc.nextInt();
		

		if (partie==3) {
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
			 + "[4]-Affichage d'une catégorie--[4]"+"\n"
			 + "[5]-----------Retour-----------[5]");
			int p=sc.nextInt();
			if(p==1) {
				System.out.println("Ajouter");
				System.out.println ("titre=");
				String titre=sc.next();
				System.out.println ("visuel=");
				String visuel=sc.next();
				try {
					daos.getCategorieDAO().create(new CMCategorie( titre, visuel));
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					Menu();
				}
					}
			else if (p==2) {
				System.out.println("Modifier");
				System.out.println("id_categorie=");
				int id=sc.nextInt();
				System.out.println ("titre=");
				String titre=sc.next();
				System.out.println ("visuel=");
				String visuel=sc.next();			
				try {
					daos.getCategorieDAO().update(new CMCategorie(id,titre,visuel));
				} catch (Exception e) {				
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if (p==3) {
				System.out.println("Supprimer");
				System.out.println("id_categorie=");
				int id=sc.nextInt();			
				try {
					daos.getCategorieDAO().delete(new CMCategorie(id));
				} catch (Exception e) {				
					e.printStackTrace();
				}finally {
					Menu();
				}
				
		}
			else if(p==4){
				System.out.println("Chercher");
				System.out.println("id_categorie=");
				int id=sc.nextInt();	
				try {
					System.out.println(daos.getCategorieDAO().getById(id));
				} catch (Exception e) {
					System.out.println("cette catégorie n'existe pas!");
					e.printStackTrace();
				}finally {
					Menu();
				}
				}
			else if(p==5) {
				Menu();
			}
				
			else{
				System.out.println("invalide");
			}
		}
		
		

		else if(partie==2) {

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
			    + "[4]----Affichage un produit----[4]"+"\n"
			    + "[5]-----------Retour-----------[5]");
			int p=sc.nextInt();
			if(p==1) {
				System.out.println("Ajouter");
				System.out.println ("nom=");
				String nom=sc.next();
				System.out.println ("description=");
				String description=sc.next();
				System.out.println ("tarif=");
				float tarif=sc.nextFloat();
				System.out.println ("visuel=");
				String visuel=sc.next();
				System.out.println ("id catégorie=");
				int idc=sc.nextInt();
				try {
					daos.getProduitDAO().create(new CMProduit(nom,description,tarif,visuel,idc));
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					Menu();
				}
			
					}
			else if(p==2) {
				System.out.println("Modifier");
				System.out.println("id_produit=");
				int id=sc.nextInt();
				System.out.println ("nom=");
				String nom=sc.next();
				System.out.println ("description=");
				String description=sc.next();
				System.out.println ("tarif=");
				float tarif=sc.nextFloat();
				System.out.println ("visuel=");
				String visuel=sc.next();
				System.out.println ("id catégorie=");
				int idc=sc.nextInt();			
				try {
					daos.getProduitDAO().update(new CMProduit(id,nom,description,tarif,visuel,idc));
				} catch (Exception e) {				
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if(p==3) {
				System.out.println("Supprimer");
				System.out.println("id produit=");
				int id=sc.nextInt();			
				try {
					daos.getProduitDAO().delete(new CMProduit(id));
				} catch (Exception e) {				
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if(p==4) {
				System.out.println("Chercher");
				System.out.println("id produit=");
				int id=sc.nextInt();	
				try {
					daos.getProduitDAO().getById(id);	;
				} catch (Exception e) {
					System.out.println("ce produit n'existe pas!");
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if(p==5) {
				Menu();
			}
			else 
			{
				System.out.println("invalide");
			}
			
			
		}
	

		else if( partie==1) {
			System.out.println("\r\n" + 
					"   _____ _ _            _   \r\n" + 
					"  / ____| (_)          | |  \r\n" + 
					" | |    | |_  ___ _ __ | |_ \r\n" + 
					" | |    | | |/ _ \\ '_ \\| __|\r\n" + 
					" | |____| | |  __/ | | | |_ \r\n" + 
					"  \\_____|_|_|\\___|_| |_|\\__|\r\n");
			System.out.println(
				  	"[1]------Ajout d'un client-----[1]"+"\n"
				  + "[2]--Modification d'un client--[2]"+"\n"
				  + "[3]--Suppression d'un client---[3]"+"\n"
			      + "[4]---Affichage d'un client----[4]"+"\n"
			      + "[5]-----------Retour-----------[5]");
			int p=sc.nextInt();
			if(p==1) {
					System.out.println("Ajouter");
					System.out.println ("nom=");
					String nom=sc.next();
					System.out.println ("prenom=");
					String prenom=sc.next();
					System.out.println ("identifiant=");
					String iden=sc.next();
					System.out.println ("mode de passe =");
					String mode=sc.next();
					System.out.println("adr_numero=");
					String adr=sc.next();
					System.out.println ("adr_voie=");
					String adr_voie=sc.next();
					System.out.println ("adr_code_postal=");
					String adr_code_postal=sc.next();
					System.out.println ("adr_ville=");
					String adr_ville=sc.next();
					System.out.println ("adr_pays=");
					String adr_pays=sc.next();
					try {
						daos.getClientDAO().create(new CMClient(nom,prenom,iden,mode,adr,adr_voie,adr_code_postal,adr_ville,adr_pays));
					} catch (Exception e) {
						e.printStackTrace();
					}finally {
						Menu();
					}
					}
			else if(p==2) {
				System.out.println("Modifier");
				System.out.println("id_client=");
				int id=sc.nextInt();
				System.out.println ("nom=");
				String nom=sc.next();
				System.out.println ("prenom=");
				String prenom=sc.next();
				System.out.println ("identifiant=");
				String iden=sc.next();
				System.out.println ("mode de passe =");
				String mode=sc.next();
				System.out.println("adr_numero=");
				String adr=sc.next();
				System.out.println ("adr_voie=");
				String adr_voie=sc.next();
				System.out.println ("adr_code_postal=");
				String adr_code_postal=sc.next();
				System.out.println ("adr_ville=");
				String adr_ville=sc.next();
				System.out.println ("adr_pays=");
				String adr_pays=sc.next();			
				try {
					daos.getClientDAO().update(new CMClient(id, nom, prenom, iden, mode, adr, adr_voie, adr_code_postal, adr_ville, adr_pays));
				} catch (Exception e) {				
					e.printStackTrace();
				}finally {
					Menu();
				}
					}
			else if (p==3) {
				System.out.println("Supprimer");
				System.out.println("id_client=");
				int id=sc.nextInt();			
				try {
					daos.getClientDAO().delete(new CMClient(id));
				} catch (Exception e) {				
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if(p==4) {
				System.out.println("Chercher");
				System.out.println("id_client=");
				int id=sc.nextInt();	
				try {
					daos.getClientDAO().getById(id);
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if(p==5) {
				Menu();
			}
		}
		
		

		else if(partie==4) {
			System.out.println("\r\n" + 
					"   _____                                          _      \r\n" + 
					"  / ____|                                        | |     \r\n" + 
					" | |     ___  _ __ ___  _ __ ___   __ _ _ __   __| | ___ \r\n" + 
					" | |    / _ \\| '_ ` _ \\| '_ ` _ \\ / _` | '_ \\ / _` |/ _ \\\r\n" + 
					" | |___| (_) | | | | | | | | | | | (_| | | | | (_| |  __/\r\n" + 
					"  \\_____\\___/|_| |_| |_|_| |_| |_|\\__,_|_| |_|\\__,_|\\___|\r\n" + 
					"                                                         \r\n" + 
					"                                                         \r\n" + 
					"");
			System.out.println(
				  	"[1]----Ajout d'une commande----[1]"+"\n"
				  + "[2]---Modification d'une com---[2]"+"\n"
				  + "[3]---Suppression d'une com----[3]"+"\n"
			      + "[4]----Affichage d'une com-----[4]"+"\n"
			      + "[5]-----------Retour-----------[5]");
			int p=sc.nextInt();
			if(p==1) {
				System.out.println("Ajouter");
				System.out.println ("date_commande=");
				String date=sc.next();
				System.out.println ("id_client");
				int idcl=sc.nextInt();
				try {
					daos.getCommandeDAO().create(new CMCommande(idcl, date,idcl));
				} catch (Exception e) {
					System.out.println("ce commande n'exist pas!");
					e.printStackTrace();
				}finally {
					Menu();
				}
					}
			else if (p==2) {
				System.out.println("Modifier");
				System.out.println("id_commande=");
				int id=sc.nextInt();
				System.out.println ("date_commande=");
				String date=sc.next();
				System.out.println ("id_client");
				int idcl=sc.nextInt();		
				try {
					daos.getCommandeDAO().update(new CMCommande(id,date,idcl));
				} catch (Exception e) {				
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if (p==3) {
				System.out.println("Supprimer");
				System.out.println("id_commande=");
				int id=sc.nextInt();			
				try {
					daos.getCommandeDAO().delete(new CMCommande(id));
				} catch (Exception e) {				
					e.printStackTrace();
				}finally {
					Menu();
				}
				
		}
			else if(p==4){
				System.out.println("Chercher");
				System.out.println("id_commande=");
				int id=sc.nextInt();	
				try {
					daos.getCommandeDAO().getById(id);	;
				} catch (Exception e) {
					System.out.println("cette commande n'a pas pu être trouvé ");
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if(p==5) {
					Menu();
				}
			else{
				System.out.println("invalide");
			}
	
		}

		else if (partie==5) {
			System.out.println("\r\n" + 
					"  _      _                     _____                \r\n" + 
					" | |    (_)                   / ____|               \r\n" + 
					" | |     _  __ _ _ __   ___  | |     ___  _ __ ___  \r\n" + 
					" | |    | |/ _` | '_ \\ / _ \\ | |    / _ \\| '_ ` _ \\ \r\n" + 
					" | |____| | (_| | | | |  __/ | |___| (_) | | | | | |\r\n" + 
					" |______|_|\\__, |_| |_|\\___|  \\_____\\___/|_| |_| |_|\r\n" + 
					"            __/ |                                   \r\n" + 
					"           |___/                                    \r\n" + 
					"");
			System.out.println("\"		  [1]----Ajout d'une Ligne com---[1]\"+\"\\n\"\r\n" + 
					"				  + \"[2]---Modification d'une LC----[2]\"+\"\\n\"\r\n" + 
					"				  + \"[3]----Suppression d'une LC----[3]\"+\"\\n\"\r\n" + 
					"			      + \"[4]-----Affichage d'une LC-----[4]\"+\"\\n\"\r\n" + 
					"			      + \"[5]-----------Retour-----------[5]\");");
			
			int p=sc.nextInt();
			if(p==1) {
				System.out.println("Ajouter");
				System.out.println ("id_commande=");
				int idcom=sc.nextInt();
				System.out.println ("id_produit=");
				int idp=sc.nextInt();
				System.out.println ("quantite=");
				int quan=sc.nextInt();
				System.out.println ("tarif_unitaire =");
				double tarifu=sc.nextDouble();
				try {
					daos.getLignedeCommandeDAO().create(new CMLignedeCommande(idcom,idp,quan,tarifu));
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					Menu();
				}
				}
			else if(p==2) {
				System.out.println("Modifier");
				System.out.println ("id_commande=");
				int idcom=sc.nextInt();
				System.out.println ("id_produit=");
				int idp=sc.nextInt();
				System.out.println ("quantite=");
				int quan=sc.nextInt();
				System.out.println ("tarif_unitaire =");
				double tarifu=sc.nextDouble();
				try {
					daos.getLignedeCommandeDAO().update(new CMLignedeCommande(idcom,idp,quan,tarifu));
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					Menu();
				}
					}
			else if(p==3) {
				System.out.println("Supprimer");
				System.out.println ("id_commande=");
				int idcom=sc.nextInt();
				System.out.println ("id_produit=");
				int idp=sc.nextInt();
				try {
					daos.getLignedeCommandeDAO().delete(new CMLignedeCommande(idcom,idp));
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			
			
			else if(p==4) {
				System.out.println("Chercher");
				System.out.println("id_commande=");
				int id=sc.nextInt();	
				try {
					daos.getLignedeCommandeDAO().getById(id);
				} catch (Exception e) {
					System.out.println("cette ligne de commande n'existe pas!");
					e.printStackTrace();
				}finally {
					Menu();
				}
			}
			else if(p==5) {
				Menu();
			}
		}
		else if (partie == 6) {
			System.exit(0);
		}
			
		}
		
		
	}
	
