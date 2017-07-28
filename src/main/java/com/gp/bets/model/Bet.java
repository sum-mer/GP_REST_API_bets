package com.gp.bets.model;

import com.sun.javafx.beans.IDProperty;

import javax.persistence.*;

/**
 * Created by eolgzac on 2017-07-27.
 */
@Entity
@Table
public class Bet {
    @Id
    @GeneratedValue
    @Column
    private Long id;

    @Column
    private String playerName;

    @Column
    private String gameName;

    @Column
    private Integer moneyAmount;

    public Bet(Long id, String playerName, String gameName, Integer moneyAmount) {
        this.id = id;
        this.playerName = playerName;
        this.gameName = gameName;
        this.moneyAmount = moneyAmount;
    }

    // for JPA
    public Bet() {

    }

    public Long getId() {
        return id;
    }

    public String getPlayerName() {
        return playerName;
    }

    public String getGameName() {
        return gameName;
    }

    public Integer getMoneyAmount() {
        return moneyAmount;
    }

    @Override
    public String toString() {
        return "Bet{" +
                "id=" + id +
                ", playerName='" + playerName + '\'' +
                ", gameName='" + gameName + '\'' +
                ", moneyAmount=" + moneyAmount +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Bet bet = (Bet) o;

        if (id != null ? !id.equals(bet.id) : bet.id != null) return false;
        if (playerName != null ? !playerName.equals(bet.playerName) : bet.playerName != null) return false;
        if (gameName != null ? !gameName.equals(bet.gameName) : bet.gameName != null) return false;
        return moneyAmount != null ? moneyAmount.equals(bet.moneyAmount) : bet.moneyAmount == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (playerName != null ? playerName.hashCode() : 0);
        result = 31 * result + (gameName != null ? gameName.hashCode() : 0);
        result = 31 * result + (moneyAmount != null ? moneyAmount.hashCode() : 0);
        return result;
    }
}
