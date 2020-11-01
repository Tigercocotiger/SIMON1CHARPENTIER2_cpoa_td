package metier;


public class CMCategorie {
	private int id_categorie;
	private String titre,visuel;
	
	public CMCategorie (String titre,String visuel)
	{
		this(0,titre,visuel);
	}

	public CMCategorie(int id_categorie,String titre,String visuel)
	{
		this.setId(id_categorie);
		this.setTitre(titre);
		this.setVisuel(visuel);
	}
public CMCategorie(int id) {
	this.setId(id);
	}

	//-------------------------------------------------	
	public int getId() 
	{
		return this.id_categorie;
	}
	public void setId(int id_categorie)
	{
		this.id_categorie=id_categorie;
	}
//------------------------------------------------	
	public String getTitre()
	{
		return this.titre;
	}
	public void setTitre(String titre)
	{   
		if(titre==null|| titre.trim().length()==0)
		{
			throw new IllegalArgumentException("Titre de la catégorie!");
		}
		this.titre=titre;
	}
//-------------------------------------------------
	public String getVisuel()
	{
		return this.visuel;
	}
	public void setVisuel(String visuel)
	{
		this.visuel=visuel;
	}
//--------------------------------------------------
	
	public boolean equals(Object objet) {
	    if(objet == null) return false;
	    if(this == objet) return true;
	    if(getClass() != objet.getClass()) return false;
	    CMCategorie cat = (CMCategorie) objet;
	    return this.getId() == cat.getId();}
	
	
	
}
