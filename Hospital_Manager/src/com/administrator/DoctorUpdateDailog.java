package com.administrator;

/*
 * �޸���Ϣ����
 */
import java.awt.BorderLayout;
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
import javax.swing.JPanel;
import javax.swing.JTextField;

public class DoctorUpdateDailog extends JDialog implements ActionListener{
	
	//������Ҫ�Ŀؼ�
	JLabel jl1,jl2,jl3,jl4,jl5,jl6,jl7,jl8;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6,jtf7,jtf8;
	JPanel jp1,jp2,jp3,jp4;
	
	//owner�����ĸ����ڣ�title��������modelָ����ģʽ���ڻ��Ƿ�ģʽ����
	//ģʽ��ģʽ�����ڵ㿪���ܲ����ٵ��Ĵ���
	public DoctorUpdateDailog(Frame owner,String title,boolean model,Model_doctor mm,int rowNum){
		super(owner,title,model);//���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		
		jl1=new JLabel("ҽ��ID");		
		jl2=new JLabel("����ID");		
		jl3=new JLabel("����");		
		jl4=new JLabel("�Ա�");		
		jl5=new JLabel("����");		
		jl6=new JLabel("ְ��");
		jl7=new JLabel("ѧ��");
		jl8=new JLabel("����");
		
		jtf1=new JTextField(8);
		//��ʼ����
		jtf1.setText((String) mm.getValueAt(rowNum, 0));
		//��jtf1�����޸�
		jtf1.setEditable(false);
		jtf2=new JTextField(8);
		jtf2.setText((String) mm.getValueAt(rowNum, 1));
		jtf3=new JTextField(8);
		jtf3.setText((String) mm.getValueAt(rowNum, 2));
		jtf4=new JTextField(8);
		jtf4.setText((String) mm.getValueAt(rowNum, 3));
		jtf5=new JTextField(8);
		jtf5.setText((String) mm.getValueAt(rowNum, 4));
		jtf6=new JTextField(8);
		jtf6.setText((String) mm.getValueAt(rowNum, 5));
		jtf7=new JTextField(8);
		jtf7.setText((String) mm.getValueAt(rowNum, 6));
		jtf8=new JTextField(8);
		jtf8.setText((String) mm.getValueAt(rowNum, 7));
		
		jb1=new JButton("ȷ���޸�");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		
		this.setLayout(new GridLayout(4,1));
		
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
		
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		//չ��
		this.setSize(550,300);
		this.setLocationRelativeTo(null);// ����
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
				 //��һ��SQL
				 String sql="update Doctor set OfficeID=?,Doctorname=?,Doctorsex=?,doctorage=?,doctorlevel=?,doctordegree=?,Doctorpass=? where DoctorID=?";
				 String []paras={jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText(),jtf7.getText(),jtf8.getText(),jtf1.getText()};
				 Model_doctor mm=new Model_doctor();
				 mm.updateDoctor(sql, paras);
				 this.dispose();
		}else if(e.getSource()==jb2){
			this.dispose();
		}
	}
}

