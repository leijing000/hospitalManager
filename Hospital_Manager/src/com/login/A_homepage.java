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

import com.administrator.Doctor;
import com.administrator.Medicine;
import com.administrator.Nurse;
import com.administrator.Office;
import com.administrator.Operate;
import com.administrator.Patient;
import com.administrator.Room;

public class A_homepage extends JFrame implements ActionListener{
		//����һЩ���
		JLabel lb1,lb2,lb3,lb4,lb5,lb6,lb7,lb8,bg;
		JButton jb1,jb2,jb3,jb4,jb5,jb6,jb7;
		JPanel jp;
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			new A_homepage();
		}

		//���캯��
		public A_homepage(){
			
			lb1=new JLabel(new ImageIcon("images/guanli.png"));
			lb1.setBounds(150,70,80,80);
			jb1=new JButton("ҽ������");
			jb1.setBounds(140, 170, 100, 30);
			jb1.addActionListener(this);
			
			lb2=new JLabel(new ImageIcon("images/yisheng.png"));
			lb2.setBounds(300,70,80,80);
			jb2=new JButton("��ʿ����");
			jb2.setBounds(290, 170, 100, 30);
			jb2.addActionListener(this);
			
			lb3=new JLabel(new ImageIcon("images/bingren.png"));
			lb3.setBounds(450,70,80,80);
			jb3=new JButton("���˹���");
			jb3.setBounds(440, 170, 100, 30);
			jb3.addActionListener(this);
			
			lb4=new JLabel(new ImageIcon("images/yaopin.png"));
			lb4.setBounds(80,230,80,80);
			jb4=new JButton("ҩƷ����");
			jb4.setBounds(70, 330, 100, 30);
			jb4.addActionListener(this);
			
			lb5=new JLabel(new ImageIcon("images/bingchuang.png"));
			lb5.setBounds(230,230,80,80);
			jb5=new JButton("��������");
			jb5.setBounds(220, 330, 100, 30);
			jb5.addActionListener(this);
			
			lb6=new JLabel(new ImageIcon("images/keshi.png"));
			lb6.setBounds(380,230,80,80);
			jb6=new JButton("���ҹ���");
			jb6.setBounds(370, 330, 100, 30);
			jb6.addActionListener(this);
			
			lb7=new JLabel(new ImageIcon("images/shoushu.png"));
			lb7.setBounds(530,230,80,80);
			jb7=new JButton("��������");
			jb7.setBounds(520, 330, 100, 30);
			jb7.addActionListener(this);
			
			lb8=new JLabel(new ImageIcon("image/bg2.jpg"));
			lb8.setBounds(0,0,700,500);
			
			JPanel jp=(JPanel)this.getContentPane();
			jp.setLayout(null);
			
			jp.add(lb1);
			jp.add(lb2);
			jp.add(lb3);
			jp.add(lb4);
			jp.add(lb5);
			jp.add(lb6);
			jp.add(lb7);
			jp.add(lb8);
			jp.add(jb1);
			jp.add(jb2);
			jp.add(jb3);
			jp.add(jb4);
			jp.add(jb5);
			jp.add(jb6);
			jp.add(jb7);
			
			jp.setOpaque(false);
			
			ImageIcon imageIcon = new ImageIcon("image/icon.png");
			Image _image = imageIcon.getImage();
			this.setIconImage(_image);
			
			//���ñ���
			this.setTitle("�ҵĹ�������Ա��");
			//���ô�С
			this.setSize(700, 500);
			//���ÿɼ�
			this.setVisible(true);
			this.setLocationRelativeTo(null);// ����
		}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==jb1){
			//��ʾҽ����
			new Doctor();
		}else if(e.getSource()==jb2){
			//��ʾ��ʿ��
			new Nurse();
		}else if(e.getSource()==jb3){
			//��ʾ���˱�
			new Patient();
		}else if(e.getSource()==jb4){
			//��ʾҩƷ��
			new Medicine();
		}else if(e.getSource()==jb5){
			//��ʾ������
			new Room();
		}else if(e.getSource()==jb6){
			//��ʾ���ұ�
			new Office();
		}else if(e.getSource()==jb7){
			//��ʾ������
			new Operate();
		}
	}
}

