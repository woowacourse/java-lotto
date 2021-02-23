package domain;

import util.LottoGenerator;

import java.math.BigDecimal;

public class GameMoney {
    private static final int SINGLE_LOTTO_GAME_MONEY = 1000;

    private BigDecimal gameMoney;

    public GameMoney(int gameMoney) {
        validateBudget(gameMoney);
        this.gameMoney = new BigDecimal(gameMoney);
    }

    private void validateBudget(int gameMoney) {
        if (gameMoney < SINGLE_LOTTO_GAME_MONEY) {
            throw new IllegalArgumentException("게임에는 최소 " + SINGLE_LOTTO_GAME_MONEY + "원이 필요합니다.");
        }
    }

    public LottoBundle buyLotto() {
        final int numberOfLottoToBuy = gameMoney.divide(new BigDecimal(SINGLE_LOTTO_GAME_MONEY)).intValue();
        calculateGameMoneyLeft(numberOfLottoToBuy);

        final LottoBundle lottoBundle = LottoGenerator.createRandomLottoBundle(numberOfLottoToBuy);
        return lottoBundle;
    }

    private void calculateGameMoneyLeft(int numberOfLottoToBuy) {
        final BigDecimal lottoBuyingMoney = new BigDecimal(numberOfLottoToBuy * SINGLE_LOTTO_GAME_MONEY);
        final BigDecimal gameMoneyLeft = gameMoney.subtract(lottoBuyingMoney);
        gameMoney = gameMoneyLeft;
    }
}
