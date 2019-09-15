package com.home.library.service;

import com.home.library.dao.BookDao;
import com.home.library.dto.BookDto;
import com.home.library.entity.Book;
import com.home.library.entity.Writer;
import com.home.library.exceptions.NotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    private BookDao bookDao;

    public BookService(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    public List<BookDto> getAllBook() {
        return bookDao.findAll()
                .stream()
                .map(book -> {
                    BookDto bookDto = new BookDto();
                    bookDto.setTitle(book.getTitle());
                    bookDto.setWriterName(getWriterName(book));
                    return bookDto;
                })
                .collect(Collectors.toList());
    }

    private String getWriterName(Book book) {
        Writer bookWriter = book.getWriter();
        if (bookWriter == null) {
              throw new NotFoundException("Unknown Author");
        }

        return bookWriter.getFirstname() +" "+ bookWriter.getSurname();
    }

}
