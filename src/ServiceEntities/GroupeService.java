package ServiceEntities;

import java.util.ArrayList;

import DAOs.GroupeDAO;
import Models.Contact;
import Models.Groupe;

public class GroupeService {

	GroupeDAO gd = new GroupeDAO();
	
	public Groupe createGroupe(String nom)
	{
		return gd.createGroupe(nom);
	}
	
	public void deleteGroup(int idGroupe)
	{
		gd.deleteGroup(idGroupe);
	}
	
	public boolean updateGroupe(int idGroupe, String newNom)
	{
		return gd.updateGroupe(idGroupe, newNom);
	}
	
	public ArrayList<Groupe> getGroups()
	{
		return gd.getGroups();
	}
	
	public Groupe getGroupById(int idGroupe)
	{
		return gd.getGroupById(idGroupe);
	}
	
	public int getGroupIdByName(String name) 
	{
		return gd.getGroupIdByName(name);
	}
	
	public boolean groupExists(String nom)
	{
		return gd.groupExists(nom);
	}

	public boolean groupExists(int idGroupe) {
		return gd.groupExists(idGroupe);
	}

	public String getGroupNameById(int idGroupe) {
		return gd.getGroupNameById(idGroupe);
	}
}
