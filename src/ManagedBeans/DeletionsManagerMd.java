package ManagedBeans;

import java.util.ArrayList;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;

import Models.Contact;
import ServiceEntities.AdresseService;
import ServiceEntities.ContactService;
import ServiceEntities.GroupeService;
import ServiceEntities.MembreService;
import ServiceEntities.TelephoneService;

@ManagedBean
public class DeletionsManagerMd 
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
	
//	public String delete(AjaxBehaviorEvent e)
	
	public String delete()
	{
//		FacesContext context = FacesContext.getCurrentInstance();
//		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		this.setType(FacesContext.getCurrentInstance().
				getExternalContext().getRequestParameterMap().get("type"));
		
		System.out.println("Suppression");
		int[] ids = this.getIds();
		Map<String,String> params = null;
		
		if(FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get("from") == "welcomePage")
			params = FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();		

		if(params != null)
			this.setType("welcomePage"+params.get("type"));
//			String type = params.get("type")!=null ? "welcomePage"+params.get("type") : this.getType();
//		this.setType(type);
		int singleId = -1;
		
		System.out.println(type);
		if(type.equals("welcomePagecontact"))
			singleId = params.get("id")!=null ? Integer.valueOf(params.get("id")) : singleId;
		
		System.out.println(singleId);

		ContactService cs = new ContactService();

		switch(type)
		{
			case "welcomePagecontact":
				System.out.println("Single");
					cs.deleteContact(singleId);
			break;
			
			case "contact":
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
