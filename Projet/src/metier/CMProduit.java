package metier;
//import java.util.Scanner;
//import dao.DAOFactory;

public class CMProduit {
	private int id_produit,id_categorie;
	private String nom, description, visuel;
	private float tarif;
	
	public CMProduit(String nom, String description, float tarif, String visuel, int id_categorie ){
		this(-1,nom,description,tarif,visuel,id_categorie);
	}
	
	public CMProduit(int id_produit,String nom, String description, float tarif, String visuel, int id_categorie) {
		this.setId_produit(id_produit);
		this.setNom(nom);
		this.setDescription(description);
		this.setTarif(tarif);
		this.setVisuel(visuel);
		this.setId_categorie(id_categorie);
		}

	public CMProduit(int id_produit) {
        this.setId_produit(id_produit);
        }
	
	public int getId_produit() {
		return id_produit;
	}

	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	
	public int getId_categorie() {
		return id_categorie;
	}

	public void setId_categorie(int id_categorie) {
		this.id_categorie = id_categorie;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getVisuel() {
		return visuel;
	}

	public void setVisuel(String visuel) {
		this.visuel = visuel;
	}

	public float getTarif() {
		return tarif;
	}

	public void setTarif(float tarif) {
		this.tarif = tarif;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMProduit other = (CMProduit) obj;
		if (description == null) {
			if (other.description != null)
				return false;
		} else if (!description.equals(other.description))
			return false;
		if (id_categorie != other.id_categorie)
			return false;
		if (id_produit != other.id_produit)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Float.floatToIntBits(tarif) != Float.floatToIntBits(other.tarif))
			return false;
		if (visuel == null) {
			if (other.visuel != null)
				return false;
		} else if (!visuel.equals(other.visuel))
			return false;
		return true;
	}
	
	
}
