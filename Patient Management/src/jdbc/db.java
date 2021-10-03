package jdbc;

import java.sql.*;
import java.util.Scanner;

public class db {
   static String dbURL = "jdbc:mysql://localhost:3306/patientinfo";
    static String dbUser = "root";
    static String dbPassword = "Admin";

    public static void dbView(){
        try{
            //getting connection
            Connection myConn = DriverManager.getConnection(dbURL,dbUser,dbPassword);

            //create a statement which enables us to excute sqlQuery
            Statement myStmnt = myConn.createStatement();

            //excute sql query
            ResultSet myR = myStmnt.executeQuery("select * from patients");

            //process the result set from each row
            while(myR.next()){
                System.out.println(myR.getString("surname") + ", " + myR.getString("name")+ "" );
            }
            myConn.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }
    public static void dbInsertPrepared(String surname,String name,int id,double balance){
        try{
            //getting connection
            Connection myConn = DriverManager.getConnection(dbURL,dbUser,dbPassword);

            //create a statement which enables us to excute sqlQuery
            Statement state = myConn.createStatement();

            //insert data and executing sql
            state.executeUpdate("insert into patients"+ "(surname,name,ID,balance)"+" values('"+name+"','" + surname +"','"+id+"','"+balance+"')");//be careful of the conetation

            System.out.println("Insert complete");
            //process the result set from each row
            myConn.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }
    public static void dbInsert(){
        try{
            //getting connection
            Connection myConn = DriverManager.getConnection(dbURL,dbUser,dbPassword);

            //create a statement which enables us to excute sqlQuery
           // Statement myStmnt = myConn.createStatement();

            //create a prepared statement which enables us to excute sqlQuery
            PreparedStatement myStmnt = myConn.prepareStatement("insert into patients (surname,name,ID,balance)");

            //excute sql query
            String sql = "insert into patients"+ "(surname,name,ID,balance)"+" values ('Tshivas','Rex','3','250')";

            myStmnt.executeUpdate(sql);

            System.out.println("Insert complete");
            //process the result set from each row
            myConn.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }


    public static void dbUpdate(int id){

        try{
            //getting connection
            Connection myConn = DriverManager.getConnection(dbURL,dbUser,dbPassword);

            //create a statement which enables us to excute sqlQuery
            Statement myStmnt = myConn.createStatement();

            //excute sql query
            String sql = "UPDATE  patients"+ " set name = 'vele'"+ " WHERE ID="+id;

            int rowsAffected = myStmnt.executeUpdate(sql);
            System.out.println("Rows affected:"+ rowsAffected);
            System.out.println("update complete");
            //process the result set from each row
            myConn.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }
    public static void dbDelete(int id){

        try{
            //getting connection
            Connection myConn = DriverManager.getConnection(dbURL,dbUser,dbPassword);

            //create a statement which enables us to excute sqlQuery
            Statement myStmnt = myConn.createStatement();

            //excute sql query
            String sql = "delete from patients"+ " WHERE ID="+id;

            int rowsAffected = myStmnt.executeUpdate(sql);
            System.out.println("Rows affected:"+ rowsAffected);
            System.out.println("update complete");
            //process the result set from each row
            myConn.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }
    public static void main(String []args){
  /*      Scanner input = new Scanner(System.in);

        String surname = "mabada",name = "jennet";
        int id=5;
        double cash=369.15;
        String nameInput,surnameInput="hello";

        System.out.println("Enter name:");
        nameInput = input.next();
*/

        //dbInsertPrepared(surname,name,id,cash);
       dbUpdate(77);
        //dbDelete(2);
        dbView();
    }
}
