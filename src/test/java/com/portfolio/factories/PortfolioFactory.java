package com.portfolio.factories;

import com.github.javafaker.Faker;
import com.portfolio.models.Portfolio;

public class PortfolioFactory {

    private Faker faker = new Faker();

    public Portfolio portfolioModel() {
        return Portfolio.builder()
                .description(this.faker.lorem().characters())
                .imageURL(this.faker.internet().image())
                .twitterUsername(this.faker.name().username())
                .title(this.faker.book().title())
                .build();
    }
}
