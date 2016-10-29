package ManagedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

@ManagedBean
public class LoginMd 
{
	private String nom;
	private String password;
	
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String checkLogin()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		String nom = this.getNom();
		String password = this.getPassword();
		String missingFields = bundle.getString("form.missingField");
		String failure = bundle.getString("form.login.failed");
				
		if(nom.isEmpty() || password.isEmpty())
			context.addMessage(null, new FacesMessage(missingFields));
		else if(!nom.equals(password))
			context.addMessage(null, new FacesMessage(failure));
		
		return context.getMessageList().size()>0 ? null : "welcome";
	}
}
