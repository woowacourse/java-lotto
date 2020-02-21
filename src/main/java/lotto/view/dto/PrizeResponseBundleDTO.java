package lotto.view.dto;

import lotto.domain.result.win.prize.PrizeGroup;

import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class PrizeResponseBundleDTO {
    private static final String RATE_PERCENT = "%";
    private static final String CUT_DIGIT_FORMAT = "0";
    private static final int PERCENTAGE = 100;
    private final List<PrizeResponseDTO> prizeResponseDTOS;

    public PrizeResponseBundleDTO(List<PrizeGroup> prizeResults) {
        this.prizeResponseDTOS = Arrays.stream(PrizeGroup.values())
                .map(criteria -> new PrizeResponseDTO(criteria, prizeResults))
                .collect(Collectors.toList());
    }

    public String getRate() {
        double bettingMoney = getBettingMoney();
        double totalPrize = getPrize();

        DecimalFormat decimalFormat = new DecimalFormat(CUT_DIGIT_FORMAT);
        String rate = decimalFormat.format(totalPrize / bettingMoney * PERCENTAGE);
        return rate + RATE_PERCENT;
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


    public int getDefaultPrize(int index) {
        return this.prizeResponseDTOS.get(index).getDefaultPrize();
    }

    public int getMatchTicketCount(int index) {
        return this.prizeResponseDTOS.get(index).getMatchTicketCount();
    }

    public String getName(int index) {
        return this.prizeResponseDTOS.get(index).getName();
    }
}
