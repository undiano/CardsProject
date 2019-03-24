package main.components;

import java.util.ArrayList;

import javax.swing.DefaultListModel;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;

import org.bson.Document;

import com.google.gson.Gson;

import main.View.VentanaCartas;
import main.daoImpl.Connexio;
import main.daoImpl.MongoDB;
import main.model.Baraja;
import main.model.Cards;

public class UtilsCards {

	public static ArrayList<Cards> cardList = new ArrayList<>();
	public static ArrayList<Cards> barajaList = new ArrayList<>();
	public static int contadorValor;
	public static boolean loadBaraja = false;
	public static Baraja barajaCargada;

	public static DefaultListModel obtenerCartas() {
		cardList = Connexio.getExistCards().getCards();
		DefaultListModel modelo = new DefaultListModel();
		for (Cards cards : cardList) {
			modelo.addElement(cards.getName() + " Valor: " + cards.getValue());
		}
		return modelo;
	}

	public static void randomDeck(JList lista, JList lista2, JLabel numero) {
		int valorPequeno = 20;
		int random;
		boolean exit = true;
		UtilsCards.contadorValor = 0;
		UtilsCards.barajaList.clear();
		lista.setModel(UtilsCards.obtenerCartas());
		ArrayList <Cards> cartasposibles = new ArrayList();
		cartasposibles.clear();
		do {
			cartasposibles.clear();
			for (Cards cards : UtilsCards.cardList) {
				if (cards.getValue() <= (20 - contadorValor)) {
					cartasposibles.add(cards);
				}
			}
			if(cartasposibles.size() != 0) {
				random = (int) (Math.random() * cartasposibles.size());
				UtilsCards.contadorValor = UtilsCards.contadorValor + cartasposibles.get(random).getValue();
				if (UtilsCards.contadorValor <= 20) {
						UtilsCards.barajaList.add(cartasposibles.get(random));
						UtilsCards.cardList.remove(cartasposibles.get(random));
				}else {
					UtilsCards.contadorValor = UtilsCards.contadorValor - UtilsCards.cardList.get(random).getValue();
					exit = false;
				}
			} else {
				exit = false;
			}
			
		} while (exit);
		DefaultListModel modelo = new DefaultListModel<>();
		DefaultListModel modelo2 = new DefaultListModel<>();
		for (Cards cards : UtilsCards.cardList) {
			modelo.addElement(cards.getName() + " Valor: " + cards.getValue());
		}
		lista.setModel(modelo);
		for (Cards cards : UtilsCards.barajaList) {
			modelo2.addElement(cards.getName() + " Valor: " + cards.getValue());
		}
		lista2.setModel(modelo2);
		numero.setText(Integer.toString(UtilsCards.contadorValor));
	}

	public static void guardarOActualizar() {
		if (loadBaraja) {
			int reply = JOptionPane.showConfirmDialog(null, "Quieres sobreEscribir la baraja?", "SobreEscribir",
					JOptionPane.YES_NO_OPTION);
			if (reply == JOptionPane.YES_OPTION) {
				updateBaraja();
			} else {
				insertCards();
			}
		} else {
			insertCards();
		}
	}

	public static void insertCards() {
		String DeckName = JOptionPane.showInputDialog("Introduce el nombre de la baraja: ");
		Baraja b11 = new Baraja(DeckName, contadorValor, barajaList);
		Gson gson = new Gson();
		String JSON = gson.toJson(b11);
		Document userDoc = Document.parse(JSON);
		if (Connexio.insertMongo().insertOne(b11, userDoc)) {
			JOptionPane.showMessageDialog(null, "Baraja insertada correctamente", "Insertar baraja",
					JOptionPane.INFORMATION_MESSAGE);
		} else {
			JOptionPane.showMessageDialog(null, "Ya existe una baraja con ese nombre", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void moverDerecha(JList lista, JList lista2) {
		try {
			Cards carta = UtilsCards.cardList.get(lista.getSelectedIndex());
			DefaultListModel modelo = new DefaultListModel<>();
			DefaultListModel modelo2 = new DefaultListModel<>();
			UtilsCards.contadorValor = UtilsCards.contadorValor + carta.getValue();
			if (UtilsCards.contadorValor <= 20) {
				VentanaCartas.numero.setText(Integer.toString(UtilsCards.contadorValor));
				UtilsCards.barajaList.add(carta);
				UtilsCards.cardList.remove(lista.getSelectedIndex());
				for (Cards cards : UtilsCards.cardList) {
					modelo.addElement(cards.getName() + " Valor: " + cards.getValue());
				}
				lista.setModel(modelo);
				for (Cards cards : UtilsCards.barajaList) {
					modelo2.addElement(cards.getName() + " Valor: " + cards.getValue());
				}
				lista2.setModel(modelo2);
			} else {
				JOptionPane.showMessageDialog(null, "Tu baraja tiene un valor superior a 20", "Error",
						JOptionPane.ERROR_MESSAGE);
				UtilsCards.contadorValor = UtilsCards.contadorValor - carta.getValue();
			}

		} catch (NullPointerException e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Todavia nos has cargado las cartas", "Error",
					JOptionPane.ERROR_MESSAGE);
		}catch (ArrayIndexOutOfBoundsException e1) {
			JOptionPane.showMessageDialog(null, "Todavia nos seleccionado ninguna carta", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static void moverIzquierda(JList lista, JList lista2) {
		try {
			Cards carta = UtilsCards.barajaList.get(lista2.getSelectedIndex());
			DefaultListModel modelo = new DefaultListModel<>();
			DefaultListModel modelo2 = new DefaultListModel<>();
			UtilsCards.contadorValor = UtilsCards.contadorValor - carta.getValue();
			VentanaCartas.numero.setText(Integer.toString(UtilsCards.contadorValor));
			UtilsCards.barajaList.remove(lista2.getSelectedIndex());
			UtilsCards.cardList.add(carta);
			for (Cards cards : UtilsCards.cardList) {
				modelo.addElement(cards.getName() + " Valor: " + cards.getValue());
			}
			lista.setModel(modelo);
			if (UtilsCards.barajaList.isEmpty()) {

			} else {
				for (Cards cards : UtilsCards.barajaList) {
					modelo2.addElement(cards.getName() + " Valor: " + cards.getValue());
				}
			}
			lista2.setModel(modelo2);
		} catch (NullPointerException e1) {
			// TODO: handle exception
			JOptionPane.showMessageDialog(null, "Todavia nos has cargado las cartas", "Error",
					JOptionPane.ERROR_MESSAGE);
		}catch (ArrayIndexOutOfBoundsException e1) {
			JOptionPane.showMessageDialog(null, "Todavia nos seleccionado ninguna carta", "Error",
					JOptionPane.ERROR_MESSAGE);
		}

	}

	public static DefaultListModel loadDeck(String name) {
		if (name.equals("")) {
			JOptionPane.showMessageDialog(null, "No has puesto ningun nombre", "Error", JOptionPane.ERROR_MESSAGE);
		} else {
			barajaCargada = Connexio.insertMongo().getBaraja(name);
			if (barajaCargada != null) {
				DefaultListModel modelo = new DefaultListModel();
				for (Cards cards : barajaCargada.getDeck()) {
					modelo.addElement(cards.getName() + " Valor: " + cards.getValue());
				}
				barajaList.clear();
				barajaList = barajaCargada.getDeck();
				contadorValor = barajaCargada.getDeckValue();

				loadBaraja = true;
				return modelo;
			} else {
				JOptionPane.showMessageDialog(null, "No existe baraja con ese nombre", "Error baraja",
						JOptionPane.ERROR_MESSAGE);
			}

		}
		return null;
	}
	
	public static void updateBaraja() {
		Baraja baraja = new Baraja(barajaCargada.getDeckName(), contadorValor, barajaList);
		Gson gson = new Gson();
		String JSON = gson.toJson(baraja);
		Document userDoc = Document.parse(JSON);
		Connexio.insertMongo().updateBaraja(baraja.getDeckName(), userDoc);
		JOptionPane.showMessageDialog(null, "Baraja actualizada correctamente", "Insertar baraja",
				JOptionPane.INFORMATION_MESSAGE);
	}
}
