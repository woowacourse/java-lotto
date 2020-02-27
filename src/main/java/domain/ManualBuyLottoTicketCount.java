package domain;

import org.apache.commons.lang3.StringUtils;

public class ManualBuyLottoTicketCount {
    private static final int LOTTO_TICKET_PRICE = 1_000;

    private final int manualBuyLottoTicketCount;

    public ManualBuyLottoTicketCount(Money money, String inputCount) {
        validateNullOrEmpty(inputCount);
        int parseCount = validateNumber(inputCount);
        validateMoneyRange(money, parseCount);
        this.manualBuyLottoTicketCount = parseCount;
    }

    private void validateMoneyRange(Money money, int manualBuyCount) {
        if(money.getMoney() < manualBuyCount * LOTTO_TICKET_PRICE) {
            throw new IllegalArgumentException("구매하려고 하는 수동 티켓이 너무 많습니다!");
        }
    }

    private void validateNullOrEmpty(String inputCount) {
        if(StringUtils.isBlank(inputCount)) {
            throw new IllegalArgumentException("input 값이 Null 또는 공백입니다!");
        }
    }

    private int validateNumber(String inputCount) {
        try {
            return Integer.parseInt(inputCount);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("input 값이 숫자가 아닙니다!");
        }
    }

    public int getManualBuyLottoTicketCount() {
        return manualBuyLottoTicketCount;
    }
}
