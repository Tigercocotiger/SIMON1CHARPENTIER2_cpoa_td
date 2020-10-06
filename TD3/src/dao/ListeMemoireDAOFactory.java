package dao;

import ListMemoire.ListeMemoireCategorieDAO;
import ListMemoire.ListeMemoireClientDAO;
import ListMemoire.ListeMemoireCommandeDAO;
import ListMemoire.ListeMemoireLignedeCommandeDAO;
import ListMemoire.ListeMemoireProduitDAO;

public class ListeMemoireDAOFactory extends DAOFactory{

		@Override
		public CategorieDAO getCategorieDAO() {
			return ListeMemoireCategorieDAO.getInstance();
		}

		@Override
		public ClientDAO getClientDAO() {
			return ListeMemoireClientDAO.getInstance();
		}

		@Override
		public CommandeDAO getCommandeDAO() {
			return ListeMemoireCommandeDAO.getInstance();
		}

		@Override
		public LignedeCommandeDAO getLignedeCommandeDAO() {
			return ListeMemoireLignedeCommandeDAO.getInstance();
		}

		@Override
		public ProduitDAO getProduitDAO() {
			return ListeMemoireProduitDAO.getInstance();
		}
	}

