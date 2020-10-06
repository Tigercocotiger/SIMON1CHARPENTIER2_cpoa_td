package dao;

import sql.MySQLCategorieDAO;
import sql.MySQLClientDAO;
import sql.MySQLCommandeDAO;
import sql.MySQLLignedeCommandeDAO;
import sql.MySQLProduitDAO;


public class MySQLDAOFactory extends DAOFactory{

	@Override
	public CategorieDAO getCategorieDAO() {
		return MySQLCategorieDAO.getInstance();
		
	}

	@Override
	public ClientDAO getClientDAO() {
		return MySQLClientDAO.getInstance();
		
	}
	
	@Override
	public CommandeDAO getCommandeDAO() {
		return MySQLCommandeDAO.getInstance();
		
	}
	
	@Override
	public LignedeCommandeDAO getLignedeCommandeDAO() {
		return MySQLLignedeCommandeDAO.getInstance();
		
	}
	@Override
	public ProduitDAO getProduitDAO() {
		return MySQLProduitDAO.getInstance();
		
	}
	
}
