package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Contact;
import Models.Groupe;
import ServiceEntities.ContactService;
import ServiceEntities.GroupeService;

public class MembreDAO 
{

	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;
	
	public MembreDAO()
	{
		con = GlobalConnexion.getConnection();
	}
	
	public Connection getConnection()
	{
		return GlobalConnexion.getConnection();
	}
	
	public ArrayList<Integer> getGroupIdByContactId(int idContact) 
	{
		ArrayList<Integer> list = new ArrayList<Integer>();
		try
		{
			con = this.getConnection();
			String req = "select idGroupe from membre where idContact=?";
			ps = con.prepareStatement(req);
			ps.setInt(1, idContact);
			ResultSet rs = ps.executeQuery() ;
			System.out.println(ps);
			
			while(rs.next())
				list.add(rs.getInt("idGroupe"));
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		
		return list;
	}

	public boolean addContactToGroup(int idContact, int idGroupe) 
	{
		int changes = 0;
		
		try
		{
			con = this.getConnection();
			String req = "insert into membre value(?,?)";
			ps = con.prepareStatement(req);
			ps.setInt(1, idGroupe);
			ps.setInt(2, idContact);
			
			System.out.println(ps);
			changes = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

		return changes>0;
	}
	
	public boolean removeContactFromGroup(int idContact, int idGroupe) 
	{
		int changes = 0;
		
		try
		{
			con = this.getConnection();
			String req = "delete from membre where idGroupe=? and idContact=?";
			ps = con.prepareStatement(req);
			ps.setInt(1, idGroupe);
			ps.setInt(2, idContact);
			
			System.out.println(ps);
			changes = ps.executeUpdate();
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	

		return changes>0;
	}
	
	public ArrayList<Contact> getMembersByGroupId(int groupeId) {
		ArrayList<Contact> list = new ArrayList<Contact>();
		try
		{		
			con = this.getConnection();
	
			String req = "select idContact from membre where idGroupe=?";
	
			ps = con.prepareStatement(req);
			ps.setInt(1, groupeId);
			
			ResultSet rs = ps.executeQuery();
			ContactService cs = new ContactService();
			
			System.out.println(ps);
			
			while(rs.next())
			{
				Contact c = cs.getContactById(rs.getInt("idContact"));
				list.add(new Contact(c.getId(), c.getNom(), c.getPrenom(), c.getEmail()));
			}
		}
		catch(SQLException e)
		{
			System.out.println(e.getMessage());
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
		finally
		{
			try {
				if(ps != null) ps.close();
				if(con != null) con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}	
		return list;
	}
}
