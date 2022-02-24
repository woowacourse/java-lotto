package domain;

import java.util.List;

public class LottoGame {

    private static final String ERROR_MESSAGE_LOTTO_MONEY_DIGITS = "로또 구매 금액은 1000원 단위여야 합니다.";
    private static final int LOTTO_PRICE = 1000;
    private static final int REMINDER_STANDARD = 0;

    private final Lottos lottos;

    public LottoGame(Money money) {
        validateMoneyRange(money);
        int lottoCount = money.getAmount() / LOTTO_PRICE;
        this.lottos = new Lottos(lottoCount, new RandomLottoNumberGenerator());
    }

    private void validateMoneyRange(Money money) {
        if (money.getAmount() % LOTTO_PRICE != REMINDER_STANDARD) {
            throw new IllegalArgumentException(ERROR_MESSAGE_LOTTO_MONEY_DIGITS);
        }
    }

    public WinningStatistics calculateWinningStatistics(WinningLotto winningLotto) {
        List<LottoReward> lottoRewards = lottos.calculateLottoReward(winningLotto);
        return new WinningStatistics(lottoRewards);
    }

    public Lottos getLottos() {
        return lottos;
    }
}
