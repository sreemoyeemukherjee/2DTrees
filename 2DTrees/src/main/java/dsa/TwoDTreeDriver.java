// Name: Sreemoyee Mukherjee
// Course: Data Structures & Algorithms
// Assignment Number: 2
package dsa;

import java.util.Scanner;

public class TwoDTreeDriver {
    public static void main(String[] args)
    {
        TwoDTree tree = new TwoDTree("CrimeLatLonXY.csv");  // loading our 2D tree with the crime data
        System.out.println("Crime file loaded into 2d tree with " + tree.getSize() + " records. ");
        Scanner sc = new Scanner(System.in);
        int choice = 0;
        while (true){
            System.out.println("\nWhat would you like to do?\n1: inorder\n2: preorder\n" +
                    "3: levelorder\n4: postorder\n5: reverseLevelOrder\n" +
                    "6: search for points within rectangle\n7: search for nearest neighbor\n8: quit\n");
            choice = Integer.parseInt(sc.nextLine());
            if(choice == 1){
                tree.inOrderPrint();
            } else if (choice == 2) {
                tree.preOrderPrint();
            } else if (choice == 3) {
                tree.levelOrderPrint();
            } else if (choice == 4) {
                tree.postOrderPrint();
            } else if (choice == 5) {
                tree.reverseLevelOrderPrint();
            } else if (choice == 6) {
                System.out.println("Enter a rectangle bottom left (X1,Y1) and top right (X2, Y2)" +
                        " as four doubles each separated by a space.");
                double x1 = sc.nextDouble();
                double y1 = sc.nextDouble();
                double x2 = sc.nextDouble();
                double y2 = sc.nextDouble();
                sc.nextLine();
                System.out.println("Searching for points within (" + x1 + "," + y1 +
                        ") and (" + x2 + "," + y2 + ")");
                System.out.println(tree.findPointsInRange(x1, y1, x2, y2));
                System.out.println("The crime data has been written to PGHCrimes.KML." +
                        " It is viewable in Google Earth Pro.");
            } else if (choice == 7) {
                System.out.println("Enter a point to find nearest crime. Separate with a space.");
                double x1 = sc.nextDouble();
                double y1 = sc.nextDouble();
                sc.nextLine();
                Neighbor neighbor =  tree.nearestNeighbor(x1,y1);
                System.out.println("Found the nearest crime at:\n" + neighbor.getPointer().getData());
            } else {
                System.out.println("Thank you for exploring Pittsburgh crimes in the 1990’s.");
                break;
            }
        }
    }
}
