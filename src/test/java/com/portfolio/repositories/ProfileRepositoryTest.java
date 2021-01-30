package com.portfolio.repositories;

import com.github.javafaker.Faker;
import com.portfolio.factories.ProfileFactory;
import com.portfolio.models.Profile;
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
public class ProfileRepositoryTest {

    @Autowired
    private TestEntityManager testEntityManager;

    @Autowired
    private ProfileRepository profileRepository;

    private Faker faker = new Faker();

    private Profile profile;

    private static final Long FIRST_RECORD = 1l;

    @Before
    public void setup() {
        this.profile = (new ProfileFactory()).profileModel();
        this.testEntityManager.persist(this.profile);
        this.testEntityManager.flush();
    }

    @Test
    public void whenSearchAProfileById_thenPortfolioShouldFound() {
        Optional<Profile> profile = this.profileRepository.findById(this.profile.getId());
        Profile profileFound = profile.get();

        assertThat(profileFound.getId()).isEqualTo(this.profile.getId());
        assertThat(profileFound.getExperienceSummary()).isEqualTo(this.profile.getExperienceSummary());
        assertThat(profileFound.getTwitterUserId()).isEqualTo(this.profile.getTwitterUserId());
        assertThat(profileFound.getLastNames()).isEqualTo(this.profile.getLastNames());
        assertThat(profileFound.getNames()).isEqualTo(this.profile.getNames());
    }
}
