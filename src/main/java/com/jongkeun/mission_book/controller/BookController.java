package com.jongkeun.mission_book.controller;

import com.jongkeun.mission_book.model.Author;
import com.jongkeun.mission_book.model.Book;
import com.jongkeun.mission_book.repository.AuthorRepository;
import com.jongkeun.mission_book.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
@RequiredArgsConstructor
public class BookController {
    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("books", bookRepository.findAll());

        return "book-list";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model){
        Book book = bookRepository.findById(id);
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);

        return "book-detail";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", new Book());
        model.addAttribute("authors", authors);

        return "book-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Book book) {
        bookRepository.save(book);

        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editFrom(@PathVariable int id, Model model){
        Book book = bookRepository.findById(id);
        List<Author> authors = authorRepository.findAll();
        model.addAttribute("book", book);
        model.addAttribute("authors", authors);

        return "book-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Book book) {
        bookRepository.update(book);

        return "redirect:/books";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        bookRepository.delete(id);

        return "redirect:/books";
    }
}
