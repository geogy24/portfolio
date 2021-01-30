package com.portfolio.controllers;

import com.portfolio.models.Portfolio;
import com.portfolio.repositories.PortfolioRepository;
import com.portfolio.services.TwitterService;
import com.portfolio.services.dtos.TweetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import twitter4j.TwitterException;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/portfolios")
public class PortfolioController {

    /**
     * Entry point IO database operations
     */
    @Autowired
    private PortfolioRepository portfolioRepository;

    /**
     * Twitter API connection
     */
    @Autowired
    private TwitterService twitterService;

    @GetMapping("/{id}")
    public ModelAndView getUser(@PathVariable Long id, Model model) throws TwitterException {
        ModelAndView modelAndView = new ModelAndView();
        Optional<Portfolio> portfolio = portfolioRepository.findById(id);

        if (portfolio.isPresent()) {
            ArrayList<TweetDTO> tweetList = twitterService.getLastFiveTweetsFromUserTimeline(portfolio.get().getTwitterUsername());

            modelAndView.setViewName("portfolio");
            modelAndView.addObject("portfolio", portfolio.get());
            modelAndView.addObject("tweets", tweetList);
        } else {
            modelAndView.setViewName("error");
        }

        return modelAndView;
    }
}
