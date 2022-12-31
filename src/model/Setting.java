package model;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

import controller.IntReaderUtil;
import view.Start;


public class Setting {
    private int floorHeight;
    private int[] layerX;
    private int[] layerY;

    public Setting() throws FileNotFoundException {
//        System.out.println(file.canRead());
    	Random random  = new Random();
    	int r = random.nextInt(2);
        Scanner reader = new Scanner(new File(".///settings//config" + (r + 1) + ".txt"));
        Start.user.setDifficult(r + 1);
        reader.nextLine();
        IntReaderUtil readfloorHeight = new IntReaderUtil(reader.nextLine());
        this.floorHeight = readfloorHeight.read();
        layerX = new int[floorHeight];
        layerY = new int[floorHeight];
        for(int i = 0; i < floorHeight; ++i){
            IntReaderUtil readxy = new IntReaderUtil(reader.nextLine());
            readxy.read();
            layerX[i] = readxy.read();
            layerY[i] = readxy.read();
        }
        reader.close();
    }

    public void setFloorHeight(int floorHeight){ this.floorHeight = floorHeight; }
    public int getFloorHeight(){ return floorHeight; }
    public void setlayerX(int[] layerX){ this.layerX = layerX; }
    public int[] getlayerX(){ return layerX; }
    public void setlayerY(int[] layerY){ this.layerY = layerY; }
    public int[] getlayerY(){ return layerY; }
    public void print(){
        System.out.println(floorHeight);
        System.out.println(Arrays.toString(layerX));
        System.out.println(Arrays.toString(layerY));
    }

}
