package controller;


public class IntReaderUtil {
    private final String readtxt;
    private int now;
    public IntReaderUtil(String readtxt){
        this.readtxt = readtxt;
        now = 0;
    }

    public int read(){
        if(now >= readtxt.length()){
            return -1;
        }
        int res = 0;
        while(now < readtxt.length() && (readtxt.charAt(now)<'0' || readtxt.charAt(now)>'9')){
            now = now + 1;
        }
        while(now < readtxt.length() && (readtxt.charAt(now)>='0' && readtxt.charAt(now)<='9')){
            res = res * 10 + readtxt.charAt(now) - '0';
            now = now + 1;
        }
        return res;
    }
}
