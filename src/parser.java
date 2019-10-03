import java.io.*;
import java.util.*;

public class parser {
    public void parse() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\chase\\IdeaProjects\\FURI_BusProj\\src\\BusProj\\2014-10-01.txt"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        class Node {
            private double velocity;
            private String busID;
            private String busRoute;
            private double latitude;
            private double longitude;
            private Node next;
            public Node head;

            public Node(String ID, String route, double longitude, double latitude, double speed) {
                this.velocity = speed;
                this.busID = ID;
                this.busRoute = route;
                this.latitude = latitude;
                this.longitude = longitude;
            }

            public Node(String ID) {
                velocity = -1;
                this.busID = ID;
            }

            @Override
            public String toString() {
                return busID + "," + velocity;
            }
        }
        LinkedHashMap<String, LinkedList<Node>> hashMap = new LinkedHashMap<>();
        scanner.useDelimiter(",");

        try {
            File fileName = new File("C:\\Users\\chase\\IdeaProjects\\FURI_BusProj\\src\\BusProj\\output.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(" ");
            while (scanner.hasNextLine()) {

                String temp = scanner.nextLine();
                if (temp.contains("474") && temp.contains("A291060")) {
                    writer.append(temp);
                    writer.newLine();
                    String arr[] = temp.split(",");
                    if (Integer.parseInt(arr[1].substring(1, 2)) == 7 || Integer.parseInt(arr[1].substring(1, 2)) == 8) {
                        if (hashMap.containsKey(arr[1])) {
                            LinkedList<Node> list = hashMap.get(arr[1]);
                            Node curr = new Node(arr[2], (arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]));
                            hashMap.get(arr[1]).add(curr);
//                            LinkedList<Node> list = hashMap.get(arr[1]);
//                            int counter = 0;
//                            Node curr = new Node(arr[2], Double.parseDouble(arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]));
//                            for (Node n: list) {
//                                if (n.busID.equals(curr.busID))
//                                {
//                                    list.set(counter, curr);
//                                    break;
//                                }
//                                counter ++;
//                            }

                        } else {
//                            LinkedList<Node> list = new LinkedList<>();
//                            list.add(new Node("A290140"));
//                            list.add(new Node("A29089"));
//                            list.add(new Node("A411540"));
//                            list.add(new Node("A411720"));
//                            list.add(new Node("A411750"));
//                            Node curr = new Node(arr[2], Double.parseDouble(arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]));
//                            int counter = 0;
//                            for (Node n: list) {
//                                if (n.busID.equals(curr.busID))
//                                {
//
//                                    list.set(counter,curr);
//                                    break;
//                                }
//                                counter ++;
//                            }
//                            hashMap.put(arr[1], list);
                            LinkedList<Node> list = new LinkedList<>();
                            Node curr = new Node(arr[2], (arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]));
                            list.add(curr);
                            hashMap.put(arr[1], list);

                        }
                    }
                }
            }
            for (String name : hashMap.keySet()) {

                String key = name;
                String concatenation = "";
                for (Node n : hashMap.get(name)) {
//                    if (n.velocity == -1)
//                        concatenation = concatenation + "," + n.busID + "," + " ";
//                    else
                    concatenation = concatenation + "," + n.busID + "," + n.velocity;
                }
                System.out.println(key + "" + concatenation);


            }

            //Do not forget to close the scanner
            scanner.close();
            writer.close();
        } catch (Exception exception) {
            System.out.println(exception);
            exception.printStackTrace();
        }
    }
}