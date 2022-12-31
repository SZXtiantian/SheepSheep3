package controller;

import model.Layer;
import model.Map;
import model.Setting;
import view.Start;

public class MapUtil {
    public static Map buildMap(){
    	Map map = new Map();
        Setting setting = Start.setting;
        map.setFloorHeight(setting.getFloorHeight());
        map.setX(100);
        map.setY(200);
        Layer[] layers = new Layer[setting.getFloorHeight()];
        for(int i = 0; i < setting.getFloorHeight(); ++i){
            layers[i] = LayerUtil.buildLayer(setting.getlayerX()[i], setting.getlayerY()[i]);
        }
        Layer exlayer = LayerUtil.buildExtraLayer();
        for(int i = 0; i < setting.getFloorHeight(); ++i){
            if(i!=0) {
                layers[i].setParentLayer(layers[i - 1]);
            } else {
                layers[i].setParentLayer(null);
            }
        }
        for(int i = 0; i < setting.getFloorHeight(); ++i){
            map.getLayers().add(layers[i]);
        }
        map.setexLayer(exlayer);
        return map;
    }

}
