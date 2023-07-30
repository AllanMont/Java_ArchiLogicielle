package Vue;


import Controlleur.ControleurProduit;
import entite.I_Catalogue;


import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class FenetreVente extends JFrame implements ActionListener {

	private JButton btVente;
	private JTextField txtQuantite;
	private JComboBox<String> combo;

	I_Catalogue catalogue;

	public FenetreVente(String[] lesProduits, I_Catalogue catalogue) {
		this.catalogue = catalogue;

		setTitle("Vente");
		setBounds(500, 500, 200, 125);
		Container contentPane = getContentPane();
		contentPane.setLayout(new FlowLayout());
		btVente = new JButton("Vente");
		txtQuantite = new JTextField(5);
		txtQuantite.setText("0");

		combo = new JComboBox<String>(lesProduits);
		combo.setPreferredSize(new Dimension(100, 20));
		contentPane.add(new JLabel("Produit"));
		contentPane.add(combo);
		contentPane.add(new JLabel("Quantit� vendue"));
		contentPane.add(txtQuantite);
		contentPane.add(btVente);

		btVente.addActionListener(this);
		this.setVisible(true);
	}

	public void actionPerformed(ActionEvent e) {
		ControleurProduit controleurProduit = ControleurProduit.getInstance(catalogue);
		controleurProduit.venteProduit((String) combo.getSelectedItem(), Integer.parseInt(txtQuantite.getText()));



		this.dispose();
	}

}
