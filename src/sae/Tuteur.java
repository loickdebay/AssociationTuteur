package sae;

public class Tuteur extends Etudiant implements Comparable<Tuteur>{
	Matiere matiere;
	double moyenne;
	double poids;
	
	public Tuteur(String nom, String prenom, int annee, int nbAbs, int motivation, double moyenne, Matiere matiere) {
		super(nom, prenom,annee,nbAbs,motivation,moyenne,matiere);
		this.moyenne=moyenne;
		this.poids=poidsTuteur();
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
	public double poidsTuteur () {
		return moyenne-nbAbs+annee+motivation;
	}
	public void setpoids () {
		this.poids=poidsTuteur();
	}
	public double getPoids() {
		return this.poids;
	}
	public int compareTo (Tuteur t) {
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
		return "Tuteur : "+nom+" "+prenom+ " - points : "+ poids;
	}
}
