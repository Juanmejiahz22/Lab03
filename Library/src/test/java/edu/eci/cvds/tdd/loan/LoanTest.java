package edu.eci.cvds.tdd.library.loan;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.user.User;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

public class LoanTest {

    @Test
    public void testLoanInitialization() {
        Book book = new Book("Clean Code", "Robert C. Martin", "978-0132350884");
        User user = new User();
        user.setId("123456");
        LocalDateTime now = LocalDateTime.now();

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(now);
        loan.setStatus(LoanStatus.ACTIVE);

        assertEquals(book, loan.getBook(), "El libro asignado debe ser correcto.");
        assertEquals(user, loan.getUser(), "El usuario asignado debe ser correcto.");
        assertEquals(now, loan.getLoanDate(), "La fecha de préstamo debe ser correcta.");
        assertEquals(LoanStatus.ACTIVE, loan.getStatus(), "El estado del préstamo debe ser ACTIVO.");
    }

    @Test
    public void testLoanReturn() {
        Loan loan = new Loan();
        LocalDateTime returnDate = LocalDateTime.now();
        loan.setReturnDate(returnDate);
        loan.setStatus(LoanStatus.RETURNED);

        assertEquals(returnDate, loan.getReturnDate(), "La fecha de devolución debe coincidir.");
        assertEquals(LoanStatus.RETURNED, loan.getStatus(), "El estado del préstamo debe ser DEVUELTO.");
    }

    @Test
    public void testLoanEquality() {
        Book book1 = new Book("Refactoring", "Martin Fowler", "978-0201485677");
        User user1 = new User();
        user1.setId("789101");
        LocalDateTime now = LocalDateTime.now();

        Loan loan1 = new Loan();
        loan1.setBook(book1);
        loan1.setUser(user1);
        loan1.setLoanDate(now);
        loan1.setStatus(LoanStatus.ACTIVE);

        Loan loan2 = new Loan();
        loan2.setBook(book1);
        loan2.setUser(user1);
        loan2.setLoanDate(now);
        loan2.setStatus(LoanStatus.ACTIVE);

        assertEquals(loan1, loan2, "Dos préstamos con los mismos valores deben ser iguales.");
    }

    @Test
    public void testLoanHashCode() {
        Book book1 = new Book("Refactoring", "Martin Fowler", "978-0201485677");
        User user1 = new User();
        user1.setId("789101");
        LocalDateTime now = LocalDateTime.now();

        Loan loan1 = new Loan();
        loan1.setBook(book1);
        loan1.setUser(user1);
        loan1.setLoanDate(now);
        loan1.setStatus(LoanStatus.ACTIVE);

        Loan loan2 = new Loan();
        loan2.setBook(book1);
        loan2.setUser(user1);
        loan2.setLoanDate(now);
        loan2.setStatus(LoanStatus.ACTIVE);

        assertEquals(loan1.hashCode(), loan2.hashCode(), "El hashCode debe ser igual para préstamos equivalentes.");
    }
}
