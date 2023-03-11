// Name: Sreemoyee Mukherjee
// Course: Data Structures & Algorithms
// Assignment Number: 2
package dsa;

import java.io.FileWriter;
import java.io.IOException;

// class for creating a list of crimes
public class ListOfCrimes {

    // head initially points to a dummy node
    private LinkedListNode head = new LinkedListNode(null, null);
    private LinkedListNode current = head;  // current is initially same as head
    private int count;  // stores number of nodes containing data

    // Adds a crime
    public void addCrime(TwoDTreeNode data){
        head.addNodeAfter(data);
        count++;
    }

    // For getting a particular crime data
    public String getCrime(){
        if(head == null){
            return null;
        }
        if(current.getData() == null){     // to move current from the dummy node to the first node
            current = current.getLink();
        }
        return current.getData().getData();
    }

    // keeps count of the total number of crimes in the list
    public int getCount() {
        return count;
    }

    // post-condition: returns the list as a string
    @Override
    public String toString() {
        current = head.getLink(); // to move current from the dummy node to the first node
        String data = "";
        while (current != null){
            data+=current.getData().getData() + "\n";
            current = current.getLink();
        }
        current = head; // re-initialising current to head for next use
        return data;
    }

    // post-condition: creates the PGHCrimes.KML file and stores a KML representation of the list.
    public void toKML(){
        current = head.getLink(); // to move current from the dummy node to the first node
        try {
            FileWriter myWriter = new FileWriter("PGHCrimes.KML");
            myWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>" +
                    " <kml xmlns=\"http://earth.google.com/kml/2.2\">" +
                    " <Document> " +
                    "<Style id=\"style1\"> <IconStyle> <Icon>" +
                    " <href>http://maps.gstatic.com/intl/en_ALL/mapfiles/ms/micons/blue-dot.png</href>" +
                    " </Icon> </IconStyle> </Style> " );
            while (current != null){
                myWriter.write("<Placemark> <name>" + current.getData().getData().split(",")[4] +
                        "</name> <description>" + current.getData().getData().split(",")[3] +
                        "</description> <styleUrl>#style1</styleUrl> " +
                        "<Point> <coordinates>" + current.getData().getData().split(",")[8] +
                        "," + current.getData().getData().split(",")[7] +
                        ",0.000000</coordinates> </Point> </Placemark> ");
                current = current.getLink();
            }
            myWriter.write("</Document> </kml>");
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        current = head; // re-initialising current to head for next use
    }
}
