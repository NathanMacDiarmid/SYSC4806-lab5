package com.example.lab5;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class BuddyTests {

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

    @Test
    public void POSTBuddy() throws Exception {
      BuddyInfo buddy = new BuddyInfo("Wayne Gretzky", "999999999", 99);
      ObjectMapper mapper = new ObjectMapper();
      String json = mapper.writeValueAsString(buddy);
      this.mockMvc.perform(post("/buddyInfoes").contentType(MediaType.APPLICATION_JSON).content(json));
      this.mockMvc.perform(get("/buddyInfoes/99")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Wayne Gretzky")));
      this.mockMvc.perform(get("/buddyInfoes/99")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("999999999")));
    }

    @Test
    public void DELETEBuddy() throws Exception {
      this.mockMvc.perform(delete("/buddyInfoes/99"));
      this.mockMvc.perform(get("/buddyInfoes/99")).andExpect(status().isNotFound());
    }
}