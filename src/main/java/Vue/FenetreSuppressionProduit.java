package Vue;

import Controlleur.ControleurProduit;
import entite.I_Catalogue;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreSuppressionProduit extends JFrame implements ActionListener {

	I_Catalogue catalogue;
	private JButton btSupprimer;
	private JComboBox<String> combo;
	
	public FenetreSuppressionProduit(String lesProduits[], I_Catalogue catalogue) {

		this.catalogue = catalogue;


		setTitle("Suppression produit");
		setBounds(500, 500, 200, 105);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btSupprimer = new JButton("Supprimer");


		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(btSupprimer);

		btSupprimer.addActionListener(this);

		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		ControleurProduit controllerSuppr = ControleurProduit.getInstance(catalogue);
		controllerSuppr.supprimerProduit((String) combo.getSelectedItem());

		this.dispose();
	}

}
