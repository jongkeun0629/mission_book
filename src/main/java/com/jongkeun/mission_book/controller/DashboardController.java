package com.jongkeun.mission_book.controller;

import com.jongkeun.mission_book.repository.DashboardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/authors")
@RequiredArgsConstructor
public class DashboardController {
    private final DashboardRepository dashboardRepository;

    @GetMapping
    public String list(Model model) {
        model.addAttribute("author_book_count", dashboardRepository.findAll());

        return "dashboard";
    }
}
