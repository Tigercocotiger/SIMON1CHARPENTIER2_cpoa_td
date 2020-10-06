package ListMemoire;

import java.util.ArrayList;
import java.util.List;

import metier.CMCommande;
import dao.CommandeDAO;

public class ListeMemoireCommandeDAO implements CommandeDAO {
	private static ListeMemoireCommandeDAO instance;
	private List<CMCommande> donnees;
	public static ListeMemoireCommandeDAO getInstance() {
		if (instance == null) {
			instance = new ListeMemoireCommandeDAO();
		}
		return instance;
	}
	private ListeMemoireCommandeDAO() {
		this.donnees = new ArrayList<CMCommande>();
		this.donnees.add(new CMCommande(1, "2000-1-1",2));
		this.donnees.add(new CMCommande(2, "2000-5-1", 3));
	}
	@Override
	public boolean create(CMCommande objet) throws Exception {
		objet.setId(3);;
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId()+ 1);
		}
		boolean ok = this.donnees.add(objet);
		
		return ok;
	}
	@Override
	public boolean update(CMCommande objet) throws Exception {
				int idx = this.donnees.indexOf(objet);
				if (idx == -1) {
					throw new IllegalArgumentException("Tentative de modification d'une commande inexistante");
				} else {
					this.donnees.set(idx, objet);
				}
				return true;
	}
	@Override
	public boolean delete(CMCommande objet) throws Exception {
		CMCommande supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une commande inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}
	@Override
	public CMCommande getById(int id) throws Exception {
		
				int idx = this.donnees.indexOf(new CMCommande(id,"2000-1-1",2));
				if (idx == -1) {
					throw new IllegalArgumentException("Aucune commande ne possède cet identifiant");
				} else {
					return this.donnees.get(idx);
				}
	}
	@Override
	public ArrayList<CMCommande> findAll() throws Exception {
		return (ArrayList<CMCommande>) this.donnees;
	}
}


