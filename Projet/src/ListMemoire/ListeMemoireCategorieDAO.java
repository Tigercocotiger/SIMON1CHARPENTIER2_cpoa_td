package ListMemoire;
import java.util.ArrayList;
import java.util.List;
import metier.CMCategorie;
import dao.CategorieDAO;

public class ListeMemoireCategorieDAO implements CategorieDAO{
	private static ListeMemoireCategorieDAO instance;
	private List<CMCategorie> donnees;
	public static ListeMemoireCategorieDAO getInstance() {
		if (instance == null) {
			instance = new ListeMemoireCategorieDAO();
		}
		return instance;
	}
	private ListeMemoireCategorieDAO() {
		this.donnees = new ArrayList<CMCategorie>();
		this.donnees.add(new CMCategorie(1, "Pulls", "pulls.png"));
		this.donnees.add(new CMCategorie(2, "Bonnets", "bonnets.png"));
	}
	public boolean create(CMCategorie objet) {
		objet.setId(3);
		while (this.donnees.contains(objet)) {
			objet.setId(objet.getId() + 1);
		}
		boolean ok = this.donnees.add(objet);
		return ok;
	}
	public boolean update(CMCategorie objet) {
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'une categorie inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		return true;
	}
	public boolean delete(CMCategorie objet) {
		CMCategorie supprime;
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'une catégorie inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		return objet.equals(supprime);
	}
	public CMCategorie getById(int id) {
		int idx = this.donnees.indexOf(new CMCategorie(id, "test", "test.png"));
		if (idx == -1) {
			throw new IllegalArgumentException("Aucune catégorie ne possède cet identifiant");
		} else {
			return this.donnees.get(idx);
		}
	}
	public ArrayList<CMCategorie> findAll() {
		return (ArrayList<CMCategorie>) this.donnees;
	}
}
