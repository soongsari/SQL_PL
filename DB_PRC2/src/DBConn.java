import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBConn {
	
	private String host;
	private String port;
	private String name;
	private String password;
	private String schema;
	
	public DBConn(String host, String port, String name, String password, String schema){
		this.host = host;
		this.port = port;
		this.name = name;
		this.password = password;
		this.schema = schema;
	}
	
	public Connection dbConn() {
		Connection conn = null;
		String url = "jdbc:mysql://"+host+":"+port+"/"+schema;
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection(url, name, password);
		} catch(Exception e){
			System.out.println(e.toString());
		}
		return conn;
	}
	
	public void dbClose(Connection conn, PreparedStatement pstmt)
	{
		try{
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		}catch(Throwable e){
			System.out.println(e.toString());
		}finally{
			try{
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch (Exception e){
				System.out.println(e.toString());
			}
		}
	}
	
	public void dbClose(Connection conn, PreparedStatement pstmt, ResultSet rs)
	{
		try{
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(conn != null) conn.close();
		} catch(Throwable e){
			System.out.println(e.toString());
		} finally{
			try{
				if(rs != null) rs.close();
				if(pstmt != null) pstmt.close();
				if(conn != null) conn.close();
			} catch(Exception e){
			}
		}
	}
}