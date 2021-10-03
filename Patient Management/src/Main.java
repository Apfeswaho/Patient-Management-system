import jdbc.db;

import java.util.ArrayList;
import java.util.Scanner;

class Patient {
    //Instance variables
    

    private String Name;
    private String Surname;
    private int Id;
    private double AccountBalance;

     public Patient(String name, String surname, int id, double accountBalance) {
         Name = name;
         Surname = surname;
         Id = id;
         AccountBalance = accountBalance;
     }

     public String getName() {
         return Name;
     }

     public void setName(String name) {
         Name = name;
     }

     public String getSurname() {
         return Surname;
     }

     public void setSurname(String surname) {
         Surname = surname;
     }

     public int getId() {
         return Id;
     }

     public void setId(int id) {
         Id = id;
     }

     public double getAccountBalance() {
         return AccountBalance;
     }

     public void setAccountBalance(double accountBalance) {
         if(accountBalance >= 0){
             AccountBalance = accountBalance;
         }

     }

 }

public class Main{

    public static Scanner input = new Scanner(System.in); //Scanner Object for taking users input

    static ArrayList<Patient> patients = new ArrayList<Patient>();

    //static db dataBase = new db();

    public static void main(String [] args){
        Menu();
    }

    public static void Menu(){//menu methods which contains the menu

        int selection;//variable that will take in the users choice on the menu

        System.out.println("please select:\n 1.To view the patient list\n 2.delete a patient \n 3.add new patient \n 4.view patient Balance \n 5. Exit");
        selection = input.nextInt();

        switch(selection){//switch statement that will function as the menu nevigator
            case 1:{
                patientList();
            }break;
            case 2:{
                deletePatient();
            }break;
            case 3:{
                addPatient();
            }break;
            case 4:{
                viewBalance();
            }break;
            case 5:{
                System.exit(0);
            }break;
            default:
                System.out.println("Wrong input selected try again");
                Menu();
        }

    }

    public static void viewBalance() {
            double total=0;
                int size = patients.size();
                for(Patient patientB:patients ){
                   total += patientB.getAccountBalance();
                }
            System.out.println("the Balance on the account is:R"+total);
                Menu();
        }
    public static void addPatient(){
        String Name,Surname;
        int Id;
        double AccountBalance;
        //count++;

        System.out.print("Enter patient Name:");
        Name = input.next();
        System.out.print("Enter patient Surname:");
        Surname = input.next();
        System.out.print("Enter patient Id number:");
        Id = input.nextInt();
        System.out.print("Enter patient account Balance:R");
        AccountBalance = input.nextDouble();

        db.dbInsertPrepared(Name,Surname,Id,AccountBalance);
        patients.add(new Patient(Name,Surname,Id,AccountBalance));
       // PatientDetails[count] = new Patient(Name,Surname,Id,AccountBalance);
        Menu();
    }
    public static void deletePatient(){
        int tempId;
        System.out.println("Enter the  Id of the patient you want to delete:");
        tempId = input.nextInt();
        Patient temp;
        int j;

         for(int i = 0; i <= patients.size(); i++){
            if(tempId == patients.get(i).getId()){
                j=i;
                patients.remove(i);
                //patient. = PatientDetails[j+1];
                db.dbDelete(tempId);
            }
        }
            Menu();
        }
    public static void patientList(){//
        SelectionSort();
        for(Patient viewPatients:patients){
            System.out.println("ID:"+viewPatients.getId()+" Surname:"+viewPatients.getSurname()+" Name:"+viewPatients.getName()+" Acccount Balance: R"+viewPatients.getAccountBalance());
        }
        //*******************************FROM THE DATABASE************************************************************
        db.dbView();
        Menu();
    }
    public static void SelectionSort(){//sorting algorithm to sort the database

        int i,j;
        Patient temp;

        for(i=0;i<patients.size();i++){

            for(j=i+1;j<patients.size();j++){

                if(patients.get(i).getId() > patients.get(j).getId()){

                    temp = patients.get(i);
                    patients.add(i,patients.get(j));
                    patients.add(j,temp);
                }
            }
        }
    }

}
