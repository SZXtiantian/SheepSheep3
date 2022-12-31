package controller;

import static controller.ShuffleUtil.shuffle;

import java.util.Arrays;
import java.util.Random;

import model.*;

public class LayerUtil {
    public static String[] brandNames={
    		"刷子","剪刀","叉子","手套","水桶","火","玉米","球","瓶子","白菜","稻草","肉腿","胡萝卜","苹果","苹果","铃铛","青草"
    };
    public static Layer buildExtraLayer(){
        Layer layer = new Layer(3, 9);
        Cell[][] cells = layer.getCells();
        for (Cell[] cell : cells) {
            Arrays.fill(cell, null);
        }
        layer.setSize(0);
        return layer;
    }

    public static Layer buildLayer(int cellNumx, int cellNumy){
        Layer layer = new Layer(cellNumx, cellNumy);
        Cell[][] cells = layer.getCells();
        Brand[] brands = new Brand[layer.getCapacity()];
        for(int i = 0; i < brands.length; i = i + 3){
            int rand = new Random().nextInt(brandNames.length);
            String brandName = brandNames[rand];
            Brand brand1 = new Brand(brandName);
            Brand brand2 = new Brand(brandName);
            Brand brand3 = new Brand(brandName);
            brands[i] = brand1;
            brands[i + 1] = brand2;
            brands[i + 2] = brand3;
        }
        shuffle(brands, brands.length);
        for(int i = 0; i < brandNames.length; ++i){
            Brand brand = brands[i];
            int rand = new Random().nextInt(brandNames.length);
            brands[i] = brands[rand];
            brands[rand] = brand;
        }
        int cnt = 0;
        for (int i = 0; i < cells.length; i++) {
            for (int j = 0; j < cells[i].length; j++) {
                Brand brand = brands[cnt];
                Cell cell = new Cell(brand);
                brand.setCell(cell);
                cells[i][j] = cell;
                cnt++;
            }
        }
        layer.setSize(cnt);
        return layer;
    }


}
