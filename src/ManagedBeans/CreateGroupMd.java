package ManagedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import Models.Groupe;
import ServiceEntities.ContactService;
import ServiceEntities.GroupeService;
import ServiceEntities.MembreService;

@ManagedBean
public class CreateGroupMd 
{
	private String nom;
	private String reponse = "no";
	private String[] ids;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getReponse() {
		return reponse;
	}
	public void setReponse(String reponse) {
		this.reponse = reponse;
	}
	public String[] getIds() {
		return ids;
	}
	public void setIds(String[] ids) {
		this.ids = ids;
	}
	
	public String checkCreation()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		String nom = this.getNom();
		String[] ids = this.getIds();
		
		String missingFields = bundle.getString("form.missingField");
		String groupExists = bundle.getString("form.group.alreadyExists");
		
		GroupeService gs = new GroupeService();
		
		if(nom.isEmpty())
			context.addMessage(null, new FacesMessage(missingFields));
		else if(gs.groupExists(nom))
			context.addMessage(null, new FacesMessage(groupExists));
		
		if(context.getMessageList().size()>0)
			return null;
		else
		{
			Groupe g = gs.createGroupe(nom);

			MembreService ms = new MembreService();

			if(ids!=null && ids.length!=0)
			{
				int idGroupe = gs.getGroupIdByName(g.getNom());
				
				for(int i=0;i<ids.length;i++)
					ms.addContactToGroup(Integer.valueOf(ids[i]), idGroupe);
			}

			return "welcome";
		}
	}
}
