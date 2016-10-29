package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Contact;
import Models.Groupe;

public class GroupeDAO {
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;
	
	public GroupeDAO()
	{
		con = GlobalConnexion.getConnection();
	}
	
	public Connection getConnection()
	{
		return GlobalConnexion.getConnection();
	}

	public Groupe createGroupe(String nom){
		
		Groupe g = null;
		
		System.out.println("Creation du groupe : "+nom);
		
		try
		{		
			con = this.getConnection();
	
			String req = "insert into groupe(nom) values(?)";
	
			ps = con.prepareStatement(req);
			ps.setString(1, nom);
			
			System.out.println(ps);
			ps.execute();
			
			g = new Groupe(nom);
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
		
		return g;
	}

	public boolean updateGroupe(int idGroupe, String newNom) 
	{
		int changes = 0;
		System.out.println("Creation du groupe : "+newNom);
		
		try
		{		
			con = this.getConnection();
	
			String req = "update groupe set nom=? where idGroupe=?";
	
			ps = con.prepareStatement(req);
			ps.setString(1, newNom);
			ps.setInt(2, idGroupe);
			
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

	public ArrayList<Groupe> getGroups() {
		
		ArrayList<Groupe> list = new ArrayList<Groupe>();
		
		try
		{		
			con = this.getConnection();
	
			String req = "select * from groupe";
	
			ps = con.prepareStatement(req);
			
			ResultSet rs = ps.executeQuery();
			while(rs.next())
				list.add(new Groupe(rs.getInt("idGroupe"),rs.getString("nom")));
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

	public Groupe getGroupById(int groupId) 
	{
		Groupe g= null;
		try
		{		
			con = this.getConnection();
	
			String req = "select nom from groupe where idGroupe=?";
	
			ps = con.prepareStatement(req);
			ps.setInt(1, groupId);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			
			if(rs.next())
				g = new Groupe(groupId, rs.getString("nom"));
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
		return g;
	}
	
	public int getGroupIdByName(String name) 
	{
		int id=0;
		try
		{		
			con = this.getConnection();
	
			String req = "select idGroupe from groupe where nom=?";
	
			ps = con.prepareStatement(req);
			ps.setString(1, name);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			
			if(rs.next())
				id = rs.getInt("idGroupe");
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
		return id;
	}
	
	public String getGroupNameById(int idGroupe) 
	{
		String name ="";
		try
		{		
			con = this.getConnection();
	
			String req = "select nom from groupe where idGroupe=?";
	
			ps = con.prepareStatement(req);
			ps.setInt(1, idGroupe);
			ResultSet rs = ps.executeQuery();
			System.out.println(ps);
			
			if(rs.next())
				name = rs.getString("nom");
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
		return name;
	}

	public boolean groupExists(String nom) 
	{
		boolean exists = false;
		try
		{
			con = this.getConnection();
			String req = "select * from groupe where nom=?";
	
			ps = con.prepareStatement(req);
			
			ps.setString(1, nom);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				exists=true;
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

		return exists;
	}
	
	public boolean groupExists(int idGroupe) 
	{
		boolean exists = false;
		try
		{
			con = this.getConnection();
			String req = "select * from groupe where idGroupe=?";
	
			ps = con.prepareStatement(req);
			
			ps.setInt(1, idGroupe);
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next())
				exists=true;
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

		return exists;
	}
	
	public void deleteGroup(int idGroupe) 
	{
		System.out.println("Suppression : "+idGroupe);
		
		try
		{
		con = this.getConnection();
		String req = "delete from groupe where idGroupe=?";
	
		ps = con.prepareStatement(req);;
		ps.setInt(1, idGroupe);
		
		ps.execute();
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
	}
}
