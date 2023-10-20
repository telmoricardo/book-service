package br.com.telmo.bookservice.service;

import br.com.telmo.bookservice.model.Book;
import br.com.telmo.bookservice.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;
    public Book getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book não encontrado"));
    }
}
