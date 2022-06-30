package mx.tc.j2se.evaluation;

public class Evaluation1 {

    public static void main(String[] args) {

        Circle circle1 = new Circle();
        Circle circle2 = new Circle(4);
        Circle circle3 = new Circle(8);

        circle1.setRadius(3);

        Circle[] myArray = {circle1,circle2,circle3};
        int bigIndex=biggestCircle(myArray);
        int theBiggest=(myArray[bigIndex]).getRadius();

        System.out.println(theBiggest);
    }

    public static int biggestCircle(Circle[] circleArray){
        int size=circleArray.length;
        int index=0;
        double biggestOne=0;
        for (int i=0;i<size;i++){
            Circle currentCircle=circleArray[i];
            double area=currentCircle.getArea();
            if (area>biggestOne){
                index=i;
            }
        }
        return index;
    }

}