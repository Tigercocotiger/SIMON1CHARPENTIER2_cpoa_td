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
import ListMemoire.ListeMemoireCategorieDAO;
import metier.CMCategorie;




/*
  _      _     _                                              _             
 | |    (_)   | |                                            | |            
 | |     _ ___| |_ ___   _ __ ___   ___ _ __ ___     ___ __ _| |_ ___  __ _ 
 | |    | / __| __/ _ \ | '_ ` _ \ / _ \ '_ ` _ \   / __/ _` | __/ _ \/ _` |
 | |____| \__ \ ||  __/ | | | | | |  __/ | | | | | | (_| (_| | ||  __/ (_| |
 |______|_|___/\__\___| |_| |_| |_|\___|_| |_| |_|  \___\__,_|\__\___|\__, |
                                                                       __/ |
                                                                      |___/ 
 */
public class TListMemCategorie {
    private CMCategorie c;
    
    @Before
    public void Setup() {
	c=new CMCategorie(1,"test", "test.png");
	ListeMemoireCategorieDAO.getInstance().create(c);
    }
    
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */	@Test
	public void testSelectExiste() throws Exception {
	int id=c.getId();
	
	CMCategorie cLm=ListeMemoireCategorieDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
   /*
   ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
  \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                      
  */	@Test
	public void testGetbyId() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO().getById(c.getId());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
  /*
  ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
 \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                     
 */	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(c.getTitre(),"test");
		assertEquals(c.getVisuel(),"test.png");

	}
 /*
 ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                    
*/
 @Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireCategorieDAO.getInstance().delete(c)), "");
		int id = c.getId();
		
		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO().getById(id);
		fail("La catégorie existe toujours");
		}catch(Exception e){
		    
		}
		
		try {
		ListeMemoireCategorieDAO.getInstance().delete(c);
		fail("La catégorie existe toujours");
		}
		catch (Exception e){
		    ;
		}
	}
 /*
 ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                    
*/	@Test
	public void testUpdate() throws Exception {

		
		CMCategorie c2= new CMCategorie(c.getId(),"test2","test2.png");
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO().update(c2);
		CMCategorie c3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO().getById(c2.getId());
		assertEquals("test2", c3.getTitre());
		assertEquals("test2.png", c3.getVisuel());
	
	}
/*
____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                   
*/	@Test
	public void testfindAll() throws Exception{
		    	
			CMCategorie c2=new CMCategorie(1,"test", "test");
			
			ListeMemoireCategorieDAO lma = (ListeMemoireCategorieDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO();
			
			ArrayList<CMCategorie> ar = new ArrayList<CMCategorie>(lma.findAll());
			
			ar.add(c2);
			
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getCategorieDAO().delete(c);
			
		}
}