package com.gp.bets.model;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

/**
 * Created by eolgzac on 2017-07-27.
 */
public interface BetRepository extends JpaRepository<Bet, Long> {

    /**
     * Find all bets for specified player;
     * @param playerName
     * @return collection of player's Bets
     */
    Collection<Bet> findByPlayerName(String playerName);
}
