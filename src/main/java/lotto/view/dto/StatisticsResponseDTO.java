package lotto.view.dto;

import lotto.domain.result.win.prize.PrizeGroup;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class StatisticsResponseDTO {
    private static final String RATE_PERCENT = "%";
    private final List<PrizeResponseDTO> prizeResponseDTOS;

    public StatisticsResponseDTO(List<PrizeGroup> prizeResults) {
        this.prizeResponseDTOS = Arrays.stream(PrizeGroup.values())
                .map(criteria -> new PrizeResponseDTO(criteria, prizeResults))
                .collect(Collectors.toList());
    }

    public String getRate() {
        double bettingMoney = getBettingMoney();
        double totalPrize = getPrize();
        return (totalPrize / bettingMoney * 100) + RATE_PERCENT;
    }

    private int getBettingMoney() {
        return this.prizeResponseDTOS.stream()
                .mapToInt(PrizeResponseDTO::getMatchTicketCount)
                .sum() * LOTTO_PRICE;
    }

    private double getPrize() {
        return this.prizeResponseDTOS.stream()
                .mapToDouble(PrizeResponseDTO::getSum)
                .sum();
    }

    public int size() {
        return this.prizeResponseDTOS.size();
    }

    public int getMatchCount(int index) {
        return this.prizeResponseDTOS.get(index).getMatchCount();
    }


    public int getDefaultPrize(int i) {
        return this.prizeResponseDTOS.get(i).getDefaultPrize();
    }

    public int getMatchTicketCount(int i) {
        return this.prizeResponseDTOS.get(i).getMatchTicketCount();
    }

    public String getName(int i) {
        return this.prizeResponseDTOS.get(i).getName();
    }
}
