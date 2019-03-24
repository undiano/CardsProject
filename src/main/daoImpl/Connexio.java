package main.daoImpl;
import java.util.ArrayList;

import main.idao.IExistsDB;
import main.idao.IMongoDB;
import main.model.Cards;

public class Connexio {
	private static IExistsDB existDb;
	private static IMongoDB mongoWrite;
	
	
	public static IExistsDB getExistCards() {
		existDb = new ExistsDB();
		return existDb;
	}
	
	public static IMongoDB insertMongo() {
		mongoWrite = new MongoDB();
		return mongoWrite;
	}

}
