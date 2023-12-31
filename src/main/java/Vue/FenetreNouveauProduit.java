package Vue;


import Controlleur.ControleurProduit;

import entite.Catalogue;
import entite.I_Catalogue;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreNouveauProduit extends JFrame implements ActionListener {

	I_Catalogue catalogue;
	private JTextField txtPrixHT;
	private JTextField txtNom;
	private JTextField txtQte;
//	private JComboBox<String> combo;
	private JButton btValider;


//	public FenetreNouveauProduit(String[] lesCategories) {

	public FenetreNouveauProduit(String[] lesProduits, I_Catalogue catalogue) {
		this.catalogue = catalogue;

		setTitle("Creation Produit");
		setBounds(500, 500, 200, 250);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());

		JLabel labNom = new JLabel("Nom produit");
		JLabel labPrixHT = new JLabel("Prix Hors Taxe");
		JLabel labQte = new JLabel("Quantit� en stock");
//		JLabel labCategorie = new JLabel("Categorie");
		contentPane.add(labNom);
		txtNom = new JTextField(15);
		contentPane.add(txtNom);
		contentPane.add(labPrixHT);
		txtPrixHT = new JTextField(15);
		contentPane.add(txtPrixHT);
		contentPane.add(labQte);
		txtQte = new JTextField(15);
		contentPane.add(txtQte);

//		combo = new JComboBox<String>(lesCategories);
//		combo.setPreferredSize(new Dimension(100, 20));
//		contentPane.add(labCategorie);
//		contentPane.add(combo);

		
		btValider = new JButton("Valider");
		contentPane.add(btValider);

		btValider.addActionListener(this);
		setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		System.out.println("Ajout du produit ...");
		catalogue.addProduit(txtNom.getText(),Double.parseDouble(txtPrixHT.getText()),Integer.parseInt(txtQte.getText()));
		System.out.println(catalogue);

		ControleurProduit controleurProduit = ControleurProduit.getInstance(catalogue);
		controleurProduit.ajouterProduit(txtNom.getText(), Float.parseFloat(txtPrixHT.getText()), Integer.parseInt(txtQte.getText()));

		this.dispose();
	}

}
