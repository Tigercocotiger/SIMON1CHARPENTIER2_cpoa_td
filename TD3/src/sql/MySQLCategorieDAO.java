package sql;

import java.sql.*;
import java.util.ArrayList;
import metier.CMCategorie;
import dao.CategorieDAO;

public class MySQLCategorieDAO implements CategorieDAO {
	
public CMCategorie getById(int id_categorie) throws SQLException{
		
		CMCategorie categorie = null;
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Categorie where id_categorie = ?");
		req.setInt(1, id_categorie);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			categorie= new CMCategorie(id_categorie, res.getString(2), res.getString(3));
			
		}
		
		cnx.close();
		req.close();
		res.close();
		
		return categorie;
		
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public ArrayList<CMCategorie> getByTitreVisuel(String t, String v) throws SQLException{
		ArrayList<CMCategorie> categorie = new ArrayList<CMCategorie>();
		
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("select * from Categorie where titre = ? and visuel = ?");
		req.setString(1, t);
		req.setString(2, v);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			categorie.add(new CMCategorie(res.getInt(1), res.getString(2), res.getString(3)));
			
		}
		

		
		req.close();
		res.close();
		return categorie;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean create(CMCategorie c) throws  SQLException{
		//
		Connection cnx = Connexion.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("INSERT INTO Categorie (titre,visuel) values (?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
				req.setString(1, c.getTitre());
				req.setString(2, c.getVisuel());
			
				
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();

				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					c.setId(clef);
						
				}
				
				cnx.close();
				req.close();
				res.close();
				
				return nbLignes==1;
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean update(CMCategorie c) throws SQLException {
        int nbLignes =0;
        Connection cnx = Connexion.creeConnexion();
        PreparedStatement req = cnx.prepareStatement("update Categorie set titre=?, visuel = ? where id_categorie=?");
        req.setInt(3, c.getId());
        req.setString(1,c.getTitre());
        req.setString(2,c.getVisuel());

        nbLignes = req.executeUpdate();



        cnx.close();
        req.close();

    return nbLignes==1;
    }
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean delete(CMCategorie c) {
		int nbLignes=0;
    	try {
    	Connexion.getInstance();
		Connection laConnexion = Connexion.creeConnexion();
	PreparedStatement requete = laConnexion.prepareStatement("delete from Categorie where id_categorie=?");
	
	requete.setInt(1,c.getId());
	nbLignes = requete.executeUpdate();
    	} catch(SQLException sqle) {
    		System.out.println("Pb delete categorie"+sqle.getMessage());
    	}

	return nbLignes==1;
  
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	private static MySQLCategorieDAO instance;
	private MySQLCategorieDAO() {}

	public static MySQLCategorieDAO getInstance() {
		if (instance==null) {
			instance = new MySQLCategorieDAO();
		}
		return instance;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	


	public ArrayList<CMCategorie> findAll() throws Exception {
	ArrayList<CMCategorie> cl = new ArrayList<CMCategorie>();
		
		
		Connection con = Connexion.creeConnexion();
		PreparedStatement req = con.prepareStatement("select * from Categorie ");
		
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			cl.add(new CMCategorie(res.getInt(1), res.getString(2), res.getString(3)));
			
		}
		

		req.close();
		res.close();
		return cl;
	}

}
