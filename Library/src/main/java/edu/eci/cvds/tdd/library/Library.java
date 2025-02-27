/**
 * Clase que representa una Biblioteca, donde se pueden registrar usuarios,
 * agregar libros y gestionar préstamos y devoluciones.
 */
public class Library {

    /** Lista de usuarios registrados en la biblioteca */
    private final List<User> users;
    
    /** Mapa de libros disponibles en la biblioteca con su cantidad */
    private final Map<Book, Integer> books;
    
    /** Lista de préstamos activos en la biblioteca */
    private final List<Loan> loans;

    /**
     * Constructor que inicializa las listas de usuarios, libros y préstamos.
     */
    public Library() {
        users = new ArrayList<>();
        books = new HashMap<>();
        loans = new ArrayList<>();
    }

    /**
     * Agrega un libro al inventario de la biblioteca.
     * 
     * @param book El libro a agregar.
     * @return true si el libro se agregó correctamente, false si es nulo.
     */
    public boolean addBook(Book book) {
        if (book == null) {
            return false;
        }
        int count = books.getOrDefault(book, 0);
        books.put(book, count + 1);
        return true;
    }

    /**
     * Realiza un préstamo de un libro a un usuario.
     * 
     * @param userId Identificador del usuario que solicita el préstamo.
     * @param isbn ISBN del libro a prestar.
     * @return El préstamo generado si es exitoso, null en caso contrario.
     */
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

        loans.add(loan);
        books.put(book, books.get(book) - 1);

        return loan;
    }

    /**
     * Devuelve un préstamo activo.
     * 
     * @param loan El préstamo a devolver.
     * @return El préstamo actualizado si se devuelve correctamente, null en caso contrario.
     */
    public Loan returnLoan(Loan loan) {
        if (loan == null || !loans.contains(loan)) {
            return null;
        }

        loan.setStatus(LoanStatus.RETURNED);
        loan.setReturnDate(LocalDateTime.now());
        books.put(loan.getBook(), books.getOrDefault(loan.getBook(), 0) + 1);

        return loan;
    }

    /**
     * Agrega un usuario a la biblioteca.
     * 
     * @param user El usuario a registrar.
     * @return true si el usuario se agregó correctamente, false en caso contrario.
     */
    public boolean addUser(User user) {
        return users.add(user);
    }

    /**
     * Busca un libro por su ISBN en la biblioteca.
     * 
     * @param isbn El ISBN del libro a buscar.
     * @return El libro encontrado, o null si no existe.
     */
    private Book findBookByIsbn(String isbn) {
        for (Book book : books.keySet()) {
            if (book.getIsbn().equals(isbn)) {
                return book;
            }
        }
        return null;
    }

    /**
     * Verifica si un libro está disponible en la biblioteca.
     * 
     * @param book El libro a verificar.
     * @return true si hay ejemplares disponibles, false en caso contrario.
     */
    private boolean isBookAvailable(Book book) {
        return books.getOrDefault(book, 0) > 0;
    }

    /**
     * Busca un usuario por su ID en la biblioteca.
     * 
     * @param userId El ID del usuario a buscar.
     * @return El usuario encontrado, o null si no existe.
     */
    private User findUserById(String userId) {
        for (User user : users) {
            if (user.getId().equals(userId)) {
                return user;
            }
        }
        return null;
    }
}
