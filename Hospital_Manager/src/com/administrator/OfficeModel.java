package com.administrator;

//��װ���ֲ���(��ɾ�Ĳ飩��ģ��
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.table.AbstractTableModel;

//һ�����ģ��
public class OfficeModel extends AbstractTableModel{
	//rowData������������ݣ�columnNames�������
	Vector rowData,columnNames;
	//�������ݿ�Ҫ�õĶ���
	Connection ct=null;
	PreparedStatement ps= null;	
	ResultSet rs=null;
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://localhost:1433;databaseName=hospital";
	String user="aaa";
	String password="111";
	
	//��ӣ��޸ģ�ɾ��
	public boolean updateOffice(String sql,String []paras){
		boolean b=true;
		try{
			//�������ݿ�
			Connection ct=null;
			PreparedStatement ps= null;	
			ResultSet rs=null;
			try{
				//1����������(������Ҫ��������������ڴ���)
				Class.forName(driver);
				//2���õ�����(ָ�����ӵ��ĸ�����Դ)
				ct = DriverManager.getConnection(url,user,password); 
				//3������ps
				ps=ct.prepareStatement(sql);
				 //Ԥ����������
				for(int i=0;i<paras.length;i++){
					ps.setString(i+1, paras[i]);
				}
				//4��ִ��(��������ӣ�ɾ�����޸�ʹ��executeUpdate();��ѯexecuteQuery)
				if(ps.executeUpdate()!=1){
					b=false;//���ʧ��
				}
			}catch(Exception e1){
				b=false;
				e1.printStackTrace();
			}
			finally{
				//�ر���Դ���ر�˳��˭�󴴽����ȹر�)
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
		if(sql.equals("")){//��ʼ�����
			sql="select * from Office";
		}
		columnNames = new Vector();
		//��������
		columnNames.add("����ID");
		columnNames.add("��������");
		columnNames.add("��������");
		columnNames.add("������");
		
		rowData = new Vector();//�ɴ�Ŷ���
		
		try{
			//1����������(������Ҫ��������������ڴ���)
			 Class.forName(driver);
			//2���õ�����(ָ�����ӵ��ĸ�����Դ)
			 ct = DriverManager.getConnection(url,user,password); 
			//3������ps
			ps=ct.prepareStatement(sql);
			//4��ִ��(��������ӣ�ɾ�����޸�ʹ��executeUpdate();��ѯexecuteQuery)
			rs = ps.executeQuery();
			 
			while(rs.next()){ 
				Vector col = new Vector();
				col.add(rs.getString(1));
				col.add(rs.getString(2));
				col.add(rs.getString(3));
				col.add(rs.getString(4));
				
				//���뵽rowData
				rowData.add(col);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			//�ر���Դ���ر�˳��˭�󴴽����ȹر�)
			try{
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			}catch(Exception e){
				
			}
		}
	}
	
	//ͨ�����ݵ�sql������������ģ��
	public OfficeModel(String sql){
		this.init(sql);
	}
	//��һ�����캯�������ڳ�ʼ������ģ��
	public OfficeModel(){
		this.init("");
	}
	@Override
	//�õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.rowData.size();
	}

	@Override
	//�õ����ж�����
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
	//�õ�ĳ��ĳ�е�����
	public Object getValueAt(int row, int column) {
		// TODO Auto-generated method stub
		return ((Vector)this.rowData.get(row)).get(column);
	}

}
