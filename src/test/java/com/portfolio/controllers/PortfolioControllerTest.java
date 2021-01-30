package com.portfolio.controllers;

import com.github.javafaker.Faker;
import com.portfolio.factories.PortfolioFactory;
import com.portfolio.models.Portfolio;
import com.portfolio.repositories.PortfolioRepository;
import com.portfolio.services.TwitterService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PortfolioController.class)
@ContextConfiguration(classes = {PortfolioController.class})
public class PortfolioControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TwitterService twitterService;

    @MockBean
    private PortfolioRepository portfolioRepository;

    private Faker faker = new Faker();

    private Portfolio portfolio;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.portfolio = (new PortfolioFactory()).portfolioModel();
    }

    @Test
    public void whenGetAPortfolio_thenReturnHTTPOKstatus() throws Exception {
        given(this.portfolioRepository.findById(Mockito.anyLong())).willReturn(Optional.ofNullable(this.portfolio));
        given(this.twitterService.getLastFiveTweetsFromUserTimeline(this.portfolio.getTwitterUsername()))
                .willReturn(new ArrayList<>());

        this.mockMvc.perform(get("/portfolios/" + faker.number().randomDigit()))
            .andExpect(status().isOk());
    }

    @Test
    public void whenGetAPortfolio_ButNotFoundThenReturnHTTPNOTFOUNDStatus() throws Exception {
        when(this.portfolioRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/portfolios/" + faker.number().randomDigit()))
                .andExpect(status().isNotFound());
    }
}
