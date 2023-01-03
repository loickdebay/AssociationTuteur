package sae;

import java.util.ArrayList;

import fr.ulille.but.sae2_02.graphes.GrapheNonOrienteValue;

public class Graphe {
	GrapheNonOrienteValue<String> graphe;
	
	public Graphe() {
		this.graphe = new GrapheNonOrienteValue<String>();
	}
	
	public void ajouterSommets(ArrayList<Tuteur> tuteurs, ArrayList<Tutore> tutore) {
		for(Tuteur t : tuteurs) {
			graphe.ajouterSommet(t.getNom());
		}
		for(Tutore t : tutore) {
			graphe.ajouterSommet(t.getNom());
		}
	}
	public void ajouterAretes(ArrayList<Tuteur> tuteurs, ArrayList<Tutore> tutore) {
		for(Tuteur t : tuteurs) {
    		for(Tutore ts : tutore) {
    			graphe.ajouterArete(t.getNom(),ts.getNom(),t.getPoids()+ts.getPoids());
    		}
    	}
	}

	public GrapheNonOrienteValue<String> getGraphe() {
		return graphe;
	}

	public void setGraphe(GrapheNonOrienteValue<String> etus) {
		this.graphe = etus;
	}
	
	
}







