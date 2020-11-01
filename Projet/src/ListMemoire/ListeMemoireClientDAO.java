package ListMemoire;
import java.util.ArrayList;
import java.util.List;

import metier.CMClient;
import dao.ClientDAO;

public class ListeMemoireClientDAO implements ClientDAO{
	private static ListeMemoireClientDAO instance;
	private List<CMClient> donnees;
	public static ListeMemoireClientDAO getInstance() {
		if(instance == null) {
			instance = new ListeMemoireClientDAO();
		}
		return instance;
	}
	private ListeMemoireClientDAO() {
		this.donnees=new ArrayList<CMClient>();
		this.donnees.add(new CMClient(1,"CHARPENTIER","Clément","charpe111u","mdp","1","57","57000","Metz","France"));
		this.donnees.add(new CMClient(2,"SIMON","Marco","simon429u","mdp","1","57","57000","Metz","France"));
	}
	@Override
	public boolean create(CMClient objet) {
		objet.setId_client(3);
		while (this.donnees.contains(objet)) {
			objet.setId_client(objet.getId_client()+1);
		}
		boolean ok= this.donnees.add(objet);
		return ok;
	}
	@Override
	public boolean update(CMClient objet){
		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de modification d'un client inexistante");
		} else {
			
			this.donnees.set(idx, objet);
		}
		
		return true;
	}
	@Override
	public boolean delete(CMClient objet) {

		CMClient supprime;

		int idx = this.donnees.indexOf(objet);
		if (idx == -1) {
			throw new IllegalArgumentException("Tentative de suppression d'un client inexistante");
		} else {
			supprime = this.donnees.remove(idx);
		}
		
		return objet.equals(supprime);
	}
	@Override
	public CMClient getById(int id) throws Exception {
	
				int idx = this.donnees.indexOf(new CMClient(id,"Marco","Simon","simon429u","2","2334","57","57000","Metz","France"));
				if (idx == -1) {
					throw new IllegalArgumentException("Aucune categorie ne possède de cet identifiant");
				} else {
					return this.donnees.get(idx);
				}
	}
	@Override
	public ArrayList<CMClient> findAll() throws Exception {
		// TODO Auto-generated method stub
		return (ArrayList<CMClient>) this.donnees;
	}
	@Override
	public ArrayList<CMClient> getByNomPrenom(String n, String p) throws Exception {
		ArrayList<CMClient> ar = new ArrayList<CMClient>();
		for(int i=0;i<this.donnees.size();i++) {
			if(this.donnees.get(i).getNom()==n && this.donnees.get(i).getPrenom() == p) {
				ar.add(this.donnees.get(i));
			}
		}
		return ar;
	}
}