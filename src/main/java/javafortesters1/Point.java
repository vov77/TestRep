package javafortesters1;

public class Point {
  double x;
  double y;

  public Point(double x, double y) {
    this.x = x;
    this.y = y;
  }


  public double getX() {
    return x;
  }

  public void setX(double x) {
    this.x = x;
  }

  public double getY() {
    return y;
  }

  public void setY(double y) {
    this.y = y;
  }

  public static double distance (Point p1, Point p2) {
    double x = (p1.x-p2.x)*(p1.x-p2.x);
    double y = (p1.y - p2.y)*(p1.y - p2.y);
    return Math.sqrt(x+y);
  }


}
