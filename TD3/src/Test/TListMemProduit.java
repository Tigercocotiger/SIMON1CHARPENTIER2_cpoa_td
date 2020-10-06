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
import ListMemoire.ListeMemoireProduitDAO;
import metier.CMProduit;


/*

  _      _     _                                                        _       _ _   
 | |    (_)   | |                                                      | |     (_) |  
 | |     _ ___| |_ ___   _ __ ___   ___ _ __ ___    _ __  _ __ ___   __| |_   _ _| |_ 
 | |    | / __| __/ _ \ | '_ ` _ \ / _ \ '_ ` _ \  | '_ \| '__/ _ \ / _` | | | | | __|
 | |____| \__ \ ||  __/ | | | | | |  __/ | | | | | | |_) | | | (_) | (_| | |_| | | |_ 
 |______|_|___/\__\___| |_| |_| |_|\___|_| |_| |_| | .__/|_|  \___/ \__,_|\__,_|_|\__|
                                                   | |                                
                                                   |_|                                

*/


public class TListMemProduit {
    private CMProduit p;
    
    @Before
    public void Setup() throws Exception {
    	
	p =new CMProduit(1, "test", "test",(float) 1.0,"test.png",10);
	ListeMemoireProduitDAO.getInstance().create(p);
    }

    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */       
    @Test
	public void testSelectExiste() throws Exception {
		
	int id=p.getId_produit();
	
	CMProduit cLm=ListeMemoireProduitDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
    /*Q
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */    
    @Test
	public void testGetbyId() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(p.getId_produit());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
 /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
 */    
    @Test
	public void testCreate() throws Exception {
		
		assertEquals(p.getNom(),"test");
		assertEquals(p.getDescription(),"test");
		assertEquals(p.getTarif(),1.0,1.0);
		assertEquals(p.getVisuel(),"test.png");
		assertEquals(p.getId_categorie(),10);

	}

    
    
/*
 ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                    
*/                                                                                                                                                                                    

   @Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireProduitDAO.getInstance().delete(p)), "");
		int id = p.getId_produit();

		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(id);
		fail("Le produit existe toujours");
		}catch(Exception e){
		    ;
		}
		
		try {
		ListeMemoireProduitDAO.getInstance().delete(p);
		fail("Le produit existe toujours");
		}
		catch (Exception e){
		    ;
		}	
	}
   /*
   ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
  \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                      
  */    	@Test
	public void testUpdate() throws Exception {
		
		CMProduit p2= new CMProduit(p.getId_produit(),"test2","test2",(float)2.0,"test2.png",12);
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().update(p2);
		CMProduit p3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().getById(p2.getId_produit());
		
		assertEquals("test2", p3.getNom());
		assertEquals("test2", p3.getDescription());
		assertEquals((float)1,0, p3.getTarif());
		assertEquals("test2.png", p3.getVisuel());
		assertEquals(2, p3.getId_categorie());
	}
  /*
  ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
 \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                     
 */    	@Test
	public void testfindAll() throws Exception{
		
			CMProduit c2=new CMProduit(1, "test", "test",(float) 1.0,"test.png",10);
			ListeMemoireProduitDAO lma = (ListeMemoireProduitDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO();
			ArrayList<CMProduit> ar = new ArrayList<CMProduit>(lma.findAll());
			ar.add(c2);
			lma.create(c2);	
			assertEquals(lma.findAll(), ar);
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getProduitDAO().delete(p);
			
		}
}