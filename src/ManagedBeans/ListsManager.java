package ManagedBeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import Models.Adresse;
import Models.Contact;
import Models.Groupe;
import Models.Membre;
import Models.Telephone;
import ServiceEntities.AdresseService;
import ServiceEntities.ContactService;
import ServiceEntities.GroupeService;
import ServiceEntities.MembreService;
import ServiceEntities.TelephoneService;

@ManagedBean
public class ListsManager 
{
	private ContactService cs = new ContactService();
	private ArrayList<Contact> contacts;
	private AdresseService as = new AdresseService();
	private ArrayList<Adresse> addresses ;
	private GroupeService gs = new GroupeService();
	private ArrayList<Groupe> groupes;
	private TelephoneService ts = new TelephoneService();
	private ArrayList<Telephone> phones;
	private MembreService ms = new MembreService();
	private ArrayList<Contact> sansGroupe;
	
	public ArrayList<Contact> getContacts() {
		this.setContacts(cs.getContacts());
		return contacts;
	}
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	public ArrayList<Adresse> getAddresses() {
		this.setAddresses(as.getAdresses());
		return addresses;
	}
	public void setAddresses(ArrayList<Adresse> addresses) {
		this.addresses = addresses;
	}
	public ArrayList<Groupe> getGroupes() {
		this.setGroupes(gs.getGroups());
		return groupes;
	}
	public void setGroupes(ArrayList<Groupe> groupes) {
		this.groupes = groupes;
	}
	public ArrayList<Telephone> getPhones() {
		this.setPhones(ts.getTelephones());
		return phones;
	}
	public void setPhones(ArrayList<Telephone> phones) {
		this.phones = phones;
	}
	public ArrayList<Contact> getSansGroupe() {
		this.setSansGroupe(ms.getContactsWithoutGroup());
		return sansGroupe;
	}
	public void setSansGroupe(ArrayList<Contact> membres) {
		this.sansGroupe = membres;
	}
}
