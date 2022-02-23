package lotto.model;

import java.util.List;

public class LottoGame {
    private static final int LOTTO_PRICE = 1000;
    public static final String ERROR_NOT_DIVIDED_BY_UNIT_PRICE = "거스름돈을 지급하지 않습니다. 금액이 남지 않게 지불해주세요.";

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

    public static int getLottoSize(int money) {
        validateUnitPrice(money);
        return money / LOTTO_PRICE;
    }

    private static void validateUnitPrice(int money) {
        if (money % LOTTO_PRICE != 0) {
            throw new IllegalArgumentException(ERROR_NOT_DIVIDED_BY_UNIT_PRICE);
        }
    }

    public static List<Lotto> buyLottos(int money) {
        int lottoSize = getLottoSize(money);
        LottoGenerator lottoGenerator = new LottoGenerator();

        return lottoGenerator.generateLottos(lottoSize);
    }

    public ResultMap getWinningResult(List<Lotto> lottos) {
        return lottoMatcher.getWinningResult(lottos);
    }
}
