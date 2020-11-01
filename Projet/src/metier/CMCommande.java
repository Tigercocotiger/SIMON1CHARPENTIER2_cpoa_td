package metier;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;


public class CMCommande {
	private int id_commande, id_client;
	private Date date_commande;
	

	public CMCommande(String date_commande,int id_client) {
		this(-1,date_commande,id_client);
		
		}
	public CMCommande(int id_commande,Date date_commande,int id_client) {
		this.setId(id_commande);
		this.setDate_commande1(date_commande);
		this.setId_client(id_client);
		}
		
	public CMCommande(int id_commande,String date_commande,int id_client) {
		this.setId(id_commande);
		this.setDate_commande2(date_commande);
		this.setId_client(id_client);
		}
	/*public CMCommande(int id_commande,LocalDate date_commande,int id_client) {
		this.setId(id_commande);
		this.setDate_commande3(date_commande);
		this.setId_client(id_client);
		}
*/
		public CMCommande(int id) {
			this.setId(id);
			}
	public int getId() {
		return id_commande;
	}

	public void setId(int id_commande) {
		this.id_commande = id_commande;
	}

	public int getId_client() {
		return id_client;
	}

	public void setId_client(int id_client) {
		this.id_client = id_client;
	}

	public Date getDate_commande() {
		return date_commande;
	}

	public void setDate_commande1(Date date_commande) {
		this.date_commande = date_commande;
	}
	public void setDate_commande2(String date_commande) {
		this.date_commande = java.sql.Date.valueOf(date_commande);
	}
	public void setDate_commande3(LocalDate date_commande) {
		this.date_commande = java.sql.Date.valueOf(date_commande);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CMCommande other = (CMCommande) obj;
		if (date_commande == null) {
			if (other.date_commande != null)
				return false;
		} else if (!date_commande.equals(other.date_commande))
			return false;
		if (id_client != other.id_client)
			return false;
		if (id_commande != other.id_commande)
			return false;
		return true;
	}
}
