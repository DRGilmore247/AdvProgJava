/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package advprogjava;

import java.util.ArrayList;
import java.util.Collections;
/**
 *
 * @author David
 */
public class AdvProgJava {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        AdvProgJava apj = new AdvProgJava();
        apj.run();
    }

    private void run() {
        
        Student alice = new Student(1, "Alice", "Zeigler", "Botany");
        Student bob = new Student(2, "Bob", "Yannay", "Literature");
        Student charlie = new Student(3, "Charlie", "Xemmex", "Engineering");
        
        ArrayList<Student> students = new ArrayList<Student> ();
        
        
        sortStudents(students, charlie, bob, alice);
        
        if (students.get(0).getSID() == 1)
        {
            System.out.println("It Worked");
        }
        else
            System.out.println("It Didn't Work");
        
        
    }
    
    private ArrayList<Student> sortStudents(ArrayList<Student> Students, Student a, Student b, Student c)
    {
        Students.add(c);
        Students.add(b);
        Students.add(a);
        
        Collections.sort(Students);
        
        return Students;
    }
    
    private class Student implements Comparable
            
    {
        int sID;
        String firstName;
        String lastName;
        String major;
        
        public Student(int ID, String first, String last, String maj)
        {
            sID = ID;
            firstName = first;
            lastName = last;
            major = maj;
        }
        
        public int getSID()
        {
            return this.sID;
        }

        @Override
        public int compareTo(Object t) {
            Student T = (Student)t;
            if (this.sID < T.sID)
                return -1;
            else if (this.sID == T.sID)
                return 0;
            else
                return 1;
        }
    }
    
    
}
