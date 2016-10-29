package DAOs;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Models.Contact;
import Models.Groupe;
import Models.Telephone;
import ServiceEntities.ContactService;

public class TelephoneDAO 
{
	
	PreparedStatement ps = null;
	ResultSet rs = null;
	Connection con = null;
	
	public TelephoneDAO()
	{
		con = GlobalConnexion.getConnection();
	}
	
	public Connection getConnection()
	{
		return GlobalConnexion.getConnection();
	}
	
	public Telephone createTelephone(String type, String numero, int idContact)
	{
		Telephone t = null;
		
		System.out.println("Creation du tel : "+type+" | "+numero+" idContact "+idContact);
		
		try
		{		
		con = this.getConnection();

		String req = "insert into telephone(type,numero,idContact) values(?,?,?)";

		ps = con.prepareStatement(req);
		ps.setString(1, type);
		ps.setString(2, numero);
		ps.setInt(3, idContact);
		
		System.out.println(ps);
		ps.execute();
		
		t = new Telephone(type, numero);
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
		
		return t;
	}
	
	public void deleteTelephone(int idTelephone)
	{
		System.out.println("Suppression : "+idTelephone);
		
		try
		{
		con = this.getConnection();
		String req = "delete from telephone where idTelephone=?";
	
		ps = con.prepareStatement(req);;
		ps.setInt(1, idTelephone);
		
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
	
	public void updateNumero(int idTelephone, String newType, String newNumero)
	{
		System.out.println("MAJ du tel : "+idTelephone+"\n"+newNumero);
		
		try
		{
		con = this.getConnection();
		String req = "update telephone set type=?,numero=? where idTelephone=?";

		ps = con.prepareStatement(req);;
		ps.setString(1, newType);
		ps.setString(2, newNumero);
		ps.setInt(3, idTelephone);
		
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
	
	public ArrayList<Telephone> getTelephonesByContactId(int idContact)
	{
		ArrayList<Telephone> list = new ArrayList<Telephone>();
		
		try
		{
			con = this.getConnection();
			String req = "select * from telephone where idContact=?";
	
			ps = con.prepareStatement(req);
			ps.setInt(1, idContact);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())			
				list.add(new Telephone(rs.getString("type"), rs.getString("numero")));
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
	
	public Telephone getNumberById(int id)
	{
		Telephone tel=null;
		try
		{
			con = this.getConnection();
			String req = "select * from telephone where idTelephone=?";
	
			ps = con.prepareStatement(req);;
			ps.setInt(1, id);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			tel= new Telephone(rs.getString("type"), rs.getString("numero"), rs.getInt("idContact"));
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

		return tel;
	}
	
	public int getIdByNumber(String numero)
	{
		int id = -1;
		try
		{
			con = this.getConnection();
			String req = "select idTelephone from telephone where numero=?";
	
			ps = con.prepareStatement(req);;
			ps.setString(1, numero);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			id=Integer.valueOf(rs.getString("idTelephone"));
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

	public boolean deleteTelephoneByContactId(int idContact) 
	{
		int changes = 0;
		try
		{
			con = this.getConnection();
			String req = "delete from telephone where idContact=?";
	
			ps = con.prepareStatement(req);;
			
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

	public ArrayList<Telephone> getTelephones() 
	{
		ArrayList<Telephone> list = new ArrayList<Telephone>();
		
		try
		{		
			con = this.getConnection();
	
			String req = "select * from telephone";
	
			ps = con.prepareStatement(req);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
				list.add(new Telephone(rs.getInt("idTelephone"),rs.getString("type"),rs.getString("numero"),rs.getInt("idContact")));
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

	public boolean telephoneExists(int idTelephone) {
		boolean exists = false;
		try
		{
			con = this.getConnection();
			String req = "select * from telephone where idTelephone=?";
	
			ps = con.prepareStatement(req);
			
			ps.setInt(1, idTelephone);
			
			ResultSet rs = ps.executeQuery();
			
			System.out.println(ps);
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

	public boolean telephoneExists(String type, String numero) {
		boolean exists = false;
		try
		{
			con = this.getConnection();
			String req = "select * from telephone where type=? and numero=?";
	
			ps = con.prepareStatement(req);
			
			ps.setString(1, type);
			ps.setString(2, numero);
			
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
