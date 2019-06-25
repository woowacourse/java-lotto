package lotto.domain.dto;

import lotto.domain.LottoTicket;

import java.util.List;

public class LottoGameResultDto {
    private static final int MONEY_OFFSET = 1_000;
    private static final String DELIMITER = ",";

    private int winningLottoId;
    private String[] winningNumbers;
    private int bonusBall;
    private List<LottoTicket> lottoTickets;
    private List<String> ranks;
    private int money;
    private int profit;
    private double incomingRate;

    public LottoGameResultDto(
            int winningLottoId, String winningNumbers, int bonusBall, List<LottoTicket> lottoTickets,
            List<String> ranks, int profit, double incomingRate) {
        this.winningLottoId = winningLottoId;
        this.winningNumbers = winningNumbers.split(DELIMITER);
        this.bonusBall = bonusBall;
        this.lottoTickets = lottoTickets;
        this.ranks = ranks;
        this.money = lottoTickets.size() * MONEY_OFFSET;
        this.profit = profit;
        this.incomingRate = incomingRate;
    }

    public int getWinningLottoId() {
        return winningLottoId;
    }

    public String[] getWinningNumbers() {
        return winningNumbers;
    }

    public int getBonusBall() {
        return bonusBall;
    }

    public List<LottoTicket> getLottoTickets() {
        return lottoTickets;
    }

    public List<String> getRanks() {
        return ranks;
    }

    public int getMoney() {
        return money;
    }

    public int getProfit() {
        return profit;
    }

    public String getIncomingRate() {
        return String.format("%.2f", incomingRate);
    }
}
