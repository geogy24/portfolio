package com.portfolio.controllers;

import com.portfolio.models.Portfolio;
import com.portfolio.repositories.PortfolioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Optional;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    /**
     * Entry point IO database operations
     */
    @Autowired
    private PortfolioRepository portfolioRepository;

    @GetMapping("/{id}")
    public ModelAndView getUser(@PathVariable Long id, Model model) {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);

        if (portfolio.isPresent()) {
            modelAndView.setViewName("portfolio");
            modelAndView.addObject("portfolio", portfolio.get());
        } else {
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
