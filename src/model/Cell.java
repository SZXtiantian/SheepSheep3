package model;

public class Cell {
    private int state = 1;
    private Brand brand = null;
    public Cell(Brand brand){
        this.brand = brand;
        this.state = 2;
    }
    public int getState() {
        return state;
    }
    public void setState(int state) {
        this.state = state;
    }
    public Brand getBrand() {
        return brand;
    }
    public void setBrand(Brand brand) {
        this.brand = brand;
    }
}
