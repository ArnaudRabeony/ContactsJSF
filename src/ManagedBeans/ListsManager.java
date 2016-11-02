package ManagedBeans;

import java.util.ArrayList;

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
	private ArrayList<Contact> contacts = cs.getContacts();
	private AdresseService as = new AdresseService();
	private ArrayList<Adresse> addresses = as.getAdresses();
	private GroupeService gs = new GroupeService();
	private ArrayList<Groupe> groupes = gs.getGroups();
	private TelephoneService ts = new TelephoneService();
	private ArrayList<Telephone> phones = ts.getTelephones();
	
	public ArrayList<Contact> getContacts() {
		return contacts;
	}
	public void setContacts(ArrayList<Contact> contacts) {
		this.contacts = contacts;
	}
	public ArrayList<Adresse> getAddresses() {
		return addresses;
	}
	public void setAddresses(ArrayList<Adresse> addresses) {
		this.addresses = addresses;
	}
	public ArrayList<Groupe> getGroupes() {
		return groupes;
	}
	public void setGroupes(ArrayList<Groupe> groupes) {
		this.groupes = groupes;
	}
	public ArrayList<Telephone> getPhones() {
		return phones;
	}
	public void setPhones(ArrayList<Telephone> phones) {
		this.phones = phones;
	}
}
