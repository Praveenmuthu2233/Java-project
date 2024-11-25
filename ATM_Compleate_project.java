import java.util.*;

public class ATM_Compleate_project{
    public static void main(String[] args){
        int pin = 1234;
        int balance = 10000;

        int addAmount = 0;
        int takeAmount = 0;

        String name;
        
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter your pin number");
        
        int password = sc.nextInt();

        if(password ==pin)
        {
            System.out.println("Enter your name ");
            name = sc.next();
            System.out.println("Welcome " + name);

            while(true){
                System.out.println("Press 1 to check your balance");
                System.out.println("Press 2 to add amount");
                System.out.println("Press 3 to take amount");
                System.out.println("Press 4 to take resipt");
                System.out.println("Press 5 to Exit");

                int opt = sc.nextInt();
                
                switch (opt)
                {
                    case 1:
                        System.out.println("Your current balance is " + balance);
                        break;
                    case 2:
                        System.out.println("How much amount did you want to ADD to your account ");
                        addAmount = sc.nextInt();
                        System.out.println("Successfully credited");

                        balance = addAmount + balance;
                        break;
                    case 3:
                        System.out.println("How much amount did you want to take");
                        takeAmount = sc.nextInt();

                        if(takeAmount > balance)
                        {
                            System.out.println("your balance is insufficient");
                            System.out.println("Try less your available balance");
                        }else
                        {
                            System.out.println("Successfully taken");
                            balance = balance - takeAmount;
                        }
                        break;
                    case 4:
                        System.out.println("Welcome to All in one mini ATM");
                        System.out.println("Available balance is " + balance);
                        System.out.println("Amount deposited     " + addAmount);
                        System.out.println("Amount taken     " + takeAmount);
                        System.out.println("Thank you for coming");

                }
                if(opt==5)
                {
                    System.out.println("Thank you");
                    break;
                }
            }
        }
        else
        {
            System.out.println("Wrong pin number");
        }
    }
}