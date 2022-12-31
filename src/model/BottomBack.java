package model;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;

public class BottomBack extends JButton {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int x;
    private int y;
    public BottomBack(String label, int x, int y){
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
				BottomBack revoke = (BottomBack) e.getSource();

            }
        });
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
