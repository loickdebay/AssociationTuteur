package sae;
/**
 * permet de creer un etudiant de type tutore
 */
public class Tutore extends Etudiant implements Comparable<Tutore>{

	Matiere matiere;
	double moyenne;
	double poids;
	
	public Tutore(String nom, String prenom, int annee, int nbAbs, int motivation, double moyenne, Matiere matiere) {
		super(nom, prenom,annee,nbAbs,motivation,moyenne,matiere);
		this.moyenne=moyenne;
		this.poids=poidsTutore();
	}
	
	public Matiere getMatiere() {
		return matiere;
	}
	public void setMatiere(Matiere matiere) {
		this.matiere = matiere;
	}
	public double getMoyenne() {
		return moyenne;
	}
	public void setMoyenne(double moyenne) {
		this.moyenne = moyenne;
	}

	public double poidsTutore () {
		return moyenne-nbAbs+annee+motivation;
	}
	
	public void setpoids () {
		this.poids=poidsTutore();
	}
	public double getPoids() {
		return this.poids;
	}
	public int compareTo (Tutore t) {
		int res=0;
		if(this.getPoids()>t.getPoids()) {
			res= 1;
		}
		if(this.getPoids()<t.getPoids()) {
			res= -1;
		}
		if(this.getPoids()==t.getPoids()) {
			res= 0;
		}
		return res;
	}

	@Override
	public String toString() {
		return "Tutore : "+nom+" "+prenom+ " - points : "+ poids;
	}
	

}
