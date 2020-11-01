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

import metier.CMClient;
import sql.MySQLClientDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;


/*
   _____  ____  _         _____ _ _            _   
  / ____|/ __ \| |       / ____| (_)          | |  
 | (___ | |  | | |      | |    | |_  ___ _ __ | |_ 
  \___ \| |  | | |      | |    | | |/ _ \ '_ \| __|
  ____) | |__| | |____  | |____| | |  __/ | | | |_ 
 |_____/ \___\_\______|  \_____|_|_|\___|_| |_|\__|
                                                   
                                                   */
public class TSQLClient {
private CMClient c;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	c=new CMClient(1,"test", "test", null, null, null, null, null, null, null);
	MySQLClientDAO.getInstance().create(c);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLClientDAO.getInstance().delete(c);
    }
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	@Test
	public void testSelectExiste() throws Exception {

	int id=c.getId_client();
	
	CMClient cBdd=MySQLClientDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(c.getId_client());}
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
	    CMClient c2 = new CMClient(1,"test", "test", null, null, null, null, null, null, null);
		try {
		    
		MySQLClientDAO.getInstance().create(c2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		//assertEquals(c.getId(),1);
		assertEquals(c.getNom(),"test");
		assertEquals(c.getPrenom(),"test");

		
		MySQLClientDAO.getInstance().delete(c2);
		
	}
	
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	
	@Test
	public void testDelete() throws Exception {
	    

	    CMClient c2 =new CMClient(1,"test", "test", null, null, null, null, null, null, null);
	    MySQLClientDAO.getInstance().create(c2);
		
		int idd = c2.getId_client();
		assertTrue(MySQLClientDAO.getInstance().delete(c2));
		
		CMClient cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(idd);
		assertNull(cl);
		
		assertFalse(MySQLClientDAO.getInstance().delete(cl));

		
	}
	
	
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	@Test
	public void testUpdate() throws Exception {
		
		
		CMClient c2= new CMClient(c.getId_client(),"test2","test2", null, null, null, null, null, null, null);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().update(c2);
		CMClient c3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getClientDAO().getById(c2.getId_client());
		
		assertEquals("test2", c3.getNom());
		assertEquals("test2", c3.getPrenom());

	}

}