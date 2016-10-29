package ServiceEntities;

import java.util.ArrayList;

import DAOs.AdresseDAO;
import Models.Adresse;

public class AdresseService {
	
	AdresseDAO ad = new AdresseDAO();

	public Adresse createAdresse(String rue, String ville, String codePostal, String pays)
	{
		return ad.createAdresse(rue, ville, codePostal, pays);
	}
	
	public int getAddressId(Adresse a)
	{
		return ad.getAddressId(a);
	}
	
	public boolean deleteAdresse(int idAdresse)
	{
		return ad.deleteAdresse(idAdresse);
	}
	
	public void updateAdresse(int idAdresse, String newRue, String newVille, String newCodePostal, String newPays)
	{
		ad.updateAdresse(idAdresse, newRue, newVille, newCodePostal, newPays);
	}
	
	public Adresse getAdresseById(int idAdresse)
	{
		return ad.getAdresseById(idAdresse);
	}

	public boolean deleteAdressesByContactId(int idContact)
	{
		return ad.deleteAdressesByContactId(idContact);
	}
	
	public ArrayList<Adresse> getAdresses()
	{
		return ad.getAdresses();
	}
	
	public boolean addressExists(int idAddress)
	{
		return ad.addressExists(idAddress);
	}

	public boolean addressExists(String rue, String ville,String codep, String pays)
	{
		return ad.addressExists(rue,ville,codep,pays);
	}
}
