package projectalgorithm;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;
import java.io.*;
import java.util.*;

public class ProjectAlgorithm {

    

    public static void main(String[] args) throws FileNotFoundException {
        Scanner s = new Scanner(new File("input.txt"));//scanner to read from file

        ArrayList<Course> al = new ArrayList();
        
        
        
        Course cu;
        String cn;
        String[] pr;
        int ch;
        String[] line;
        String val;
        //start reading from file
        while (s.hasNextLine()) {
            val = s.nextLine();//get the whole line
            line = val.split(",");//split the values and store them in an array
            cn = line[0];//gets course name

            //if course has more than one prerequisite split and store them in an array
            pr = line[1].split("-");//get prerequisite

            ch = Integer.parseInt(line[2]);//get credit Hours
            cu = new Course(cn, pr, ch, 0);//create course object

            al.add(cu);//add couse to arraylist

        }

        int i = 0;
        String[] arr = new String[al.size()];
        //loop through the arraylist get course name and store it the array
        for (Course c : al) {
            arr[i] = c.getName();
            i++;
        }

        int indx1;
        int indx2;
        Graph1 g = new Graph1(al.size());
        for (Course c : al) {
            //if course has prerquisite (prerequisite not empty)
            if (!c.getPrerequisite()[0].equals(" ")) {
                //if course has more than one prerequisite
                if (c.getPrerequisite().length > 1) {
                    //loop and get courseName and prerequisite indexes
                    for (int x = 0; x < c.getPrerequisite().length; x++) {
                        //get index of the course prerequisite in the arraylist
                        indx1 = Arrays.asList(arr).indexOf(c.getPrerequisite()[x]);
                        //get index of the course in the arraylist
                        indx2 = Arrays.asList(arr).indexOf(c.getName());
                        System.err.println(indx1 + " " + indx2);
                        g.addEdge(indx1, indx2);//create an edge between prerequisite and the course 
                    }
                } else {//if course has 1 prerquisite
                    //get index of the course prerequisite in the arraylist
                    indx1 = Arrays.asList(arr).indexOf(c.getPrerequisite()[0]);
                    //get index of the course in the arraylist
                    indx2 = Arrays.asList(arr).indexOf(c.getName());
                    //create an edge between prerequisite and the course
                    System.err.println(indx1 + " " + indx2);
                    g.addEdge(indx1, indx2);
                }

            }

        }
        g.DFS();

        //check if graph has cycle
        if (g.flag == 1) {
            //graph has cycly
            System.out.println("No Study Plan not Possible");
        } else {

            //System.out.println("Following is a Topological "
            //        + "sort of the given graph");
            //graph has no cycly
            ArrayList<Course> ar1 = new ArrayList<>();
            g.topologicalSort();
            //ar1=al;
            for (int in : g.ar) {
                Course crs = al.get(in);
                ar1.add(crs);
            }
            //start here
            
//            Scanner scan = new Scanner(System.in);
//            System.out.print("enter course to get its prerquisite: ");
//            String str1 = scan.next();
//            boolean flag = true;
//            int f = 0;
//            outerloop:
//            while (flag) {
//                int var = 1;
//                for (Course c : al) {
//                    if (c.getName().equals(str1)) {
//                        if (c.getPrerequisite().length > 1) {
//                            for (int w = 0; w < c.getPrerequisite().length; w++) {
//                                System.out.println(c.getPrerequisite()[w]);
//                                str1 = c.getPrerequisite()[w];
//                                meathod(al, str1);
//                            }
//                            break outerloop;
//                        } else if (c.getPrerequisite()[0].equals(" ") && f == 0) {
//                            System.out.println("Course has no prerequisite.");
//                            break outerloop;
//                        } else {
//                            if (c.getPrerequisite()[0].equals(" ")) {
//                                break outerloop;
//                            }
//                            System.out.println(c.getPrerequisite()[0]);
//                            str1 = c.getPrerequisite()[0];
//                            f = 1;
//                            break;
//                        }
//
//                    }
//                    if (var == al.size()) {
//                        System.out.println("enter valid course name.");
//                        flag = false;
//                    }
//                    var++;
//                }
//            }
            //end here
            MainMenu gui = new MainMenu(ar1);
            gui.setVisible(true);
        }
    }

//    static void meathod(ArrayList<Course> al, String str1) {
//        boolean flag = true;
//        int f = 0;
//        int f2=0;
//        outerloop:
//        while (flag) {
//            int var = 1;
//            for (Course c : al) {
//                if (c.getName().equals(str1)) {
//                    if (c.getPrerequisite().length > 1) {
//                        for (int w = 0; w < c.getPrerequisite().length; w++) {
//                            System.out.println(c.getPrerequisite()[w]);
//                            str1 = c.getPrerequisite()[w];
//                            meathod(al, str1);
//                            f2=1;
//                        }
//                        break outerloop;
//                    } else if (c.getPrerequisite()[0].equals(" ") && f == 0) {
//                        System.out.println("Course has no prerequisite.");
//                        break outerloop;
//                    } else {
//                        if (c.getPrerequisite()[0].equals(" ")) {
//                            break outerloop;
//                        }
//                        System.out.println(c.getPrerequisite()[0]);
//                        str1 = c.getPrerequisite()[0];
//                        f = 1;
//                        break;
//                    }
//
//                }
//                if (var == al.size() && f2==0) {
//                    System.out.println("enter valid course name.");
//                    flag = false;
//                }
//                var++;
//            }
//        }
//    }

}

class Graph1 {

    int flag = 0;
    private int V;   // No. of vertices
    LinkedList<Integer> adj[]; // Adjacency List

    //Constructor
    Graph1(int v) {

        V = v;
        adj = new LinkedList[v];
        for (int i = 0; i < v; ++i) {
            adj[i] = new LinkedList();
        }
    }

    // Function to add an edge into the graph
    void addEdge(int v, int w) {
        adj[v].add(w);
        System.err.println(adj[v]);
    }

    // A recursive function used by topologicalSort
    void topologicalSortUtil(int v, boolean visited[],
            Stack stack) {
        // Mark the current node as visited.
        visited[v] = true;
        Integer i;

        // Recur for all the vertices adjacent to this
        // vertex
        Iterator<Integer> it = adj[v].iterator();
        while (it.hasNext()) {
            i = it.next();
            if (!visited[i]) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Push current vertex to stack which stores result
        stack.push(new Integer(v));
    }

    // The function to do Topological Sort. It uses
    // recursive topologicalSortUtil()
    Integer[] ar = new Integer[40];
    int x = 0;

    void topologicalSort() {
        Stack stack = new Stack();

        // Mark all the vertices as not visited
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            visited[i] = false;
        }

        // Call the recursive helper function to store
        // Topological Sort starting from all vertices
        // one by one
        for (int i = 0; i < V; i++) {
            if (visited[i] == false) {
                topologicalSortUtil(i, visited, stack);
            }
        }

        // Print contents of stack
        while (stack.empty() == false) {
            ar[x] = (int) stack.pop();
            x++;
        }

    }

    void DFSUtil(boolean visited[], int i, Stack<Integer> st) {
        st.push(i);
        visited[i] = true;
        for (int k : adj[i]) {
            if (!visited[k]) {
                DFSUtil(visited, k, st);
            } else if (st.contains(k)) {
                flag = 1;
                break;
            }
        }
        if (flag != 1) {
            st.pop();
        }
    }

    void DFS() {
        Stack<Integer> st = new Stack<Integer>();
        boolean visited[] = new boolean[V];
        for (int i = 0; i < V; i++) {
            if (!visited[i]) {
                DFSUtil(visited, i, st);
            }
        }
    }

}
// This code is contributed by Aakash Hasija

