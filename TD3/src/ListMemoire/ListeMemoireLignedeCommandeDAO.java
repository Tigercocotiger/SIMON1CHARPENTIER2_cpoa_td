package ListMemoire;
import java.util.ArrayList;
import java.util.List;

import metier.CMLignedeCommande;
import dao.LignedeCommandeDAO;

public class ListeMemoireLignedeCommandeDAO implements LignedeCommandeDAO{
	private static ListeMemoireLignedeCommandeDAO instance;
	private List<CMLignedeCommande> donnees;
	public static ListeMemoireLignedeCommandeDAO getInstance() {
		if(instance==null) {
			instance=new ListeMemoireLignedeCommandeDAO();
		}
		return instance;
	}
	private ListeMemoireLignedeCommandeDAO() {
		this.donnees = new ArrayList<CMLignedeCommande>();
		this.donnees.add(new CMLignedeCommande(1,2,3,4));
		this.donnees.add(new CMLignedeCommande(2,2,3,4));
	}
	@Override
	public boolean create(CMLignedeCommande objet) throws Exception {
		objet.setId_commande(3);
		while (this.donnees.contains(objet)) {
			objet.setId_commande(objet.getId_commande() + 1);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
	}
	@Override
	public boolean update(CMLignedeCommande objet) throws IllegalArgumentException {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une ligne de commande inexistante");
		} else {
			this.donnees.set(idx, objet);
		}
		return true;
	}
	@Override
	public boolean delete(CMLignedeCommande objet) throws Exception {
		CMLignedeCommande supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une ligne de commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}
	@Override
	public CMLignedeCommande getById(int id_commande) throws IllegalArgumentException {
		int idx = this.donnees.indexOf(new CMLignedeCommande(id_commande, 2,3,4));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune categorie ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	@Override
	public ArrayList<CMLignedeCommande> findAll() {
		return (ArrayList<CMLignedeCommande>) this.donnees;
	}
}
