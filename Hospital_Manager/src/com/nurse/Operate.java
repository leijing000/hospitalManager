package com.nurse;
/*
 * 主界面，可查询，可删除
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
	//定义控件
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
	//构造函数:用于初始化数据模型
	public Operate()
	{
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入");
		String[] find={"医生姓名","病人姓名"};
		Find=new JComboBox(find);
		
		//把各个控件加入到jp1
		jp1.add(jl1);
		jp1.add(Find);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		
		//创建一个数据模型对象
		OperateModel model=new OperateModel();
		//初始化JTable
		jt = new JTable(model);
		
		//初始化jSP
		jsp = new JScrollPane(jt);
		
		//把jsp放入到JFrame
		this.add(jsp);
		this.add(jp1,"North");
		this.add(jp2,"South");
		this.setSize(700,500);
		this.setLocationRelativeTo(null);// 居中
		this.setTitle("手术管理");
		this.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//判断是哪个按钮被点击
		if(arg0.getSource()==jb1){
			if(Find.getSelectedItem().toString()=="医生姓名"){
				//因为把对表的数据封装，能较简单完成查询操作
				String name=this.jtf.getText().trim();//trim用来过滤空字符串
				//写一个sql语句
				String sql="select * from Operate where Doctorname ='"+name+"'";
				//构建新的数据模型类，并更新
				mm=new OperateModel(sql);
				//更新JTable(setModel函数会自动更新)
				jt.setModel(mm);
			}else if(Find.getSelectedItem().toString()=="病人姓名"){
				//因为把对表的数据封装，能较简单完成查询操作
				String name=this.jtf.getText().trim();//trim用来过滤空字符串
				//写一个sql语句
				String sql="select * from Operate where Patientname ='"+name+"'";
				//构建新的数据模型类，并更新
				mm=new OperateModel(sql);
				//更新JTable(setModel函数会自动更新)
				jt.setModel(mm);
			}
		}
	}
}