package Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.Before;
import org.junit.Test;

import dao.DAOFactory;
import dao.DAOFactory.Persistance;
import ListMemoire.ListeMemoireClientDAO;
import metier.CMClient;

/*
  _      _     _                                     _____ _ _            _   
 | |    (_)   | |                                   / ____| (_)          | |  
 | |     _ ___| |_ ___   _ __ ___   ___ _ __ ___   | |    | |_  ___ _ __ | |_ 
 | |    | / __| __/ _ \ | '_ ` _ \ / _ \ '_ ` _ \  | |    | | |/ _ \ '_ \| __|
 | |____| \__ \ ||  __/ | | | | | |  __/ | | | | | | |____| | |  __/ | | | |_ 
 |______|_|___/\__\___| |_| |_| |_|\___|_| |_| |_|  \_____|_|_|\___|_| |_|\__|
                                                                              
                                                                              
  */

public class TListMemClient {
    
    private CMClient c;
    
    @Before
    public void Setup() {
	c=new CMClient(1,"test", "test", null, null, null, null, null, null, null);
	ListeMemoireClientDAO.getInstance().create(c);
    }
    
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
    \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
    */	@Test
	public void testSelectExiste() throws Exception {
	int id=c.getId_client();
	
	CMClient cLm=ListeMemoireClientDAO.getInstance().getById(id);
	assertNotNull(cLm);
 }
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
    \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
    */	@Test
	public void testGetbyid() throws Exception {
		
	    try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(c.getId_client());} catch(Exception e) {
		    fail("Erreur lors de la récupération");
		}
		
	}
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
    \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
    */	@Test
	public void testCreate() throws Exception {
		
		//assertEquals(c.getId(),1);
		assertEquals(c.getNom(),"aa");
		assertEquals(c.getPrenom(),"aaa");

	}
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
    \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
    */	@Test
	public void testDelete() throws Exception {
		
		assertTrue((ListeMemoireClientDAO.getInstance().delete(c)), "");
		int id = c.getId_client();
		
		
		
		try {
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(id);
		fail("Le client existe toujours");
		}catch(Exception e){
		    
		}
		
		try {
		ListeMemoireClientDAO.getInstance().delete(c);
		fail("Le client existe toujours");
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

		
		CMClient c2= new CMClient(c.getId_client(),"test2","test2", null, null, null, null, null, null, null);
		DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().update(c2);
		CMClient c3 = DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().getById(c2.getId_client());
		
		assertEquals("bb", c3.getNom());
		assertEquals("bbb", c3.getPrenom());

	}
    /*
    ____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ _____ 
    \____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\\____\
                                                                                                                                                                                       
    */	@Test
	public void testfindAll() throws Exception{
		    	
			CMClient c2=new CMClient(1,"test", "test", null, null, null, null, null, null, null);
			
			ListeMemoireClientDAO lma = (ListeMemoireClientDAO) DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO();
			
			ArrayList<CMClient> ar = new ArrayList<CMClient>(lma.findAll());
			
			ar.add(c2);
			
			lma.create(c2);
			
			
			assertEquals(lma.findAll(), ar);
			
			DAOFactory.getDAOFactory(Persistance.ListMemoire).getClientDAO().delete(c);
			
		}
	

}