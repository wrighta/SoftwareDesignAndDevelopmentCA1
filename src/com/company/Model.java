package com.company;

import java.util.ArrayList;
import java.util.List;

public class Model {

    private static Model instance = null;

    // we only want one instance of the Model - ie. one instance of the data, we don't want to create lots of Model object like we would the Programmer object.
    // So this is a special method that insures only one instance of the Model ever exists, and all handles to this object refer to the same model
    public static synchronized Model getInstance() {
        if (instance == null) {
            instance = new Model();
        }
        return instance;
    }

    // This model is has hardcoded programmers in the array, this data will eventually be retrieved from the database and we will delete this hardcoded data
    private List<Programmer> programmers;

    // Model constructor, at present it sets up the arraylist of programmers by hardcoding the data into the ArrayList, later we will set up connections to the database here
    private Model() {

        this.programmers = new ArrayList<>();

        // Hard code a few progrmmers into the array list until we have hooked up to the database.
        this.programmers.add(
                new Programmer(1,
                        "Joe Bloggs", "joe@bloggs.com", "087-6543210",
                        "Java C++ C#", 70000.00));

        this.programmers.add(
                new Programmer(2,
                        "Anne Bloggs", "anne@bloggs.com", "087-7654321",
                        "HTML CSS JavaScript", 75000.00));

        this.programmers.add(
                new Programmer(3,
                        "Fred Bloggs", "fred@bloggs.com", "087-8765432",
                         "UML JUnit Git", 78000.00));

        this.programmers.add(
                new Programmer(4,
                        "Mary Bloggs", "mary@bloggs.com", "087-9876543",
                         "PHP MySQL", 80000.00));
    }


    // returns the array list of programmers to the calling program.
    public List<Programmer> getProgrammers() {
        return new ArrayList<>(this.programmers);
    }

    // adds the programmer object that is passed in to the array list - note this is temporary while the program is running.
    // later this method will call database code to add the programmer to the database.
    public void addProgrammer(Programmer p) {
        this.programmers.add(p);
    }


}