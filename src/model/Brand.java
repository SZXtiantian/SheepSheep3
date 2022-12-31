package model;

import java.awt.Component;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.Objects;
import java.util.UUID;

import controller.BrandUtil;
import view.Start;


public class Brand extends Component{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name;
    @SuppressWarnings("unused")
	private String id;
    private int x = 0;
    private int y = 0;
    private Cell cell;
    private Image image;
    private Image grayImage;
    private Boolean isGray = false;
    public Brand(){}
    public Brand(String name){
        this.name = name;
        this.image = BrandUtil.get(name + ".png");
        this.grayImage = BrandUtil.get(name + "_gray.png");
        this.id = UUID.randomUUID().toString();
        Brand self = this;
        this.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                Brand brand = (Brand) e.getSource();
                if(Objects.equals("消除区域", brand.getName()) || 
                		Objects.equals("背景草地", brand.getName()) || 
                		brand.getCell().getState()==1 || brand.getGray()){
                    //System.out.println(brand.getName());
                    return ;
                } else {
                    System.out.println("Brand.clicked");
                    History.operate("click", brand);
                    self.getCell().setState(1);
                    Start.eliminatebox.addSlot(brand);
                    Start.map.grayCheck();
                    try {
						Start.map.checkClr();
					} catch (HeadlessException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
                }
            }
        });
    }


    @Override
    public void paint(Graphics g){
        if(isGray){
            g.drawImage(this.getGrayImage(), x, y, null);
        } else{
            g.drawImage(this.getImage(), x, y, null);
        }
    }


    public Cell getCell() { return cell; }
    public void setCell(Cell cell) { this.cell = cell; }
    public Boolean getGray() { return isGray; }
    public void setGray(Boolean gray) {
        isGray = gray;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Image getImage() {
        return image;
    }
    public void setImage(Image image) {
        this.image = image;
    }
    public Image getGrayImage() {
        return grayImage;
    }
    public void setGrayImage(Image grayImage) {
        this.grayImage = grayImage;
    }
}
