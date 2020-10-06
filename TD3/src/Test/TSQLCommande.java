package Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.IOException;
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import metier.CMCommande;
import sql.MySQLCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;


/*
   _____  ____  _         _____                                          _      
  / ____|/ __ \| |       / ____|                                        | |     
 | (___ | |  | | |      | |     ___  _ __ ___  _ __ ___   __ _ _ __   __| | ___ 
  \___ \| |  | | |      | |    / _ \| '_ ` _ \| '_ ` _ \ / _` | '_ \ / _` |/ _ \
  ____) | |__| | |____  | |___| (_) | | | | | | | | | | | (_| | | | | (_| |  __/
 |_____/ \___\_\______|  \_____\___/|_| |_| |_|_| |_| |_|\__,_|_| |_|\__,_|\___|
                                                                                
                                                                                
*/
public class TSQLCommande {
private CMCommande c;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	c=new CMCommande(1, "01-01-2020" ,1);
	MySQLCommandeDAO.getInstance().create(c);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLCommandeDAO.getInstance().delete(c);
    }
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	@Test
	public void testSelectExiste() throws Exception {
	int id=c.getId();
	
	CMCommande cBdd=MySQLCommandeDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(c.getId());}
	    	catch(Exception e) {
	    	    fail("erreur de getbyid");
	    	}
	    	
	    
	}
	
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	@Test
	public void testCreate() throws Exception {
	    CMCommande c2 = new CMCommande(1, "01-01-2000",1);
		try {
		    
		MySQLCommandeDAO.getInstance().create(c2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(c.getId(),1);
		assertEquals(c.getId_client(),1);
		assertEquals(c.getDate_commande(),"01-01-2000");
		
		MySQLCommandeDAO.getInstance().delete(c2);
		
	}
	
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	@Test
	public void testDelete() throws Exception {
	    

	    CMCommande c2 =new CMCommande(1, "01-01-2000",1);
	    MySQLCommandeDAO.getInstance().create(c2);
		
		int idd = c2.getId();
		assertTrue(MySQLCommandeDAO.getInstance().delete(c2));
		
		CMCommande cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(idd);
		assertNull(cl);
		
		assertFalse(MySQLCommandeDAO.getInstance().delete(cl));
	
		
	
		
		
	}
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	@Test
	public void testUpdate() throws Exception {
		
		
		CMCommande c2= new CMCommande(c.getId(),"01-01-2000",1);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().update(c2);
		CMCommande c3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getCommandeDAO().getById(c2.getId());
		
		assertEquals(1, c3.getId_client());
		assertEquals("01-01-2000", c3.getDate_commande() );
	}

}
