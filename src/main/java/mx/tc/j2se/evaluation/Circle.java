package mx.tc.j2se.evaluation;

public class Circle{
    private int radius;
    double area;
    //Constructors
    public Circle (){
        this.radius=1;
    }

    public Circle(int radius) {
        this.radius=radius;
    }

    //Methods

    public void setRadius(int radius) {
        this.radius = radius;

    }

    public int getRadius() {
        return radius;
    }

    public double getArea(){
        this.area=(this.radius^2)*Math.PI;
        return area;
    }

}
