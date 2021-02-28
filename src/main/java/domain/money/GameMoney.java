package domain.money;

import domain.lotto.LottoBundle;
import domain.number.NumberGenerator;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

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

    public LottoBundle buyLotto(final List<List<Integer>> lottoNumberBundle) {
        final LottoBundle lottoBundle = LottoBundle.of(lottoNumberBundle);
        calculateGameMoneyLeft(lottoNumberBundle.size());
        return lottoBundle;
    }

    public LottoBundle buyAutoLotto(final NumberGenerator numberGenerator) {
        List<List<Integer>> lottoNumberBundle = new ArrayList<>();
        for (int i = 0; i < checkMaxLottoAvailable(); i++) {
            lottoNumberBundle.add(numberGenerator.createLottoNumber());
        }
        return buyLotto(lottoNumberBundle);
    }

    private void calculateGameMoneyLeft(final int numberOfLottoToBuy) {
        final BigDecimal lottoBuyingMoney = new BigDecimal(numberOfLottoToBuy * SINGLE_LOTTO_GAME_MONEY);
        final BigDecimal gameMoneyLeft = gameMoney.subtract(lottoBuyingMoney);
        gameMoney = gameMoneyLeft;
    }
}
