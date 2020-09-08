import java.sql.*;

public class Catégorie extends Connexion {
	int id_categorie;
	String titre;
	String visuel;
	public Catégorie(int id_categorie,String titre,String visuel) throws Exception {
		this.id_categorie=id_categorie;
		this.titre=titre;
		this.visuel=visuel;
		//test git
	}
	
		public  void post() throws Exception{
			final int var1 = id_categorie;
			final String var2 = titre;
			final String var3 =visuel;
			try{
				Connection con = creeConnexion();
				PreparedStatement posted = con.prepareStatement("INSERT INTO Categorie  VALUES ('"+var1+"', '"+var2+"', '"+var3+"')");
				
				posted.executeUpdate();
			} catch(Exception e){System.out.println(e);}
			finally {
				System.out.println("Insert Completed.");
			}
	}
	
		
		public void del() throws Exception{
			
			try{
				Connection con = creeConnexion();
				PreparedStatement requete =con.prepareStatement("delete from Categorie where id_categorie='"+this.id_categorie+"'");

				
				requete.executeUpdate();
			} catch(Exception e){System.out.println(e);}
			finally {
				System.out.println("Supprimé.");
			}
		}
	
		}
	

