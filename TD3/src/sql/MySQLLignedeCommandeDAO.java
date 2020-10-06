package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import metier.CMLignedeCommande;
import dao.LignedeCommandeDAO;

public class MySQLLignedeCommandeDAO implements LignedeCommandeDAO{

public CMLignedeCommande getById(int id_commande) throws SQLException {
		
		CMLignedeCommande lignedecommande = null;
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req =cnx.prepareStatement("select * from LignedeCommande where id_commande = ?");
		req.setInt(1, id_commande);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			lignedecommande= new CMLignedeCommande(id_commande, res.getInt(2),res.getInt(3), res.getDouble(4));
			
		}
		
		cnx.close();
		req.close();
		res.close();
		
		return lignedecommande;
		
	}
	
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public ArrayList<CMLignedeCommande> getByIdproduitQuantiteTarifu(int idp, int q, double tu) throws SQLException {
		ArrayList<CMLignedeCommande> ldc = new ArrayList<CMLignedeCommande>();
		
		
		Connection cnx = Connexion.creeConnexion();
		PreparedStatement req = cnx.prepareStatement("select * from Categorie where id_produit = ? and quantite = ? and tarif_unitaire");
		req.setInt(1, idp);
		req.setInt(2, q);
		req.setDouble(3, tu);
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			ldc.add(new CMLignedeCommande(res.getInt(1), res.getInt(2), res.getDouble(3)));
			
		}
		

		
		req.close();
		res.close();
		return ldc;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean create(CMLignedeCommande ldc) throws  SQLException{
		//
		Connection cnx = Connexion.creeConnexion();
			PreparedStatement req = cnx.prepareStatement("INSERT INTO LignedeCommande (id_produit,quantite,prix_unitaire) values (?,?,?)", java.sql.Statement.RETURN_GENERATED_KEYS);
				req.setInt(1, ldc.getId_produit());
				req.setInt(2, ldc.getQuantite());
				req.setDouble(2, ldc.getTarif_unitaire());
				
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();

				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					ldc.setId_commande(clef);
						
				}
				
				cnx.close();
				req.close();
				res.close();
				
				return nbLignes==1;
		}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	public boolean update(CMLignedeCommande ldc) throws SQLException {
		
		Connection cnx = Connexion.creeConnexion();
		
		PreparedStatement req = cnx.prepareStatement("update LignedeCommande set id_produit=? ,"
				+ "quantite=?, tarif_unitaire = ? where id_commande=?");
		req.setInt(8, ldc.getId_produit());
		req.setInt(1,ldc.getQuantite());
		req.setDouble(2,ldc.getTarif_unitaire());
	
		int nbLignes = req.executeUpdate();
	
		

		cnx.close();
		req.close();
		
	return nbLignes==1;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	
	public boolean delete(CMLignedeCommande ldc) {
		int nbLignes=0;
    	try {
    	Connexion.getInstance();
		Connection laConnexion = Connexion.creeConnexion();
	PreparedStatement requete = laConnexion.prepareStatement("delete * from LignedeCommande where id_commande=?");
	
	requete.setInt(1,ldc.getId_commande());
	nbLignes = requete.executeUpdate();
    	} catch(SQLException sqle) {
    		System.out.println("Pb delete ligne de commande"+sqle.getMessage());
    	}

	return nbLignes==1;
  
}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------		
	private static MySQLLignedeCommandeDAO instance;
	private MySQLLignedeCommandeDAO() {}

	public static MySQLLignedeCommandeDAO getInstance() {
		if (instance==null) {
			instance = new MySQLLignedeCommandeDAO();
		}
		return instance;
	}
//----------------------------------------------------------------------------------------------------------------------------------------------------------------	


	public ArrayList<CMLignedeCommande> findAll() throws Exception {
	ArrayList<CMLignedeCommande> ldc = new ArrayList<CMLignedeCommande>();
		
		
		Connection con = Connexion.creeConnexion();
		PreparedStatement req = con.prepareStatement("select * from LignedeCommande ");
		
		
		
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			ldc.add(new CMLignedeCommande(res.getInt(1), res.getInt(2), res.getInt(3), res.getDouble(4)));
			
		}
		

		req.close();
		res.close();
		return ldc;
	}



}
