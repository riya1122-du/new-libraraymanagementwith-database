
package com.kiet.library.service;

import com.kiet.library.model.Book;
import com.kiet.library.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(int bookId) {
        return bookRepository.findById(bookId).orElse(null);
    }

    public Book addBook(Book book) {
        if (bookRepository.existsById(book.getBookId())) {
            return null;
        }
        return bookRepository.save(book);
    }

    public Book updateBook(int bookId, Book updatedBook) {
        Optional<Book> existingBook = bookRepository.findById(bookId);

        if (existingBook.isEmpty()) {
            return null;
        }

        Book book = existingBook.get();
        book.setTitle(updatedBook.getTitle());
        book.setAuthor(updatedBook.getAuthor());
        book.setCategory(updatedBook.getCategory());
        book.setPublicationYear(updatedBook.getPublicationYear());
        book.setAvailable(updatedBook.isAvailable());

        return bookRepository.save(book);
    }

    public boolean deleteBook(int bookId) {
        if (!bookRepository.existsById(bookId)) {
            return false;
        }

        bookRepository.deleteById(bookId);
        return true;
    }

    public List<Book> searchByCategory(String category) {
        return bookRepository.findByCategoryIgnoreCase(category);
    }

    public List<Book> findPublishedFromYear(int fromYear) {
        return bookRepository.findByPublicationYearGreaterThanEqual(fromYear);
    }
}
