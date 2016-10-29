package ServiceEntities;

import java.util.ArrayList;

import DAOs.TelephoneDAO;
import Models.Contact;
import Models.Groupe;
import Models.Telephone;

public class TelephoneService 
{
	TelephoneDAO td = new TelephoneDAO();
	
	public Telephone createTelephone(String type, String numero, int idContact)
	{
		return td.createTelephone(type, numero, idContact);
	}
	
	public void deleteTelephone(int idTelephone)
	{
		td.deleteTelephone(idTelephone);
	}
	
	public void updateNumero(int idTelephone, String newType, String newNumero)
	{
		td.updateNumero(idTelephone, newType, newNumero);
	}
	
	public ArrayList<Telephone> getTelephonesByContactId(int idContact)
	{
		return td.getTelephonesByContactId(idContact);
	}
	
	public Telephone getTelephoneById(int id)
	{
		return td.getNumberById(id);
	}
	
	public int getIdByNumber(String numero)
	{
		return td.getIdByNumber(numero);
	}

	public boolean deleteTelephoneByContactId(int idContact) {
		return td.deleteTelephoneByContactId(idContact);
	}
	
	public ArrayList<Telephone> getTelephones()
	{
		return td.getTelephones();
	}

	public boolean telephoneExists(int idTelephone) {
		return td.telephoneExists(idTelephone);
	}

	public boolean telephoneExists(String type, String numero) {
		return td.telephoneExists(type,numero);
	}
}
