package com.gp.bets.controller;

import com.gp.bets.model.Bet;
import com.gp.bets.model.BetRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

/**
 * Created by eolgzac on 2017-07-27.
 */
@RestController
@EnableAutoConfiguration
public class BetController {

    private final BetRepository betRepository;
    public static final Logger logger = LoggerFactory.getLogger(BetController.class);

    BetController(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    @RequestMapping("/")
    public String index() {
        return "Try with /bets";
    }


    @RequestMapping(value = "/bets", method = RequestMethod.POST)
    public ResponseEntity<?>  addBet(@RequestBody Bet bet) {
        logger.info("New bet: " + bet);
        betRepository.save(bet);
        return new ResponseEntity<Bet>(bet, HttpStatus.CREATED); // HTTP 201
    }

    @RequestMapping(value = "/bets/{playerName}", method = RequestMethod.GET)
    public ResponseEntity<?> getBetsForPlayer(@PathVariable("playerName") String playerName) {
        Collection<Bet> bets = betRepository.findByPlayerName(playerName);
        if (bets.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT); // HTTP 204
        }
        return new ResponseEntity<Collection<Bet>>(bets, HttpStatus.OK); // HTTP 200
    }

}