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
import ListMemoire.ListeMemoireCommandeDAO;
import metier.CMCommande;

/* 
  _      _     _                                     _____                                          _      
 | |    (_)   | |                                   / ____|                                        | |     
 | |     _ ___| |_ ___   _ __ ___   ___ _ __ ___   | |     ___  _ __ ___  _ __ ___   __ _ _ __   __| | ___ 
 | |    | / __| __/ _ \ | '_ ` _ \ / _ \ '_ ` _ \  | |    / _ \| '_ ` _ \| '_ ` _ \ / _` | '_ \ / _` |/ _ \
 | |____| \__ \ ||  __/ | | | | | |  __/ | | | | | | |___| (_) | | | | | | | | | | | (_| | | | | (_| |  __/
 |______|_|___/\__\___| |_| |_| |_|\___|_| |_| |_|  \_____\___/|_| |_| |_|_| |_| |_|\__,_|_| |_|\__,_|\___|
                                                                                                           
                                                                                                           */

public class TListMemCommande {
private CMCommande c;
    
    @Before
    public void Setup() throws Exception {
	c =new CMCommande(1, "01-01-2020",1 );
	ListeMemoireCommandeDAO.getInstance().create(c);
    }
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */ 	@Test
	public void testSelectExiste() throws Exception {
		
	int id=c.getId();
	
	CMCommande cLm=ListeMemoireCommandeDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
   /*
   ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
  \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                      
  */ 	@Test
	public void testGetbyId() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().getById(c.getId());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
  /*
  ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
 \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                     
 */ 	@Test
	public void testCreate() throws Exception {
			
		//assertEquals(c.getId(),1);
		assertEquals(c.getId(),1);
		assertEquals(c.getId_client(),1);
		assertEquals(c.getDate_commande(),"01-01-2000"); 
		}	
 /*
 ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                    
*/ 	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireCommandeDAO.getInstance().delete(c)), "");
		int id = c.getId();

		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().getById(id);
		fail("La commande existe toujours");
		}catch(Exception e){
		    ;
		}
		
		try {
		ListeMemoireCommandeDAO.getInstance().delete(c);
		fail("La commande existe toujours");
		}
		catch (Exception e){
		    ;
		}	
	}
/*
____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                   
*/ 	@Test
	public void testUpdate() throws Exception {
			
		CMCommande c2= new CMCommande(c.getId(),"01-01-2020",1);
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().update(c2);
		CMCommande c3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().getById(c2.getId());
		assertEquals(1, c3.getId_client());
		assertEquals("01-01-2020", c3.getDate_commande() );
	}
/*
____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                   
*/ 	@Test
	public void testfindAll() throws Exception{
		    	
			CMCommande c2=new CMCommande(2,"01-01-2020", 2);
			
			ListeMemoireCommandeDAO lma = (ListeMemoireCommandeDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO();			
			ArrayList<CMCommande> ar = new ArrayList<CMCommande>(lma.findAll());

			ar.add(c2);
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);		
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getCommandeDAO().delete(c);
				
			}
}