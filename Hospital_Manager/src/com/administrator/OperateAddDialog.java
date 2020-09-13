package com.administrator;
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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
/*
 * ������Ϣ����
 */
public class OperateAddDialog extends JDialog implements ActionListener{
	
	//������Ҫ�Ŀؼ�
	JLabel jl1,jl2,jl3,jl4,jl5,jl6;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4,jtf5,jtf6;
	JPanel jp1,jp2,jp3,jp4;
	
	//owner�����ĸ����ڣ�title��������modelָ����ģʽ���ڻ��Ƿ�ģʽ����
	//ģʽ��ģʽ�����ڵ㿪���ܲ����ٵ��Ĵ���
	public OperateAddDialog(Frame owner,String title,boolean model){
		super(owner,title,model);//���ø��๹�췽�����ﵽģʽ�Ի���Ч��
		jl1=new JLabel("����ID   ");
		jl2=new JLabel("ҽ��ID   ");
		jl3=new JLabel("����ʱ��");
		jl4=new JLabel("ҽ������");
		jl5=new JLabel("��������");
		jl6=new JLabel("������    ");
		
		jtf1=new JTextField(10);
		jtf2=new JTextField(10);
		jtf3=new JTextField(10);
		jtf4=new JTextField(10);
		jtf5=new JTextField(10);
		jtf6=new JTextField(10);
		
		jb1=new JButton("ȷ�����");
		jb1.addActionListener(this);
		jb2=new JButton("ȡ��");
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
		
		jp3.add(jl5);
		jp3.add(jtf5);
		jp3.add(jl6);
		jp3.add(jtf6);

		jp4.add(jb1);
		jp4.add(jb2);
		
		this.setLayout(new GridLayout(4,1));
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		this.add(jp4);
		
		//չ��
		this.setSize(400,300);
		this.setLocationRelativeTo(null);// ����
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			//ϣ�����
			OperateModel mm=new OperateModel();
			String sql="insert into Operate values(?,?,?,?,?,?)";
			String[] paras={jtf1.getText(),jtf2.getText(),jtf3.getText(),jtf4.getText(),jtf5.getText(),jtf6.getText()};
			if(!(mm.updateOperate(sql,paras))){
				//��ʾ
				JOptionPane.showMessageDialog(this,"���ʧ��");
			}
			//�رնԻ���
			this.dispose();
		}else if(e.getSource()==jb2){
			this.dispose();
		}
	}
}
