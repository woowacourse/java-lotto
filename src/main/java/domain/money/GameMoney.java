package domain.money;

import java.math.BigDecimal;

public class GameMoney {
    private static final int SINGLE_LOTTO_GAME_MONEY = 1000;

    private BigDecimal gameMoney;

    public GameMoney(final int gameMoney) {
        validateBudget(gameMoney);
        this.gameMoney = new BigDecimal(gameMoney);
    }

    public static int getSingleLottoPrice() {
        return SINGLE_LOTTO_GAME_MONEY;
    }

    private void validateBudget(final int gameMoney) {
        if (gameMoney < SINGLE_LOTTO_GAME_MONEY) {
            throw new IllegalArgumentException("게임에는 최소 " + SINGLE_LOTTO_GAME_MONEY + "원이 필요합니다.");
        }
    }

    public void checkManualBuyingAvailable(final int quantity) {
        if (quantity < 0 || checkMaxLottoAvailable() < quantity) {
            throw new IllegalArgumentException("구입할 수 없는 수량입니다.");
        }
    }

    public int checkMaxLottoAvailable() {
        return gameMoney.divide(new BigDecimal(SINGLE_LOTTO_GAME_MONEY)).intValue();
    }

    public void buyLotto(final int number) {
        final BigDecimal lottoBuyingMoney = new BigDecimal(number * SINGLE_LOTTO_GAME_MONEY);
        final BigDecimal gameMoneyLeft = gameMoney.subtract(lottoBuyingMoney);
        if (gameMoneyLeft.intValue() < 0) {
            throw new IllegalArgumentException("돈이 부족합니다.");
        }
        gameMoney = gameMoneyLeft;
    }
}
