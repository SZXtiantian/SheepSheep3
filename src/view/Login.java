package view;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.*;

import model.IsLegal;
import model.User;

@SuppressWarnings("serial")
public class Login extends JFrame implements ActionListener {

	JButton jb1, jb2, jb3; // 按钮
	JPanel jp1, jp2, jp3, jp4; // 面板
	JTextField jtf; // 文本框
	JLabel jlb1, jlb2, jlb3; // 标签
	JPasswordField jpf; // 密码框
	Socket socket;

	public Login(Socket socket) {
		
		// TODO Auto-generated constructor stub
		this.socket = socket;
		
		jb1 = new JButton("登录");
		jb2 = new JButton("注册");
		// 设置按钮监听
		jb1.addActionListener(this);
		jb2.addActionListener(this);

		jp1 = new JPanel();
		jp2 = new JPanel();
		jp3 = new JPanel();
		jp4 = new JPanel();

		jlb1 = new JLabel("用户名");
		jlb2 = new JLabel("  密  码");

		jtf = new JTextField(10); 
		jpf = new JPasswordField(10);

		// 加入面板中
		jp1.add(jlb1);
		jp1.add(jtf);

		jp2.add(jlb2);
		jp2.add(jpf);

		jp3.add(jb1);
		jp3.add(jb2);

		// 将JPane加入JFrame中
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);

		// 设置布局
		this.setTitle("用户登录");
		this.setLayout(new GridLayout(3, 1));
		this.setSize(280, 170);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE); 

		this.setVisible(true); 
		this.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "登录") {
			try {
				login(socket);
			} catch (HeadlessException | IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getActionCommand() == "注册") {
			new Register(socket);
			dispose(); 
		}
	}
	private void clear() {
		// TODO Auto-generated method stub
		jtf.setText(""); // 设置为空
		jpf.setText("");

	}

	@SuppressWarnings("deprecation")
	public  void login(Socket socket) throws HeadlessException, IOException
	{

		if (new Check().checkcountname(jtf.getText())||new Check().checkcountname(jpf.getText()))
		{
			JOptionPane.showMessageDialog(null, "用户名或密码存在中文，不合法!","消息提示",JOptionPane.WARNING_MESSAGE);
		}
		else if(jtf.getText().isEmpty()&&jpf.getText().isEmpty())
		{
			JOptionPane.showMessageDialog(null, "账号密码为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
		}
		else if (jtf.getText().isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "账号为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
		}
		else if (jpf.getText().isEmpty()) 
		{
			JOptionPane.showMessageDialog(null, "密码为空，请输入！","消息提示",JOptionPane.WARNING_MESSAGE);
			
		}
		else {
			ObjectOutputStream os = new ObjectOutputStream(socket.getOutputStream());           
			User user = new User(jtf.getText(), jpf.getText(), 0);
			System.out.println(user);
			os.writeObject(user);
			os.flush();
			
			
			Object object = null;
			ObjectInputStream objectInputStream = 
					new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
			try {
				object = objectInputStream.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			IsLegal isLegal = (IsLegal)object;
			if (isLegal.isIslegal())
			{
				JOptionPane.showMessageDialog(null,"登录成功！",
						"提示消息",JOptionPane.WARNING_MESSAGE);
				dispose();
				User user1 = 
						new User(jtf.getText(), UserMessage.getWinNum(jtf.getText()),socket);
				new Start(user1);
			}
			else
			{
				JOptionPane.showMessageDialog(null, "账号密码错误请重新输入！",
						"消息提示",JOptionPane.ERROR_MESSAGE);
				clear();
			}
		}
		
	}

}