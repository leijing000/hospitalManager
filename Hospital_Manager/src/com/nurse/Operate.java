package com.nurse;
/*
 * �����棬�ɲ�ѯ����ɾ��
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

public class Operate extends JFrame implements ActionListener{
	//����ؼ�
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	JComboBox Find;
	OperateModel mm;
//	 public static void main(String[] args){
//		   new Operate();
//	   }
	public static int rownum;
	//���캯��:���ڳ�ʼ������ģ��
	public Operate()
	{
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("��ѯ");
		jb1.addActionListener(this);
		jl1=new JLabel("������");
		String[] find={"ҽ������","��������"};
		Find=new JComboBox(find);
		
		//�Ѹ����ؼ����뵽jp1
		jp1.add(jl1);
		jp1.add(Find);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		
		//����һ������ģ�Ͷ���
		OperateModel model=new OperateModel();
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
		this.setTitle("��������");
		this.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//�ж����ĸ���ť�����
		if(arg0.getSource()==jb1){
			if(Find.getSelectedItem().toString()=="ҽ������"){
				//��Ϊ�ѶԱ�����ݷ�װ���ܽϼ���ɲ�ѯ����
				String name=this.jtf.getText().trim();//trim�������˿��ַ���
				//дһ��sql���
				String sql="select * from Operate where Doctorname ='"+name+"'";
				//�����µ�����ģ���࣬������
				mm=new OperateModel(sql);
				//����JTable(setModel�������Զ�����)
				jt.setModel(mm);
			}else if(Find.getSelectedItem().toString()=="��������"){
				//��Ϊ�ѶԱ�����ݷ�װ���ܽϼ���ɲ�ѯ����
				String name=this.jtf.getText().trim();//trim�������˿��ַ���
				//дһ��sql���
				String sql="select * from Operate where Patientname ='"+name+"'";
				//�����µ�����ģ���࣬������
				mm=new OperateModel(sql);
				//����JTable(setModel�������Զ�����)
				jt.setModel(mm);
			}
		}
	}
}