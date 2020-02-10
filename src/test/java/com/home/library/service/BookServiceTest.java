package com.home.library.service;

import com.home.library.dao.BookDao;
import com.home.library.dto.BookDto;
import com.home.library.entity.Book;
import com.home.library.entity.Writer;
import com.home.library.exceptions.NotFoundException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringJUnitConfig(classes = BookService.class)
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @MockBean
    private BookDao bookDao;

    @Test
    @DisplayName("Test getting all books")
    public void testGetAll() {
        // Given
        Mockito.when(bookDao.findAll()).thenReturn(
                List.of(getBook("Harry Potter", "Jean", "Rowling"),
                        getBook("Lord of the Rings", "J.R.R.", "Tolkien")
                )
        );

        // When
        List<BookDto> foundList = bookService.getAllBook();

        //Then
        assertNotNull(foundList);
        assertEquals(2L, foundList.size());
        assertNotNull(foundList.get(0));
        BookDto firstBook = foundList.get(0);
        assertAll(
                "first list element",
                () -> assertEquals("Harry Potter", firstBook.getTitle()),
                () -> assertEquals("Jean Rowling", firstBook.getWriterName())
        );
        assertNotNull(foundList.get(1));
        BookDto secondBook = foundList.get(1);
        assertAll(
                "second list element",
                () -> assertEquals("Lord of the Rings", secondBook.getTitle()),
                () -> assertEquals("J.R.R. Tolkien", secondBook.getWriterName())
        );

    }

    @Test
    @DisplayName("Test no book in the list")
    public void testGetAllWithEmptyList() {
        // Given
        Mockito.when(bookDao.findAll()).thenReturn(new ArrayList<>());

        // When
        List<BookDto> foundList = bookService.getAllBook();

        //Then
        assertNotNull(foundList);
        assertTrue(foundList.isEmpty());

    }

    @Test
    @DisplayName("Test exception for unknown author")
    public void testNotFoundWriter() {
        //Given
        Book book = new Book();
        book.setTitle("Title1");
        Mockito.when(bookDao.findAll()).thenReturn(List.of(book));

        //When
        NotFoundException exception = assertThrows(NotFoundException.class, () -> bookService.getAllBook());
        assertEquals("Unknown Author", exception.getNotFoundMessage());
    }

    private Book getBook(String title, String writerFirstName, String writerSurname) {
        Book book = new Book();
        book.setTitle(title);
        Writer writer = new Writer(writerFirstName, writerSurname);
        book.setWriter(writer);

        return book;
    }
}
