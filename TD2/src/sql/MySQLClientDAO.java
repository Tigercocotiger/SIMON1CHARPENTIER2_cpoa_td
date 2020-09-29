package sql;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import metier.CMClient;
import dao.ClientDAO;

public class MySQLClientDAO implements ClientDAO {
	public CMClient getById(int id_client) throws SQLException {
		CMClient client = null;
		Connection con = Connexion.creeConnexion();
		PreparedStatement req = con.prepareStatement("SELECT * from Client where id_client = ?");
		req.setInt(1, id_client);
		ResultSet res = req.executeQuery();
		
		while (res.next()) {
			client= new CMClient(id_client, res.getString(2), res.getString(3),res.getString(4), res.getString(5),res.getString(6),res.getString(7),
					res.getString(8),res.getString(9),res.getString(10));
		}
		con.close();
		req.close();
		res.close();
		return client;
	}
	public boolean create(CMClient c) throws  SQLException{
		Connection laConnection = Connexion.creeConnexion();
			PreparedStatement req = laConnection.prepareStatement("INSERT INTO Client (nom, prenom, no_rue, voie, code_postal, ville, pays) values (?,?, ?,?, ?,?, ?)", java.sql.Statement.RETURN_GENERATED_KEYS);
				req.setString(1, c.getNom());
				req.setString(2, c.getPrenom());
				req.setString(3, c.getIdentifiant());
				req.setString(4, c.getMot_de_passe());
				req.setString(5, c.getAdr_numero());
				req.setString(6, c.getAdr_voie());
				req.setString(7, c.getAdr_code_postal());
				req.setString(8, c.getAdr_ville());
				req.setString(9, c.getAdr_pays());
				int nbLignes = req.executeUpdate();
				ResultSet res = req.getGeneratedKeys();
				int clef;
				if(res.next()) {
					clef = res.getInt(1);
					c.setId_client(clef);
						
				}
				laConnection.close();
				req.close();
				res.close();
				return nbLignes==1;
	}
	public boolean update(CMClient c) throws SQLException {
		Connection laConnection = Connexion.creeConnexion();
		PreparedStatement req = laConnection.prepareStatement("update Client set nom=? ,"
				+ "prenom=?, identifiant = ?, mot_de_passe=?,adr_numero=?,adr_voie=?, adr_code_postal=?, adr_ville=?, adr_pays=? where id_client=?");
		req.setInt(8, c.getId_client());
		req.setString(1,c.getNom());
		req.setString(2,c.getPrenom());
		req.setString(3, c.getIdentifiant());
		req.setString(4, c.getMot_de_passe());
		req.setString(5, c.getAdr_numero());
		req.setString(6, c.getAdr_voie());
		req.setString(7, c.getAdr_code_postal());
		req.setString(8, c.getAdr_ville());
		req.setString(9, c.getAdr_pays());
		int nbLignes = req.executeUpdate();
		laConnection.close();
		req.close();
		return nbLignes==1;
	}
	public boolean delete(CMClient c) throws SQLException {
	    try {
	    Connection laConnection = Connexion.creeConnexion();
		PreparedStatement req = laConnection.prepareStatement("delete * from Client where id_client=?");
		req.setInt(1,c.getId_client());	
		int nbLignes = req.executeUpdate();
		laConnection.close();
		req.close();
		
		return nbLignes==1;
	    } catch(Exception e) {
	    	return false;
	    }
}
	public ArrayList<CMClient> getByNomPrenom(String n, String p) throws SQLException{
		ArrayList<CMClient> cl = new ArrayList<CMClient>();
		Connection con = Connexion.creeConnexion();
		PreparedStatement req = con.prepareStatement("select * from Client where nom = ? and prenom = ?");
		req.setString(1, n);
		req.setString(2, p);
		ResultSet res = req.executeQuery();	
		while (res.next()) {
			cl.add(new CMClient(res.getInt(1), res.getString(2), res.getString(3),res.getString(4), res.getString(5),res.getString(6),res.getString(7),
					res.getString(8),res.getString(9),res.getString(10)));
		}
		req.close();
		res.close();
		return cl;
	}
	private static MySQLClientDAO instance;
	private  MySQLClientDAO() {
		
	}
	public static MySQLClientDAO getInstance() {
		if (instance==null) {
			instance = new MySQLClientDAO();
		}
	return instance;
	}
	public ArrayList<CMClient> findAll() throws  SQLException {
		ArrayList<CMClient> cl = new ArrayList<CMClient>();
		Connection con = Connexion.creeConnexion();
		PreparedStatement req = con.prepareStatement("select * from Client ");
		ResultSet res = req.executeQuery();
		while (res.next()) {
			cl.add(new CMClient(res.getInt(1), res.getString(2), res.getString(3),res.getString(4), res.getString(5),res.getString(6),res.getString(7),
					res.getString(8),res.getString(9),res.getString(10)));
		}
		req.close();
		res.close();
		return cl;}		
}