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

public class choose_login extends JFrame implements ActionListener{

	JButton jlb1;
	JButton jlb2;
	JButton jlb3;
	public choose_login(){
		jlb1=new JButton("管理员登录");
		jlb1.addActionListener(this);
		jlb2=new JButton("医生登录");
		jlb2.addActionListener(this);
		jlb3=new JButton("护士登录");
		jlb3.addActionListener(this);
		
		jlb1.setBounds(120, 260, 100, 30);
		jlb2.setBounds(300, 260, 100, 30);
		jlb3.setBounds(480, 260, 100, 30);
		
		JPanel jp=(JPanel)this.getContentPane();
		jp.setLayout(null);
		

		JLabel name=new JLabel("医院管理系统");
		name.setBounds(230,30,230,40);
		name.setFont(new Font("宋体", Font.BOLD, 35));
		JLabel bg1=new JLabel(new ImageIcon("images/guanli1.png"));
		bg1.setBounds(110,120,120,120);
		JLabel bg2=new JLabel(new ImageIcon("images/yisheng1.png"));
		bg2.setBounds(290,120,120,120);
		JLabel bg3=new JLabel(new ImageIcon("images/hushi1.png"));
		bg3.setBounds(470,120,120,120);
		JLabel bg4=new JLabel(new ImageIcon("images/beijing3.jpg"));
		bg4.setBounds(0,0,700,450);
		
		
		jp.add(jlb1);
		jp.add(jlb2);
		jp.add(jlb3);
		jp.add(name);
		jp.add(bg1);
		jp.add(bg2);
		jp.add(bg3);
		jp.add(bg4);
		
		jp.setOpaque(false);
		
		ImageIcon imageIcon = new ImageIcon("image/icon.png");
		Image _image = imageIcon.getImage();
		this.setIconImage(_image);
		
		//设置标题
		this.setTitle("医院管理系统");
		//设置大小
		this.setSize(700, 500);
		//设置可见
		this.setVisible(true);
		this.setLocationRelativeTo(null);// 居中
		//点关闭按钮时退出
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new choose_login();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jlb1){
			new Login_adm();
		}else if(e.getSource()==jlb2){
			new Login_doctor();
		}else if(e.getSource()==jlb3){
			new Login_nurse();
		}	
	}
}
