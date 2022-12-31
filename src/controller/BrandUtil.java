package controller;

import java.awt.*;


public class BrandUtil {

    public static Image get(String fileName){
        return  Toolkit.getDefaultToolkit().getImage("imgs\\"+fileName);
    }

}