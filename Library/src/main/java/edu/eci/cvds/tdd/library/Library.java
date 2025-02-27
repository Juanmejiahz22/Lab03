package edu.eci.cvds.tdd.library;

import edu.eci.cvds.tdd.library.book.Book;
import edu.eci.cvds.tdd.library.loan.Loan;
import edu.eci.cvds.tdd.library.loan.LoanStatus;
import edu.eci.cvds.tdd.library.user.User;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Library {

    private final List<User> users;
    private final Map<Book, Integer> books;
    private final List<Loan> loans;

    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        int count = books.getOrDefault(book, 0);
        books.put(book, count + 1);
        return true;
    }

    public Loan loanABook(String userId, String isbn) {
        Book book = findBookByIsbn(isbn);
        if (book == null || !isBookAvailable(book)) {
            return null;
        }

        User user = findUserById(userId);
        if (user == null) {
            return null;
        }

        Loan loan = new Loan();
        loan.setBook(book);
        loan.setUser(user);
        loan.setLoanDate(LocalDateTime.now());
        loan.setStatus(LoanStatus.ACTIVE);

        loans.add(loan); // Asegúrate de agregar el préstamo a la lista
        books.put(book, books.get(book) - 1);

        return loan;
    }

    public Loan returnLoan(Loan loan) {
        if (loan == null || !loans.contains(loan)) {
            return null;
        }

        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());
        books.put(loan.getBook(), books.getOrDefault(loan.getBook(), 0) + 1);

        return loan;
    }

    public boolean addUser(User user) {
        return users.add(user);
    }

    private Book findBookByIsbn(String isbn) {
        for (Book book : books.keySet()) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    private boolean isBookAvailable(Book book) {
        return books.getOrDefault(book, 0) > 0;
    }

    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}