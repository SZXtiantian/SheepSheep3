package model;

import java.util.Random;

public class Layer {
    private int offset = 10;

    private int x;
    private int y;

    private int cellNumx;
    private int cellNumy;

    private Cell[][]  cells = null;
    private int capacity;
    private int size = 0;
    private Layer parentLayer;

    public Layer(int cellNumx, int cellNumy){
        this.cellNumx = cellNumx;
        this.cellNumy = cellNumy;
        this.capacity = cellNumx * cellNumy;
        this.cells = new Cell[cellNumx][cellNumy];
        this.offset = new Random().nextInt(100);
    }
    public int getCapacity() {
        return capacity;
    }
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public int getOffset() {
        return offset;
    }
    public void setOffset(int offset) {
        this.offset = offset;
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
    public int getCellNumx() {
        return cellNumx;
    }
    public void setCellNumx(int cellNumx) {
        this.cellNumx = cellNumx;
    }
    public int getCellNumy() {
        return cellNumy;
    }
    public void setCellNumy(int cellNumy) {
        this.cellNumy = cellNumy;
    }
    public Cell[][] getCells() {
        return cells;
    }
    public void setCells(Cell[][] cells) {
        this.cells = cells;
    }
    public int getSize() {
        return size;
    }
    public void setSize(int size) {
        this.size = size;
    }
    public boolean isClr(){
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if (value != null && value.getState() == 2) {
                    return false;
                }
            }
        }
        return true;
    }
    public void setParentLayer(Layer parentLayer) {
        this.parentLayer = parentLayer;
    }

    public void show(){
        if(cells == null){
            return ;
        }
        for (Cell[] cell : cells) {
            for (Cell value : cell) {
                if(value==null) {
                    continue;
                }
                System.out.print(value.getBrand().getName());
            }
            System.out.println();
        }
    }
    public Cell getIndex(int index){
        int indexX = index/this.getCellNumy();
        int indexY = index%this.getCellNumy();
        return this.cells[indexX][indexY];
    }
    public Layer getParentLayer() {
        return  this.parentLayer;
    }

}
