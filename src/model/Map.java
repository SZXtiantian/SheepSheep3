package model;

import javax.swing.*;
import view.Start;
import view.UserMessage;

import java.awt.HeadlessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Map extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
	private int y;
	// private User usertemp = Start.user;
	private int floorHeight;
	private List<Layer> layers = new ArrayList<>();
	private Layer exlayer = null;

	public Map() {
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getFloorHeight() {
		return floorHeight;
	}

	public List<Layer> getLayers() {
		return layers;
	}

	public void setexLayer(Layer exlayer) {
		this.exlayer = exlayer;
	}

	public Layer getexLayer() {
		return exlayer;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setFloorHeight(int floorHeight) {
		this.floorHeight = floorHeight;
	}

	public void setLayers(List<Layer> layers) {
		this.layers = layers;
	}

	public boolean checkClr() throws HeadlessException, IOException {
		for (Layer layer : layers) {
			if (!layer.isClr()) {
				return false;
			}
		}
		if (exlayer != null && !exlayer.isClr()) {
			return false;
		}
		
		//Start.self.setVisible(false);
		Start.user.calculate();
		UserMessage.updateScore(Start.user.getName(), Start.user.getDifficult()*10 + UserMessage.getScoreNum(Start.user.getName()));
		Start.user.setScore(UserMessage.getScoreNum(Start.user.getName()));
		JOptionPane.showMessageDialog(null, "                You Win!\n     Player " + Start.user.getName()
				+ " total Win:" + Start.user.getWinNum()+"\n            total Score:"+Start.user.getScore());
		
		//new Selet(Start.user.getMySocket());
		// System.exit(0);
		return true;
	}

	public void grayCheck() {
		List<Layer> list = this.getLayers();
		for (int i = 1; i < list.size(); i++) {
			Layer layer = list.get(i);
			for (int j = 0; j < layer.getCapacity(); j++) {
				Cell cell = layer.getIndex(j);
				if (cell.getState() == 2) {
					Brand brand = cell.getBrand();
					boolean flag = this.brand2layer(brand, layer.getParentLayer());
					brand.setGray(flag);
				}
			}
		}
	}

	private boolean brand2layer(Brand brand, Layer layer) {
		for (int j = 0; j < layer.getCapacity(); ++j) {
			Cell cell = layer.getIndex(j);
			if (cell.getState() == 2) {
				Brand temp = cell.getBrand();
				boolean flag = brand.getBounds().intersects(temp.getBounds());
				if (flag) {
					return true;
				}
			}
		}
		if (layer.getParentLayer() == null) {
			return false;
		} else {
			return brand2layer(brand, layer.getParentLayer());
		}
	}

}
