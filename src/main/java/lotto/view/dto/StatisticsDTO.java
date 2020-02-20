package lotto.view.dto;

import lotto.domain.PrizeGroup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.LottoTicket.LOTTO_PRICE;

public class StatisticsDTO {
    private final List<PrizeDTO> prizeDTOs;

    public StatisticsDTO(List<PrizeGroup> prizeResults) {
        this.prizeDTOs = Arrays.stream(PrizeGroup.values())
                .map(criteria -> new PrizeDTO(criteria, prizeResults))
                .collect(Collectors.toList());
    }

    public double getRate() {
        double bettingMoney = getBettingMoney();
        double totalPrize = getPrize();
        return totalPrize / bettingMoney;
    }

    private int getBettingMoney() {
        return this.prizeDTOs.stream()
                .mapToInt(PrizeDTO::getMatchTicketCount)
                .sum() * LOTTO_PRICE;
    }

    private double getPrize() {
        return this.prizeDTOs.stream()
                .mapToDouble(PrizeDTO::getSum)
                .sum();
    }

    public int size() {
        return this.prizeDTOs.size();
    }

    public int getMatchCount(int index) {
        return this.prizeDTOs.get(index).getMatchCount();
    }


    public int getDefaultPrize(int i) {
        return this.prizeDTOs.get(i).getDefaultPrize();
    }

    public int getMatchTicketCount(int i) {
        return this.prizeDTOs.get(i).getMatchTicketCount();
    }

    public String getName(int i) {
        return this.prizeDTOs.get(i).getName();
    }
}
