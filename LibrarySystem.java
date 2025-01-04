import java.util.ArrayList;

// Book class
class Book {
    private final String title;
    private final String author;
    private final int publicationYear;
    private boolean isAvailable;

    // Constructor
    public Book(String title, String author, int publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
        this.isAvailable = true;
    }

    // Getters
    public String getTitle() { return title; }
    public String getAuthor() { return author; }
    public int getPublicationYear() { return publicationYear; }
    public boolean isAvailable() { return isAvailable; }

    // Borrow method
    public boolean borrow() {
        if (isAvailable) {
            isAvailable = false;
            return true;
        }
        return false;
    }

    // Return method
    public void returnBook() {
        isAvailable = true;
    }

    @Override
    public String toString() {
        return "Book [Title: " + title + ", Author: " + author + ", Year: " + publicationYear + 
               ", Available: " + isAvailable + "]";
    }
}

// Member class
class Member {
    private final String name;
    private final String id;
    private final ArrayList<Book> borrowedBooks;

    // Constructor
    public Member(String name, String id) {
        this.name = name;
        this.id = id;
        this.borrowedBooks = new ArrayList<>();
    }

    // Borrow book method
    public boolean borrowBook(Book book) {
        if (book.borrow()) {
            borrowedBooks.add(book);
            return true;
        } else {
            System.out.println("Book '" + book.getTitle() + "' is not available.");
            return false;
        }
    }

    // Return book method
    public void returnBook(Book book) {
        if (borrowedBooks.contains(book)) {
            book.returnBook();
            borrowedBooks.remove(book);
        } else {
            System.out.println("This book was not borrowed by this member.");
        }
    }

    // Display borrowed books
    public void displayBorrowedBooks() {
        if (borrowedBooks.isEmpty()) {
            System.out.println(name + " has not borrowed any books.");
        } else {
            System.out.println(name + "'s borrowed books:");
            for (Book book : borrowedBooks) {
                System.out.println(" - " + book.getTitle());
            }
        }
    }

    @Override
    public String toString() {
        return "Member [Name: " + name + ", ID: " + id + ", Borrowed Books: " + borrowedBooks.size() + "]";
    }
}

// Library class
class Library {
    private final ArrayList<Book> books;
    private final ArrayList<Member> members;

    // Constructor
    public Library() {
        books = new ArrayList<>();
        members = new ArrayList<>();
    }

    // Add book
    public void addBook(Book book) {
        books.add(book);
    }

    // Add member
    public void addMember(Member member) {
        members.add(member);
    }

    // Display all books
    public void displayBooks() {
        System.out.println("Library Books:");
        for (Book book : books) {
            System.out.println(book);
        }
    }

    // Display all members
    public void displayMembers() {
        System.out.println("Library Members:");
        for (Member member : members) {
            System.out.println(member);
        }
    }
}

// Main class to simulate library system
public class LibrarySystem {
    public static void main(String[] args) {
        // Create a library instance
        Library library = new Library();

        // Add some books to the library
        Book book1 = new Book("Computer Networks", "Andrew S. Tanenbaum, David J. Wetherall", 2010);
        Book book2 = new Book("Harry Potter and the Sorcerer's Stone", "J.K. Rowling", 1997);
        Book book3 = new Book("The Lean Startup", "Eric Ries", 2011);
        Book book4 = new Book("The Algorithm Design Manual", "Steven S. Skiena", 2008);
        Book book5 = new Book("Database System Concepts", "Abraham Silberschatz", 2020);
        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);
        library.addBook(book4);
        library.addBook(book5);


        // Add some members to the library
        Member member1 = new Member("Aryan", "MT001");
        Member member2 = new Member("Prachita", "MT002");
        Member member3 = new Member("Neha", "MT003");
        library.addMember(member1);
        library.addMember(member2);
        library.addMember(member3);

        // Display library books and members
        library.displayBooks();
        library.displayMembers();

        // Simulate borrowing books
        System.out.println("\nPrachita borrows 'The Lean Startup':");
        member2.borrowBook(book3);
        System.out.println("\nAryan tries to borrow 'The Lean Startup' again:");
        member1.borrowBook(book3);

        // Display the updated status of books and members
        System.out.println("\nStatus after borrowing:");
        library.displayBooks();
        member1.displayBorrowedBooks();
        member2.displayBorrowedBooks();

        // Simulate returning books
        System.out.println("\nPrachita returns 'The Lean Startup':");
        member2.returnBook(book3);

        // Display the updated status of books and members after return
        System.out.println("\nStatus after returning:");
        library.displayBooks();
        member2.displayBorrowedBooks();
    }
}
