package com.portfolio.repositories;

import com.github.javafaker.Faker;
import com.portfolio.factories.PortfolioFactory;
import com.portfolio.models.Portfolio;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class PortfolioRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private PortfolioRepository portfolioRepository;

    private Faker faker = new Faker();

    private Portfolio portfolio;

    private Portfolio portfolioEntity;

    @Before
    public void setup() {
        this.portfolio = (new PortfolioFactory()).portfolioModel();
        this.portfolioEntity = this.testEntityManager.persist(this.portfolio);
        this.testEntityManager.flush();
    }

    @Test
    public void whenSearchAPortfolioById_thenPortfolioShouldFound() {
        Optional<Portfolio> portfolio = this.portfolioRepository.findById(this.portfolioEntity.getId());
        Portfolio portfolioFound = portfolio.get();

        assertThat(portfolioFound.getId()).isNotNull();
        assertThat(portfolioFound.getTwitterUsername()).isEqualTo(this.portfolio.getTwitterUsername());
        assertThat(portfolioFound.getDescription()).isEqualTo(this.portfolio.getDescription());
        assertThat(portfolioFound.getTitle()).isEqualTo(this.portfolio.getTitle());
        assertThat(portfolioFound.getImageURL()).isEqualTo(this.portfolio.getImageURL());
    }
}
