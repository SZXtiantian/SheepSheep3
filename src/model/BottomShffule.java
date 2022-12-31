package model;

import javax.swing.*;

import controller.LayerUtil;
import controller.BrandUtil;
import view.Start;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;
import java.util.Random;


public class BottomShffule extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
    private int y;
    public BottomShffule(String label, int x, int y){
        this.setText(label);
        this.setX(x);
        this.setY(y);
        this.setFont(new Font("宋体",Font.BOLD,24));
        this.setEnabled(true);
        this.setVisible(true);
        this.setLocation(x, y);
        this.setSize(90, 60);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                @SuppressWarnings("unused")
				BottomShffule shuffle = (BottomShffule) e.getSource();
                List<Layer> layers = Start.map.getLayers();
                for (Layer layer : layers){
                    layerShuffle(layer);
                }
                layerShuffle(Start.map.getexLayer());
                Start.map.grayCheck();
            }
        });
    }
    public void layerShuffle(Layer layer){
        Cell[][] cells = layer.getCells();
        @SuppressWarnings("unused")
		Brand[] brands = new Brand[layer.getCapacity()];
        @SuppressWarnings("unused")
		int tot = 0;
        for (Cell[] value : cells) {
            for (Cell cell : value) {
                if (cell != null && cell.getState() == 2) {
                    int rand = new Random().nextInt(LayerUtil.brandNames.length);
                    String brandName = LayerUtil.brandNames[rand];
                    cell.getBrand().setName(brandName);
                    cell.getBrand().setImage(BrandUtil.get(brandName + ".png"));
                    cell.getBrand().setGrayImage(BrandUtil.get(brandName + "_gray.png"));
                }
            }
        }
    }
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
}
