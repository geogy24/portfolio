package com.portfolio.models;

import com.portfolio.factories.PortfolioFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
public class PortfolioTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private Portfolio portfolio;

    @Before
    public void setUp() {
        this.portfolio = (new PortfolioFactory()).portfolioModel();
    }

    @Test
    public void whenSavePortfolio_thenPortfolioShouldFound() {
        Portfolio savedPortfolio = this.testEntityManager.persistFlushFind(this.portfolio);

        assertThat(savedPortfolio.getId()).isNotNull();
        assertThat(savedPortfolio.getTwitterUsername()).isEqualTo(this.portfolio.getTwitterUsername());
        assertThat(savedPortfolio.getDescription()).isEqualTo(this.portfolio.getDescription());
        assertThat(savedPortfolio.getTitle()).isEqualTo(this.portfolio.getTitle());
        assertThat(savedPortfolio.getImageURL()).isEqualTo(this.portfolio.getImageURL());
    }

}
