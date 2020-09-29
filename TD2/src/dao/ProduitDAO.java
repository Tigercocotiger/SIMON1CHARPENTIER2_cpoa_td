package dao;

import metier.CMProduit;
public interface ProduitDAO extends DAO<CMProduit> {

	public abstract CMProduit getById(int id_produit) throws Exception;

}
