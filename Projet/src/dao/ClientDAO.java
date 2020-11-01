package dao;

import java.util.ArrayList;
import metier.CMClient;

public interface ClientDAO extends DAO<CMClient> {

	public abstract ArrayList<CMClient> getByNomPrenom(String n,String p) throws Exception;
}
