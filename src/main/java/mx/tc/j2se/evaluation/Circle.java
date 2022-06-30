package mx.tc.j2se.evaluation;

public class Circle{
    private int radius;
    double area;

    //Constructors
    public Circle (){
        this.radius=1;
    }

    public Circle(int radius) throws IllegalArgumentException{
        if (radius<=0){
            throw new IllegalArgumentException("The radius must be a positive integer");
        }
        else{
            this.radius=radius;
        }
    }

    //Methods
    public void setRadius(int radius) throws IllegalArgumentException{
        if (radius<=0){
            throw new IllegalArgumentException("The radius must be a positive integer");
        }
        else{
            this.radius=radius;
        }
    }

    public int getRadius() {
        return this.radius;
    }

    public double getArea(){
        this.area=Math.pow(this.radius,2)*Math.PI;
        return area;
    }
}
