package view;

import java.io.*;

public class UserMessage {

	public void write(String[] message) throws IOException {
		File file = new File("Message.txt");
		String messagesum = "";
		for (int i = 0; i < 2; i++)
			messagesum += message[i] + "~";
		messagesum += "0~0~";
		if (!file.exists())
			file.createNewFile();
		FileOutputStream out = new FileOutputStream(file, true); 
		StringBuffer sb = new StringBuffer(); 
		sb.append(messagesum + "\n");
		out.write(sb.toString().getBytes("gb2312"));
		out.close(); 
	}

	public String[] read(String countname) throws IOException {
		File file = new File("Message.txt");
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		@SuppressWarnings("unused")
		StringBuffer sb = new StringBuffer();
		temp = br.readLine();

		String[] message = new String[5];
		while (temp != null) {
			String sbstring = temp.toString();
			int n = sbstring.length();
			for (int i = 0; i < 5; i++)
				message[i] = "";

			int k = 0;
			for (int i = 0; i < n; i++) {
				if (sbstring.charAt(i) == '~') {
					k++;
				} else {
					message[k] += sbstring.charAt(i);
				}
			}
			if (message[2].equals(countname)) 
			{
				return message;
			}
			temp = br.readLine();
		}
		return null;
	}

	public static String updateWinNum(String countname, int num) throws IOException {
		File file = new File("Message.txt");
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		@SuppressWarnings("unused")
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();

		String moneystring = "";

		temp = br.readLine();
		String[] message = new String[5];
		while (temp != null) {
			String sbstring = temp.toString();
			int n = sbstring.length();
			for (int i = 0; i < 5; i++)
				message[i] = "";

			int k = 0;
			for (int i = 0; i < n; i++)
			{
				if (sbstring.charAt(i) == '~') {
					// System.out.println("@"+message[k]);
					k++;
				} else {
					message[k] += sbstring.charAt(i);
				}
			}

			if (message[0].equals(countname))
			{
				sb1.append(message[0] + "~" + message[1] + "~" + Integer.toString(num) + "~" + message[3] + "~\n");
			} else {
				sb1.append(temp + "\n");
			}
			temp = br.readLine();
		}
		File file1 = new File("Message.txt");
		if (!file1.exists())
			file1.createNewFile();
		FileOutputStream out = new FileOutputStream(file1, false);
		out.write(sb1.toString().getBytes("gb2312"));
		out.close();

		return moneystring;
	}

	public static String updateScore(String countname, int score) throws IOException {
		File file = new File("Message.txt");
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		@SuppressWarnings("unused")
		StringBuffer sb = new StringBuffer();

		StringBuffer sb1 = new StringBuffer();

		String moneystring = "";

		temp = br.readLine();
		String[] message = new String[5]; 
		while (temp != null) {
			String sbstring = temp.toString();
			int n = sbstring.length(); 
			for (int i = 0; i < 5; i++)
				message[i] = "";

			int k = 0;
			for (int i = 0; i < n; i++)
			{
				if (sbstring.charAt(i) == '~') {
					k++;
				} else {
					message[k] += sbstring.charAt(i);
				}
			}

			if (message[0].equals(countname)) // 找到该账户名
			{
				sb1.append(message[0] + "~" + message[1] + "~" + message[2] + "~" + Integer.toString(score) + "~\n");
			} else {
				sb1.append(temp + "\n");
			}
			temp = br.readLine();
		}

		File file1 = new File("Message.txt");
		if (!file1.exists())
			file1.createNewFile();
		FileOutputStream out = new FileOutputStream(file1, false);
		out.write(sb1.toString().getBytes("gb2312"));
		out.close();

		return moneystring;
	}

	public static int getWinNum(String countname) throws IOException {

		File file = new File("Message.txt"); 
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		temp = br.readLine();
		while (temp != null) {
			String sbstring = temp.toString(); 
			int n = sbstring.length();
			String[] message = new String[5];
			int k = 0;

			for (int i = 0; i < 5; i++)
				message[i] = "";
			for (int i = 0; i < n; i++) {
				if (sbstring.charAt(i) == '~') {
					k++;
				} else {
					message[k] += sbstring.charAt(i);
				}
			}
			if (countname.equals(message[0]))
				return Integer.parseInt(message[2]);
			temp = br.readLine();
		}
		return 0;
	}

	public static int getScoreNum(String countname) throws IOException {

		File file = new File("Message.txt");
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();
		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file)); 
		String temp = null;

		temp = br.readLine(); 
		while (temp != null) {
			String sbstring = temp.toString(); 
			int n = sbstring.length(); 
			String[] message = new String[5]; 
			int k = 0;

			for (int i = 0; i < 5; i++)
				message[i] = "";
			for (int i = 0; i < n; i++) {
				if (sbstring.charAt(i) == '~') {
					k++;
				} else {
					message[k] += sbstring.charAt(i);
				}
			}
			if (countname.equals(message[0]))
				return Integer.parseInt(message[3]);
			temp = br.readLine();
		}
		return 0;
	}

	public String updatepwd(String countname, String pwd) throws IOException {
		File file = new File("Message.txt");
		if (!file.exists() || file.isDirectory())
			throw new FileNotFoundException();

		@SuppressWarnings("resource")
		BufferedReader br = new BufferedReader(new FileReader(file));
		String temp = null;
		@SuppressWarnings("unused")
		StringBuffer sb = new StringBuffer();
		StringBuffer sb1 = new StringBuffer();

		String moneystring = "";

		temp = br.readLine();
		String[] message = new String[5];
		while (temp != null) {
			String sbstring = temp.toString();
			int n = sbstring.length();
			for (int i = 0; i < 5; i++)
				message[i] = "";

			int k = 0;
			for (int i = 0; i < n; i++)
			{
				if (sbstring.charAt(i) == '~') {
					k++;
				} else {
					message[k] += sbstring.charAt(i);
				}
			}

			if (message[0].equals(countname)) // 找到该账户名
			{

				sb1.append(message[0] + "~" + pwd + "~\n");
			} else {
				sb1.append(temp + "\n");
			}
			temp = br.readLine();
		}

		File file1 = new File("Message.txt");
		if (!file1.exists())
			file1.createNewFile();
		FileOutputStream out = new FileOutputStream(file1, false);
		out.write(sb1.toString().getBytes("gb2312"));
		out.close();

		return moneystring;
	}

}
