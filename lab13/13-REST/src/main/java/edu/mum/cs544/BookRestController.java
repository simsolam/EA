package edu.mum.cs544;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

@RestController
@RequestMapping("/api")
public class BookRestController {
    @Autowired
    private BookService bookService;

    @GetMapping("/books")
    public List<Book> getAll() {
        return bookService.getAll();
    }

    @GetMapping("/books/{id}")
    public Book get(@PathVariable int id) {
        Book b =  bookService.get(id);
        b.getAuthor();
        return b;
    }

    @PostMapping("/books")
    public RedirectView add(@RequestBody Book book) {
        bookService.add(book);
        return new RedirectView("/books/" + book.getId());
    }

    @PutMapping("/books/{id}")
    public void update(@RequestBody Book book) {
        bookService.update(book);
    }

    @DeleteMapping("/books/{id}")
    public void delete(@PathVariable int id) {
        bookService.delete(id);
    }
}