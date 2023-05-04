package models;

public class Pixel {
    private int a;
    private int r;
    private int g;
    private int b;

    public Pixel(int point) {
        int offset = 0xff;
        setA((point >> 24) & offset);
        setR((point >> 16) & offset);
        setG((point >> 8) & offset);
        setB((point & offset));
    }

    public Pixel(int a, int r, int g, int b) {
        this.a = a;
        this.r = r;
        this.g = g;
        this.b = b;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getR() {
        return r;
    }

    public void setR(int r) {
        this.r = r;
    }

    public int getG() {
        return g;
    }

    public void setG(int g) {
        this.g = g;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public int getPoint(){
        return ((a << 24) | (r << 16) | (g << 8) | (b));
    }

    public void invertPixel(){
        setR(255 - r);
        setG(255 - g);
        setB(255 -b);
    }

    public void eslInvert(){
            int tmp = r;
            r = g;
            g = b;
            b = tmp;
    }

}
