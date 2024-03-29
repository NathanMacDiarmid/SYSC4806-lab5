package com.example.lab5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class Tests {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void GETBuddyNameTest() throws Exception {
        this.mockMvc.perform(get("/buddyInfoes/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Bob Dylan")));
    }

    @Test
    public void GETBuddyNumberTest() throws Exception {
        this.mockMvc.perform(get("/buddyInfoes/1")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("123456789")));
    }
}