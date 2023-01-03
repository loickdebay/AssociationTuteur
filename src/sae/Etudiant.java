package sae;
/**
 * Classe gerant la creation d'un etudiant
 */
public class Etudiant {
	String nom;
	String prenom;
	int annee;
	int nbAbs;
	int motivation;
	double moyenne;
	double poids;
	Matiere matiere;
	
	/**
	 * constructeur completement defini
	 * @param nom nom de l'etudiant
	 * @param prenom prenom de l'etudiant
	 * @param annee annee d'etude de l'etudiant de 1 a 3
	 * @param nbAbs nombre d'abscences de l'etudiant
	 * @param motivation motivation de l'etudiant sur une echelle de 1 a 10
	 * @param moyenne moyenne de l'etudiant
	 * @param matiere matiere du tutorat de l'etudiant
	 */
	public Etudiant(String nom, String prenom, int annee, int nbAbs, int motivation, double moyenne, Matiere matiere) {
		this.nom = nom;
		this.prenom = prenom;
		this.annee = annee;
		this.nbAbs = nbAbs;
		this.moyenne=moyenne;
		this.motivation = motivation;
		this.matiere=matiere;
		this.poids=poidsEtudiant();
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public int getAnnee() {
		return annee;
	}
	public void setAnnee(int annee) {
		this.annee = annee;
	}
	public int getNbAbs() {
		return nbAbs;
	}
	public void setNbAbs(int nbAbs) {
		this.nbAbs = nbAbs;
	}
	public int getMotivation() {
		return motivation;
	}
	public void setMotivation(int motivation) {
		this.motivation = motivation;
	}
	public double getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}
	public double getPoids() {
		return poids;
	}
	public double poidsEtudiant () {
		return 20-moyenne+nbAbs+(3-annee);	
	}
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	
	@Override
	public String toString() {
		return prenom + " " + nom;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + annee;
		result = prime * result + ((matiere == null) ? 0 : matiere.hashCode());
		result = prime * result + motivation;
		long temp;
		temp = Double.doubleToLongBits(moyenne);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + nbAbs;
		result = prime * result + ((nom == null) ? 0 : nom.hashCode());
		temp = Double.doubleToLongBits(poids);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((prenom == null) ? 0 : prenom.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Etudiant other = (Etudiant) obj;
		if (annee != other.annee)
			return false;
		if (matiere != other.matiere)
			return false;
		if (motivation != other.motivation)
			return false;
		if (Double.doubleToLongBits(moyenne) != Double.doubleToLongBits(other.moyenne))
			return false;
		if (nbAbs != other.nbAbs)
			return false;
		if (nom == null) {
			if (other.nom != null)
				return false;
		} else if (!nom.equals(other.nom))
			return false;
		if (Double.doubleToLongBits(poids) != Double.doubleToLongBits(other.poids))
			return false;
		if (prenom == null) {
			if (other.prenom != null)
				return false;
		} else if (!prenom.equals(other.prenom))
			return false;
		return true;
	}
	
	
}
