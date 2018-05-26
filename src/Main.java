import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;

public class Main {


    public static void main(String[] args) {

          File f = new File("records.bat");
          if(f.length()>0) {
              try (ObjectInputStream oos = new ObjectInputStream(new FileInputStream("records.bat"))) {
                  Authentication.personSignature = (HashMap<String, Employee>) oos.readObject();
                  Authentication.emailSignature=(HashSet<String>) oos.readObject();

              } catch (FileNotFoundException e) {
                  e.printStackTrace();
              } catch (IOException e) {
                  e.printStackTrace();
              } catch (ClassNotFoundException c) {
                  c.printStackTrace();
              }
          }

        Scanner sc = new Scanner(System.in);
        while (true) {
            String s;

            System.out.println("Write \"Entry\" if you want to enter an employee details \n Write \"Query\" if you want to get the records of an employee" +
                    "\nOr enter \"save\" to save the records and exit the program");
            s = sc.nextLine();
            s = s.toLowerCase();
            if (s.equals("entry")) {
                System.out.println("Enter the employee First name ");
                String firstName = sc.nextLine();
                System.out.println("Enter the employee Last name ");
                String lastName = sc.nextLine();
                System.out.println("Enter the employee Department ");
                String department = sc.nextLine();
                Employee e = new Employee(firstName, lastName, department);
                Authentication.personSignature.put(e.getEmail() + e.getPassword(), e);

                for (String str : Authentication.personSignature.keySet()) {
                    Employee emp = Authentication.personSignature.get(str);
                    System.out.println("The name of the person is " + emp.getFirstName() + " " + emp.getLastName() + " The email id is " + emp.getEmail());
                }
            } else if (s.equals("query")) {
                System.out.println("Give email id");
                String email = sc.nextLine();
                System.out.println("Give password");
                String password = sc.nextLine();
                if (!Authentication.personSignature.containsKey(email + password)) {

                    System.out.println("No record of employee found, try again");
                } else {
                    Employee e = Authentication.personSignature.get(email + password);
                    System.out.println("The name of the person is " + e.getFirstName() + " " + e.getLastName() + " \n and he belongs to " + e.getDepartment());
                }
            }
            else if (s.equals("save")){
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("records.bat"))) {


                    oos.writeObject(Authentication.personSignature);
                    oos.writeObject(Authentication.emailSignature);
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                System.out.println("Entries saved");
                break;
            }

        }


    }


}
//    Your email is ED.Thomas@accounts.acme.com
//Your password is iisro
//        Your department is accounts