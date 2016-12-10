package ManagedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import Models.Contact;
import ServiceEntities.ContactService;
import ServiceEntities.GroupeService;
import ServiceEntities.MembreService;

@ManagedBean
public class UpdateGroupMd {

	private int id;
	private String nom;
	private String[] ids;
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
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	public String checkUpdate()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		String nom = this.getNom();
		String[] ids = this.getIds();
		int id = this.getId();
		
		String missingFields = bundle.getString("form.missingField");
		String groupExists = bundle.getString("form.group.alreadyExists");
		
		GroupeService gs = new GroupeService();
		
		if(id==-1 || nom.isEmpty())
			context.addMessage(null, new FacesMessage(missingFields));
		else if(gs.groupExists(nom))
			context.addMessage(null, new FacesMessage(groupExists));
		
		if(context.getMessageList().size()>0)
			return null;
		else
		{
			gs.updateGroupe(id, nom);
			
			ContactService cs = new ContactService();
			MembreService ms = new MembreService();
			
			if(ids!=null)
			{
				for(Contact c : cs.getContacts())
					if(ms.getGroupIdByContactId(c.getId()).contains(id))
						ms.removeContactFromGroup(c.getId(), id);
				
				for(String newContactId : ids)
				{
					Contact c = cs.getContactById(Integer.valueOf(newContactId));
					ms.addContactToGroup(c.getId(), id);
				}
			}
			else
			{
				for(Contact c : cs.getContacts())
					if(ms.getGroupIdByContactId(c.getId()).contains(id))
						ms.removeContactFromGroup(c.getId(), id);
			}
			return "welcome";
		}
	}
}
