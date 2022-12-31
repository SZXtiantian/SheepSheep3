package model;

import javax.swing.*;

import view.Start;

import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Iterator;
import java.util.List;

public class BottomUp extends JButton{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private int x;
    @SuppressWarnings("unused")
	private int y;
    public BottomUp(String label, int x, int y){
        this.setText(label);
        this.x = x;
        this.y = y;
        this.setFont(new Font("宋体",Font.BOLD,24));
        this.setEnabled(true);
        this.setVisible(true);    
        this.setLocation(x, y);
        this.setSize(90, 60);
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
/*                @SuppressWarnings("unused")
				BottomUpmove upmove = (BottomUpmove) e.getSource();*/
                List<Brand> slot = Start.eliminatebox.getSlot();
                int lim = Math.min(slot.size(), 3);
                int i = 0;
                Iterator<Brand> iterator = slot.iterator();
                Cell[][] cells = Start.map.getexLayer().getCells();
                while(i<lim && iterator.hasNext()){
                    Brand brand = iterator.next();
                    int x = i * 50 + 135;
                    int y = 530;
                    brand.setBounds(x, y, 50, 50);

                    cells[Start.cnt/9][Start.cnt%9] = new Cell(brand);
                    brand.setCell(cells[Start.cnt/9][Start.cnt%9]);
                    iterator.remove();
                    ++i;
                    ++Start.cnt;
                }
                Start.map.getexLayer().setCells(cells);
                Start.eliminatebox.setSlot();
            }
        });
    }

    public void setX(int x){ this.x = x; }
    public void setY(int y){ this.y = y; }

}
