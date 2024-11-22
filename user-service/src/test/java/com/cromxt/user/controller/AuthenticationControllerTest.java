package com.cromxt.user.controller;

import com.cromxt.user.dtos.requests.PhoneNumberDTO;
import com.cromxt.user.dtos.requests.RecoveryAccountDetailsDTO;
import com.cromxt.user.dtos.requests.RegisterUserDTO;
import com.cromxt.user.service.UserEntityService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class AuthenticationControllerTest {
    @Autowired
    private MockMvc mockMvc;


    @Test
    void registerUser() throws Exception {
//   The Test not passed. later write tests.
        RegisterUserDTO userDetail = new RegisterUserDTO(
                "lambo",
                "somepassword",
                "Akash",
                "Biswas",
                "male",
                "1999-11-27",
                new RecoveryAccountDetailsDTO(
                        new PhoneNumberDTO(
                                "+91",
                                "9123925041"
                        ),
                        "abc@gamil.com"
                )
        );

        mockMvc.perform(
                post("/cromxt/auth/register")
                        .content(new ObjectMapper().writeValueAsString(userDetail))
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                status().isCreated()
        );

    }
}