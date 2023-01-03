package sae;

import java.util.ArrayList;
import java.util.Collections;
/**
 * Classe de gestion des listes d'etudiants, tuteurs, et tutores
 */
public class Etablissement {
	private ArrayList<Tuteur> tuteur = new ArrayList<Tuteur>();
	private ArrayList<Tutore> tutore =  new ArrayList<Tutore>();
	private ArrayList<Etudiant> etudiants = new ArrayList<Etudiant>();
	public Etablissement() {
	}
	/**
	 * ajoute un tuteur à la liste des tuteurs 
	 * @param t Tuteur a ajouter
	 */
	public void addTuteur(Tuteur t) {
		if(!tuteur.contains(t)) {
			tuteur.add(t);
		}
	}
	/**
	 * ajoute un Etudiant à la liste des tuteurs 
	 * @param e Etudiant a ajouter
	 */
	public void addTuteur(Etudiant e) {
		tuteur.add(new Tuteur(e.getNom(),e.getPrenom(),e.getAnnee(),e.getNbAbs(),e.getMotivation(),e.getMoyenne(),e.getMatiere()));
	}
	/**
	 * ajoute un tutore à la liste des tutores 
	 * @param t Tutore a ajouter
	 */
	public void addTutore(Tutore t) {
		if(!tutore.contains(t)) {
			tutore.add(t);
		}
	}
	/**
	 * ajoute un Etudiant à la liste des tutore
	 * @param e Etudiant a ajouter
	 */
	public void addTutore(Etudiant e) {
		tutore.add(new Tutore(e.getNom(),e.getPrenom(),e.getAnnee(),e.getNbAbs(),e.getMotivation(),e.getMoyenne(),e.getMatiere()));
	}
	/**
	 * ajoute un Etudiant a la liste des Etudiants
	 * @param e
	 */
	public void addEtudiant(Etudiant e) {
		if(!etudiants.contains(e)) {
			etudiants.add(e);
		}
	}
	/**
	 * retire un tuteur de la liste des tuteurs
	 * @param t
	 */
	public void removeTuteur(Tuteur t) {
		if(tuteur.contains(t)) {
			tuteur.remove(t);
		}
	}
	/**
	 * retire un tutore de la liste des tutore
	 * @param t
	 */
	public void removeTutore(Tutore t) {
		if(tutore.contains(t)) {
			tutore.remove(t);
		}
	}
	/**
	 * retire un etudiant de la liste des etudiants
	 * @param e
	 */
	public void removeEtudiant(Etudiant e) {
		if(etudiants.contains(e)) {
			etudiants.remove(e);
		}
	}
	/**
	 * retourne la liste des tuteurs
	 * @return la liste des tuteurs
	 */
	public ArrayList<Tuteur> getTuteur() {
		return tuteur;
	}
	/**
	 * definit la liste des tuteurs
	 * @param tuteur liste definie
	 */
	public void setTuteur(ArrayList<Tuteur> tuteur) {
		this.tuteur = tuteur;
	}
	/**
	 * retourne la liste des tutore
	 * @return liste des tutores
	 */
	public ArrayList<Tutore> getTutore() {
		return tutore;
	}
	/**
	 * definit la liste des tutores
	 * @param tutore la liste definie
	 */
	public void setTutore(ArrayList<Tutore> tutore) {
		this.tutore = tutore;
	}
	/**
	 * retourne la liste des etudiants
	 * @return la liste des etudiants
	 */
	public ArrayList<Etudiant> getEtudiants() {
		return etudiants;
	}
	/**
	 * definit la liste des etudiants
	 * @param etudiants la liste definie
	 */
	public void setEtudiants(ArrayList<Etudiant> etudiants) {
		this.etudiants = etudiants;
	}
	/**
	 * permet de trier la liste des tuteurs par leur poids
	 */
	public void triTuteur() {
		Collections.sort(tuteur);
	}
	/**
	 * permet de trier la liste des tutores par leur poids
	 */
	public void triTutore() {
		Collections.sort(tutore);
	}
	/**
	 * restrinct la liste des etudiants aux etudiants concernes pas une seule matiere
	 * @param matiere la matiere demandee
	 */
	public void etuParMatiere(Matiere matiere){
		for(int i=0;i<etudiants.size();) {
			if(!this.etudiants.get(i).matiere.equals(matiere)) {
				removeEtudiant(this.etudiants.get(i));
			}else i++;
		}
	}
	/**
	 * divise la liste des etudiants en deux listes de tuteurs et de tutores
	 */
	public void split() {
		for(Etudiant e : etudiants) {
			if(e instanceof Tuteur) {
				tuteur.add((Tuteur) e);
			}
			if(e instanceof Tutore) {
				tutore.add((Tutore) e);
			}
		}
	}
	/**
	 * equilibre le nombre d'etudiants dans chaque liste pour permettre une affectation, en retirant les meilleurs etudiants ou les moins bons tuteurs
	 */
	public void balanceLists () {
		int sizetuto=tutore.size();
		int sizetute=tuteur.size();
		
		if(sizetuto<sizetute) {
			Collections.sort(tuteur);
        	for(int i =0; i<sizetute-sizetuto;i++) {
        		tuteur.remove(i);
        	}
		}
		if(sizetute<sizetuto) {
			Collections.sort(tuteur);
        	for(int i =0; i<sizetuto-sizetute;i++) {
        		tutore.remove(i);
        	}
		}
	}
	
	public void sortEtudiants (ArrayList<Etudiant> listejson) {
		for(int i=0;i<listejson.size();i++) {
			if(listejson.get(i).annee==1) {
				Tutore tutor = new Tutore(listejson.get(i).nom,
						listejson.get(i).prenom,
						listejson.get(i).annee,
						listejson.get(i).nbAbs,
						listejson.get(i).motivation,
						listejson.get(i).moyenne,
						listejson.get(i).matiere);
				this.addEtudiant(tutor);
			}else{
				Tuteur tuteur = new Tuteur(listejson.get(i).nom,
						listejson.get(i).prenom,
						listejson.get(i).annee,
						listejson.get(i).nbAbs,
						listejson.get(i).motivation,
						listejson.get(i).moyenne,
						listejson.get(i).matiere);
				this.addEtudiant(tuteur);
			}
		}
		
	}
	
	
}
