package com.doctor;

/*
 * �޸���Ϣ����
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import com.login.Login_adm;
import com.login.Login_doctor;

public class Doctor_inf extends JDialog implements ActionListener{
	
	//������Ҫ�Ŀؼ�
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7;
	JPanel jp1,jp2,jp3,jp4;
	
	//�������ݿ�Ҫ�õĶ���
	Connection ct=null;
	PreparedStatement ps= null;	
	ResultSet rs=null;
	String driver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url="jdbc:sqlserver://localhost:1433;databaseName=hospital";
	String user="aaa";
	String password="111";
			
	//owner�����ĸ����ڣ�title��������modelָ����ģʽ���ڻ��Ƿ�ģʽ����
	//ģʽ��ģʽ�����ڵ㿪���ܲ����ٵ��Ĵ���
	public Doctor_inf(){
		
		jl1=new JLabel("ҽ��ID");		
		jl2=new JLabel("����ID");		
		jl3=new JLabel("ҽ������");		
		jl4=new JLabel("�Ա�");		
		jl5=new JLabel("����");		
		jl6=new JLabel("ְ��");
		jl7=new JLabel("ѧ��");
		
		jb1=new JButton("�޸�");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
	
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jtf3=new JTextField(10);
		jtf4=new JTextField(10);
		jtf5=new JTextField(10);
		jtf6=new JTextField(10);
		jtf7=new JTextField(10);
		
		try{
			//1����������(������Ҫ��������������ڴ���)
			Class.forName(driver);
			//2���õ�����(ָ�����ӵ��ĸ�����Դ)
			ct = DriverManager.getConnection(url,user,password); 
			//3������ps
			ps=ct.prepareStatement("select * from Doctor where DoctorID="+Login_doctor.userId+"");
			//Ԥ����������
			rs=ps.executeQuery();//���ز�ѯ���
			//����в�ѯ���
			while(rs.next()){
				//��ʼ����
				jtf1.setText(rs.getString(1));
				//��jtf1�����޸�
				jtf1.setEditable(false);
				jtf2.setText(rs.getString(2));
				jtf2.setEditable(false);
				jtf3.setText(rs.getString(3));
				jtf3.setEditable(false);
				jtf4.setText(rs.getString(4));
				jtf4.setEditable(false);
				jtf5.setText(rs.getString(5));
				jtf5.setEditable(false);
				jtf6.setText(rs.getString(6));
				jtf6.setEditable(false);
				jtf7.setText(rs.getString(7));
				jtf7.setEditable(false);
			}
		}catch(Exception e2){
			
		}finally{
			//�ر���Դ���ر�˳��˭�󴴽����ȹر�)
			try{
				if(rs!=null) rs.close();
				if(ps!=null) ps.close();
				if(ct!=null) ct.close();
			}catch(Exception e1){	
			}
		}	 
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
		
		jp3.add(jl5);
		jp3.add(jtf5);
		jp3.add(jl6);
		jp3.add(jtf6);
		jp3.add(jl7);
		jp3.add(jtf7);
		
		jp4.add(jb1);
		jp4.add(jb2);
		
		this.setLayout(new GridLayout(4,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		//չ��
		this.setSize(500,300);
		this.setTitle("�ҵ���Ϣ");
		this.setLocationRelativeTo(null);// ����
		this.setVisible(true);
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			new Doctor_update(this,"�޸ĸ�����Ϣ",true);
			this.dispose();
		}else if(e.getSource()==jb2){
			this.dispose();
		}
	}
	
	
}
