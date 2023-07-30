package entite;

import java.text.DecimalFormat;

public class Produit implements I_Produit{
    private int quantiteStock;
    private String nom;
    private double prixUnitaireHT;
    static double tauxTVA = 1.2;

    public Produit(String nom, double prixUniaireHT, int qte) {
        this.nom = nom;
        this.prixUnitaireHT = prixUniaireHT;
        this.quantiteStock = qte;
    }

    @Override
    public boolean ajouter(int qteAchetee) {
        if(qteAchetee<=0){
            return false;
        }
        this.quantiteStock+=qteAchetee;
        return true;
    }

    @Override
    public boolean enlever(int qteVendue) {
        if(qteVendue<=0){
            return false;
        }
        this.quantiteStock-=qteVendue;
        return true;
    }

    @Override
    public String getNom() {
        return this.nom;
    }

    @Override
    public int getQuantite() {
        return this.quantiteStock;
    }

    @Override
    public double getPrixUnitaireHT() {
        return this.prixUnitaireHT;
    }
 
    @Override
    public double getPrixUnitaireTTC() {
        return this.getPrixUnitaireHT()*this.tauxTVA;
    }

    @Override
    public double getPrixStockTTC() {
        return this.getPrixUnitaireTTC()*this.quantiteStock;
    }

    @Override
    public String toString() {
        DecimalFormat df = new DecimalFormat("0.00");

        String nomProduit = getNom().replaceAll("\t", "");

        return nomProduit.trim() + " - prix HT : " + df.format(getPrixUnitaireHT()) + " € - prix TTC : " + df.format(getPrixUnitaireTTC()) + " € - quantité en stock : " + getQuantite() + "\n";
    }
}
