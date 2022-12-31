package controller;

import java.awt.Font;

import javax.swing.*;

import model.Brand;
import model.Cell;
import model.Eliminatebox;
import model.Layer;
import model.Map;


public class DifficultyUtil {
    public static double calculate(Eliminatebox eliminatebox, Map map){
        double []res = new double[2];
        for(Layer layer : map.getLayers()){
            res = calculate(res, layer);
        }       
        res = calculate(res, map.getexLayer());
        double totalNum = res[0];
        double moveNum = res[1];
        double slotNum = eliminatebox.getSlot().size();
        return totalNum <= 1 ? 0 : totalNum - moveNum / totalNum + slotNum / (7 + totalNum);
    }
    private static double []calculate(double []res, Layer layer){
        Cell[][] cells = layer.getCells();
        for(Cell[] celli : cells){
            for(Cell cell : celli){
                if(cell!=null&&cell.getState()==2){
                	if (!cell.getBrand().getGray()) {
						res[0]++;
					}else{
						res[0]++;
						res[1]++;
					}
                }
            }
        }
        return res;
    }
    public static double totalNum(Eliminatebox eliminatebox, Map map) {
    	double []res = new double[2];
        for(Layer layer : map.getLayers()){
            res = calculate(res, layer);
        }       
        res = calculate(res, map.getexLayer());
        return res[0];
	}
    public static void paintDifficulty(double theta, JFrame self, JLabel label, Brand bj){
        label.setText("当前通关难度:"+ String.format("%.2f", theta));
        label.setFont(new Font("宋体",Font.BOLD,24));
        label.setBounds(115, 730, 400, 100);
        self.getContentPane().add(label);
        bj.setBounds(0,0,450,800);
        self.getContentPane().add(bj);
        self.setVisible(true);
    }
}
