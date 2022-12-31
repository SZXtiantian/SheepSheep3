package view;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;

import javax.swing.JOptionPane;

import model.User;

public class Select {
	public Select(Socket socket) {
        Object[] list = {"查看战绩", "退出游戏"};
        Object objResult = JOptionPane.showOptionDialog(null, "请选择：", "hhhhhh~",
                JOptionPane.YES_NO_CANCEL_OPTION, 2, null, list, list[0]);
        if ((int)objResult == 1) {
        	System.out.println("3");
			System.out.println(Start.user.getMySocket());
			User user1 =new User();
			String string = Start.user.getName();
			//user1.setMySocket();
			user1.setName(string);
			user1.setType(-1);
			ObjectOutputStream os = null;
			try {
				os = new ObjectOutputStream(socket.getOutputStream());
				os.reset();
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
			System.exit(0);
        }else{
        	System.out.println("查看战绩");
			try {
				System.out.println(Start.user.getWinNum());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }
	}
	public static void main(String[] args) {
		//new Select();
	}
}
