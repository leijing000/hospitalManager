package com.administrator;
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
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

public class Medicine extends JFrame implements ActionListener{
	//定义控件
	JPanel jp1,jp2;
	JLabel jl1;
	JButton jb1,jb2,jb3,jb4;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;
	MedicineModel mm;
//	public static void main(String[] args){
//		new Medicine();
//	}
	public static int rownum;

	//构造函数:用于初始化数据模型
	public Medicine()
	{
		jp1=new JPanel();
		jtf=new JTextField(10);
		jb1=new JButton("查询");
		jb1.addActionListener(this);
		jl1=new JLabel("请输入药品名");
		
		//把各个控件加入到jp1
		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(jb1);
		
		jp2=new JPanel();
		
		jb2=new JButton("添加");
		jb2.addActionListener(this);
		jb3=new JButton("修改");
		jb3.addActionListener(this);
		jb4=new JButton("删除");
		jb4.addActionListener(this);
		//把各个按钮加入到jp2中
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
		//创建一个数据模型对象
		MedicineModel model=new MedicineModel();
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
		this.setTitle("药品管理");
		this.setVisible(true);	
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		//判断是哪个按钮被点击
		if(arg0.getSource()==jb1){
			//因为把对表的数据封装，能较简单完成查询操作
			String name=this.jtf.getText().trim();//trim用来过滤空字符串
			//写一个sql语句
			String sql="select * from Medicine where Medicinename ='"+name+"'";
			//构建新的数据模型类，并更新
			mm=new MedicineModel(sql);
			//更新JTable(setModel函数会自动更新)
			jt.setModel(mm);
		}
		////当用户点击添加
		else if(arg0.getSource()==jb2){
			MedicineAddDialog pd=new MedicineAddDialog(this,"添加药品信息",true);
			//重新再获得新的数据模型
			mm=new MedicineModel();
			//更新JTable(setModel函数会自动更新)
			jt.setModel(mm);
		}else if(arg0.getSource()==jb3){
			//用户希望修改
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return ;
			}
			mm=new MedicineModel();
			//显示修改对话框
			new MedicineUpdateDialog(this,"修改药品信息",true,mm,rowNum);
			mm=new MedicineModel();
			//更新JTable(setModel函数会自动更新)
			jt.setModel(mm);
		}else if(arg0.getSource()==jb4){
			//说明用户希望删除记录
			//1、得到该学生的id
			//getSelectedRows会返回用户点中的行，如果该用户一行都没选择，就返回-1
			rownum=this.jt.getSelectedRow();
			if(rownum==-1){
				//提示
				JOptionPane.showMessageDialog(this, "请选择一行");
				return ;//谁调用就返回到哪
			}
			mm=new MedicineModel();
			//得到编号
			String PID=(String)mm.getValueAt(rownum, 0);
			
			//创建一个SQL语句
			String sql="delete from Medicine where MedicineID=?";
			String[] paras={PID};
			mm=new MedicineModel();
			mm.updateMedicine(sql, paras);
			
			//更新数据模型
			mm=new MedicineModel();
			//更新JTable(setModel函数会自动更新)
			jt.setModel(mm);
		}
	}
}


