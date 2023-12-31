package Vue;

import Controlleur.ControleurProduit;
import entite.Catalogue;
import entite.I_Catalogue;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreAchat extends JFrame implements ActionListener {

	I_Catalogue catalogue;

	private JButton btAchat;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	public FenetreAchat(String[] lesProduits, I_Catalogue catalogue) {
		this.catalogue = catalogue;


		setTitle("Achat");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btAchat = new JButton("Achat");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� achet�e"));
		contentPane.add(txtQuantite);
		contentPane.add(btAchat);

		btAchat.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		ControleurProduit controleurProduit = ControleurProduit.getInstance(catalogue);
//		controleurProduit.achatProduit((String) combo.getSelectedItem(), Integer.parseInt(txtQuantite.getText()));
		controleurProduit.achatProduit((String) combo.getSelectedItem(), Integer.parseInt(txtQuantite.getText()));

		this.dispose();
	}

}
