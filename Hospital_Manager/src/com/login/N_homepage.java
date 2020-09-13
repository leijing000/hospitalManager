package com.login;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.administrator.Patient;
import com.administrator.Room;
import com.nurse.Nurse_inf;
import com.nurse.Operate;

public class N_homepage extends JFrame implements ActionListener{
		//定义一些组件
		JLabel lb1,lb2,lb3,lb4,lb5,bg;
		JButton jb1,jb2,jb3,jb4;
		JPanel jp;
		//构造函数
		public N_homepage(){
			
			lb1=new JLabel(new ImageIcon("images/guanli.png"));
			lb1.setBounds(70,100,80,80);
			jb1=new JButton("个人信息");
			jb1.setBounds(60, 200, 100, 30);
			jb1.addActionListener(this);
			
			lb2=new JLabel(new ImageIcon("images/bingren.png"));
			lb2.setBounds(220,100,80,80);
			jb2=new JButton("病人管理");
			jb2.setBounds(210, 200, 100, 30);
			jb2.addActionListener(this);
			
			lb3=new JLabel(new ImageIcon("images/bingchuang.png"));
			lb3.setBounds(370,100,80,80);
			jb3=new JButton("病房管理");
			jb3.setBounds(360, 200, 100, 30);
			jb3.addActionListener(this);
			
			lb4=new JLabel(new ImageIcon("images/shoushu.png"));
			lb4.setBounds(520,100,80,80);
			jb4=new JButton("查看手术安排");
			jb4.setBounds(510, 200, 120, 30);
			jb4.addActionListener(this);
			
			lb5=new JLabel(new ImageIcon("image/bg2.jpg"));
			lb5.setBounds(0,0,700,400);
			
			JPanel jp=(JPanel)this.getContentPane();
			jp.setLayout(null);
			
			jp.add(lb1);
			jp.add(lb2);
			jp.add(lb3);
			jp.add(lb4);
			jp.add(jb1);
			jp.add(jb2);
			jp.add(jb3);
			jp.add(jb4);
			jp.add(lb5);
			
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("image/icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//设置标题
			this.setTitle("我的管理（护士）");
			//设置大小
			this.setSize(700, 400);
			//设置可见
			this.setVisible(true);
			this.setLocationRelativeTo(null);// 居中
		}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new N_homepage();	
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			new Nurse_inf();
		}else if(e.getSource()==jb2){
			new Patient();
		}else if(e.getSource()==jb3){
			new Room();
		}else if(e.getSource()==jb4){
			new Operate();
		}
	}
}
