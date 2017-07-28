package com.gp.bets;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.gp.bets.controller.BetController;
import com.gp.bets.model.Bet;
import com.gp.bets.model.BetRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest
public class BetsApplicationTests {

	@Autowired
	private BetController betController;
	@Autowired
	private ObjectMapper objectMapper;
	private MockMvc mockMvc;

	@Autowired
	private BetRepository betRepository;

	private Bet betPN, betAN, betPH;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.standaloneSetup(betController).build();
		// populate repository
		betPN = new Bet(1L, "Jan", "Mecz Polska-Niemcy", 100);
		betAN = new Bet(2L, "Piotr", "Mecz Austria-Niemcy", 400);
		betPH = new Bet(3L, "Jan", "Mecz Polska-Hiszpania", 300);
		betRepository.save(betPN);
		betRepository.save(betAN);
		betRepository.save(betPH);
	}


	@Test
	public void addBetTest() throws Exception {
		//given
		Bet bet = new Bet(1L, "Marcin", "Mecz Polska-Niemcy", 200);
		// when, then
		this.mockMvc.perform(MockMvcRequestBuilders.post("/bets")
				.contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(bet)))
				.andExpect(status().isCreated());
	}

	@Test
	public void getBetsFromRepositoryForPlayerTest() throws Exception {
		// given
		String playerName = "Jan";
		Collection<Bet> expectedBets = Arrays.asList(betPN, betPH);

		// when
		Collection<Bet> playerBets = betRepository.findByPlayerName(playerName);

		// then
		assertArrayEquals(expectedBets.toArray(), playerBets.toArray());

	}

	@Test
	public void getBetsForPlayerPostTest() throws Exception {
		// given - three bets for two different players in repository
		String playerName = "Jan";

		// when
		MvcResult result = this.mockMvc.perform(get("/bets/" + playerName)
				.contentType(MediaType.APPLICATION_JSON))
				.andDo(print())
				.andExpect(status().isOk())
				.andReturn();

		String responseContent = result.getResponse().getContentAsString();
		String expectedContent = "<ArrayList>" +
				"<item><id>1</id><playerName>Jan</playerName><gameName>Mecz Polska-Niemcy</gameName><moneyAmount>100</moneyAmount></item>" +
				"<item><id>3</id><playerName>Jan</playerName><gameName>Mecz Polska-Hiszpania</gameName><moneyAmount>300</moneyAmount></item>" +
				"</ArrayList>";
		// then
		assertEquals(expectedContent,responseContent);
	}

	@Test
	public void getBetsForNonExistingPlayerPostTest() throws Exception {
		// given
		String playerName = "Tomasz";

		// when, then
		this.mockMvc.perform(get("/bets/" + playerName)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isNoContent());

	}

	@Test
	public void indexTest() throws Exception {
		this.mockMvc.perform(get("/")).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Try with /bets")));
	}

}
