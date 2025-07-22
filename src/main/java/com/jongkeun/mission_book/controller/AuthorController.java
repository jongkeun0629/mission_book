package com.jongkeun.mission_book.controller;

import com.jongkeun.mission_book.model.Author;
import com.jongkeun.mission_book.repository.AuthorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authors")
@RequiredArgsConstructor
public class AuthorController {
    private final AuthorRepository authorRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("authors", authorRepository.findAll());

        return "author-list";
    }

    @GetMapping("/add")
    public String addForm(Model model) {
        model.addAttribute("author", new Author());

        return "author-form";
    }

    @PostMapping("/add")
    public String add(@ModelAttribute Author author) {
        authorRepository.save(author);

        return "redirect:/authors";
    }

    @GetMapping("/edit/{id}")
    public String editFrom(@PathVariable int id, Model model){
        model.addAttribute("author", authorRepository.findById(id));

        return "author-form";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Author author){
        authorRepository.update(author);

        return "redirect:/authors";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id){
        int affected = authorRepository.delete(id);

        if (affected == 0){
            System.out.println("해당 저자를 찾을 수 없습니다.");
        }

        return "redirect:/authors";
    }
}
