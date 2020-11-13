package com.company;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ProgrammerTableGateway {
        private static final String TABLE_NAME = "programmer";
        private static final String COLUMN_ID = "id";
        private static final String COLUMN_NAME = "name";
        private static final String COLUMN_EMAIL = "email";
        private static final String COLUMN_MOBILE = "mobile";
        private static final String COLUMN_SKILLS = "skills";
        private static final String COLUMN_SALARY = "salary";

        private Connection mConnection;

        public ProgrammerTableGateway(Connection connection) {
            mConnection = connection;
        }

        // Called from the Model when the user wants to create a new programmer in the database, the new ID is created in the database and returned here
        public boolean insertProgrammer(Programmer p)  {

            String query;                   // the SQL query to execute
            PreparedStatement stmt;         // the java.sql.PreparedStatement object used to execute the SQL query
            int numRowsAffected;

            // I am going to create a new Programmer object, this object will have the Id after the row is inserted into the database
         //   Programmer dbProgrammer = null;

            // the required SQL INSERT statement with place holders for the values to be inserted into the database
            query = "INSERT INTO " + TABLE_NAME + " (" +
                    COLUMN_NAME + ", " +
                    COLUMN_EMAIL + ", " +
                    COLUMN_MOBILE + ", " +

                    COLUMN_SKILLS + ", " +
                    COLUMN_SALARY +
                    ") VALUES (?, ?, ?, ?, ?)";

            try {
                // create a PreparedStatement object to execute the query and insert the values into the query
                stmt = mConnection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
                stmt.setString(1, p.getName());
                stmt.setString(2, p.getEmail());
                stmt.setString(3, p.getMobile());

                stmt.setString(4, p.getSkills());
                stmt.setDouble(5, p.getSalary());

                //  execute the query and make sure that one and only one row was inserted into the database
                numRowsAffected = stmt.executeUpdate();

                // if numRowsAffected is 1 - that means the SQL insert inserted one row into the table, so the ID should have been auto-incremented and sent back here.
                // so assign this new ID to the ID.
                if (numRowsAffected == 1) {
//                    // if one row was inserted, retrieve the id that was assigned to that row in the database and ret
//                    ResultSet keys = stmt.getGeneratedKeys();
//                    keys.next();
//
//                    id = keys.getInt(1);
//                    //dbProgrammer.setId(id);
                     return true;
                }

            }
            catch (SQLException e)
            {
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in ProgrammerTableGateway : insertProgrammer(), Check the SQL you have created to see where your error is", e);
            }

            // return the Programmer object created with the new ID, The calling program in the Model should check to ensure it is not null
            return false;
        }


        // Called from the Model to get the complete list of programmers from the programmer table in the database
        public List<Programmer> getProgrammers()  {
            String query;                   // the SQL query to execute

            List<Programmer> programmers = new ArrayList<>(); // the java.util.List containing the Programmer objects created for each row
            // in the result of the query the id of a programmer

            String name, email, mobile, skills;
            int id;
            double salary;

            Programmer p;                   // a Programmer object created from a row in the result of the query

            // execute an SQL SELECT statement to get a java.util.ResultSet representing
            // the results of the SELECT statement
            query = "SELECT * FROM " + TABLE_NAME;

            try {
                Statement stmt;                 // the java.sql.Statement object used to execute the SQL query
                ResultSet rs;                   // the java.sql.ResultSet representing the result of SQL query

                stmt = this.mConnection.createStatement();
                // rs is a ResultSet object. It contains the rows of data from the database.
                rs = stmt.executeQuery(query);


                // loop through the result set taking out the programmer data from the DB
                // create a prorgammer object with this data and pop it into an arraylist
                while (rs.next()) {
                    id = rs.getInt(COLUMN_ID);
                    name = rs.getString(COLUMN_NAME);
                    email = rs.getString(COLUMN_EMAIL);
                    mobile = rs.getString(COLUMN_MOBILE);

                    skills = rs.getString(COLUMN_SKILLS);
                    salary = rs.getDouble(COLUMN_SALARY);

                    p = new Programmer(id, name, email, mobile, skills, salary);
                    programmers.add(p);
                }
            }

            catch (SQLException e){
                Logger.getLogger(Model.class.getName()).log(Level.SEVERE, "SQL Exception in ProgrammerTableGateway : getProgrammers(), Check the SQL you have created to see where your error is", e);
            }

            // return the arraylist of Programmer objects to the model.
            return programmers;
        }

        }
