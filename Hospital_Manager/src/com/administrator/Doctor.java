package com.administrator;
import java.io.*;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * �����棬�ɲ�ѯ����ɾ��
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import com.login.choose_login;


public class Doctor extends JFrame implements ActionListener{
	
	public static void main(String[] args) {
		new Doctor();
	}
	
	//����ؼ�
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	Model_doctor mm;
	
	public static int rownum;

	//���캯��:���ڳ�ʼ������ģ��
	public Doctor()
	{
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1=new JLabel("����������");
		//�Ѹ����ؼ����뵽jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		
		jb2=new JButton("���");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
//		jb5=new JButton("����");
//		jb5.addActionListener(this);
		//�Ѹ�����ť���뵽jp2��
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		//jp2.add(jb5);
		
		
		//����һ������ģ�Ͷ���
		Model_doctor model=new Model_doctor();
		//��ʼ��JTable
		jt = new JTable(model);
		
		//��ʼ��jSP
		jsp = new JScrollPane(jt);
		
		//��jsp���뵽JFrame
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);// ����
		this.setTitle("ҽ������");
		this.setVisible(true);	
	}
	//����EXCEL��

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//�ж����ĸ���ť�����
		if(arg0.getSource()==jb1){
				//��Ϊ�ѶԱ�����ݷ�װ���ܽϼ���ɲ�ѯ����
				String name=this.jtf.getText().trim();//trim�������˿��ַ���
				//дһ��sql���
				String sql="select * from Doctor where Doctorname ='"+name+"'";
				//�����µ�����ģ���࣬������
				mm=new Model_doctor(sql);
				//����JTable(setModel�������Զ�����)
				jt.setModel(mm);	
		}
		////���û�������
		else if(arg0.getSource()==jb2){
			DoctorAddDialog pd=new DoctorAddDialog(this,"���ҽ����Ϣ",true);
			//�����ٻ���µ�����ģ��
			mm=new Model_doctor();
			//����JTable(setModel�������Զ�����)
			jt.setModel(mm);
		}else if(arg0.getSource()==jb3){
			//�û�ϣ���޸�
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;
			}
			mm=new Model_doctor();
			//��ʾ�޸ĶԻ���
			new DoctorUpdateDailog(this,"�޸�ҽ����Ϣ",true,mm,rowNum);
			mm=new Model_doctor();
			//����JTable(setModel�������Զ�����)
			jt.setModel(mm);
		}else if(arg0.getSource()==jb4){
			//˵���û�ϣ��ɾ����¼
			//1���õ���ѧ����id
			//getSelectedRows�᷵���û����е��У�������û�һ�ж�ûѡ�񣬾ͷ���-1
			rownum=this.jt.getSelectedRow();
			if(rownum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;//˭���þͷ��ص���
			}
			mm=new Model_doctor();
			//�õ����
			String PID=(String)mm.getValueAt(rownum, 0);
			
			//����һ��SQL���
			String sql="delete from Doctor where DoctorID=?";
			String[] paras={PID};
			mm=new Model_doctor();
			mm.updateDoctor(sql, paras);
			
			//��������ģ��
			mm=new Model_doctor();
			//����JTable(setModel�������Զ�����)
			jt.setModel(mm);
		}
	}
	
}


