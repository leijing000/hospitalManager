package com.administrator;

/*
 * 修改信息界面
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
	
	//定义需要的控件
	JLabel jl1,jl2,jl3,jl4;
	JButton jb1,jb2;
	JTextField jtf1,jtf2,jtf3,jtf4;
	JPanel jp1,jp2,jp3,jp4,jp5;
	
	//owner是它的父窗口，title窗口名，model指定是模式窗口还是非模式窗口
	//模式非模式：窗口点开后能不能再点别的窗口
	public OfficeUpdateDialog(Frame owner,String title,boolean model,OfficeModel mm,int rowNum){
		super(owner,title,model);//调用父类构造方法，达到模式对话框效果
		
		jl1=new JLabel("科室ID   ");		
		jl2=new JLabel("科室主任");		
		jl3=new JLabel("科室名称");		
		jl4=new JLabel("病房数    ");		
		
		jtf1=new JTextField(10);
		//初始数据
		jtf1.setText((String) mm.getValueAt(rowNum, 0));
		//让jtf1不能修改
		jtf1.setEditable(false);
		jtf2=new JTextField(10);
		jtf2.setText((String) mm.getValueAt(rowNum, 1));
		jtf3=new JTextField(10);
		jtf3.setText((String) mm.getValueAt(rowNum, 2));
		jtf4=new JTextField(10);
		jtf4.setText((String) mm.getValueAt(rowNum, 3));
		
		jb1=new JButton("确认修改");
		jb1.addActionListener(this);
		jb2=new JButton("取消");
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
		
		//展现
		this.setSize(400,300);
		this.setLocationRelativeTo(null);// 居中
		this.setVisible(true);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
				 //做一个SQL
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

