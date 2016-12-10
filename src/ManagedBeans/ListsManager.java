package ManagedBeans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.faces.bean.ManagedBean;

import Models.Adresse;
import Models.Contact;
import Models.Groupe;
import Models.Telephone;
import ServiceEntities.AdresseService;
import ServiceEntities.ContactService;
import ServiceEntities.GroupeService;
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
}
