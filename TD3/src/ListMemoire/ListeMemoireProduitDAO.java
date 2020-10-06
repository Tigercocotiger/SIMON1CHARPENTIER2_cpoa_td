package ListMemoire;

import java.util.ArrayList;
import java.util.List;
import metier.CMProduit;
import dao.ProduitDAO;

public class ListeMemoireProduitDAO implements ProduitDAO {
	private static ListeMemoireProduitDAO instance;

	private List<CMProduit> donnees;

	public static ListeMemoireProduitDAO getInstance() {

		if (instance == null) {
			instance = new ListeMemoireProduitDAO();
		}

		return instance;
	}

	private ListeMemoireProduitDAO() {

		this.donnees = new ArrayList<CMProduit>();

		this.donnees.add(new CMProduit(1, "Café", "café sans sucre",3,"eif.png",2));
		this.donnees.add(new CMProduit(2, "Stylo", "stylo noire",5,"stylo.png",1));
	}
	

	@Override
	public boolean create(CMProduit objet) throws Exception {
		objet.setId_produit(3);
		while (this.donnees.contains(objet)) {
			objet.setId_produit(objet.getId_produit()+1);
		}
boolean ok = this.donnees.add(objet);
		
		return ok;
	}

	@Override
	public boolean update(CMProduit objet) throws Exception {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une produit inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}
	

	@Override
	public boolean delete(CMProduit objet) throws Exception {
		CMProduit supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une produit inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}

	@Override
	public CMProduit getById(int id) throws Exception {
		int idx = this.donnees.indexOf(new CMProduit(id,"Café", "café sans sucre",3,"eif.png",2));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune produit prossède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	@Override
	public ArrayList<CMProduit> findAll() throws Exception {
		return (ArrayList<CMProduit>) this.donnees;
	}

}
