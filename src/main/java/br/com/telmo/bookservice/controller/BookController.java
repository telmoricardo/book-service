package br.com.telmo.bookservice.controller;

import br.com.telmo.bookservice.model.Book;
import br.com.telmo.bookservice.proxy.CambioProxy;
import br.com.telmo.bookservice.service.BookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Tag(name = "Book endpoint")
@RestController
@RequestMapping("book-service")
public class BookController {

    @Autowired
    private Environment environment;

    @Autowired
    private BookService service;
    @Autowired
    private CambioProxy proxy;

    @Operation(summary = "Find a specific book by your ID")
    @GetMapping(value = "/{id}/{currency}")
    public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency) {

        var book = service.getById(id);
        if(book == null) throw new RuntimeException("Book not found");

        var cambio = proxy.getCambio(book.getPrice(), "USD",currency);

        var port = environment.getProperty("local.server.port");
        book.setEnviroment("Book port " + port + " Cambio port " + cambio.getEnviroment());
        book.setPrice(cambio.getConverterdValue());
        return book;
    }

    /*public Book findBook(
            @PathVariable("id") Long id,
            @PathVariable("currency") String currency) {

        var book = service.getById(id);
        if(book == null) throw new RuntimeException("Book not found");

        HashMap<String, String> params = new HashMap<>();
        params.put("amount", book.getPrice().toString());
        params.put("from", "USD");
        params.put("to", currency);

        var response = new RestTemplate().getForEntity("http://localhost:8000/cambio/" +
                "{amount}/{from}/{to}", Cambio.class, params);

        var cambio = response.getBody();

        var port = environment.getProperty("local.server.port");
        book.setEnviroment(port);
        book.setPrice(cambio.getConverterdValue());
        return book;
    }*/
}
