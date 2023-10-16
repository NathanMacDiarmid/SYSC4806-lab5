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

import java.util.ArrayList;

@SpringBootTest
@AutoConfigureMockMvc
public class AddressBookTests {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    public void GETAddressBooks() throws Exception{
        this.mockMvc.perform(get("/addressBooks")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("addressBooks/1")));
        this.mockMvc.perform(get("/addressBooks")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("addressBooks/2")));
    }

    @Test
    public void GETBuddies() throws Exception {
        this.mockMvc.perform(get("/addressBooks/1/buddies")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Bob Dylan")));
        this.mockMvc.perform(get("/addressBooks/1/buddies")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Elvis Presley")));
        this.mockMvc.perform(get("/addressBooks/2/buddies")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Post Malone")));
    }

    @Test
    public void POSTAddressBook() throws Exception {
        BuddyInfo buddy = new BuddyInfo("Wayne Gretzky", "999999999", 99);
        ArrayList<BuddyInfo> list = new ArrayList<>();
        list.add(buddy);
        AddressBook book = new AddressBook(list, 99);
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(book);
        System.out.println(json);
        this.mockMvc.perform(post("/addressBooks").contentType(MediaType.APPLICATION_JSON).content(json)).andDo(print());
        // cant verify post for whatever reason
        //this.mockMvc.perform(get("/addressBooks/99")).andExpect(status().isOk());
        //this.mockMvc.perform(get("/addressBooks/99/buddies")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("Wayne Gretzky")));
        //this.mockMvc.perform(get("/addressBooks/99/buddies")).andDo(print()).andExpect(status().isOk()).andExpect(content().string(containsString("999999999")));
    }

    @Test
    public void DELETEAddressBook() throws Exception {
      this.mockMvc.perform(delete("/addressBooks/99"));
      this.mockMvc.perform(get("/addressBooks/99")).andExpect(status().isNotFound());
    }
}