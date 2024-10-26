package com.itschool.performance_tracker.integration_tests;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.itschool.performance_tracker.models.dtos.PlayerDTO;
import com.itschool.performance_tracker.models.dtos.RequestPlayerDTO;
import com.itschool.performance_tracker.models.dtos.ResponsePlayerDTO;

import jakarta.transaction.Transactional;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@Transactional
@AutoConfigureTestDatabase
public class PlayerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    private final ObjectMapper objectMapper = JsonMapper.builder()
    	    .addModule(new JavaTimeModule())
    	    .build();
    
	@Test
	public void testCreatePlayer() throws Exception {
		RequestPlayerDTO requestPlayer = createPlayerRequest();
		mockMvc.perform(post("/api/performance-tracker")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestPlayer)))
			.andExpect(status().isOk());
	}

	@Test
	public void testGetPlayer() throws Exception {
		RequestPlayerDTO requestPlayer = createPlayerRequest();
		MvcResult saveResult = mockMvc.perform(post("/api/performance-tracker")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestPlayer)))
			.andReturn();
		
		Long id = objectMapper.readValue(saveResult.getResponse().getContentAsString(), ResponsePlayerDTO.class)
				.getId();
		
		MvcResult result = mockMvc.perform(get("/api/performance-tracker")
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isOk())
			.andReturn();
		
        List<PlayerDTO> players = objectMapper.readValue(result.getResponse().getContentAsString(), 
        		objectMapper.getTypeFactory().constructCollectionType(List.class, PlayerDTO.class));
        assertEquals(List.of(createPlayerDTO(id)), players);
	}
	
	@Test
	public void testDeletePlayer() throws Exception {
		RequestPlayerDTO requestPlayer = createPlayerRequest();
		MvcResult saveResult = mockMvc.perform(post("/api/performance-tracker")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestPlayer)))
			.andReturn();
		
		Long id = objectMapper.readValue(saveResult.getResponse().getContentAsString(), ResponsePlayerDTO.class)
				.getId();
		
		mockMvc.perform(delete("/api/performance-tracker/" + id)
				.contentType(MediaType.APPLICATION_JSON))
			.andExpect(status().isNoContent());
	}

	@Test
	void testUpdatePlayer() throws Exception {
		RequestPlayerDTO requestPlayer = createPlayerRequest();
		MvcResult saveResult = mockMvc.perform(post("/api/performance-tracker")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(requestPlayer)))
			.andReturn();
		
		PlayerDTO savedPlayer = objectMapper.readValue(saveResult.getResponse().getContentAsString(), PlayerDTO.class);
		savedPlayer.setFirstName("Xavier");
		
		MvcResult result = mockMvc.perform(patch("/api/performance-tracker/" + savedPlayer.getId())
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(savedPlayer)))
			.andExpect(status().isOk())
			.andReturn();
		
		PlayerDTO resultPlayer = objectMapper.readValue(result.getResponse().getContentAsString(), PlayerDTO.class);
		assertEquals("Xavier", resultPlayer.getFirstName());
	}
	
    private RequestPlayerDTO createPlayerRequest() {
        RequestPlayerDTO requestPlayer = new RequestPlayerDTO();
        requestPlayer.setContractEnd(LocalDate.EPOCH);
        requestPlayer.setFirstName("Csaba");
        requestPlayer.setLastName("Antal");
        requestPlayer.setPosition("forward");
        return requestPlayer;
    }
    
    private PlayerDTO createPlayerDTO(Long id) {
        PlayerDTO playerDTO = new PlayerDTO();
        playerDTO.setContractEnd(LocalDate.EPOCH);
        playerDTO.setId(id);
        playerDTO.setFirstName("Csaba");
        playerDTO.setLastName("Antal");
        playerDTO.setPosition("forward");
        return playerDTO;
    }
}
