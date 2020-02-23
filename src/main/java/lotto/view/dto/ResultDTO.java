package lotto.view.dto;

import lotto.domain.result.rank.Rank;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class ResultDTO {
    private final List<RankDTO> rankDTOS;

    public ResultDTO(List<Rank> rankResults) {
        this.rankDTOS = Arrays.stream(Rank.values())
                .map(criteria -> new RankDTO(criteria, rankResults))
                .collect(Collectors.toList());
    }

    public double getRate() {
        double bettingMoney = getBettingMoney();
        double totalPrize = getPrize();
        return totalPrize / bettingMoney;
    }

    private int getBettingMoney() {
        return this.rankDTOS.stream()
                .mapToInt(RankDTO::getMatchTicketCount)
                .sum() * LOTTO_PRICE;
    }

    private double getPrize() {
        return this.rankDTOS.stream()
                .mapToDouble(RankDTO::getSum)
                .sum();
    }

    public int size() {
        return this.rankDTOS.size();
    }

    public int getMatchCount(int index) {
        return this.rankDTOS.get(index).getMatchCount();
    }


    public int getDefaultPrize(int i) {
        return this.rankDTOS.get(i).getDefaultPrize();
    }

    public int getMatchTicketCount(int i) {
        return this.rankDTOS.get(i).getMatchTicketCount();
    }

    public String getName(int i) {
        return this.rankDTOS.get(i).getName();
    }
}
