package ServiceEntities;

import java.util.ArrayList;

import DAOs.MembreDAO;
import Models.Contact;

public class MembreService 
{
	MembreDAO md = new MembreDAO();
	
	public ArrayList<Integer> getGroupIdByContactId(int idContact)
	{
		return md.getGroupIdByContactId(idContact);
	}
	
	public boolean addContactToGroup(int idContact, int idGroupe)
	{
		return md.addContactToGroup(idContact, idGroupe);
	}
	
	public boolean removeContactFromGroup(int idContact, int idGroupe)
	{
		return md.removeContactFromGroup(idContact, idGroupe);
	}
	
	public ArrayList<Contact> getContactsWithoutGroup()
	{
		ArrayList<Contact> list = new ArrayList<Contact>();
		ContactService cs = new ContactService();
		for(Contact c : cs.getContacts())
			if(md.getGroupIdByContactId(c.getId()).size()==0)
				list.add(c);
		
		return list;
	}
	
	public ArrayList<Contact> getMembersByGroupId(int idGroupe)
	{
		return md.getMembersByGroupId(idGroupe);
	}
}
