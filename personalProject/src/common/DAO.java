package common;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DAO {
	//oracle db에 대한 정보
	private String jdbcDriver;
	private String oracleUrl;
	private String connectedId;
	private String connectedPwd;
	
	
	//자식클래스에서 사용할 필드(공통적으로 사용)
	protected Connection conn;
	protected Statement stmt;
	protected PreparedStatement pstmt;
	protected ResultSet rs;
	
	
	public DAO() {
		dbConfig(); //dbConfig는 어디 놔두던지 상관x 생성자에 넣어도 됨(1번만 실행시키려면)
	}
	
	//DB에 접속하는 메소드
	public void connect() {
		try {
			Class.forName(jdbcDriver);
			conn = DriverManager.getConnection(oracleUrl, connectedId, connectedPwd);
		} catch (ClassNotFoundException e){
			System.out.println("jddbc driver 로딩 실패");
		} catch (SQLException e) {
			System.out.println("DB 연결 실패");
		} finally {
			
		}
	}
	
	//DB에 정보를 가져오는 메소드
	private void dbConfig() {
		String resource = "config/db.properties";
		Properties properties = new Properties();
		
		try {
			String filePath = ClassLoader.getSystemClassLoader().getResource(resource).getPath();
			properties.load(new FileInputStream(filePath));
			
		} catch(IOException e) {
			e.printStackTrace();
		} 
		jdbcDriver = properties.getProperty("driver");
		oracleUrl = properties.getProperty("url");
		connectedId = properties.getProperty("id");
		connectedPwd =  properties.getProperty("password");
		
	}
	
	//DB에 접속을 해제하는 메소드
	public void disconnect() {
		try {
			//close를 하기전에 인스턴스가 있는지 확인해야함
			if(rs != null) rs.close();
			if(pstmt != null) pstmt.close();
			if(stmt != null) stmt.close();
			if(conn != null) conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
