package metier;
//import java.util.Scanner;
//import dao.DAOFactory;

public class CMClient {
private int id_client;
private String nom, prenom, identifiant,mot_de_passe,adr_numero,adr_voie,adr_code_postal, adr_ville,adr_pays;
	public CMClient(String nom, String prenom, String identifiant,String mot_de_passe,String adr_numero,String adr_voie,String adr_code_postal,String adr_ville,String adr_pays)
	{
		this(-1,nom, prenom, identifiant,mot_de_passe,adr_numero,adr_voie,adr_code_postal, adr_ville,adr_pays);
	}
	public CMClient(int id_client,String nom, String prenom, String identifiant,String mot_de_passe,String adr_numero,String adr_voie,String adr_code_postal,String adr_ville,String adr_pays) {
		this.setId_client(id_client);
		this.setNom(nom);
		this.setPrenom(prenom);
		this.setIdentifiant(identifiant);
		this.setMot_de_passe(mot_de_passe);
		this.setAdr_numero(adr_numero);
		this.setAdr_voie(adr_voie);
		this.setAdr_code_postal(adr_code_postal);
		this.setAdr_ville(adr_ville);
		this.setAdr_pays(adr_pays);
	}
	public CMClient(int id_client) {
		this.setId_client(id_client);

	}
	public int getId_client() {
		return id_client;
	}
	public void setId_client(int id_client) {
		this.id_client = id_client;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getIdentifiant() {
		return identifiant;
	}
	public void setIdentifiant(String identifiant) {
		this.identifiant = identifiant;
	}
	public String getMot_de_passe() {
		return mot_de_passe;
	}
	public void setMot_de_passe(String mot_de_passe) {
		this.mot_de_passe = mot_de_passe;
	}
	public String getAdr_numero() {
		return adr_numero;
	}
	public void setAdr_numero(String adr_numero) {
		this.adr_numero = adr_numero;
	}
	public String getAdr_voie() {
		return adr_voie;
	}
	public void setAdr_voie(String adr_voie) {
		this.adr_voie = adr_voie;
	}
	public String getAdr_code_postal() {
		return adr_code_postal;
	}
	public void setAdr_code_postal(String adr_code_postal) {
		this.adr_code_postal = adr_code_postal;
	}
	public String getAdr_ville() {
		return adr_ville;
	}
	public void setAdr_ville(String adr_ville) {
		this.adr_ville = adr_ville;
	}
	public String getAdr_pays() {
		return adr_pays;
	}
	public void setAdr_pays(String adr_pays) {
		this.adr_pays = adr_pays;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMClient other = (CMClient) obj;
		if (adr_code_postal == null) {
			if (other.adr_code_postal != null)
				return false;
		} else if (!adr_code_postal.equals(other.adr_code_postal))
			return false;
		if (adr_numero == null) {
			if (other.adr_numero != null)
				return false;
		} else if (!adr_numero.equals(other.adr_numero))
			return false;
		if (adr_pays == null) {
			if (other.adr_pays != null)
				return false;
		} else if (!adr_pays.equals(other.adr_pays))
			return false;
		if (adr_ville == null) {
			if (other.adr_ville != null)
				return false;
		} else if (!adr_ville.equals(other.adr_ville))
			return false;
		if (adr_voie == null) {
			if (other.adr_voie != null)
				return false;
		} else if (!adr_voie.equals(other.adr_voie))
			return false;
		if (id_client != other.id_client)
			return false;
		if (identifiant == null) {
			if (other.identifiant != null)
				return false;
		} else if (!identifiant.equals(other.identifiant))
			return false;
		if (mot_de_passe == null) {
			if (other.mot_de_passe != null)
				return false;
		} else if (!mot_de_passe.equals(other.mot_de_passe))
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
}
