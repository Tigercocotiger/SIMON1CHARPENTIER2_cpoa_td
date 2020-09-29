package sql;


import java.sql.*;
import java.util.ArrayList;
import metier.CMProduit;
import dao.ProduitDAO;



public class MySQLProduitDAO implements ProduitDAO{

	
public CMProduit getById(int id_produit) throws SQLException {
		
		CMProduit produit = null;
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from Produit where id_produit = ?");
		req.setInt(1, id_produit);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			produit= new CMProduit(id_produit, res.getString(2), res.getString(3), res.getFloat(4), res.getString(5), res.getInt(6));
			
		}
		
		cnx.close();
		req.close();
		res.close();
		
		return produit;
		
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public ArrayList<CMProduit> getByNomDescriptionTarifVisuelIdcategorie(String n, String d, float t, String v, int idc) throws SQLException {
		ArrayList<CMProduit> produit = new ArrayList<CMProduit>();
		
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("select * from Produit where nom = ? and description =? and tarif =? and visuel = ? and id_categorie=?");
		req.setString(1, n);
		req.setString(2, d);
		req.setFloat(3, t);
		req.setString(4, v);
		req.setInt(5, idc);
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			produit.add(new CMProduit(res.getInt(1), res.getString(2), res.getString(3),res.getFloat(4), res.getString(5), res.getInt(6)));
			
		}
		

		
		req.close();
		res.close();
		return produit;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean create(CMProduit p) throws  SQLException{
		//
		Connection cnx = Connexion.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("INSERT INTO Produit (nom,description,tarif,visuel,id_categorie) values (?,?,?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
				req.setString(1, p.getNom());
				req.setString(2, p.getDescription());
				req.setFloat(3, p.getTarif());
				req.setString(4,p.getVisuel());
				req.setInt(5, p.getId_categorie());
				
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();

				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					p.setId_produit(clef);
						
				}
				
				cnx.close();
				req.close();
				res.close();
				
				return nbLignes==1;
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean update(CMProduit p) throws SQLException {
		
		Connection cnx = Connexion.creeConnexion();
		
		PreparedStatement req = cnx.prepareStatement("update Produit set nom=? ,"
				+ "nom=?, description=?, tarif=?, visuel = ?, id_categorie=?  where id_produit=?");
		req.setInt(8, p.getId_produit());
		req.setString(1,p.getNom());
		req.setString(2, p.getDescription());
		req.setFloat(3, p.getTarif());
		req.setString(4,p.getVisuel());
		req.setInt(5, p.getId_categorie());
	
		int nbLignes = req.executeUpdate();
	
		

		cnx.close();
		req.close();
		
	return nbLignes==1;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean delete(CMProduit p) throws SQLException{
		
    	try {
    	Connection cnx = Connexion.creeConnexion();
	PreparedStatement req = cnx.prepareStatement("delete * from Produit where id_produit=?");
	req.setInt(1,p.getId_produit());
	
	
	
	int nbLignes = req.executeUpdate();
	

	cnx.close();
	req.close();
	
	return nbLignes==1;
    	}catch(Exception e) {
    	    return false;
    	}
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	private static MySQLProduitDAO instance;

	public static MySQLProduitDAO getInstance() {
		if (instance==null) {
			instance = new MySQLProduitDAO();
		}
		return instance;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	

	@Override
	public ArrayList<CMProduit> findAll() throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	

}

