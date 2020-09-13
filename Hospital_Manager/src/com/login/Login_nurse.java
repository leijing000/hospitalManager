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
	    //�������
		JLabel jlb1,jlb2;
		JButton jb1,jb2;
		JTextField jtf;
		JPasswordField jpf;
		
		//�������ݿ�Ҫ�õĶ���
		Connection ct=null;
		PreparedStatement ps= null;	
		ResultSet rs=null;
		String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
		String url="jdbc:sqlserver://localhost:1433;databaseName=hospital";
		String user="aaa";
		String password="111";
				
		//���캯��
		public Login_nurse()
		{

			jlb1=new JLabel("�û���:");
			jlb2=new JLabel("��  �� :");
			jb1=new JButton("��¼");
			jb1.addActionListener(this);
			jb2=new JButton("ȡ��");
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
			
			//���ñ���
			this.setTitle("��ʿ��¼");
			//���ô�С
			this.setSize(360, 240);
			this.setLocationRelativeTo(null);// ����
			this.setResizable(false);// �̶����ڴ�С
			//���ÿɼ�
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
				//�������ݿ�
				Connection ct=null;
				PreparedStatement ps= null;	
				ResultSet rs=null;
				if(users.length()==0){
					JOptionPane.showMessageDialog((Component)null,"�����������û���","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
					jlb1.requestFocus();
				}else if(passwords.length()==0){
					JOptionPane.showMessageDialog((Component)null,"��������������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
					jlb2.requestFocus();
				}else{
					try{
						//1����������(������Ҫ��������������ڴ���)
						Class.forName(driver);
						//2���õ�����(ָ�����ӵ��ĸ�����Դ)
						ct = DriverManager.getConnection(url,user,password); 
						//3������ps
						ps=ct.prepareStatement("select * from Nurse where NurseID="+users+"");
						//Ԥ����������
						rs=ps.executeQuery();//���ز�ѯ���
						//����в�ѯ���
						rs.next();
						String sql_pass=rs.getString(7).trim();
						userId=rs.getString(1).trim();
						if(!rs.next()){
							if(passwords.equals(sql_pass)){
								System.out.println("����"+passwords);
								new N_homepage();
								this.dispose();
							}else{
								JOptionPane.showMessageDialog((Component)null,"�������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
								jpf.requestFocus();
							}
						}
					}catch(Exception e2){
						JOptionPane.showMessageDialog((Component)null,"�û�������","��ʾ��Ϣ",JOptionPane.ERROR_MESSAGE);
						jlb1.requestFocus();
					}finally{
						//�ر���Դ���ر�˳��˭�󴴽����ȹر�)
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
