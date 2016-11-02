package ManagedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ServiceEntities.ContactService;

@ManagedBean
public class CreateContactMd 
{
	private String nom;
	private String prenom;
	private String email;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String checkCreation()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		String nom = this.getNom();
		String prenom = this.getPrenom();
		String email = this.getEmail();
		
		String missingFields = bundle.getString("form.missingField");
		String contactExists = bundle.getString("form.contact.alreadyExists");
		
		ContactService cs = new ContactService();
		
		if(nom.isEmpty() || prenom.isEmpty() || email.isEmpty())
			context.addMessage(null, new FacesMessage(missingFields));
		else if(cs.contactExists(nom, prenom))
			context.addMessage(null, new FacesMessage(contactExists));
		
		if(context.getMessageList().size()>0)
			return null;
		else
		{
			cs.createContact(nom, prenom, email);
			return "welcome";
		}
	}
}
