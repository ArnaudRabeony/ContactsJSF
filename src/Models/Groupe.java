package Models;

import java.util.ArrayList;

import ServiceEntities.ContactService;

public class Groupe 
{
	private int id;
	private String nom;
	private ArrayList<Contact> membres;
	
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

	public ArrayList<Contact> getMembres() 
	{
		ContactService cs = new ContactService();
		ArrayList<Contact> list = new ArrayList<Contact>();
		
		ArrayList<Membre> membres = cs.getMembres();
		
		for(Membre m : membres)
			if(m.getIdGroupe() == this.getId())
				list.add(cs.getContactById(m.getIdContact()));
		
		return list;
	}

	public void setMembres(ArrayList<Contact> contacts) {
		this.membres = contacts;
	}
	
	public ArrayList<Contact> addContact(Contact contact)
	{
		this.membres.add(contact);
		return this.membres;
	}

	public void addOwner(Contact contactOwner)
	{
		this.membres.add(contactOwner);
	}
}
