package edu.ithaca.dturnbull.bank;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     * @throws InsufficientFundsException if amount is greater than balance
     * @throws IllegalArgumentException if amount is negative
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if(amount >= 0){
            if(amount <= balance){
                balance -= amount;
            }
            else{
                throw new InsufficientFundsException("Cannot withdraw more than the balance");
            }
        }
        else{
            throw new IllegalArgumentException("Cannot withdraw a negative amount");
        }
    }


    public static boolean isEmailValid(String email){
        if (email.indexOf('@') == -1){
            return false;
        }
        else {
            if (!email.matches("^[a-zA-Z0-9].*")){
                return false;
            }else{
                String user = email.substring(0, email.indexOf('@'));
                String domain = email.substring(email.indexOf('@')+1, email.length());
                if (user == "" || domain == ""){
                    return false;
                }
                else{
                    if (domain.indexOf('.') == -1){
                        return false;
                    }
                    else{
                        String predot = domain.substring(0, domain.indexOf('.'));
                        String dot = domain.substring(domain.indexOf('.'), domain.length());
                        if (dot == "" || predot == ""){
                            return false;
                        }
                        else{
                            return true;
                        }
                    }
                }
            }
        }
    }
}