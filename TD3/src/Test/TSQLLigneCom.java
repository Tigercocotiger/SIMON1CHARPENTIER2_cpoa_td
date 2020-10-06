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

import metier.CMLignedeCommande;
import sql.MySQLLignedeCommandeDAO;
import dao.DAOFactory;
import dao.DAOFactory.Persistance;



/*
   _____  ____  _        _      _                     _____                
  / ____|/ __ \| |      | |    (_)                   / ____|               
 | (___ | |  | | |      | |     _  __ _ _ __   ___  | |     ___  _ __ ___  
  \___ \| |  | | |      | |    | |/ _` | '_ \ / _ \ | |    / _ \| '_ ` _ \ 
  ____) | |__| | |____  | |____| | (_| | | | |  __/ | |___| (_) | | | | | |
 |_____/ \___\_\______| |______|_|\__, |_| |_|\___|  \_____\___/|_| |_| |_|
                                   __/ |                                   
                                  |___/                                    */

public class TSQLLigneCom {
private CMLignedeCommande l;
    
    @BeforeEach
    public void Setup() throws InvalidPropertiesFormatException, SQLException, IOException {
	l=new CMLignedeCommande(1,1,5,3.0);
	MySQLLignedeCommandeDAO.getInstance().create(l);
    }
    
    @AfterEach
    public void tearDown() throws InvalidPropertiesFormatException, SQLException, IOException {
	MySQLLignedeCommandeDAO.getInstance().delete(l);
    }
    
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
    
    
	@Test
	public void testSelectExiste() throws Exception {
	int id=l.getId_commande();
	
	CMLignedeCommande cBdd=MySQLLignedeCommandeDAO.getInstance().getById(id);
	assertNotNull(cBdd);
 }
	
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	
	@Test
	public void testGetbyid() throws Exception {
		
	    
	    	try {
		DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(l.getId_commande());}
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
	    CMLignedeCommande l2 = new CMLignedeCommande(1,1,5,3.0);
		try {
		    
		MySQLLignedeCommandeDAO.getInstance().create(l2);
		
		}catch(Exception e) {
		    fail("Erreur lors de l'insertion");
		}
		
		assertEquals(l.getId_produit(),1);
		assertEquals(l.getQuantite(),3);
		assertEquals(l.getTarif_unitaire(), 1.0, 1.0); 
		
		MySQLLignedeCommandeDAO.getInstance().delete(l2);
		
	}
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	
	
	@Test
	public void testDelete() throws Exception {
	    

	    CMLignedeCommande l2 =new CMLignedeCommande(1,1,5,3.0);
	    MySQLLignedeCommandeDAO.getInstance().create(l2);
		
		int idd = l2.getId_commande();
		assertTrue(MySQLLignedeCommandeDAO.getInstance().delete(l2));
		
		CMLignedeCommande cl = DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(idd);
		assertNull(cl);
		
		assertFalse(MySQLLignedeCommandeDAO.getInstance().delete(cl));
	
	}
	
	/*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
   \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
   */
	
	@Test
	public void testUpdate() throws Exception {
		
		
		CMLignedeCommande c2= new CMLignedeCommande(l.getId_commande(),1,5,3.0);
		DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().update(c2);
		CMLignedeCommande l3 = DAOFactory.getDAOFactory(Persistance.MYSQL).getLignedeCommandeDAO().getById(c2.getId_commande());
		
		assertEquals(1, l3.getId_produit());
		assertEquals(3, l3.getQuantite());
		assertEquals(1.0, 1.0 ,l3.getTarif_unitaire());
	}

}