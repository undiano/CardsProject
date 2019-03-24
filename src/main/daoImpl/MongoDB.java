package main.daoImpl;

import java.util.ArrayList;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;

import javax.swing.JOptionPane;

import org.bson.Document;
import org.bson.conversions.Bson;

import com.google.gson.Gson;
import com.mongodb.Block;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;

import main.idao.IMongoDB;
import main.model.Baraja;

public class MongoDB implements IMongoDB{

	private  MongoClientURI connectionString;
	private  MongoClient mongoClient;
	private  MongoDatabase database;
	private  MongoCollection<org.bson.Document> collection;
	private static Document DocumentLoadCards;

	private void connectDatabase() {
		connectionString = new MongoClientURI("mongodb://localhost:27017");
		mongoClient = new MongoClient(connectionString);
		database = mongoClient.getDatabase("dbDecks");
		collection = database.getCollection("decks");
	}
	
	private void disconnect() {
		collection = null;
		database = null;
	}

	public boolean insertOne(Baraja b1, Document userDoc) {
		connectDatabase();
		
		MongoCursor<Document> cursor = collection.find(Filters.eq("DeckName", b1.getDeckName())).iterator();
		
		if (!cursor.hasNext()) {
			collection.insertOne(userDoc);
			 disconnect();
			return true;
		} else {
			 disconnect();
			return false;
		}

	}
	
	public Baraja getBaraja (String name) {
		Baraja deck;
		connectDatabase();
		MongoCursor<Document> cursor = collection.find(Filters.eq("DeckName", name)).iterator();
		
		if(cursor.hasNext()) {
			DocumentLoadCards = cursor.next();
			deck = new Gson().fromJson(DocumentLoadCards.toJson(), Baraja.class);
			return deck;
		}else {
			deck=null;
			return null;
		}
	}
	
	public void updateBaraja(String name, Document userDoc) {
		connectDatabase();
		
		collection.deleteOne(DocumentLoadCards);
		collection.insertOne(userDoc);
		
		disconnect();
	}

}
