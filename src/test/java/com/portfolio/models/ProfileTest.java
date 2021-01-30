package com.portfolio.models;

import com.portfolio.factories.ProfileFactory;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@AutoConfigureTestDatabase(replace = Replace.NONE)
@DataJpaTest
@ActiveProfiles("test")
public class ProfileTest {

    @Autowired
    private TestEntityManager testEntityManager;

    private Profile profile;

    @Before
    public void setUp() {
        this.profile = (new ProfileFactory()).profileModel();
    }

    @Test
    public void whenSaveProfile_thenProfileShouldFound() {
        Profile savedProfile = this.testEntityManager.persistFlushFind(this.profile);

        assertThat(savedProfile.getId()).isEqualTo(this.profile.getId());
        assertThat(savedProfile.getExperienceSummary()).isEqualTo(this.profile.getExperienceSummary());
        assertThat(savedProfile.getTwitterUserId()).isEqualTo(this.profile.getTwitterUserId());
        assertThat(savedProfile.getLastNames()).isEqualTo(this.profile.getLastNames());
        assertThat(savedProfile.getNames()).isEqualTo(this.profile.getNames());
    }

}
