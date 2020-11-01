package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import ListMemoire.ListeMemoireLignedeCommandeDAO;
import metier.CMLignedeCommande;


/*
  _      _     _                                    _      _                                        
 | |    (_)   | |                                  | |    (_)                                       
 | |     _ ___| |_ ___   _ __ ___   ___ _ __ ___   | |     _  __ _ _ __   ___    ___ ___  _ __ ___  
 | |    | / __| __/ _ \ | '_ ` _ \ / _ \ '_ ` _ \  | |    | |/ _` | '_ \ / _ \  / __/ _ \| '_ ` _ \ 
 | |____| \__ \ ||  __/ | | | | | |  __/ | | | | | | |____| | (_| | | | |  __/ | (_| (_) | | | | | |
 |______|_|___/\__\___| |_| |_| |_|\___|_| |_| |_| |______|_|\__, |_| |_|\___|  \___\___/|_| |_| |_|
                                                              __/ |                                 
                                                             |___/                                  */
public class TListMemLigneCom {
    private CMLignedeCommande l;
    
    @Before
    public void Setup() throws Exception {
	l =new CMLignedeCommande(1,1,5,3.0);
	ListeMemoireLignedeCommandeDAO.getInstance().create(l);
    }
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */  	@Test
	public void testSelectExiste() throws Exception {
		
	int id=l.getId_produit();
	
	CMLignedeCommande cLm=ListeMemoireLignedeCommandeDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
	 /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */  	@Test
	public void testGetbyId() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().getById(l.getId_commande());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}

	 /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */  	@Test
	public void testCreate() throws Exception {

		assertEquals(l.getId_produit(),1);
		assertEquals(l.getQuantite(),3);
		assertEquals(l.getTarif_unitaire(), 1.0, 1.0); 
	}
	 /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */  	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireLignedeCommandeDAO.getInstance().delete(l)), "");
		int id = l.getId_commande();

		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().getById(id);
		fail("La ligne de commande existe toujours");
		}catch(Exception e){
		    ;
		}
		
		try {
		ListeMemoireLignedeCommandeDAO.getInstance().delete(l);
		fail("La ligne de commande existe toujours");
		}
		catch (Exception e){
		    ;
		}	
	}
	 /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */  	@Test
	public void testUpdate() throws Exception {
			
		CMLignedeCommande l2= new CMLignedeCommande(l.getId_commande(),1,3,1.0);
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().update(l2);
		CMLignedeCommande l3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().getById(l2.getId_commande());
		assertEquals(1, l3.getId_produit());
		assertEquals(3, l3.getQuantite());
		assertEquals(1.0, 1.0 ,l3.getTarif_unitaire());
		
	
	}
	 /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */  	@Test
	public void testfindAll() throws Exception{
		    	
		CMLignedeCommande c2=new CMLignedeCommande(2,4, 2.0);
		
		ListeMemoireLignedeCommandeDAO lma = (ListeMemoireLignedeCommandeDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO();			
		ArrayList<CMLignedeCommande> ar = new ArrayList<CMLignedeCommande>(lma.findAll());

		ar.add(c2);
		lma.create(c2);
			
		assertEquals(lma.findAll(), ar);		
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getLignedeCommandeDAO().delete(l);
			
		}
	
	
}