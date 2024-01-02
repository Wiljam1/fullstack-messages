package se.kth.nrww.fullstackmessages.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import se.kth.nrww.fullstackmessages.controller.MessageController;
import se.kth.nrww.fullstackmessages.model.Message;
import se.kth.nrww.fullstackmessages.services.MessageService;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    public MessageControllerTest() {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Test
    @WithMockUser
    void newMessage() throws Exception {
        assertNotNull(messageService);
        Message inputMessage = new Message();
        when(messageService.create(any(Message.class))).thenReturn(inputMessage);

        mockMvc.perform(post("/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));

        verify(messageService, times(1)).create(any(Message.class));
    }

    @Test
    @WithMockUser
    void getConversation() throws Exception {
        assertNotNull(messageService);
        Long senderId = 1L;
        Long receiverId = 2L;
        List<Message> conversation = Arrays.asList(new Message(), new Message());
        when(messageService.getConversation(senderId, receiverId)).thenReturn(conversation);

        mockMvc.perform(get("/messages/{senderId}/{receiverId}", senderId, receiverId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));

        verify(messageService, times(1)).getConversation(senderId, receiverId);
    }

    @Test
    @WithMockUser
    void getSentMessages() throws Exception {
        assertNotNull(messageService);
        Long senderId = 1L;
        List<Message> sentMessages = Arrays.asList(new Message(), new Message());
        when(messageService.getSentMessages(senderId)).thenReturn(sentMessages);

        mockMvc.perform(get("/messages/{senderId}", senderId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.length()").value(2));

        verify(messageService, times(1)).getSentMessages(senderId);
    }
}
