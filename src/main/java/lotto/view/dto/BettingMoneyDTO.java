package lotto.view.dto;

import static lotto.domain.ticket.LottoTicket.LOTTO_PRICE;

public class BettingMoneyDTO {
    private static final String MESSAGE_FOR_NOT_ENOUGH_MONEY = "%d는 최소 구매 금액보다 작습니다.";

    private final int bettingMoney;

    public BettingMoneyDTO(int bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateMoney(int bettingMoney) {
        if (bettingMoney < LOTTO_PRICE) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_NOT_ENOUGH_MONEY, bettingMoney));
        }
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
