package com.fredrikpedersen.obligatoryassignment.web.controllers.v1;

import com.fredrikpedersen.obligatoryassignment.service.TicketService;
import com.fredrikpedersen.obligatoryassignment.web.controllers.BaseControllerTest;
import com.fredrikpedersen.obligatoryassignment.web.models.v1.TicketDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * @author Fredrik Pedersen
 * @version 1.0
 * @since 12/03/2020 at 21:54
 */
class TicketControllerTest extends BaseControllerTest {

    private final String URL = TicketController.BASE_URL;
    private final Long ID = 1L;
    private final String FIRST_NAME = "Fredrik";

    @Mock
    TicketService ticketService;

    @InjectMocks
    TicketController ticketController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(ticketController).build();
    }

    @Test
    void getAllTicketsTest() throws Exception {

        //given
        TicketDTO ticketDTO1 = new TicketDTO();
        TicketDTO ticketDTO2 = new TicketDTO();

        List<TicketDTO> ticketDTOs = Arrays.asList(ticketDTO1, ticketDTO2);
        when(ticketService.findAll()).thenReturn(ticketDTOs);

        //when/then
        mockMvc.perform(get(URL)
                .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.tickets", hasSize(2)));

    }

    @Test
    void createNewTicketTest() throws Exception {

        //given
        TicketDTO ticketDTO = TicketDTO.builder()
                .firstName(FIRST_NAME)
                .build();

        TicketDTO returnDTO = TicketDTO.builder()
                .firstName(FIRST_NAME)
                .url(URL + ID)
                .build();

        when(ticketService.createNew(ticketDTO)).thenReturn(returnDTO);

        //when/then
        mockMvc.perform(post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(mapToJson(ticketDTO)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.firstName", equalTo(FIRST_NAME)))
                .andExpect(jsonPath("$.url", equalTo(URL + ID)));
    }

    @Test
    void deleteByIdTest() throws Exception {
        mockMvc.perform(delete(URL + ID)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(ticketService).deleteById(anyLong());
    }

}