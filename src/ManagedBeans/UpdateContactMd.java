package ManagedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ServiceEntities.ContactService;

@ManagedBean
public class UpdateContactMd
{
	private int id;
	private String nom;
	private String prenom;
	private String email;
	
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
	
	public String checkUpdate()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		
		int id = this.getId();
		String nom = this.getNom();
		String prenom = this.getPrenom();
		String email = this.getEmail();
		
		ContactService cs = new ContactService();
		
		String missingFields = bundle.getString("form.missingField");
		String contactExists = bundle.getString("form.contact.mailAlreadyExists");
		
		if(id==-1 || nom.isEmpty() || prenom.isEmpty() || email.isEmpty())
			context.addMessage(null, new FacesMessage(missingFields));
		else if(cs.mailExists(email))
			context.addMessage(null, new FacesMessage(contactExists));
		
		if(context.getMessageList().size()>0)
			return null;
		else
		{
			cs.updateContact(id, nom, prenom, email);
			return "welcome";
		}
	} 
}
