package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse(BankAccount.isEmailValid(""));         // empty string - boundary case
        assertFalse(BankAccount.isEmailValid("gighoithaca.com"));  // equivalence partition - no @
        assertFalse(BankAccount.isEmailValid("renn@ithaca"));    // equivalence partition - no .com/.edu etc   
        assertFalse(BankAccount.isEmailValid("@ithaca.com"));      // equivalence partition - no user - boundary case
        assertFalse(BankAccount.isEmailValid("garenigho@gmailcom")); //equivalence partition - no . in domain
        assertFalse(BankAccount.isEmailValid("g@.com"));      // equivalence partition - no domain - boundary case
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }
    

}