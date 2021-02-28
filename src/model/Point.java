package model;

import javafx.scene.paint.Color;

public class Point {
    private int x;
    private int y;
    private Color color;

    public Point(double x, double y, Color color) {
        this.x = (int) x;
        this.y = (int) y;
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
