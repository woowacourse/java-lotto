package lotto.view.dto;

import lotto.domain.result.rank.Rank;

public class ResultDTO {
    private final String name;
    private final int numberOfMatch;
    private final int prize;
    private final int numberOfMatchTickets;

    public ResultDTO(Rank rank, int numberOfMatchTickets) {
        this.name = rank.name();
        this.numberOfMatch = rank.getNumberOfMatch();
        this.prize = rank.getPrize();
        this.numberOfMatchTickets = numberOfMatchTickets;
    }

    public String getName() {
        return name;
    }

    public int getNumberOfMatch() {
        return numberOfMatch;
    }

    public int getPrize() {
        return prize;
    }

    public int getNumberOfMatchTickets() {
        return numberOfMatchTickets;
    }
}
