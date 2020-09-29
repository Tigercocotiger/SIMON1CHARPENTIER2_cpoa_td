package dao;


import metier.CMCommande;

public interface CommandeDAO extends DAO<CMCommande> {
	public abstract CMCommande getById(int id) throws Exception;
}
