package com.doctor;

import java.awt.BorderLayout;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import com.login.Login_doctor;

public class Doctor_update extends JDialog implements ActionListener{
	//定义需要的控件
		JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8;
		JButton jb1,jb2;
		JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8;
		JPanel jp1,jp2,jp3,jp4;
		
		//连接数据库要用的东西
		Connection ct=null;
		PreparedStatement ps= null;	
		ResultSet rs=null;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;databaseName=hospital";
		String user="aaa";
		String password="111";
		
		//owner是它的父窗口，title窗口名，model指定是模式窗口还是非模式窗口
		//模式非模式：窗口点开后能不能再点别的窗口
		public Doctor_update(Doctor_inf Doctor_inf,String title,boolean model){
			super(Doctor_inf,title,model);//调用父类构造方法，达到模式对话框效果
			
			jl1=new JLabel("医生ID");		
			jl2=new JLabel("科室ID");		
			jl3=new JLabel("姓     名");		
			jl4=new JLabel("性     别");		
			jl5=new JLabel("年     龄");		
			jl6=new JLabel("职     称");
			jl7=new JLabel("学     历");
			jl8=new JLabel("密     码");
			
			jtf1=new JTextField(8);
			jtf2=new JTextField(8);
			jtf3=new JTextField(8);
			jtf4=new JTextField(8);
			jtf5=new JTextField(8);
			jtf6=new JTextField(8);
			jtf7=new JTextField(8);
			jtf8=new JTextField(8);
			
			try{
				//1、加载驱动(把下需要的驱动程序加入内存中)
				Class.forName(driver);
				//2、得到连接(指定连接到哪个数据源)
				ct = DriverManager.getConnection(url,user,password); 
				//3、创建ps
				ps=ct.prepareStatement("select * from Doctor where DoctorID="+Login_doctor.userId+"");
				//预编译语句对象
				rs=ps.executeQuery();//返回查询结果
				//如果有查询结果
				while(rs.next()){
					//初始数据
					jtf1.setText(rs.getString(1));
					//让jtf1不能修改
					jtf1.setEditable(false);
					jtf2.setText(rs.getString(2));
					jtf3.setText(rs.getString(3));
					jtf4.setText(rs.getString(4));
					jtf5.setText(rs.getString(5));
					jtf6.setText(rs.getString(6));
					jtf7.setText(rs.getString(7));
					jtf8.setText(rs.getString(8));
				}
			}catch(Exception e2){
				
			}finally{
				//关闭资源（关闭顺序：谁后创建则先关闭)
				try{
					if(rs!=null) rs.close();
					if(ps!=null) ps.close();
					if(ct!=null) ct.close();
				}catch(Exception e1){	
				}
			}
			
			jb1=new JButton("确认修改");
			jb1.addActionListener(this);
			jb2=new JButton("取消");
			jb2.addActionListener(this);
			
			jp1=new JPanel();
			jp2=new JPanel();
			jp3=new JPanel();
			jp4=new JPanel();
			
			jp1.add(jl1);
			jp1.add(jtf1);
			jp1.add(jl2);
			jp1.add(jtf2);
			
			jp2.add(jl3);
			jp2.add(jtf3);
			jp2.add(jl4);
			jp2.add(jtf4);
			jp2.add(jl5);
			jp2.add(jtf5);
			
			jp3.add(jl6);
			jp3.add(jtf6);
			jp3.add(jl7);
			jp3.add(jtf7);
			jp3.add(jl8);
			jp3.add(jtf8);
			
			jp4.add(jb1);
			jp4.add(jb2);
			
			this.setLayout(new GridLayout(4,1));
			this.add(jp1);
			this.add(jp2);
			this.add(jp3);
			this.add(jp4);
			
			//展现
			this.setSize(550,300);
			this.setLocationRelativeTo(null);// 居中
			this.setVisible(true);
		}
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==jb1){
					 //做一个SQL
					 String sql="update Doctor set OfficeID=?,Doctorname=?,Doctorsex=?,doctorage=?,doctorlevel=?,doctordegree=? where DoctorID=?";
					 try{
							//1、加载驱动(把下需要的驱动程序加入内存中)
							Class.forName(driver);
							//2、得到连接(指定连接到哪个数据源)
							ct = DriverManager.getConnection(url,user,password); 
							//3、创建ps
							ps=ct.prepareStatement(sql);
							//预编译语句对象
							ps.setString(1, jtf2.getText());
							ps.setString(2, jtf3.getText());
							ps.setString(3, jtf4.getText());
							ps.setString(4, jtf5.getText());
							ps.setString(5, jtf6.getText());
							ps.setString(6, jtf7.getText());
							ps.setString(7, jtf1.getText());
							
							//4、执行(如果是增加，删除，修改使用executeUpdate();查询executeQuery)
							ps.executeUpdate();
						}catch(Exception e2){
							
						}finally{
							//关闭资源（关闭顺序：谁后创建则先关闭)
							try{
								if(rs!=null) rs.close();
								if(ps!=null) ps.close();
								if(ct!=null) ct.close();
							}catch(Exception e1){	
							}
						}
					 new Doctor_inf();
					 this.dispose();
			}else if(e.getSource()==jb2){
				this.dispose();
			}
		}
}
