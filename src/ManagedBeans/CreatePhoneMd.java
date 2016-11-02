package ManagedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ServiceEntities.TelephoneService;

@ManagedBean
public class CreatePhoneMd 
{
	private String num;
	private String type;
	private int contactId;
	
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
		this.num = num;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getContactId() {
		return contactId;
	}
	public void setContactId(int contactId) {
		this.contactId = contactId;
	}
	
	public String checkCreation()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		String num = this.getNum();
		String type = this.getType();
		int contactId = this.getContactId();
		
		String missingFields = bundle.getString("form.missingField");
		String phoneExists = bundle.getString("form.telephone.alreadyExists");
		
		TelephoneService ts = new TelephoneService();
		
		if(num.isEmpty() || type.isEmpty() || contactId==0)
			context.addMessage(null, new FacesMessage(missingFields));
		else if(ts.telephoneExists(type, num))
			context.addMessage(null, new FacesMessage(phoneExists));
		
		if(context.getMessageList().size()>0)
			return null;
		else
		{
			ts.createTelephone(type, num, contactId);
			return "welcome";
		}
	}
}
