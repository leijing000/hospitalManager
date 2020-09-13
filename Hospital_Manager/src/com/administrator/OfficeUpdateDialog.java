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

public class OfficeUpdateDialog extends JDialog implements ActionListener{
	
	//������Ҫ�Ŀؼ�
	JLabel jl1,jl2,jl3,jl4;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4;
	JPanel jp1,jp2,jp3,jp4,jp5;
	
	//owner�����ĸ����ڣ�title��������modelָ����ģʽ���ڻ��Ƿ�ģʽ����
	//ģʽ��ģʽ�����ڵ㿪���ܲ����ٵ��Ĵ���
	public OfficeUpdateDialog(Frame owner,String title,boolean model,OfficeModel mm,int rowNum){
		super(owner,title,model);//���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		
		jl1=new JLabel("����ID   ");		
		jl2=new JLabel("��������");		
		jl3=new JLabel("��������");		
		jl4=new JLabel("������    ");		
		
		jtf1=new JTextField(10);
		//��ʼ����
		jtf1.setText((String) mm.getValueAt(rowNum, 0));
		//��jtf1�����޸�
		jtf1.setEditable(false);
		jtf2=new JTextField(10);
		jtf2.setText((String) mm.getValueAt(rowNum, 1));
		jtf3=new JTextField(10);
		jtf3.setText((String) mm.getValueAt(rowNum, 2));
		jtf4=new JTextField(10);
		jtf4.setText((String) mm.getValueAt(rowNum, 3));
		
		jb1=new JButton("ȷ���޸�");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
		jb2.addActionListener(this);
		
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		jp4=new JPanel();
		jp5=new JPanel();
		
		jp1.add(jl1);
		jp1.add(jtf1);
		
		jp2.add(jl2);
		jp2.add(jtf2);
		
		jp3.add(jl3);
		jp3.add(jtf3);
		
		jp4.add(jl4);
		jp4.add(jtf4);
	
		jp5.add(jb1);
		jp5.add(jb2);
		
		this.setLayout(new GridLayout(5,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		this.add(jp5);
		
		//չ��
		this.setSize(400,300);
		this.setLocationRelativeTo(null);// ����
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
				 //��һ��SQL
				 String sql="update Office set Officehead=?,Officename=?,Roomnum=? where OfficeID=?";
				 String []paras={jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf1.getText()};
				 OfficeModel mm=new OfficeModel();
				 mm.updateOffice(sql, paras);
				 this.dispose();
		}else if(e.getSource()==jb2){
			this.dispose();
		}
	}
}

