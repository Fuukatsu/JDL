package jdl.dao;


import java.awt.Font;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.TableModel;

import jdl.controller.objectFilter;
import jdl.model.Client;
import jdl.model.Transaction;
import jdl.model.User;
import net.proteanit.sql.DbUtils;

public class Queries 
{
	static databaseProperties dP = new databaseProperties();
	public static User loginDAO(String username, String password)
	{
		User user = null;
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{	
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE user_username = ? AND user_password = ? AND user_isActive IS NULL");
			ps.setString(1, username);
			ps.setString(2, password);
			ResultSet rs = ps.executeQuery();
			while (rs.next())
			{
				user = new User();
				user.setUser_id(rs.getInt("user_id"));
				user.setUser_username(rs.getString("user_username"));
				user.setUser_password(rs.getString("user_password"));
				user.setUser_ifAdmin(rs.getInt("user_ifAdmin"));
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		return user;		
	}
	public static ArrayList<Client> getClients()
	{
		ArrayList<Client> lists = new ArrayList<Client>();
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM jdl_accounts.clients where client_isActive = 1 OR null");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{        
				Client c = new Client();
				c.setClient_id(rs.getInt(1));
				c.setClient_lastname(rs.getString(2));
				c.setClient_firstname(rs.getString(3));
				c.setClient_nationality(rs.getString(4));
				c.setClient_birthdate(rs.getDate(5));
				c.setClient_gender(rs.getString(6));
				c.setClient_company(rs.getString(7));
				c.setClient_position(rs.getString(8));
				c.setClient_alias(rs.getString(9));
				c.setClient_contact(rs.getString(10));
				c.setClient_email(rs.getString(11));
				lists.add(c);
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			if(lists.isEmpty())
				return null;
		}
		return lists;
	}
	public static TableModel getTransactions(int id)
	{
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("SELECT client_id AS 'Client ID',"
					+ "trans_transId AS 'Transaction ID'" +
					",trans_passportNo AS 'Passport No' "+ 
					", trans_tinID AS 'TIN ID' " + 
					", trans_visaType AS 'Visa Type' " + 
					", trans_visaStartDate AS 'Visa Start Date' " + 
					", trans_visaEndDate AS 'Visa Expiry Date' " + 
					", trans_permitType AS 'Permit Type' " + 
					", trans_permitStartDate AS 'Permit Start Date' " + 
					", trans_permitEndDate AS 'Permit Expiry Date' " + 
					", trans_aepID AS 'AEP ID' " + 
					", trans_aepStartDate AS 'AEP Start Date' " + 
					", trans_aepEndDate AS 'AEP Expiry Date' " + 
					" FROM transactions WHERE client_id = ? AND trans_isActive = 1 ORDER BY trans_transId DESC");
			ps.setInt(1, id);
			rs = ps.executeQuery();
			TableModel tm = DbUtils.resultSetToTableModel(rs);
			con.close();
			return tm;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	public static boolean insertTransaction(Transaction t)
	{
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("INSERT INTO jdl_accounts.transactions (trans_passportNo, trans_tinID, trans_visaType, trans_visaStartDate, trans_visaEndDate, trans_permitType, trans_permitStartDate, trans_permitEndDate, trans_aepID, "
					+ "trans_aepStartDate, trans_aepEndDate, client_id, trans_transTimestamp, trans_transAuthor, trans_transAction) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1, t.getPassportNo());
			ps.setString(2, t.getTinID());
			ps.setString(3, t.getVisaType());
			ps.setDate(4, t.getVisaStartDate());
			ps.setDate(5, t.getVisaEndDate());
			ps.setString(6, t.getPermitType());
			ps.setDate(7, t.getPermitStartDate());
			ps.setDate(8, t.getPermitEndDate());
			ps.setString(9, t.getAepID());
			ps.setDate(10, t.getAepStartDate());
			ps.setDate(11, t.getAepEndDate());
			ps.setInt(12, t.getClient_id());
			ps.setDate(13, t.getTransTimestamp());
			ps.setString(14, t.getTransAuthor());
			ps.setString(15, t.getTransAction());
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static boolean updateTransaction(Transaction t)
	{
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("UPDATE jdl_accounts.transactions SET trans_passportNo = ?, trans_tinID = ?, trans_visaType=?, trans_visaStartDate=?, trans_visaEndDate=?, trans_permitType=?, trans_permitStartDate=?, trans_permitEndDate=?, trans_aepID=?, "
					+ "trans_aepStartDate=?, trans_aepEndDate=?, client_id=?, trans_transTimestamp=?, trans_transAction=?, trans_transAuthor=?  WHERE trans_transId = ?");
			ps.setString(1, t.getPassportNo());
			ps.setString(2, t.getTinID());
			ps.setString(3, t.getVisaType());
			ps.setDate(4, t.getVisaStartDate());
			ps.setDate(5, t.getVisaEndDate());
			ps.setString(6, t.getPermitType());
			ps.setDate(7, t.getPermitStartDate());
			ps.setDate(8, t.getPermitEndDate());
			ps.setString(9, t.getAepID());
			ps.setDate(10, t.getAepStartDate());
			ps.setDate(11, t.getAepEndDate());
			ps.setInt(12, t.getClient_id());
			ps.setDate(13, t.getTransTimestamp());
			ps.setString(14, t.getTransAction());
			ps.setString(15, t.getTransAuthor());
			ps.setInt(16, t.getTransID());
			ps.executeUpdate();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	public static ArrayList<User> getUsers()
	{
		ArrayList<User> lists = new ArrayList<User>();
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM jdl_accounts.users");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{        
				User u = new User();
				u.setUser_id(rs.getInt("user_id"));
				u.setUser_username(rs.getString("user_username"));
				u.setUser_password(rs.getString("user_password"));
				u.setUser_ifAdmin(rs.getInt("user_ifAdmin"));
				lists.add(u);
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			if(lists.isEmpty())
				return null;
		}
		return lists;
	}
	
	public static Client getClient(int id)
	{
		Client c = new Client();
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM clients WHERE client_id = ? and client_isActive = 1");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{        
				c = new Client();
				c.setClient_id(rs.getInt("client_id"));
				c.setClient_lastname(rs.getString(2));
				c.setClient_firstname(rs.getString(3));
				c.setClient_nationality(rs.getString(4));
				c.setClient_birthdate(rs.getDate(5));
				c.setClient_gender(rs.getString(6));
				c.setClient_company(rs.getString(7));
				c.setClient_position(rs.getString(8));
				c.setClient_alias(rs.getString(9));
				c.setClient_contact(rs.getString(10));
				c.setClient_email(rs.getString(11));
				c.setClient_action(rs.getString(12));
				c.setClient_isActive(rs.getInt(13));
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		return c;
	}
	public static TableModel getClientTransactions(String u)
	{
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("SELECT client_id AS 'Client ID',"
					+ "trans_transId AS 'Transaction ID'" +
					", trans_passportNo AS 'Passport No' "+ 
					", trans_tinID AS 'TIN ID' " + 
					", trans_visaType AS 'Visa Type' " + 
					", trans_visaStartDate AS 'Visa Start Date' " + 
					", trans_visaEndDate AS 'Visa Expiry Date' " + 
					", trans_permitType AS 'Permit Type' " + 
					", trans_permitStartDate AS 'Permit Start Date' " + 
					", trans_permitEndDate AS 'Permit Expiry Date' " + 
					", trans_aepID AS 'AEP ID' " + 
					", trans_aepStartDate AS 'AEP Start Date' " + 
					", trans_aepEndDate AS 'AEP Expiry Date' " + 
					", trans_transTimestamp AS 'Timestamp' "+
					", trans_transAuthor AS 'Author' "+
					", trans_transAction AS 'Action' "+
					" FROM transactions WHERE trans_transAuthor = ? AND trans_isActive = 1 ORDER BY trans_transId DESC");
			ps.setString(1, u);
			rs = ps.executeQuery();
			TableModel tm = DbUtils.resultSetToTableModel(rs);
			con.close();
			return tm;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	public static TableModel getClientTransactions2(String u)
	{
		ResultSet rs = null;
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("SELECT client_id AS 'Client ID',"
					+ "trans_transId AS 'Transaction ID'" +
					", trans_passportNo AS 'Passport No' "+ 
					", trans_tinID AS 'TIN ID' " + 
					", trans_visaType AS 'Visa Type' " + 
					", trans_visaStartDate AS 'Visa Start Date' " + 
					", trans_visaEndDate AS 'Visa Expiry Date' " + 
					", trans_permitType AS 'Permit Type' " + 
					", trans_permitStartDate AS 'Permit Start Date' " + 
					", trans_permitEndDate AS 'Permit Expiry Date' " + 
					", trans_aepID AS 'AEP ID' " + 
					", trans_aepStartDate AS 'AEP Start Date' " + 
					", trans_aepEndDate AS 'AEP Expiry Date' " + 
					", trans_transTimestamp AS 'Timestamp' "+
					", trans_transAuthor AS 'Author' "+
					", trans_transAction AS 'Action' "+
					" FROM transactions WHERE trans_transAuthor = ? ORDER BY trans_transId DESC");
			ps.setString(1, u);
			rs = ps.executeQuery();
			TableModel tm = DbUtils.resultSetToTableModel(rs);
			con.close();
			return tm;
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return null;
	}
	public static ArrayList<Transaction> getTransactions()
	{
		ArrayList<Transaction> tlist = new ArrayList<Transaction>();
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM TRANSACTIONS WHERE trans_isActive = 1");
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Transaction t = new Transaction();
				t.setPassportNo(rs.getString(1));
				t.setTinID(rs.getString(2));
				t.setVisaType(rs.getString(3));
				t.setVisaStartDate(rs.getDate(4));
				t.setVisaEndDate(rs.getDate(5));
				t.setPermitType(rs.getString(6));
				t.setPermitStartDate(rs.getDate(7));
				t.setPermitEndDate(rs.getDate(8));
				t.setAepID(rs.getString(9));
				t.setAepStartDate(rs.getDate(10));
				t.setAepEndDate(rs.getDate(11));
				t.setTransID(rs.getInt(12));
				t.setClient_id(rs.getInt(13));
				t.setTransTimestamp(rs.getDate(14));
				t.setTransAuthor(rs.getString(15));
				t.setTransAction(rs.getString(16));
				tlist.add(t);
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		return tlist;
	}
	
	public static boolean checkNotification(Date date) {
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("SELECT * FROM jdl_accounts.notifications WHERE notif_date = ?");
			ps.setDate(1, java.sql.Date.valueOf((date.toString())));
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				return false;
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
		}
		return true;
	}
	public static boolean insertNotification(Date date)
	{
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			PreparedStatement ps = con.prepareStatement("INSERT INTO jdl_accounts.notifications (notif_date) values (?) ON DUPLICATE KEY UPDATE\r\n" + 
					"notif_date = ?");
			ps.setDate(1, java.sql.Date.valueOf((date.toString())));
			ps.setDate(2, java.sql.Date.valueOf((date.toString())));
			ps.executeUpdate();
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	public static ArrayList<Transaction> getTransactionsBetweenDate(Date startDate, Date endDate)
	{
		ArrayList<Transaction> tlist = new ArrayList<Transaction>();
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			String sql = "SELECT * FROM jdl_accounts.transactions WHERE trans_isActive = 1 AND trans_visaEndDate "+
					"BETWEEN ? AND ? "+
					"UNION "+
					"SELECT * FROM jdl_accounts.transactions WHERE trans_isActive = 1 AND trans_permitEndDate "+
					"BETWEEN ? AND ? "+
					"UNION "+
					"SELECT * FROM jdl_accounts.transactions WHERE trans_isActive = 1 AND trans_aepEndDate "+
					"BETWEEN ? AND ? ";
			PreparedStatement ps = con.prepareStatement(sql);
			for(int i = 0; i < 6; i+=2)
			{
				ps.setDate(1+i, startDate);
				ps.setDate(2+i, endDate);	
			}
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Transaction t = new Transaction();
				t.setPassportNo(rs.getString(1));
				t.setTinID(rs.getString(2));
				t.setVisaType(rs.getString(3));
				t.setVisaStartDate(rs.getDate(4));
				t.setVisaEndDate(rs.getDate(5));
				t.setPermitType(rs.getString(6));
				t.setPermitStartDate(rs.getDate(7));
				t.setPermitEndDate(rs.getDate(8));
				t.setAepID(rs.getString(9));
				t.setAepStartDate(rs.getDate(10));
				t.setAepEndDate(rs.getDate(11));
				t.setTransID(rs.getInt(12));
				t.setClient_id(rs.getInt(13));
				t.setTransTimestamp(rs.getDate(14));
				t.setTransAuthor(rs.getString(15));
				t.setTransAction(rs.getString(16));
				tlist.add(t);
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		return tlist;
	}
	public static ArrayList<Transaction> getBetweenTransactionDate(Date startDate, Date endDate)
	{
		ArrayList<Transaction> tlist = new ArrayList<Transaction>();
		try (Connection con = DriverManager.getConnection(dP.url, dP.username, dP.password)) 
		{
			String sql = "SELECT * FROM jdl_accounts.transactions WHERE trans_transTimestamp "+
					"BETWEEN ? AND ? AND trans_isActive = 1";
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setDate(1, startDate);
			ps.setDate(2, endDate);	
			ResultSet rs = ps.executeQuery();
			while(rs.next())
			{
				Transaction t = new Transaction();
				t.setPassportNo(rs.getString(1));
				t.setTinID(rs.getString(2));
				t.setVisaType(rs.getString(3));
				t.setVisaStartDate(rs.getDate(4));
				t.setVisaEndDate(rs.getDate(5));
				t.setPermitType(rs.getString(6));
				t.setPermitStartDate(rs.getDate(7));
				t.setPermitEndDate(rs.getDate(8));
				t.setAepID(rs.getString(9));
				t.setAepStartDate(rs.getDate(10));
				t.setAepEndDate(rs.getDate(11));
				t.setTransID(rs.getInt(12));
				t.setClient_id(rs.getInt(13));
				t.setTransTimestamp(rs.getDate(14));
				t.setTransAuthor(rs.getString(15));
				t.setTransAction(rs.getString(16));
				tlist.add(t);
			}
			con.close();
		} catch (SQLException e) 
		{
			e.printStackTrace();
			return null;
		}
		return tlist;
	}
}
