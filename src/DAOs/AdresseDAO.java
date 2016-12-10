package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Adresse;
import ServiceEntities.ContactService;

public class AdresseDAO 
{
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;
	
	public AdresseDAO()
	{
		con = GlobalConnexion.getConnection();
	}
	
	public Connection getConnection()
	{
		return GlobalConnexion.getConnection();
	}
	
	public Adresse createAdresse(String rue, String ville, String codePostal, String pays)
	{
		Adresse a = null;
		
		System.out.println("Creation adresse pour : "+rue+" | "+ville+" | "+codePostal+" | "+pays);
		
		try
		{		
		con = this.getConnection();

		String req = "insert into adresse(rue,ville,codePostal,pays) values(?,?,?,?)";

		ps = con.prepareStatement(req);
		ps.setString(1, rue);
		ps.setString(2, ville);
		ps.setString(3, codePostal);
		ps.setString(4, pays);
		
		System.out.println(ps);
		ps.execute();
		
		ContactService cs = new ContactService();
		a = new Adresse(rue, ville, codePostal, pays);
		
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
		
		return a;
	}
	
	public int getAddressId(Adresse adresse)
	{
		int a = 0;
		try
		{
			con = this.getConnection();
			String req = "select idAdresse from adresse where rue=? and ville=? and codePostal=? and pays=?";
	
			ps = con.prepareStatement(req);
			ps.setString(1, adresse.getRue());
			ps.setString(2, adresse.getVille());
			ps.setString(3, adresse.getCodePostal());
			ps.setString(4, adresse.getPays());
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			a = rs.getInt("idAdresse");
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

		return a;
	}
	
	public boolean deleteAdresse(int idAdresse)
	{
		System.out.println("Suppression : "+idAdresse);
		int changes = 0;
		try
		{
		con = this.getConnection();
		String req = "delete from adresse where idAdresse=?";
	
		ps = con.prepareStatement(req);
		ps.setInt(1, idAdresse);
		
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
	
	public void updateAdresse(int idAdresse, String newRue, String newVille, String newCodePostal, String newPays)
	{
		System.out.println("MAJ adresse "+idAdresse+": "+newRue+"\n"+newVille+" | "+newCodePostal+"\n"+newPays);
		
		try
		{
		con = this.getConnection();
		String req = "update adresse set rue=?,ville=?,codePostal=?,pays=? where idAdresse=?";

		ps = con.prepareStatement(req);
		ps.setString(1, newRue);
		ps.setString(2, newVille);
		ps.setString(3, newCodePostal);
		ps.setString(4, newPays);
		ps.setInt(5, idAdresse);
		
		System.out.println(ps);
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
	
	public Adresse getAdresseById(int idAdresse)
	{
		Adresse a = null;
		try
		{
			con = this.getConnection();
			String req = "select * from adresse where idAdresse=?";
	
			ps = con.prepareStatement(req);
			ps.setInt(1, idAdresse);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			a = new Adresse(rs.getString("rue"), rs.getString("ville"), rs.getString("codePostal"), rs.getString("pays"));
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

		return a;
	}
	
	public boolean deleteAdressesByContactId(int idContact) 
	{
		int changes = 0;
		try
		{
			con = this.getConnection();
			String req = "delete from adresse where idContact=?";
	
			ps = con.prepareStatement(req);
			
			ps.setInt(1, idContact);
			
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

	public ArrayList<Adresse> getAdresses() 
	{
		ArrayList<Adresse> list = new ArrayList<Adresse>();
		
		try
		{
			con = this.getConnection();
			String req = "select * from adresse";
			ps = con.prepareStatement(req);
			
			rs = ps.executeQuery();
			
			while(rs.next())
				list.add(new Adresse(rs.getInt("idAdresse"),rs.getString("rue"),rs.getString("ville"), rs.getString("codePostal"), rs.getString("pays")));
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

	public boolean addressExists(int idAddress) 
	{
		boolean exists = false;
		try
		{
			con = this.getConnection();
			String req = "select * from adresse where idAdresse=?";
	
			ps = con.prepareStatement(req);
			
			ps.setInt(1, idAddress);
			
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

	public boolean addressExists(String rue, String ville,String codep, String pays)
	{
		boolean exists = false;
		try
		{
			con = this.getConnection();
			String req = "select * from adresse where rue=? and ville=? and codePostal=? and pays=?";
	
			ps = con.prepareStatement(req);
			
			ps.setString(1, rue);
			ps.setString(2, ville);
			ps.setString(3, codep);
			ps.setString(4, pays);
			
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
}
