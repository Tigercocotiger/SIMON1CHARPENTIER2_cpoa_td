package dao;

public abstract class DAOFactory {

	public enum Persistance{
		MYSQL, ListMemoire
		}

	
	public static DAOFactory getDAOFactory(Persistance cible) {
		DAOFactory daoF = null;
		switch (cible) {
		case MYSQL:
		daoF = new MySQLDAOFactory();
		break;
		case  ListMemoire:
		daoF = new ListeMemoireDAOFactory();
		break;
		}
		
		return daoF;
	}
	
	public abstract CategorieDAO getCategorieDAO(); 
	public abstract ClientDAO getClientDAO();
	public abstract ProduitDAO getProduitDAO();
	public abstract CommandeDAO getCommandeDAO();
	public abstract LignedeCommandeDAO getLignedeCommandeDAO();
	
	
}
