package com.demo.common.kit;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;


public class SQLKit {

	private String dbDriverClass;
	private String dbUrl;
	private String dbUserName;
	private String dbPassword;
	private static final int COLUMN_WIDTH = 25;

	public SQLKit(String dbDriverClass, String dbUrl, String dbUserName,
			String dbPassword) {
		this.dbDriverClass=dbDriverClass;
		this.dbUrl=dbUrl;
		this.dbUserName=dbUserName;
		this.dbPassword=dbPassword;

	}
	
	public void executeSQL(String sql){
		if(sql==null){
			return;
		}
		sql=sql.trim();
		try{
			Class.forName(dbDriverClass);
		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		try(Connection connection=DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
				Statement statement=connection.createStatement()){
			if(sql.toUpperCase().startsWith("SELECT")){
				try(ResultSet resultSet=statement.executeQuery(sql)){
					ResultSetMetaData metaData=resultSet.getMetaData();
					int columnCount = metaData.getColumnCount();
					for(int i=0;i<columnCount;i++){
						System.out.print(pad(metaData.getColumnName(i+1)));
					}
					//画分隔线
					int length=columnCount*COLUMN_WIDTH;
					StringBuilder sb=new StringBuilder(length);
					for(int i=0;i<length;i++){
						sb.append('=');
					}
					System.out.println();
					System.out.println(sb.toString());
					
					while(resultSet.next()){
						String[] row=new String[columnCount];
						for(int i=0;i<columnCount;i++){
							row[i]=resultSet.getString(i+1);
							System.out.print(pad(row[i]));
						}
						System.out.println();
					}
				}catch(SQLException e){
					e.printStackTrace();
				}
			}else{
				int recordsUpdated=statement.executeUpdate(sql);
				System.out.println(recordsUpdated+" record(s) affected");
				
			}
		}catch(SQLException e1){
			e1.printStackTrace();
		}catch(Exception e2){
			e2.printStackTrace();
		}
	}
	
	private String pad(String s){
		int padCount=COLUMN_WIDTH-s.length();
		StringBuilder sb=new StringBuilder(25);
		sb.append(s);
		for(int i=0;i<padCount;i++){
			sb.append(" ");
		}
		return sb.toString();
	}
	
	public static void main(String[] args){
		String dbDriverClass="com.mysql.jdbc.Driver";
		String dbUrl="jdbc:mysql://localhost:3306/test";
		String dbUserName="root";
		String dbPassword="admin";
		
		SQLKit sqlTool=new SQLKit(dbDriverClass,dbUrl,dbUserName,dbPassword);
		//图形界面
		String sql=JOptionPane.showInputDialog(null,"Enter an SQL Statement");
		sqlTool.executeSQL(sql);
	}

}

