package com.portfolio.controllers;

import com.github.javafaker.Faker;
import com.portfolio.factories.ProfileFactory;
import com.portfolio.models.Profile;
import com.portfolio.repositories.ProfileRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMultipartHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.Optional;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(PortfolioController.class)
@ContextConfiguration(classes = {ProfileController.class})
public class ProfileControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfileRepository profileRepository;

    private Faker faker = new Faker();

    private Profile profile;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.webApplicationContext).build();
        this.profile = (new ProfileFactory()).profileModel();
    }

    @Test
    public void whenGetAProfile_thenReturnHTTPOKstatus() throws Exception {
        given(this.profileRepository.findById(Mockito.anyString())).willReturn(Optional.of(this.profile));

        System.out.println("/api/users/" + this.profile.getId());
        this.mockMvc.perform(
                get("/api/users/" + this.profile.getId())
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(this.profile.getId()))
                .andExpect(jsonPath("$.last_name").value(this.profile.getLastNames()))
                .andExpect(jsonPath("$.first_name").value(this.profile.getNames()))
                .andExpect(jsonPath("$.twitter_user_id").value(this.profile.getTwitterUserId()))
                .andExpect(jsonPath("$.experience_summary").value(this.profile.getExperienceSummary()));
    }

    @Test
    public void whenGetAProfile_ButNotFoundThenReturnHTTPNOTFOUNDStatus() throws Exception {
        when(this.profileRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        this.mockMvc.perform(get("/api/users/" + this.faker.internet().emailAddress()))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenUpdateAProfile_ButIDMismatchThenReturnBADREQUESTStatus() throws Exception {
        String jsonRequest = "{\"id\": \"" + this.profile.getId() + "\",\"experience_summary\": \"I am a code reviewer of your test\"}";
        MockMultipartFile jsonFile = new MockMultipartFile("json", "", "application/json", jsonRequest.getBytes());
        MockMultipartFile imageFile = new MockMultipartFile("image", "", "", "".getBytes());
        MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/api/users/" + this.faker.internet().emailAddress());
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PUT");
                return request;
            }
        });

        this.mockMvc.perform(builder.file(jsonFile).file(imageFile))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenUpdateAProfile_ButProfileNotFoundThenReturnNOTFOUNDtatus() throws Exception {
        when(this.profileRepository.findById(Mockito.anyString())).thenReturn(Optional.empty());

        String jsonRequest = "{\"id\": \"" + this.profile.getId() + "\",\"experience_summary\": \"I am a code reviewer of your test\"}";
        MockMultipartFile jsonFile = new MockMultipartFile("json", "", "application/json", jsonRequest.getBytes());
        MockMultipartFile imageFile = new MockMultipartFile("image", "", "", "".getBytes());
        MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/api/users/" + this.profile.getId());
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PUT");
                return request;
            }
        });

        this.mockMvc.perform(builder.file(jsonFile).file(imageFile))
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenUpdateAProfile_ThenReturnNOCONTENTStatus() throws Exception {
        when(this.profileRepository.findById(Mockito.anyString())).thenReturn(Optional.of(this.profile));

        String jsonRequest = "{\"id\": \"" + this.profile.getId() + "\",\"experience_summary\": \"I am a code reviewer of your test\"}";
        MockMultipartFile jsonFile = new MockMultipartFile("json", "", "application/json", jsonRequest.getBytes());
        MockMultipartFile imageFile = new MockMultipartFile("image", "", "", "".getBytes());
        MockMultipartHttpServletRequestBuilder builder = MockMvcRequestBuilders.multipart("/api/users/" + this.profile.getId());
        builder.with(new RequestPostProcessor() {
            @Override
            public MockHttpServletRequest postProcessRequest(MockHttpServletRequest request) {
                request.setMethod("PUT");
                return request;
            }
        });

        this.mockMvc.perform(builder.file(jsonFile).file(imageFile))
                .andExpect(status().isNoContent());
    }
}
