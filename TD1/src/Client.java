import java.sql.*;
public class Client extends Connexion{
	public static void AjoutClient(int id, String nom, String prenom, String identifiant, String mdp, String adresse, String ad_voie, String ad_c_postal, String ad_ville, String ad_pays) {
		try {
			Connexion c1 = new Connexion();
			Connection laConnexion = c1.creeConnexion();
			Statement requete = laConnexion.createStatement();
			int res = requete.executeUpdate("INSERT INTO Client (id_client, nom, prenom, identifiant, mot_de_passe, adr_numero, adr_voie, adr_code_postal, adr_ville, adr_pays) VALUES ('"+id+"', '"+nom+"', '"+prenom+"', '"+identifiant+"', '"+mdp+"', '"+adresse+"', '"+ad_voie+"', '"+ad_c_postal+"', '"+ad_ville+"', '"+ad_pays+"')");
				System.out.println("Ajouté dans la table client un élément avec les propriétés suivantes:\n "
						+ "ID: " + id + "\nNom: "+ nom +"\nPrenom: " + prenom +"\nIdentifiant: " + identifiant +"\nMot de passe: "+mdp+"\nAdresse: "+adresse+"\nVoie: "+ad_voie+"\nCode postal: "+ad_c_postal+"\nVille: "+ad_ville+"\nPays:" + ad_pays);
		} catch (SQLException sqle) {
			System.out.println("Pb selection " + sqle.getMessage());
		}
}
	
	
	
	public static void SupprClient(int id_client) {
		try {
			Connexion c1 = new Connexion();
			Connection laConnexion = c1.creeConnexion();
			Statement requete = laConnexion.createStatement();
			int res = requete.executeUpdate("DELETE FROM Client WHERE id_client = '"+id_client+"'");
		} catch (SQLException sqle) {
			System.out.println("Pb selection " + sqle.getMessage());
		}
		}
	
	public static void  ModifClient(int Old_id,int New_id ,String New_nom,String New_prenom,String New_identifiant,String New_mdp,int New_numero,String New_voie){
		try {
			Connexion c1 = new Connexion();
			Connection laConnexion = c1.creeConnexion();
			Statement state= laConnexion.createStatement();
			state.executeUpdate("UPDATE Client SET id_client='"+New_id+"',nom='"+New_nom+"',prenom='"+New_prenom+"',identifiant='"+New_identifiant+"',mot_de_pass'"+New_mdp+"',adr_numero='"+New_numero+"',adr_voie='"+New_voie+"' WHERE id_client='"+Old_id+"'");
			state.close();
		}catch (SQLException sqle) {
			System.out.println("Pb selection " + sqle.getMessage());
			} 	
		
	}
	

}
