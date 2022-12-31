package view;

import javax.swing.*;

import controller.MapUtil;
import controller.DifficultyUtil;
import model.*;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


public class Start extends JFrame implements ThreadFactory, java.io.Serializable {
    /**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	public static int cnt = 0;
    public static Eliminatebox eliminatebox = new Eliminatebox();
    public static Setting setting;
    public static Map map;
    public static User user;
    public User user1;
    private String prefix;
    public static JFrame self;
    Socket socket;
    private final AtomicInteger threadNum = new AtomicInteger(1);
    public Start(String prefix){
    	this.prefix = prefix;
    }
    @Override
	public Thread newThread(Runnable r) {
		// TODO Auto-generated method stub
		return new Thread(null, r, prefix + threadNum.getAndIncrement());
	}
    public void setUser(User user){
    	this.user1= user;
    }
    
    public User getUser() {
		return this.user1;
	}
    
    public Start(User player) throws HeadlessException {
    	user = player;
        self = this;
    	try {
            setting = new Setting();
            setting.print();
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        map = MapUtil.buildMap();
        init();
        createMap();
        user = player;
        socket = user.getMySocket();
        BottomUp upmove = new BottomUp("上移", 30, 700);
        this.getContentPane().add(upmove);

        BottomBack revoke = new BottomBack("撤销", 185, 700);
        this.getContentPane().add(revoke);

        BottomShffule shuffle = new BottomShffule("打乱", 340, 700);
        this.getContentPane().add(shuffle);
        
        JLabel label = new JLabel("当前通关难度:"+ DifficultyUtil.calculate(eliminatebox, map));
        label.setFont(new Font("宋体",Font.BOLD,24));
        label.setBounds(140, 730, 400, 100);
        this.getContentPane().add(label);
        //refreshFirst();
        Brand xc = new Brand("消除区域");
        xc.setBounds(12,575,430,110);
        this.getContentPane().add(xc);

        Brand bj = new Brand("背景草地");
        bj.setBounds(0,0,450,800);
        this.getContentPane().add(bj);

        this.addWindowListener(new WindowAdapter() {
        	@Override
        	public void windowClosing(WindowEvent e){
        		super.windowClosing(e);
        		System.out.println("退出");
    			System.out.println(Start.user.getMySocket());
    			String string = Start.user.getName();
    			Socket socket = Start.user.getMySocket();
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
                System.exit(0);
        	}
		});
        autoRefresh(label, bj);
    }


    public void init(){
        this.setVisible(true);
        int width = 450;
        int height = 800;
        this.setSize(width, height);
        this.setTitle("羊了个羊");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLayout(null);
        this.setBounds(0, 0, 468, 848);
        this.setLocationRelativeTo(null);
    }

    public void createMap(){
        List<Layer> list = map.getLayers();
        for (Layer layer : list) {
            createLayer(this.getContentPane(), layer);
        }
        map.grayCheck();
    }
    private void createLayer(Container container, Layer layer){
        Cell[][] cells = layer.getCells();
        for (int i = 0; i < cells.length; ++i){
            for (int j = 0; j < cells[i].length; ++j){
                Cell cell = cells[i][j];
                if(cell.getState() == 2){
                    Brand brand = cell.getBrand();
                    int brandx = j*50 + layer.getOffset();
                    int brandy = i*50 + layer.getOffset();
                    brand.setBounds(brandx, brandy, 50, 50);
                    container.add(brand);
                }
            }
        }
    }

    
    
    public void autoRefresh(JLabel label, Brand bj) {
        JFrame self = this;
        JFrame jFrame = new Selet(Start.user.getMySocket());
        int flag = 0;
        ExecutorService executorService=new ThreadPoolExecutor(
        		2, 5, 10,TimeUnit.SECONDS,
        		new ArrayBlockingQueue<Runnable>(5),
        		new Start("autoRefreshPool"));
        
        executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true){
	                try {
	                    Thread.sleep(200);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                repaint();
	                if (DifficultyUtil.totalNum(eliminatebox, map) == 0&&flag == 0) {
						jFrame.setVisible(true);
						self.setVisible(false);
						break;
					}
	            }
			}
		});
        
        executorService.execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				while (true){
	                try {
	                    Thread.sleep(100);
	                } catch (InterruptedException e) {
	                    e.printStackTrace();
	                }
	                
	                DifficultyUtil.paintDifficulty(DifficultyUtil.calculate(eliminatebox, map), 
	                		self, label, bj);  
	                if (DifficultyUtil.calculate(eliminatebox, map) == 0) {
						break;
					}
	            }
			}
		});      
    }
    public static void main(String[] args) {
        //new Start();
    }


	
}
