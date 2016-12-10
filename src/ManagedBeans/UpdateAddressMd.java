package ManagedBeans;

import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import ServiceEntities.AdresseService;

@ManagedBean
public class UpdateAddressMd 
{
	private int id;
	private String num;
	private String street;
	private String city;
	private String code;
	private String country;
	
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
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	
	public String checkUpdate()
	{
		FacesContext context = FacesContext.getCurrentInstance();
		ResourceBundle bundle = context.getApplication().getResourceBundle(context, "msg");
		
		int id = this.getId();
		
		String num = this.getNum();
		String street = this.getStreet();
		String city = this.getCity();
		String code = this.getCode();
		String country = this.getCountry();
		
		String missingFields = bundle.getString("form.missingField");
		String addressExists = bundle.getString("form.address.alreadyExists");
		
		AdresseService as = new AdresseService();
		
		if(id==-1 || num.isEmpty() || street.isEmpty() || city.isEmpty() || code.isEmpty() || country.isEmpty())
			context.addMessage(null, new FacesMessage(missingFields));
		else if(as.addressExists(num+" "+street, city, code, country))
			context.addMessage(null, new FacesMessage(addressExists));
		
		if(context.getMessageList().size()>0)
			return null;
		else
		{
			as.updateAdresse(id,num+" "+street, city, code, country);
			return "welcome";
		}
	}
}
