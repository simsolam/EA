package edu.mum.cs544;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BookController {

    @Resource
    private IBookDao bookDao;

    @GetMapping("/")
    public String redirectRoot() {
        return "redirect:/books";
    }

    @GetMapping("/books")
    public String getAll(Model model) {
        model.addAttribute("books", bookDao.getAll());
        return "bookList";
    }

    @PostMapping("/books")
    public String add(Book book) {
        bookDao.add(book);
        return "redirect:/books";
    }

    @GetMapping("/books/add")
    public String viewAdd(@ModelAttribute Book book, Model model) {
        model.addAttribute("msg", "Add");
        return "bookDetail";
    }

    @GetMapping("/books/{id}")
    public String get(@PathVariable int id, Model model) {
        model.addAttribute("book", bookDao.get(id));
        model.addAttribute("msg", "Update");
        return "bookDetail";
    }

    @PostMapping("/books/{id}")
    public String update(Book book, @PathVariable int id) {
        bookDao.update(book); // book.id already set by binding
        return "redirect:/books";
    }

    @PostMapping("/books/delete")
    public String delete(int bookId) {
        bookDao.delete(bookId);
        return "redirect:/books";
    }
}
