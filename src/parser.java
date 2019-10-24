import java.io.*;
import java.text.DecimalFormat;
import java.util.*;
import java.util.Iterator;


public class parser {
    public void parse() {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File("C:\\Users\\chase\\OneDrive\\Desktop\\FURI_UrbanMobility_ChaseOvercash\\2014-10-02.txt"));
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
            File fileName = new File("C:\\Users\\chase\\IdeaProjects\\FURI_FutureUrbanMobility\\src\\output.csv");
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true));
            writer.write(" ");
            while (scanner.hasNextLine()) {

                String temp = scanner.nextLine();
                String arr[] = temp.split(",");
                if (arr[3].equals("497.0")) {
                    writer.append(temp);
                    writer.newLine();

                    if (hashMap.containsKey(arr[2])) {
                        Node newNode = new Node(arr[2], (arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]));
                        hashMap.get(arr[2]).addLast(newNode);
                    } else {
                        LinkedList<Node> list = new LinkedList<>();
                        Node curr = new Node(arr[2], (arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]));
                        list.add(curr);
                        hashMap.put(arr[2], list);
                    }

//                    if (Integer.parseInt(arr[1].substring(1, 2)) == 7 || Integer.parseInt(arr[1].substring(1, 2)) == 8) {
//                        if (hashMap.containsKey(arr[1])) {
//                            LinkedList<Node> list = hashMap.get(arr[1]);
//                            Node curr = new Node(arr[2], (arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]));
//                            hashMap.get(arr[1]).add(curr);
//
//
//                        } else {
//
//                            LinkedList<Node> list = new LinkedList<>();
//                            Node curr = new Node(arr[2], (arr[3]), Double.parseDouble(arr[4]), Double.parseDouble(arr[5]), Double.parseDouble(arr[6]));
//                            list.add(curr);
//                            hashMap.put(arr[1], list);
//
//                        }
//                    }
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
                //               System.out.println(key + "" + concatenation);


            }

            //Do not forget to close the scanner
            scanner.close();
            writer.close();
        } catch (Exception exception) {
            System.out.println(exception);
            exception.printStackTrace();
        }

//        LinkedList<Node> listT = hashMap.get("B311070");
//        LinkedList<Node> listU = hashMap.get("B310260");
//        double sum = 0;
//        double sum1 = 0;
//        int stopCounter = 0;

//        for (Node n : listU)
//        {
////            System.out.println("Bus B310260 is going " + n.velocity + " kmh fast on bus route 497. Latitude is " + n.latitude + " Longitude is: " + n.longitude);
//            sum1 += n.velocity;
//            if (n.velocity == 0)
//            {
//                stopCounter++;
//            }
//        }
//        System.out.println("Average Velocity is " + (sum1 / listU.size()));
//        System.out.println("Bus B310260 was recorded " + listU.size() + " times out of 24 hours");
//        System.out.println("Bus B310260 Stopped a total of " + stopCounter + " times!");
//        for (Node n : listT) {
//            // System.out.println("Bus B311070 is going " + n.velocity + " kmh fast on bus route 497. Latitude is " + n.latitude + " Longitude is: " + n.longitude);
//            sum += n.velocity;
//        }
//        System.out.println("Average Velocity is " + (sum / listT.size()));
//        System.out.println("Bus B311070 was recorded " + listT.size() + " times out of 24 hours");

        Set<String> set = hashMap.keySet();
        int max = 0;
        String maxBus = "";
        for (String busIDkey : set)
        {
            double avg = 0;
            double maxV = 0;
            int stopped = 0;
            for (Node ns: hashMap.get(busIDkey)) {
                avg += ns.velocity;
                if (ns.velocity > maxV)
                {
                    maxV = ns.velocity;
                }
                if (ns.velocity == 0)
                {
                    stopped++;
                }
            }
            DecimalFormat format = new DecimalFormat("#.00");
            System.out.println("Avg Velocity for Bus " + busIDkey + " is " + format.format(avg/hashMap.get(busIDkey).size()) + "kmph with a high of " + maxV + " kmph and a stopping total of " +stopped
                    + " times out of " + hashMap.get(busIDkey).size() + " total recordings!");
            if (hashMap.get(busIDkey).size() > max)
            {
                max = hashMap.get(busIDkey).size();
                maxBus = busIDkey;
            }
        }
        System.out.println(maxBus + " was recorded the most at " + max + " times!");
        System.out.println(hashMap.size() + " buses on route 497!");

    }
}