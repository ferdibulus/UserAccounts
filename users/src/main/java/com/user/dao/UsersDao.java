package com.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import org.springframework.stereotype.Repository;

@Repository
public class UsersDao {

	static dbConnection conn = new dbConnection();

	public UsersDao() {

	}

	public Connection getDatabaseConn() {
		String driver = "net.sourceforge.jtds.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/test_database";
		String user = "root";
		String pass = "1995";
		return conn.getConn(driver, url, user, pass);
	}

	// ---------------------------------------------------------------------------------------
	public static ArrayList<String[]> getDbArray(Connection conn, String sql, int limit, ArrayList<String[]> bindlist,
			int timeout_insecond, ArrayList<String> colNameList, ArrayList<String> colTypeList) {

		ArrayList<String[]> ret1 = new ArrayList<String[]>();

		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ResultSetMetaData md = null;

		int reccnt = 0;
		try {
			if (pstmt == null) {
				pstmt = conn.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
				try {
					pstmt.setFetchSize(1000);
				} catch (Exception e) {
					e.printStackTrace();
				}

			}

			// ------------------------------ end binding

			if (bindlist != null) {
				for (int i = 1; i <= bindlist.size(); i++) {
					String[] a_bind = bindlist.get(i - 1);
					String bind_type = a_bind[0];
					String bind_val = a_bind[1];

					if (bind_type.equals("INTEGER")) {
						if (bind_val == null || bind_val.equals(""))
							pstmt.setNull(i, java.sql.Types.INTEGER);
						else
							pstmt.setInt(i, Integer.parseInt(bind_val));
					} else if (bind_type.equals("LONG")) {
						if (bind_val == null || bind_val.equals(""))
							pstmt.setNull(i, java.sql.Types.INTEGER);
						else
							pstmt.setLong(i, Long.parseLong(bind_val));
					} else if (bind_type.equals("DOUBLE")) {
						if (bind_val == null || bind_val.equals(""))
							pstmt.setNull(i, java.sql.Types.DOUBLE);
						else
							pstmt.setDouble(i, Double.parseDouble(bind_val));
					} else if (bind_type.equals("FLOAT")) {
						if (bind_val == null || bind_val.equals(""))
							pstmt.setNull(i, java.sql.Types.FLOAT);
						else
							pstmt.setFloat(i, Float.parseFloat(bind_val));
					} else if (bind_type.equals("TIMESTAMP")) {
						if (bind_val == null || bind_val.equals(""))
							pstmt.setNull(i, java.sql.Types.TIMESTAMP);
						else {
							Timestamp ts = new Timestamp(System.currentTimeMillis());
							try {
								ts = new Timestamp(Long.parseLong(bind_val));
							} catch (Exception e) {
								e.printStackTrace();
							}
							pstmt.setTimestamp(i, ts);
						}
					} else {
						pstmt.setString(i, bind_val);
					}
				}
				// ------------------------------ end binding
			} // if bindlist

			if (timeout_insecond > 0)
				pstmt.setQueryTimeout(timeout_insecond);

			if (rs == null)
				rs = pstmt.executeQuery();
			if (md == null)
				md = rs.getMetaData();

			int colcount = md.getColumnCount();

			if (colNameList != null || colTypeList != null) {
				if (colNameList != null)
					colNameList.clear();
				if (colTypeList != null)
					colTypeList.clear();

				for (int i = 1; i <= colcount; i++) {
					String col_name = md.getColumnLabel(i);
					String col_type = md.getColumnTypeName(i);

					if (colNameList != null)
						colNameList.add(col_name);
					if (colTypeList != null)
						colTypeList.add(col_type);
				}
			}

			String a_field = "";

			while (rs.next()) {
				reccnt++;
				if (reccnt > limit)
					break;
				String[] row = new String[colcount];
				for (int i = 1; i <= colcount; i++) {
					try {
						a_field = rs.getString(i);

						if (a_field.equals("null"))
							a_field = "";
						if (a_field.length() > 10000)
							a_field = a_field.substring(0, 10000);
					} catch (Exception enull) {
						a_field = "";
					}
					row[i - 1] = a_field;
				}
				ret1.add(row);
			}

		} catch (SQLException sqle) {
			sqle.printStackTrace();
			System.out.println("Exception@getDbArray : SQL       => " + sql);
			System.out.println("Exception@getDbArray : MSG       => " + sqle.getMessage());
			System.out.println("Exception@getDbArray : CODE      => " + sqle.getErrorCode());
			System.out.println("Exception@getDbArray : SQL STATE => " + sqle.getSQLState());
		} catch (Exception ignore) {
			ignore.printStackTrace();
			System.out.println("Exception@getDbArray : SQL => " + sql);
			System.out.println("Exception@getDbArray : MSG => " + ignore.getMessage());
		} finally {
			try {
				rs.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			try {
				pstmt.close();
			} catch (Exception e) {
				e.printStackTrace();
			}

		}
		return ret1;
	}

	public ArrayList<String[]> getUsers() {
		Connection conn1 = getDatabaseConn();
		if (conn == null)
			return null;
		String sql = "SELECT * FROM test_database.user_accounts";
		ArrayList<String[]> ret = getDbArray(conn1, sql, Integer.MAX_VALUE, null, 0, null, null);
		if (ret.size() == 0)
			return null;

		return ret;

	}
	
	public ArrayList<String[]> insertUser() {
		Connection conn1 = getDatabaseConn();
		if (conn == null)
			return null;
		String sql = "INSERT INTO user_accounts (id,name,phone,email,address,country,department)\n"
				+ "VALUES (?,?,?,?,?,?,?)";
		ArrayList<String[]> ret = getDbArray(conn1, sql, Integer.MAX_VALUE, null, 0, null, null);
		if (ret.size() == 0)
			return null;

		return ret;

	}
	
	public boolean deleteUser(Long id) {
		Connection conn1 = getDatabaseConn();
		if (conn == null)
			return false;
		String sql ="DELETE FROM user_accounts WHERE id="+id+" ";
		ArrayList<String[]> ret = getDbArray(conn1, sql, Integer.MAX_VALUE, null, 0, null, null);
		if (ret.size() == 0)
			return false;

		return true;

	}
	
	public boolean updateUser(Long id) {
		Connection conn1 = getDatabaseConn();
		if (conn == null)
			return false;
		String sql ="UPDATE user_accounts\n"
				+ "SET id = "+id+", name= , phone= , email= , address= , country= , department= , \n"
				+ "WHERE id="+id+" ";
		ArrayList<String[]> ret = getDbArray(conn1, sql, Integer.MAX_VALUE, null, 0, null, null);
		if (ret.size() == 0)
			return false;

		return true;

	}

}
