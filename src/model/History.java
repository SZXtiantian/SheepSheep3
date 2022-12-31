package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


class Unit{
    private String o;
    private Brand brand;
    public Unit(String o, Brand brand){
        this.o = o;
        this.brand = brand;
    }

    public String geto(){ return o; }
    public Brand getBrand(){ return brand; }
}

public class History {
    private static List<Unit> brands = new ArrayList<>();
    public static void operate(String o, Brand brand){
        if (Objects.equals(o, "click")){
            brands.add(new Unit(o, brand));
        } else if (Objects.equals(o, "upmove")) {
            brands.clear();
        } else if (Objects.equals(o, "reborn")) {
            brands.clear();
        } else if (Objects.equals(o, "shuffle")) {
            brands.clear();
        }
    }
}
