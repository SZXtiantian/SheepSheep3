package test1;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import model.IsLegal;
import model.User;
import view.Check;
import view.UserMessage;

class ExcuteClientServer implements Runnable {
	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;
	private Socket client;
	private Map<String, Socket> clientMap;

	public ExcuteClientServer(Socket client, Map<String, Socket> clientMap) {
		super();
		this.client = client;
		this.clientMap = clientMap;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				ObjectInputStream objectInputStream = new ObjectInputStream(
						new BufferedInputStream(client.getInputStream()));
				Object object = null;
				try {
					object = objectInputStream.readObject();
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				User user = (User) object;
				IsLegal isLegal = null;
				if (user.getType() == 0) {
					isLegal = new IsLegal(new Check().check1(user.getName(), user.getPwd()));
					if (isLegal.isIslegal()) {
						userLoad(user, client);
					}
				} else if (user.getType() == 1) {
					isLegal = new IsLegal(new Check().check2(user.getName()));
					if (isLegal.isIslegal()) {
						String[] message = new String[2];
						message[0] = user.getName();
						message[1] = user.getPwd();
						new UserMessage().write(message);
						userRegist(user.getName(), client);
					}
				} else if (user.getType() == -1) {
					System.out.println("用户名为" + user.getName() + "的用户下线了！！！\n");
					clientMap.remove(user.getName());
					objectInputStream.close();
					client.close();
					break;
				}

				// 获得输出流
				ObjectOutputStream os = new ObjectOutputStream(client.getOutputStream());
				os.writeObject(isLegal);
				os.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void userLoad(User user, Socket client) throws IOException {

		System.out.println("用户姓名为：" + user.getName());
		// System.out.println("用户socket为：" + client);
		System.out.println("用户名为" + user.getName() + "的用户上线了！该用户历史胜利局数为" + user.getWinNum());
		System.out.println("当前用户数为：" + (clientMap.size() + 1) + "人\n");
		clientMap.put(user.getName(), client);
	}

	public void userRegist(String useName, Socket client) {
		System.out.println("新用户" + useName + "注册成功！");
		System.out.println("用户姓名为：" + useName);
		// System.out.println("用户socket为：" + client);
		System.out.println();
	}
}

public class Server {
	public static void main(String[] args) throws IOException {
		ServerSocket serverSocket = new ServerSocket(8800);
		Map<String, Socket> map = new HashMap<String, Socket>();
		ExecutorService executorService = Executors.newFixedThreadPool(20);
		System.out.println("等待客户连接中...\n");
		try {
			for (int i = 0; i < 20; i++) {
				Socket socket = serverSocket.accept();
				System.out.println("有用户连接：IP" + socket.getInetAddress() + i 
						+ " 端口号：/" + socket.getPort());
				executorService.execute(new ExcuteClientServer(socket, map));
			}
			executorService.shutdown();
			serverSocket.close();
		} catch (Exception e) {
		}
		// while(true){
		// try {
		// //1.建立一个服务器Socket(ServerSocket)绑定指定端口
		// ServerSocket serverSocket=new ServerSocket(8800);
		// System.out.println("服务器已启动。。。");
		// //2.使用accept()方法阻止等待监听，获得新连接
		// Socket socket=serverSocket.accept();
		// //3.获得输入流
		// ObjectInputStream objectInputStream = new ObjectInputStream(new
		// BufferedInputStream(socket.getInputStream()));
		// Object object = null;
		// try {
		// object = objectInputStream.readObject();
		// } catch (ClassNotFoundException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
		// User user = (User)object;
		// IsLegal isLegal = null;
		// if (user.getType() == 0) {
		// isLegal = new IsLegal(new
		// Check().check1(user.getName(),user.getPwd()));
		// }else if (user.getType() == 1) {
		// isLegal = new IsLegal(new Check().check2(user.getName()));
		// if (isLegal.isIslegal()) {
		// String []message = new String[2];
		// message[0] = user.getName();
		// message[1] = user.getPwd();
		// new UserMessage().write(message);
		// }
		// }
		//
		// //获得输出流
		// ObjectOutputStream os = new
		// ObjectOutputStream(socket.getOutputStream());
		// os.writeObject(isLegal);
		// os.flush();
		// } catch (IOException e) {
		// e.printStackTrace();
		// }
		// }
		/*
		 * try { //1.建立一个服务器Socket(ServerSocket)绑定指定端口 ServerSocket
		 * serverSocket=new ServerSocket(8800); System.out.println("服务器已启动。。。");
		 * //2.使用accept()方法阻止等待监听，获得新连接 Socket socket=serverSocket.accept();
		 * //3.获得输入流 ObjectInputStream objectInputStream = new
		 * ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
		 * Object object = null; try { object = objectInputStream.readObject();
		 * } catch (ClassNotFoundException e) { // TODO Auto-generated catch
		 * block e.printStackTrace(); } User user = (User)object; IsLegal
		 * isLegal = null; if (user.getType() == 0) { isLegal = new IsLegal(new
		 * Check().check1(user.getName(),user.getPwd())); }else if
		 * (user.getType() == 1) { isLegal = new IsLegal(new
		 * Check().check2(user.getName())); if (isLegal.isIslegal()) { String
		 * []message = new String[2]; message[0] = user.getName(); message[1] =
		 * user.getPwd(); new UserMessage().write(message); } }
		 * 
		 * //获得输出流 ObjectOutputStream os = new
		 * ObjectOutputStream(socket.getOutputStream());
		 * os.writeObject(isLegal); os.flush(); } catch (IOException e) {
		 * e.printStackTrace(); }
		 */
	}

}
