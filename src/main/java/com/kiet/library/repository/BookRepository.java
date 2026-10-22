
package com.kiet.library.repository;

import com.kiet.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    List<Book> findByCategoryIgnoreCase(String category);

    List<Book> findByPublicationYearGreaterThanEqual(int fromYear);
}
