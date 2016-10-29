package Models;

public class Membre 
{
	private int idGroupe;
	private int idContact;
	
	public Membre(int idGroupe, int idContact) {
		super();
		this.idGroupe = idGroupe;
		this.idContact = idContact;
	}
	
	public int getIdGroupe() {
		return idGroupe;
	}
	public void setIdGroupe(int idGroupe) {
		this.idGroupe = idGroupe;
	}
	public int getIdContact() {
		return idContact;
	}
	public void setIdContact(int idContact) {
		this.idContact = idContact;
	}
}
