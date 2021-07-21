package com.example.testapistackexchange.controller;

import com.example.testapistackexchange.AppConfig;
import com.example.testapistackexchange.api.ApiService;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.View;
import java.io.IOException;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {AppConfig.class})
@ExtendWith(MockitoExtension.class)
public class MainControllerTest {

    private MockMvc mockMvc;

    @Mock
    private ApiService service;

    @InjectMocks
    private MainController мainController;

    @InjectMocks
    private MyErrorController errorController;


    @Mock
    View mockView;

    @Before
    public void setUp() throws IOException {
        Mockito.when(service.getQuestions(any())).thenThrow(new Error());
        mockMvc = MockMvcBuilders.standaloneSetup(мainController).build();
    }

    @Test
    public void testHomePage() throws Exception {
        mockMvc.perform(get("/search")).andExpect(model().attribute("errors", "please give me your word"));
    }

    @Test
    public void testNotFound() throws Exception {
        mockMvc.perform(get("/404").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNotFound());
    }

    @Test
    public void testError() throws Exception {
        mockMvc = MockMvcBuilders.standaloneSetup(errorController).build();
        mockMvc.perform(get("/search?intitle=q").contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(status().isNotFound());
    }
}