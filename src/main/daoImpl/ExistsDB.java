package main.daoImpl;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.XML;
import org.xmldb.api.DatabaseManager;
import org.xmldb.api.base.Collection;
import org.xmldb.api.base.Database;
import org.xmldb.api.base.XMLDBException;
import org.xmldb.api.modules.XMLResource;

import com.google.gson.Gson;

import main.idao.IExistsDB;
import main.model.Cards;

public class ExistsDB implements IExistsDB {
	
	private final  String DRIVER = "org.exist.xmldb.DatabaseImpl";
	private final String URI = "xmldb:exist://localhost:8080/exist/xmlrpc/db/cards";
	private final  String resourceName = "card_collection";

	private ArrayList<Cards> cardList = new ArrayList<Cards>();
	
	private Class cl;
	private Database database;
	
	public void connect () {
		try {
			cl = Class.forName(DRIVER);
			database = (Database) cl.newInstance();
			DatabaseManager.registerDatabase(database); 
			
		} catch (InstantiationException | IllegalAccessException e) {
			
				e.printStackTrace();
				
		} catch (ClassNotFoundException | XMLDBException e) {
			
			e.printStackTrace();
		} 
		
	}
	
	@SuppressWarnings("finally")
	public ArrayList<Cards> getCards()  {
		connect ();
			try {
				Collection col = DatabaseManager.getCollection(URI); 

				XMLResource res = (XMLResource) col.getResource(resourceName);
				JSONObject xmlJSONObj = XML.toJSONObject((String)res.getContent());

				JSONArray allCards = xmlJSONObj.getJSONObject("cards").getJSONArray("card");

				cardList.clear();
				for (Object object : allCards) {
					Cards data = new Gson().fromJson(object.toString(), Cards.class);
					
					cardList.add(data);
				}
				
			} catch (XMLDBException e) {
				e.printStackTrace();
			}finally {
				cl = null;
				database = null;
				return cardList;
			}
			
	}
}