package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class LibraryTest {

   
    @Test
    public void testAddBook() {
        Library library = new Library();
        Book book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");

        boolean result = library.addBook(book);

        assertTrue(result, "El libro debería agregarse correctamente.");
    }

    @Test
    public void testLoanABook() {
        Library library = new Library();
        Book book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
        User user = new User();
        user.setId("123456");
        library.addUser(user);

        library.addBook(book);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());

        assertNotNull(loan, "El préstamo debería crearse correctamente.");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El préstamo debería estar activo.");
    }   

    @Test
    public void testReturnLoan() {
        Library library = new Library();
        Book book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
        User user = new User();
        user.setId("123456");

        library.addUser(user);
        library.addBook(book);
        Loan loan = library.loanABook(user.getId(), book.getIsbn());
        Loan returnedLoan = library.returnLoan(loan);

        assertNotNull(returnedLoan, "El préstamo debería devolverse correctamente.");
        assertEquals(LoanStatus.RETURNED, returnedLoan.getStatus(), "El préstamo debería estar devuelto.");
    }
}