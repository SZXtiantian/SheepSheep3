package view;

import java.awt.GridLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import model.User;

public class Selet extends JFrame implements java.io.Serializable, ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	JButton jb1, jb2, jb3; // 按钮
	JLabel jlb1, jlb2, jlb3, jlb4, jlb5, jlb6; // 标签
	JTextField jtf3, jtf4; // 文本框
	JPasswordField jpf; // 密码框
	JPanel jp3, jp4, jp6, jp7; // 面板
	Socket socket;

	public Selet(Socket socket) {
		this.socket = socket;
		jb1 = new JButton("再来一局");
		jb2 = new JButton("查看战绩");
		jb3 = new JButton("退出");
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb3.addActionListener(this);
		jp6 = new JPanel();
		jp6.add(jb1);
		jp6.add(jb2);
		jp6.add(jb3);
		this.add(jp6);

		this.setTitle("请选择");
		this.setLayout(new GridLayout(1, 1, 1, 3));
		this.setSize(260, 100);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);

		this.setVisible(false); 
		this.setResizable(false);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand() == "再来一局") {
			System.out.println("再来一局");
			new Start(Start.user);
			dispose();
		} else if (e.getActionCommand() == "查看战绩") {
			System.out.println("查看战绩");
			try {
				System.out.println(Start.user.getWinNum());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				JOptionPane.showMessageDialog(null,
						"你的胜局：" + Start.user.getWinNum() + "\n你的积分：" + Start.user.getScore(),
						"玩家" + Start.user.getName() + "的历史战绩", JOptionPane.INFORMATION_MESSAGE);
			} catch (HeadlessException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		} else if (e.getActionCommand() == "退出") {
			System.out.println("退出");
			System.out.println(Start.user.getMySocket());
			String string = Start.user.getName();
			User user1 = null;
			user1 = new User();
			user1.setName(string);
			user1.setType(-1);
			ObjectOutputStream os = null;
			try {
				os = new ObjectOutputStream(socket.getOutputStream());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				os.writeObject(user1);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			try {
				os.flush();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			dispose();
			System.exit(0);

		}

	}
}
