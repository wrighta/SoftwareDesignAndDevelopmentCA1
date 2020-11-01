package com.company;

import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner keyboard = new Scanner(System.in);
    // model is a special class called a singleton. It is only instanticated once.
    // get the model once here at the start of your application.
    // Then use model to ask it to model.addXXX(), model.removeXXX(), get etc.
    static Model model = Model.getInstance();

    public static void main(String[] args){

        Programmer p;

        int opt;
        do {
            System.out.println("\n\n********* MAIN MENU ********");
            System.out.println("1. Create new Programmer");
            System.out.println("2. View all Programmers");
            System.out.println("5. Exit");
            System.out.println();

            System.out.print("*****Enter option: *******\n\n");
            String line = keyboard.nextLine();
            opt = Integer.parseInt(line);
            // NOTE For above - slight different way of reading in an number, read the line as a String, then convert the String to an integer


            switch (opt) {

                // if the user choses option 1 create a programmer from user input and add it to the array list in Model.
                case 1: {
                    // ask the user for the programmer details, then create a programmer object p
                    p = readProgrammer();
                    // add the programmer object p to the model (ArrayList of programmers)
                    model.addProgrammer(p);
                    System.out.println("***** Programmer Added to the Model *****");

                    break;
                }

                // if the user choses option 2 get the array list of programmers from the model and display them all on screen.
                case 2: {
                    // this method will call the model to get the list of programmers and display them to the screen.
                    viewProgrammers();
                    break;
                }
            }
        }
        while (opt != 5);
        System.out.println("Goodbye");
    }

    // reads the details from the user, creates a Programmer object and returns this object to the calling program
    private static Programmer readProgrammer() {
        String name, email, mobile, skills;

        double salary;
        String line;

        // GetSting is a helper method created below to make the code a bit neater.
        // It is the similar to the two lines of code - System.out.println("Enter XXX")...readLine()

        System.out.print("Enter name : ");
        name = keyboard.nextLine();

        System.out.print("Enter email : ");
        email = keyboard.nextLine();

        System.out.print("Enter mobile : ");
        mobile = keyboard.nextLine();


        System.out.print("Enter skills : ");
        skills = keyboard.nextLine();

        System.out.print("Enter Salary : ");
        // this line reads in the double variable - salary which the user types, however the "ENTER" is still sitting in the buffer
        salary = keyboard.nextDouble();
        // this nextLine() swallows up the carraige return (ENTER) that is sitting in the buffer from when the user typed in the salary then hits enter.
        keyboard.nextLine();



        // Create the Programmer object p
        Programmer p =
                new Programmer(name, email, mobile,
                        skills, salary);

        //return the programmer object p to the calling method
        return p;
    }


    // gets the programmers array list from the model and displays it.
    private static void viewProgrammers() {

        // ask the model for the list of programmers
        List<Programmer> programmers = model.getProgrammers();

        System.out.println("***** Printing All Programmers *****");
        // display the list of programmers
        for (Programmer pr : programmers) {
            System.out.println(pr);
        }

        System.out.println("***** Finished Printing All Programmers *****");
    }
}
