package lotto.view.dto;

public class BettingMoneyRequestDTO {
    private static final int LOWER_BOUNCE = 1000;
    private static final String MESSAGE_FOR_NOT_ENOUGH_MONEY = "%d는 최소 구매 금액보다 작습니다.";

    private final int bettingMoney;

    public BettingMoneyRequestDTO(int bettingMoney) {
        validateMoney(bettingMoney);
        this.bettingMoney = bettingMoney;
    }

    private void validateMoney(int bettingMoney) {
        if (bettingMoney < LOWER_BOUNCE) {
            throw new IllegalArgumentException(String.format(MESSAGE_FOR_NOT_ENOUGH_MONEY, bettingMoney));
        }
    }

    public int getBettingMoney() {
        return bettingMoney;
    }
}
