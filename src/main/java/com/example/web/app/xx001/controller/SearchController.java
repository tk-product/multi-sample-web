package com.example.web.app.xx001.controller;

import com.example.web.app.xx001.form.SearchForm;
import com.example.web.app.xx001.service.SearchService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class SearchController {

    private final SearchService searchService;

    @GetMapping("/search")
    public String showSearch(@ModelAttribute SearchForm form) {
        return "search";
    }

    @PostMapping("/search")
    public String doSearch(@ModelAttribute SearchForm form, Model model) {
        List<String> results = searchService.search(form.getKeyword());
        model.addAttribute("results", results);
        return "search";
    }
}

