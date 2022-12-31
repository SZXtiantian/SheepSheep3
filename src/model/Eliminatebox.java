package model;

import javax.swing.*;

import view.Start;

import java.awt.event.MouseListener;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.*;
import java.util.Map;
import java.util.stream.Collectors;


public class Eliminatebox {
    private int step = 5;
    private static final List<Brand> SLOT = new ArrayList<>();

    public void deleteByBrandName(String name){
        Iterator<Brand> iterator = SLOT.iterator();
        while(iterator.hasNext()){
            Brand brand = iterator.next();
            if(brand.getName().equals(name)){
                brand.getParent().remove(brand);
                iterator.remove();
            }
        }
    }

    @SuppressWarnings("unused")
	private void noMouseListener(Brand brand){
        MouseListener[] mouseListeners = brand.getMouseListeners();
        if (mouseListeners!=null){
            for (MouseListener mouseListener : mouseListeners){
                brand.removeMouseListener(mouseListener);
            }
        }
    }

    public List<Brand> getSlot(){
        return SLOT;
    }
    public void setSlot(){
        SLOT.sort(Comparator.comparing(Brand::getName));
        paint();
    }


    public void addSlot(Brand brand){
        SLOT.add(brand);
//        noMouseListener(brand);
        SLOT.sort(Comparator.comparing(Brand::getName));
        Map<String, List<Brand>> map = SLOT.stream().collect(Collectors.groupingBy(Brand::getName));//获取牌的名称
        
        Set<String> keys = map.keySet();

        for (String key : keys) {
            List<Brand> brands = map.get(key);
            if (brands.size() == 3) {
                deleteByBrandName(key);
                break;
            }
        }
        paint();
        over(brand);
    }

    public void paint(){
        for (int i = 0; i < SLOT.size(); i++){
            Brand brand = SLOT.get(i);
            int x = step + i * brand.getWidth() + 5/2 + 10;
            brand.setBounds(x + 10, 600,brand.getWidth(),brand.getHeight());
        }
    }
    private void over(Brand brand){
        if (SLOT.size()>=7){
            JOptionPane.showMessageDialog(brand,"Game Over~");
            Object[] list = {"复活", "退出游戏"};
            Object objResult = JOptionPane.showOptionDialog(null, "请选择：", "在努力一下下你就成功了！",
                    JOptionPane.YES_NO_CANCEL_OPTION, 2, null, list, list[0]);
            if ((int)objResult == 1) {
            	//new Login();
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
            else{
                List<Brand> slot = Start.eliminatebox.getSlot();
                int lim = Math.min(slot.size(), 3);
                int i = 0;
                Iterator<Brand> iterator = slot.iterator();
                Cell[][] cells = Start.map.getexLayer().getCells();
                while(i<lim && iterator.hasNext()){
                    Brand tbrand = iterator.next();
                    /*int x = (Start.cnt % 9) * 50;
                    int y = (Start.cnt / 9) * 50 + 400;*/
                    int x = i * 50 + 135;
                    int y = 530;
//                    brand.setTX(x);
//                    brand.setTY(y);
                    tbrand.setBounds(x, y, 50, 50);
                    cells[(Start.cnt / 9)][(Start.cnt % 9)] = new Cell(tbrand);
                    tbrand.setCell(cells[(Start.cnt / 9)][(Start.cnt % 9)]);
                    iterator.remove();
                    ++i;
                    ++Start.cnt;
                }
                Start.map.getexLayer().setCells(cells);
//                Start.map.getexLayer().show();
                Start.eliminatebox.setSlot();
            }
        }
    }


}
