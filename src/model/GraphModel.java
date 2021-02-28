package model;

import java.util.ArrayList;

public class GraphModel {
    private ArrayList<Point> points;

    public GraphModel() {
        points = new ArrayList<Point>();
    }

    public int getPointCount() {
        return points.size();
    }

    public void addPoint(Point point) {
        points.add(point);
    }

    public Point getPoint(int i) {
        return points.get(i);
    }

    public void removePoint(int x, int y) {
        for (int tempX = x - 3; tempX < x + 4; tempX++) {
            for (int tempY = y - 3; tempY < y + 4; tempY++) {
                int finalTempX = tempX;
                int finalTempY = tempY;
                points.removeIf(point -> point.getX() == finalTempX && point.getY() == finalTempY);
            }
        }
    }

    public void clearPoints() {
        points.clear();
    }
}
