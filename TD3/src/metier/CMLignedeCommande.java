package metier;
//import java.util.Scanner;
//import dao.DAOFactory;

public class CMLignedeCommande {
	private int id_commande,id_produit, quantite;
	private double tarif_unitaire;
	
	
	
	public CMLignedeCommande(int id_produit, int quantite, double tarif_unitaire) {
		this(-1,id_produit,quantite,tarif_unitaire);
	}
	public CMLignedeCommande(int id_commande,int id_produit, int quantite, double tarif_unitaire) {
		this.setId_commande(id_commande);
		this.setId_produit(id_produit);
		this.setQuantite(quantite);
		this.setTarif_unitaire(tarif_unitaire);
	}
	public CMLignedeCommande(int id_commande,int id_produit) {
		this.setId_commande(id_commande);
		this.setId_produit(id_produit);
        }
	
	public int getId_commande() {
		return id_commande;
	}
	public void setId_commande(int id_commande) {
		this.id_commande = id_commande;
	}
	public int getId_produit() {
		return id_produit;
	}
	public void setId_produit(int id_produit) {
		this.id_produit = id_produit;
	}
	public int getQuantite() {
		return quantite;
	}
	public void setQuantite(int quantite) {
		this.quantite = quantite;
	}
	public double getTarif_unitaire() {
		return tarif_unitaire;
	}
	public void setTarif_unitaire(double tarif_unitaire) {
		this.tarif_unitaire = tarif_unitaire;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMLignedeCommande other = (CMLignedeCommande) obj;
		if (id_commande != other.id_commande)
			return false;
		if (id_produit != other.id_produit)
			return false;
		if (quantite != other.quantite)
			return false;
		if (Double.doubleToLongBits(tarif_unitaire) != Double.doubleToLongBits(other.tarif_unitaire))
			return false;
		return true;
	}
	
	
	
	
	
	
}
