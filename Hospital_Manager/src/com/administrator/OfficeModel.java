package com.administrator;

//封装各种操作(增删改查）的模型
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

//一个表的模型
public class OfficeModel extends AbstractTableModel{
	//rowData用来存放行数据，columnNames存放列名
	Vector rowData,columnNames;
	//连接数据库要用的东西
	Connection ct=null;
	PreparedStatement ps= null;	
	ResultSet rs=null;
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://localhost:1433;databaseName=hospital";
	String user="aaa";
	String password="111";
	
	//添加，修改，删除
	public boolean updateOffice(String sql,String []paras){
		boolean b=true;
		try{
			//连接数据库
			Connection ct=null;
			PreparedStatement ps= null;	
			ResultSet rs=null;
			try{
				//1、加载驱动(把下需要的驱动程序加入内存中)
				Class.forName(driver);
				//2、得到连接(指定连接到哪个数据源)
				ct = DriverManager.getConnection(url,user,password); 
				//3、创建ps
				ps=ct.prepareStatement(sql);
				 //预编译语句对象
				for(int i=0;i<paras.length;i++){
					ps.setString(i+1, paras[i]);
				}
				//4、执行(如果是增加，删除，修改使用executeUpdate();查询executeQuery)
				if(ps.executeUpdate()!=1){
					b=false;//添加失败
				}
			}catch(Exception e1){
				b=false;
				e1.printStackTrace();
			}
			finally{
				//关闭资源（关闭顺序：谁后创建则先关闭)
				try{
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();
				}catch(Exception e1){
					
				}
			}
		}catch(Exception e){
			
		}finally{
			
		}
		return b;
	}
	
	
	public void init(String sql){
		if(sql.equals("")){//初始化表格
			sql="select * from Office";
		}
		columnNames = new Vector();
		//设置列名
		columnNames.add("科室ID");
		columnNames.add("科室主任");
		columnNames.add("科室名称");
		columnNames.add("病房数");
		
		rowData = new Vector();//可存放多行
		
		try{
			//1、加载驱动(把下需要的驱动程序加入内存中)
			 Class.forName(driver);
			//2、得到连接(指定连接到哪个数据源)
			 ct = DriverManager.getConnection(url,user,password); 
			//3、创建ps
			ps=ct.prepareStatement(sql);
			//4、执行(如果是增加，删除，修改使用executeUpdate();查询executeQuery)
			rs = ps.executeQuery();
			 
			while(rs.next()){ 
				Vector col = new Vector();
				col.add(rs.getString(1));
				col.add(rs.getString(2));
				col.add(rs.getString(3));
				col.add(rs.getString(4));
				
				//加入到rowData
				rowData.add(col);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//关闭资源（关闭顺序：谁后创建则先关闭)
			try{
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			}catch(Exception e){
				
			}
		}
	}
	
	//通过传递的sql语句来获得数据模型
	public OfficeModel(String sql){
		this.init(sql);
	}
	//做一个构造函数，用于初始化数据模型
	public OfficeModel(){
		this.init("");
	}
	@Override
	//得到共有多少行
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	//得到共有多少列
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.columnNames.size();
	}

	@Override
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		return (String) this.columnNames.get(column);
	}
	@Override
	//得到某行某列的数据
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(column);
	}

}
