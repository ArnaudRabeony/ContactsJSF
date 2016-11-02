package ManagedBeans;

import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import Models.Contact;
import ServiceEntities.AdresseService;
import ServiceEntities.ContactService;
import ServiceEntities.GroupeService;
import ServiceEntities.MembreService;
import ServiceEntities.TelephoneService;

@ManagedBean
public class DeletionManagerMd 
{
	private String type;
	private int[] ids;
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int[] getIds() {
		return ids;
	}
	public void setIds(int[] ids) {
		this.ids = ids;
	}
	public String delete()
	{
//		FacesContext context = FacesContext.getCurrentInstance();
//		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		this.setType(FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("type"));
		String type = this.getType();
		int[] ids = this.getIds();
		
		switch(type)
		{
			case "contact":
					ContactService cs = new ContactService();
					for(int i=0;i<ids.length;i++)
						cs.deleteContact(ids[i]);
				break;
				
			case "address":
					AdresseService as = new AdresseService();
					for(int i=0;i<ids.length;i++)
						as.deleteAdresse(ids[i]);	
				break;
				
			case "group":
					GroupeService gs = new GroupeService();
					MembreService ms = new MembreService();		
					
					for(int i=0;i<ids.length;i++)
					{
						int idGroupe =ids[i];
						ArrayList<Contact> members = ms.getMembersByGroupId(idGroupe);
						
						for(Contact c : members)
							ms.removeContactFromGroup(c.getId(), idGroupe);
						
						gs.deleteGroup(idGroupe);
					}
				break;
				
			case "phone":
					TelephoneService ts = new TelephoneService();
					for(int i=0;i<ids.length;i++)
						ts.deleteTelephone(ids[i]);
				break;
				
			default:				
				break;
		}

		return "welcome";
	}
	
}
