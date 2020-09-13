package com.administrator;
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
	JButton jb1,jb2,jb3,jb4;
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
		
		jb2=new JButton("���");
		jb2.addActionListener(this);
		jb3=new JButton("�޸�");
		jb3.addActionListener(this);
		jb4=new JButton("ɾ��");
		jb4.addActionListener(this);
		//�Ѹ�����ť���뵽jp2��
		jp2.add(jb2);
		jp2.add(jb3);
		jp2.add(jb4);
		
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
		////���û�������
		else if(arg0.getSource()==jb2){
			OperateAddDialog pd=new OperateAddDialog(this,"���������Ϣ",true);
			//�����ٻ���µ�����ģ��
			mm=new OperateModel();
			//����JTable(setModel�������Զ�����)
			jt.setModel(mm);
		}else if(arg0.getSource()==jb3){
			//�û�ϣ���޸�
			int rowNum=this.jt.getSelectedRow();
			if(rowNum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;
			}
			mm=new OperateModel();
			//��ʾ�޸ĶԻ���
			new OperateUpdateDialog(this,"�޸�������Ϣ",true,mm,rowNum);
			mm=new OperateModel();
			//����JTable(setModel�������Զ�����)
			jt.setModel(mm);
		}else if(arg0.getSource()==jb4){
			//˵���û�ϣ��ɾ����¼
			//1���õ�����id
			//getSelectedRows�᷵���û����е��У�������û�һ�ж�ûѡ�񣬾ͷ���-1
			rownum=this.jt.getSelectedRow();
			if(rownum==-1){
				//��ʾ
				JOptionPane.showMessageDialog(this, "��ѡ��һ��");
				return ;//˭���þͷ��ص���
			}
			mm=new OperateModel();
			//�õ����
			String PID=(String)mm.getValueAt(rownum, 0);
			
			//����һ��SQL���
			String sql="delete from Operate where PatientID=?";
			String[] paras={PID};
			mm=new OperateModel();
			mm.updateOperate(sql, paras);
			
			//��������ģ��
			mm=new OperateModel();
			//����JTable(setModel�������Զ�����)
			jt.setModel(mm);
		}
	}
}