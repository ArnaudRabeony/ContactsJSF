package Models;

import java.util.ArrayList;

public class Contact {

	private int id;
	private String nom;
	private String prenom;
	private String email;
	
	private ArrayList<Telephone> profiles;
	private ArrayList<Groupe> groupesListe;
	private int idAdresse;
	
	public Contact(int id, String nom, String prenom, String email) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}

	public Contact(String nom, String prenom, String email)
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
	}
	
	public Contact(String nom, String prenom, String email, int idAdresse)
	{
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.idAdresse = idAdresse;
	}

	public Contact(int id, String nom, String prenom, String email, int idAdresse)
	{
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.idAdresse = idAdresse;
	}

	public int getIdAdresse() {
		return idAdresse;
	}

	public void setIdAdresse(int idAdresse) {
		this.idAdresse = idAdresse;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public ArrayList<Telephone> getProfiles() {
		return profiles;
	}

	public void setProfiles(ArrayList<Telephone> telephonesListe) {
		this.profiles = telephonesListe;
	}
	
	public ArrayList<Groupe> getGroupesListe() {
		return groupesListe;
	}

	public void setGroupesListe(ArrayList<Groupe> groupesListe) {
		this.groupesListe = groupesListe;
	}

	public void addTelephone(Telephone t)
	{
		this.profiles.add(t);
	}
	
	public void addGroupe(Groupe g)
	{
		this.groupesListe.add(g);
	}
}
