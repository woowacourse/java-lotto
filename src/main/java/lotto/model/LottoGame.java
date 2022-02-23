package lotto.model;

import java.util.List;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    private static final String ERROR_NOT_DIVIDED_BY_UNIT_PRICE = "거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.";

    private final LottoMatcher lottoMatcher;

    public LottoGame(List<Integer> winningNumbers, Integer bonusNumber) {
        this.lottoMatcher = new LottoMatcher(winningNumbers, bonusNumber);
    }

    public float calculateYield(int money, Lottos lottos) {
        LottoResult winningResult = lottoMatcher.getWinningResult(lottos);
        return getTotalWinningMoney(winningResult) / (float) money;
    }

    private Long getTotalWinningMoney(LottoResult lottoResult) {
        return lottoResult.getResult().entrySet().stream()
                .map(entry -> entry.getKey().getMoney() * entry.getValue())
                .mapToLong(i -> i)
                .sum();
    }

    public static int getLottoSize(int money) {
        validateUnitPrice(money);
        return money / LOTTO_PRICE;
    }

    private static void validateUnitPrice(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_UNIT_PRICE);
        }
    }

    public static Lottos buyLottos(int money) {
        int lottoSize = getLottoSize(money);

        return Lottos.generate(lottoSize);
    }

    public LottoResult getWinningResult(Lottos lottos) {
        return lottoMatcher.getWinningResult(lottos);
    }
}
