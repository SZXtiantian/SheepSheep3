package model;

import java.io.IOException;
import java.net.Socket;

import view.Start;
import view.UserMessage;

public class User implements java.io.Serializable{

	private static final long serialVersionUID = 1L;
	/**
	 * 0 登录 1注册
	 */
	private int type;
	private String name;
	private String pwd;
	private  int winNum;
	private int score;
	private Socket mySocket;
	private int difficult;
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWinNum() throws IOException {
		return UserMessage.getWinNum(this.name);
	}
	public void setWinNum(int winNum) {
		this.winNum = winNum;
	}
	public User(String name, int winNum) {
		super();
		this.name = name;
		this.winNum = winNum;
	}
	public User(String name, String pwd) {
		super();
		this.name = name;
		this.pwd = pwd;
	}
	public User(String name, int winNum, Socket socket) {
		super();
		this.name = name;
		this.winNum = winNum;
		mySocket = socket;
	}
	public User(String name, String pwd, int type) {
		super();
		this.name = name;
		this.pwd = pwd;
		this.type = type;
	}
	public User() {
	}
	public void calculate(){
		winNum += 1;
		try {
			UserMessage.updateWinNum(Start.user.getName(), winNum);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public Socket getMySocket() {
		return mySocket;
	}
	public void setMySocket(Socket mySocket) {
		this.mySocket = mySocket;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public int getDifficult() {
		return difficult;
	}
	public void setDifficult(int difficult) {
		this.difficult = difficult;
	}
}
