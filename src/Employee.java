import java.io.Serializable;
import java.util.Scanner;

public class Employee implements Serializable {
    private String firstName;
    private String lastName;
    private String department;
    private String email;
    private String password;


    public Employee(String firstName,String lastName, String department) {
         department=department.toLowerCase();
       while (!setDepartment(department)){
           System.out.println("You've set the wrong depatement, coose from available depatements \n 1.Sales \n 2.Dev \n 3.Accounts \n 4.Unknown");
           Scanner sc=new Scanner(System.in);
           department=sc.nextLine();
           department=department.toLowerCase();
       }
       setEmail(firstName,lastName,department);
       setPassword();
       System.out.println("Your email is "+getEmail());
       System.out.println("Your password is "+getPassword());
       System.out.println("Your department is "+getDepartment());
       setFirstName(firstName);
       setLastName(lastName);
    }

   public String getDepartment() {
        return department;
    }

    public boolean setDepartment(String department) {
      department = department.toLowerCase();
        switch (department) {
            case "sales":
                this.department="sales";
                break;
            case "dev":
                this.department="dev";
                break;
            case "accounts":
                this.department="accounts";
                break;
            case "unknown":
                this.department = "unknown";
                break;
                default:
                    return false;

        }
      return true;
    }

    public String getEmail() {
        return  email;
    }

    public void setEmail(String firstName,String lastName,String department) {
        email=firstName+"."+lastName+"@"+department+"."+"acme"+"."+"com";
        while (Authentication.emailSignature.contains(email)){
            firstName=firstName+genetateRandomChar();
            email=firstName+"."+lastName+"@"+department+"."+"acme"+"."+"com";
        }

        Authentication.emailSignature.add(email);

    }

    public String getPassword() {
        return password;
    }

    public void setPassword() {
        this.password = generatePassword();
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public  void setLastName(String lastName){
        this.lastName=lastName;
    }

    public String getFirstName() {
        return firstName;
    }
    public  String getLastName(){
        return lastName;
    }

    public static char genetateRandomChar() {
        return (char) ('a' + (Math.random() * 26));
    }

    public static String generatePassword() {
        String password = "";
        for (int i = 0; i < 5; i++) {
            password += genetateRandomChar();
        }

        return password;
    }
public  static void main(String args[]){
//
System.out.println("Yeah the employee clss is working");
}

}
