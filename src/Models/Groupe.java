package Models;

import java.util.ArrayList;

public class Groupe 
{
	private int id;
	private String nom;
	private ArrayList<Contact> contacts;
	
	public Groupe(String nom) {
		super();
		this.nom = nom;
	}
	
	public Groupe(int id, String nom) {
		super();
		this.id = id;
		this.nom = nom;
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

	public ArrayList<Contact> getContacts() {
		return contacts;
	}

	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	
	public ArrayList<Contact> addContact(Contact contact)
	{
		this.contacts.add(contact);
		return this.contacts;
	}

	public void addOwner(Contact contactOwner)
	{
		this.contacts.add(contactOwner);
	}
}
