package com.login;

import java.awt.Component;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class Login_nurse extends JFrame implements ActionListener{
		public static String userId;
	    //定义组件
		JLabel jlb1,jlb2;
		JButton jb1,jb2;
		JTextField jtf;
		JPasswordField jpf;
		
		//连接数据库要用的东西
		Connection ct=null;
		PreparedStatement ps= null;	
		ResultSet rs=null;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;databaseName=hospital";
		String user="aaa";
		String password="111";
				
		//构造函数
		public Login_nurse()
		{

			jlb1=new JLabel("用户名:");
			jlb2=new JLabel("密  码 :");
			jb1=new JButton("登录");
			jb1.addActionListener(this);
			jb2=new JButton("取消");
			jb2.addActionListener(this);
			
			jtf=new JTextField(10);
			jpf=new JPasswordField(10);
			
			JPanel jp=(JPanel)this.getContentPane();
			jp.setLayout(null);

			jlb1.setBounds(130, 40, 80, 40);
			jlb2.setBounds(130, 85, 80, 40);
			jtf.setBounds(180, 50, 160, 30);
			jpf.setBounds(180, 95, 160, 30);
			jb1.setBounds(150, 150, 80, 30);
			jb2.setBounds(240, 150, 80, 30);
			jp.add(jlb1);
			jp.add(jlb2);
			jp.add(jb1);
			jp.add(jb2);
			jp.add(jtf);
			jp.add(jpf);

			JLabel bg=new JLabel(new ImageIcon("image/login1.jpg"));
			bg.setBounds(0,0,355,220);
			jp.add(bg);
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("image/icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//设置标题
			this.setTitle("护士登录");
			//设置大小
			this.setSize(360, 240);
			this.setLocationRelativeTo(null);// 居中
			this.setResizable(false);// 固定窗口大小
			//设置可见
			this.setVisible(true);
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==jb2){
				dispose();
			}else if(e.getSource()==jb1){
				String users=jtf.getText();  
				String passwords=jpf.getText();
				//连接数据库
				Connection ct=null;
				PreparedStatement ps= null;	
				ResultSet rs=null;
				if(users.length()==0){
					JOptionPane.showMessageDialog((Component)null,"请输入您的用户名","提示信息",JOptionPane.ERROR_MESSAGE);
					jlb1.requestFocus();
				}else if(passwords.length()==0){
					JOptionPane.showMessageDialog((Component)null,"请输入您的密码","提示信息",JOptionPane.ERROR_MESSAGE);
					jlb2.requestFocus();
				}else{
					try{
						//1、加载驱动(把下需要的驱动程序加入内存中)
						Class.forName(driver);
						//2、得到连接(指定连接到哪个数据源)
						ct = DriverManager.getConnection(url,user,password); 
						//3、创建ps
						ps=ct.prepareStatement("select * from Nurse where NurseID="+users+"");
						//预编译语句对象
						rs=ps.executeQuery();//返回查询结果
						//如果有查询结果
						rs.next();
						String sql_pass=rs.getString(7).trim();
						userId=rs.getString(1).trim();
						if(!rs.next()){
							if(passwords.equals(sql_pass)){
								System.out.println("密码"+passwords);
								new N_homepage();
								this.dispose();
							}else{
								JOptionPane.showMessageDialog((Component)null,"密码错误","提示信息",JOptionPane.ERROR_MESSAGE);
								jpf.requestFocus();
							}
						}
					}catch(Exception e2){
						JOptionPane.showMessageDialog((Component)null,"用户不存在","提示信息",JOptionPane.ERROR_MESSAGE);
						jlb1.requestFocus();
					}finally{
						//关闭资源（关闭顺序：谁后创建则先关闭)
						try{
							if(rs!=null) rs.close();
							if(ps!=null) ps.close();
							if(ct!=null) ct.close();
						}catch(Exception e1){
							
						}
					}
				}
			}
		}
}
