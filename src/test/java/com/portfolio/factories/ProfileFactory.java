package com.portfolio.factories;

import com.github.javafaker.Faker;
import com.portfolio.models.Profile;

public class ProfileFactory {

    private Faker faker = new Faker();

    public Profile profileModel() {
        return Profile.builder()
                .id(this.faker.internet().emailAddress())
                .experienceSummary(this.faker.lorem().characters())
                .twitterUserId(this.faker.name().username())
                .lastNames(this.faker.name().lastName())
                .names(this.faker.name().firstName())
                .build();
    }
}
