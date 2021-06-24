/**
 * CSCI1130 Assignment 2 Cash Point
 * 
 * I declare that the assignment here submitted is original
 * except for source material explicitly acknowledged,
 * and that the same or closely related material has not been
 * previously submitted for another course.
 * I also acknowledge that I am aware of University policy and
 * regulations on honesty in academic work, and of the disciplinary
 * guidelines and procedures applicable to breaches of suck
 * policy and regulations, as contained in the website.
 * 
 * University Guideline on Academic Honesty:
 *   http://www.cuhk.edu.hk/policy/academichonesty
 * Faculty of Engineering Guidelinmes to Academic Honesty:
 *   https://www.erg.cuhk.edu.hk/erg/AcademicHonesty
 * 
 * Student Name: Pau Chun Wai
 * Student ID  : 1155136412
 * Date        : 16/10/2020
 */
package cashpoint;

import static java.lang.Integer.parseInt;
import javax.swing.JOptionPane;

/**
 * @author Chun Wai PAU <1155136412@link.cuhk.edu.hk>
 * @version 1.0
 * @since 8/10/2020
 */
public class CashPoint {
    private static double balance = 109700.0;
    
    /**
     * Show a menu of choices and get user's input, given method
     * @return a String of user input: "1", "2", "3", "4", null means Cancel/Close
     */
    public static String showMainMenu(){
        return JOptionPane.showInputDialog("Cash Point: Input your task\n"
                                            + "1. Check Balance\n"
                                            + "2. Cash Deposit\n"
                                            + "3. Cash Withdrawal\n" 
                                            + "4. Exit\n"
                                            , "<type [1 - 4] here>");
    }
   
    /**
     * Show the deposit menu, only allowed input of 1 - 20, 
     * the process of deposit is not here, please refer to deposit(int) 
     * @return the amount user want to deposit, the unit is $100=1
     */
    public static String showDepositMenu(){
            return JOptionPane.showInputDialog("Cash Deposit (HKD)\n"
                                                +"Input # of $100 banknotes [20 max]"
                                                ,"20");
    }
    
    /**
     * show the withdrawal menu, by choosing 1 or 2 to indicate the currencty
     * @return the choice of the currency
     */
    public static String showWithdrawalMenu(){
        return JOptionPane.showInputDialog("Cash Withdrawal: Choose your currency\n"
                                            +"1. Hong Kong Dollar (HKD)\n"
                                            +"2. Korean Won (KRW)\n"
                                            ,"<type [1 or 2] here>");

    }
    
    /**
     *Show the balance of the user with 2 decimal places
     */
    public static void checkBalance(){
        String balance_str=String.format("Balance (HKD): %.2f\n", balance);
        JOptionPane.showMessageDialog(null, balance_str);
    }
    
    /**
     * Start the deposit by calling showDepositMenu() to get the amount user want to deposit
     * update the balance by user input*100
     */
    public static void deposit(){
        int noOfBanknote,amountDeposit;
        boolean depositComplete=false;
        String str_noOfBanknote;
        do{
            str_noOfBanknote=showDepositMenu();
            if (str_noOfBanknote!=null){
                noOfBanknote=parseInt(str_noOfBanknote);
                if(noOfBanknote>=1 && noOfBanknote<=20){
                    amountDeposit=noOfBanknote*100;
                    balance+=amountDeposit;
                    System.out.println("The balance has been updated");
                    JOptionPane.showMessageDialog(null, "HKD "+amountDeposit+" deposited");
                    depositComplete=true;
                }
                else JOptionPane.showMessageDialog(null,"Invalid input");
            }
        }while(str_noOfBanknote!=null && depositComplete==false) ;
    }
    
    /**
     * A centre to navigate user to HKD or KRW withdrawal by their input
     */
    public static void withdraw(){
        String choice;
        double balanceTemp=balance;
        do{
            choice=showWithdrawalMenu();
            if (choice==null)System.out.println("Back to Main Menu");
            else if ("1".equals(choice)){
                System.out.println("User have entered HKD withdraw session");
                withdrawByHKD();
                if (balanceTemp!=balance)choice=null;
            }
            else if ("2".equals(choice)){
                System.out.println("User have entered KRW withdraw session");
                withdrawByKRW();
                if (balanceTemp!=balance)choice=null;
            }
            else JOptionPane.showMessageDialog(null,"Invalid input");         
        }while(choice!=null);
    }
    
    /**
     * withdraw by using HKD, withdraw by $500 and $100 banknote only
     */
    public static void withdrawByHKD(){
        boolean withdrawComplete=false;
        int noOfHKD500,noOfHKD100;
        do{
            String amountInput;
            int cashWithdraw;
            amountInput=JOptionPane.showInputDialog("Cash Withdrawal (HKD):\n"
                                                    +"100 min, 10000 max");
            if(amountInput==null){
                withdrawComplete=true;
                System.out.println("User has cancelled HKD withdrawal");
            }
            else {
                cashWithdraw=parseInt(amountInput);
                if (cashWithdraw>=100 && cashWithdraw<=10000){
                    cashWithdraw/=100;
                    cashWithdraw*=100;
                    int reply=JOptionPane.showConfirmDialog(null, "Banknotes provided for HKD are 500 & 100\nWithdraw HKD "
                                                            +cashWithdraw+" or not?","",JOptionPane.YES_NO_OPTION);
                    if (reply==JOptionPane.YES_OPTION){
                        if(cashWithdraw>balance){
                            JOptionPane.showMessageDialog(null,"Not enough balance, input again");
                        }else {
                            int amountCheck=cashWithdraw;
                            noOfHKD500=amountCheck/500;
                            amountCheck-=500*noOfHKD500;
                            noOfHKD100=amountCheck/100;
                            amountCheck-=100*noOfHKD100;
                            String message="HKD "+cashWithdraw+" withdrawn\n";
                            if (noOfHKD500>0)message+="HKD 500 x "+noOfHKD500+"\n";
                            if (noOfHKD100>0)message+="HKD 100 x "+noOfHKD100+"\n";
                            JOptionPane.showMessageDialog(null,message);
                            balance-=cashWithdraw;
                            System.out.println("Balance updated");
                            withdrawComplete=true;
                        }
                    }else System.out.println("User returned to amount input session");
                }
                else JOptionPane.showMessageDialog(null,"Invalid input");
            }
             
        }while(withdrawComplete==false);
    }
    
    /**
     * withdraw by using KRW, withdraw by $10000 and $1000 banknote only
     * currency converter is available
     */
    public static void withdrawByKRW(){
        boolean withdrawComplete=false;
        int noOfKRW10000,noOfKRW1000;
        do{
            String amountInput;
            int cashWithdraw;
            amountInput=JOptionPane.showInputDialog("Cash Withdrawal (KRD):\n"
                                                    +"1000 min, 200000 max");
            if(amountInput==null){
                withdrawComplete=true;
                System.out.println("User has cancelled KRW withdrawal");
            }
            else {
                cashWithdraw=parseInt(amountInput);
                if (cashWithdraw>=1000 && cashWithdraw<=200000){
                    cashWithdraw/=1000;
                    cashWithdraw*=1000;
                    double KRWToHKD=cashWithdraw/150.0;
                    String output=String.format("Banknotes provided for KRW are 10000 & 1000\n"
                                            + "Withdraw KRW %d (HKD %.2f) or not?",cashWithdraw,KRWToHKD);
                    int reply=JOptionPane.showConfirmDialog(null,output,"",JOptionPane.YES_NO_OPTION);
                    if (reply==JOptionPane.YES_OPTION){
                        if(KRWToHKD>balance){
                            JOptionPane.showMessageDialog(null,"Not enough balance, input again");
                        }else {
                            int amountCheck=cashWithdraw;
                            noOfKRW10000=amountCheck/10000;
                            amountCheck-=10000*noOfKRW10000;
                            noOfKRW1000=amountCheck/1000;
                            amountCheck-=1000*noOfKRW1000;
                            String message="KRW "+cashWithdraw+" withdrawn\n";
                            if (noOfKRW10000>0)message+="KRW 10000 x "+noOfKRW10000+"\n";
                            if (noOfKRW1000>0)message+="KRW 1000 x "+noOfKRW1000+"\n";
                            JOptionPane.showMessageDialog(null,message);
                            balance-=KRWToHKD;
                            System.out.println("Balance updated");
                            withdrawComplete=true;
                        }
                    }else System.out.println("User returned to amount input session");
                }
                else JOptionPane.showMessageDialog(null,"Invalid input");
            }
             
        }while(withdrawComplete==false);
    }
               
    public static void main(String[] args){
        String choice;
        do{
            choice = showMainMenu();
            if(choice == null)
                System.out.println("User closed or cancelled dialog box");
            else if ("1".equals(choice)){
                System.out.println("User picked 1");
                checkBalance();
            }
            else if ("2".equals(choice)){
                System.out.println("User picked 2");
                System.out.println("User has entered to Deposit Menu");
                deposit();
            }
            else if ("3".equals(choice)){
                System.out.println("User picked 3");
                System.out.println("User has entered to Withdraw Menu");
                withdraw();
            }
            else if ("4".equals(choice)){
                System.out.println("User picked 4");
                JOptionPane.showMessageDialog(null,"Hope to serve you again");
                System.out.println("Service Finished");
            }else JOptionPane.showMessageDialog(null,"Invalid input");
        }while(!("4".equals(choice)|| choice == null));
        
    }
}
