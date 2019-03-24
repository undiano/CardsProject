package main.View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.components.UtilsCards;
import main.model.Cards;

import javax.swing.JList;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.JLabel;

public class VentanaCartas extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private static JList list;
	private static JList list_1;
	public static JLabel numero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaCartas frame = new VentanaCartas();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaCartas() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 641, 417);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 625, 378);
		contentPane.add(panel);
		panel.setLayout(null);

		list = new JList();
		list.setBounds(54, 49, 160, 230);
		panel.add(list);

		list_1 = new JList();
		list_1.setBounds(300, 49, 160, 230);
		panel.add(list_1);

		JButton LoadCards = new JButton("Load Cards");
		LoadCards.setBackground(Color.CYAN);
		LoadCards.setFont(new Font("Broadway", Font.PLAIN, 15));
		LoadCards.setBounds(54, 15, 160, 23);
		panel.add(LoadCards);
		LoadCards.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				list.setModel(UtilsCards.obtenerCartas());
				
			}
		});

		JButton rndDeck = new JButton("Rnd Deck");
		rndDeck.setBackground(Color.CYAN);
		rndDeck.setFont(new Font("Broadway", Font.PLAIN, 15));
		rndDeck.setBounds(300, 15, 160, 23);
		panel.add(rndDeck);
		rndDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilsCards.randomDeck(list, list_1, numero);
			}
		});

		JButton moverDercha = new JButton("-->");
		moverDercha.setBackground(Color.CYAN);
		moverDercha.setBounds(231, 132, 59, 23);
		panel.add(moverDercha);
		moverDercha.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilsCards.moverDerecha(list, list_1);
			}
		});

		JButton moverIzquierda = new JButton("<--");
		moverIzquierda.setBackground(Color.CYAN);
		moverIzquierda.setBounds(231, 179, 59, 23);
		panel.add(moverIzquierda);
		moverIzquierda.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilsCards.moverIzquierda(list, list_1);
			}
		});

		JButton saveDeck = new JButton("Save Deck");
		saveDeck.setBackground(Color.CYAN);
		saveDeck.setFont(new Font("Broadway", Font.PLAIN, 15));
		saveDeck.setBounds(300, 291, 160, 23);
		panel.add(saveDeck);
		saveDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				UtilsCards.guardarOActualizar();

			}
		});

		textField = new JTextField();
		textField.setBounds(489, 110, 126, 20);
		panel.add(textField);
		textField.setColumns(10);

		JButton loadDeck = new JButton("Load Deck");
		loadDeck.setBackground(Color.CYAN);
		loadDeck.setFont(new Font("Broadway", Font.PLAIN, 15));
		loadDeck.setBounds(489, 150, 126, 23);
		panel.add(loadDeck);

		JLabel lblValorBaraja = new JLabel("Valor Baraja:");
		lblValorBaraja.setBounds(489, 20, 86, 14);
		panel.add(lblValorBaraja);

		numero = new JLabel("0");
		numero.setBounds(585, 20, 16, 14);
		panel.add(numero);

		loadDeck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					list_1.setModel(UtilsCards.loadDeck(textField.getText()));
					numero.setText(Integer.toString(UtilsCards.contadorValor));
					textField.setText("");
				} catch (Exception e1) {
					// TODO: handle exception
				}

			}
		});
	}
}
