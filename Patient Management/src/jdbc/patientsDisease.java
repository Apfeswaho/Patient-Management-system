package jdbc;
import java.util.Random;
import java.sql.*;

public class patientsDisease {

        static String dbURL = "jdbc:mysql://localhost:3306/patientinfo";
        static String dbUser = "root";
        static String dbPassword = "Admin";

        public static void dbInsert(int id, String examinationType,String diseaseDiscovered,String prescription,int patientNo) {
            try{
                //getting connection
                Connection myConn = DriverManager.getConnection(dbURL,dbUser,dbPassword);

                //create a statement which enables us to excute sqlQuery
                Statement state = myConn.createStatement();

                //insert data and executing sql
                state.executeUpdate("insert into patientsdisease"+ "(ID_patient,examinationType,diseaseDiscovered,prescription,patientNumber)"+" values('"+id+"','" +examinationType+"','" + diseaseDiscovered +"','"+prescription+"','"+patientNo+"')");//be careful of the conetation

                System.out.println("Insert complete");
                //process the result set from each row
                myConn.close();
            }catch(Exception exc){
                exc.printStackTrace();
            }
        }
        public static void dbIntersectInnerJoin() {
        try{
            //getting connection
            Connection myConn = DriverManager.getConnection(dbURL,dbUser,dbPassword);

            //create a statement which enables us to excute sqlQuery
            Statement myStmnt = myConn.createStatement();

            //excute sql query
            ResultSet join = myStmnt.executeQuery("select patients.ID,patients.name,patients.surname,patientsdisease.patientNumber from patients inner join patientsdisease on ID = ID_patient ");
            //ResultSet join = myStmnt.executeQuery("select patients.ID,patients.name,patients.surname,patientsdisease.patientNumber,patientsdisease.diseaseDiscovered from patients left join patientsdisease on ID = ID_patient ");
            //process the result set from each row
            while(join.next()){
                System.out.println(join.getInt("ID") + ", " +join.getString("surname") + ", " + join.getString("name")+", " + join.getInt("patientNumber"));

            }
            myConn.close();
        }catch(Exception exc){
            exc.printStackTrace();
        }
    }

    public static void main(String []args){

        String examinationType ="blood",diseaseDiscovered = "Diabetics", prescription ="Insulin pills";
      Random random = new Random();
        int patientNo = random.nextInt(1000);

        int id =random.nextInt(100);;

        dbInsert(id,examinationType,diseaseDiscovered,prescription,patientNo);
        dbIntersectInnerJoin();
    }


}
