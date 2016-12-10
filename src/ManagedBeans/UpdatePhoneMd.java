package ManagedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ServiceEntities.TelephoneService;

@ManagedBean
public class UpdatePhoneMd 
{
	private int id;
	private String num;
	private String type;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
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
	
	public String checkUpdate()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		
		int id = this.getId();
		String num = this.getNum();
		String type = this.getType();
		
		String missingFields = bundle.getString("form.missingField");
		String phoneExists = bundle.getString("form.telephone.alreadyExists");
		
		TelephoneService ts = new TelephoneService();
		
		if(id==-1 || num.isEmpty() || type.isEmpty())
			context.addMessage(null, new FacesMessage(missingFields));
		else if(ts.telephoneExists(type, num))
			context.addMessage(null, new FacesMessage(phoneExists));
		
		if(context.getMessageList().size()>0)
			return null;
		else
		{
			ts.updateNumero(id, type, num);
			return "welcome";
		}
	}
}
