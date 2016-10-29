package ManagedBeans;

import java.io.Serializable;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import javax.faces.application.Application;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;

@ManagedBean(name="language")
@SessionScoped
public class LanguageBean implements Serializable{

	private static final long serialVersionUID = 1L;

	private String localeCode = "fr";

	private static Map<String,Object> countries;
	static{
		countries = new LinkedHashMap<String,Object>();
		
		Application application = FacesContext.getCurrentInstance().getApplication();
		Iterator<Locale> supportedLocales = application.getSupportedLocales();
		
		while (supportedLocales.hasNext()) 
		{
			Locale loc = supportedLocales.next();
			countries.put(loc.getLanguage(),loc.getLanguage());
		}
	}

	public Map<String, Object> getCountriesInMap() {
		return countries;
	}


	public String getLocaleCode() {
		return localeCode;
	}


	public void setLocaleCode(String localeCode) {
		this.localeCode = localeCode;
	}

	//value change event listener
	public void countryLocaleCodeChanged(ValueChangeEvent e){
		String newLocaleValue = e.getNewValue().toString();
		FacesContext.getCurrentInstance().getViewRoot().setLocale(new Locale(newLocaleValue));
	}

}