package mx.tc.j2se.evaluation;

public class Evaluation1 {

    public static void main(String[] args) {

        try{
            Circle circle = new Circle(-2);
        } catch(IllegalArgumentException e){
            System.out.println("Sorry, little human, your circle is invalid. Try again :)");
        }

        Circle circle1 = new Circle();
        Circle circle2 = new Circle();
        Circle circle3 = new Circle(5);

        circle2.setRadius(15);

        Circle[] myArray = {circle1,circle2,circle3};
        int index=biggestCircle(myArray);
        int radius=(myArray[index]).getRadius();
        System.out.println("The radius of the biggest circle is" + " " + radius);

    }

    public static int biggestCircle(Circle[] circleArray){
        int size=circleArray.length;
        int index=0;
        double biggestOne=0;
        for (int i=0;i<size;i++){
            double area=(circleArray[i]).getArea();
            if (area>biggestOne){
                biggestOne=area;
                index=i;
            }
        }
        return index;
    }

}