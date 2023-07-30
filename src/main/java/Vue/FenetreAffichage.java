package Vue;

import Controlleur.ControleurProduit;
import entite.Catalogue;
import entite.I_Catalogue;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.*;

public class FenetreAffichage extends JFrame implements ActionListener {

	private JButton btOK;
	
	public FenetreAffichage(I_Catalogue catalogue) throws SQLException {

		setTitle("Affichage");
		setBounds(500, 500, 450, 250);
		JPanel panHaut = new JPanel();
		JPanel panBas = new JPanel();
		panHaut.setLayout(new BorderLayout());
		panBas.setLayout(new FlowLayout());

		ControleurProduit controleurProduit = ControleurProduit.getInstance(catalogue);
		controleurProduit.chargerCatalogue(catalogue);
		System.out.println("Le Catalogue lu est le suivant : " + catalogue.getNomCatalogue());


//		JTextArea jtaSortie = new JTextArea(controleurProduit.getAllProduits(),10,5);
		JTextArea jtaSortie = new JTextArea(catalogue.toString(),10,5);

		btOK = new JButton("Quitter");
		
		
		Container contentPane = getContentPane();
		contentPane.setLayout(new BorderLayout());
		
		panHaut.add(jtaSortie);
		panBas.add(btOK);

		contentPane.add(panHaut,"North");
		contentPane.add(panBas, "South");
		btOK.addActionListener(this);

		this.setVisible(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	 */
	public void actionPerformed(ActionEvent e) {
		this.dispose();
	}

}
