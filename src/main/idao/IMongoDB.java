package main.idao;

import org.bson.Document;

import main.model.Baraja;

public interface IMongoDB {

	 public boolean insertOne(Baraja b1,Document userDoc);
	 public Baraja getBaraja (String name);
	 public void updateBaraja(String name, Document userDoc);
}
