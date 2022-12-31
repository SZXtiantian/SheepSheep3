package test1;

import java.io.IOException;
import java.net.Socket;
import view.Login;
import java.net.UnknownHostException;
 
public class Client3 {
    public static void main(String[] args) {
        try {
            Socket socket =new Socket("localhost",8800);
            System.out.println("loading...");
            new Login(socket);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
