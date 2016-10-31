package ManagedBeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;

import Models.Contact;
import ServiceEntities.ContactService;

@ManagedBean
public class ContactManagerMd 
{
	private ContactService cs = new ContactService();
	private ArrayList<Contact> liste = cs.getContacts();

	public ArrayList<Contact> getListe() {
		return liste;
	}

	public void setListe(ArrayList<Contact> liste) {
		this.liste = liste;
	}
}
