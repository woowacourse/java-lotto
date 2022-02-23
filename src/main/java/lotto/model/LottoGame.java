package lotto.model;

import java.util.List;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;

    private final LottoMatcher lottoMatcher;

    public LottoGame(List<Integer> winningNumbers, Integer bonusNumber) {
        this.lottoMatcher = new LottoMatcher(winningNumbers, bonusNumber);
    }

    public float calculateYield(int money, List<Lotto> lottos) {
        ResultMap winningResult = lottoMatcher.getWinningResult(lottos);
        return getWinningAmount(winningResult) / (float) money;
    }

    private Long getWinningAmount(ResultMap rank) {
        return rank.getResult().entrySet().stream()
                .map(entry -> entry.getKey().getMoney() * entry.getValue())
                .mapToLong(i -> i)
                .sum();
    }

    public static int buyLottos(int money) {
        validateUnitPrice(money);
        return money / LOTTO_PRICE;
    }

    private static void validateUnitPrice(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException("거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.");
        }
    }
}
