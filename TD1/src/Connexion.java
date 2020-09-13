import java.sql.*;
public class Connexion {
		
		public Connection creeConnexion() {
		
			String url ="jdbc:mysql://devbdd.iutmetz.univ-lorraine.fr:3306/simon429u_cpoa";
			url += "?serverTimezone=Europe/Paris";
			String login = "simon429u_appli";
			String pwd = "31915262";
			Connection maConnection = null;
		
			try {
				maConnection = DriverManager.getConnection(url, login, pwd);
			} catch (SQLException sqle) {
				System.out.println("Erreur connexion" + sqle.getMessage());
			}
			
			return maConnection;}
			
		

		public Statement uneRequete() {
			try {
				Connection laConnexion = creeConnexion();
				Statement requete = laConnexion.createStatement();
				ResultSet res = requete.executeQuery("select id_client, id_client from Client");
				while (res.next()) {
					String nom = res.getString("id_client");
					System.out.println(nom);
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
			return null; 		 
			 
		}
		
}


