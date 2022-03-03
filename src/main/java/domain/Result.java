package domain;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.List;

public class Result {

    private static final int RESULT_INIT_NUMBER = 0;
    private static final int INIT_WIN_PRICE = 0;
    private static final String PROFIT_NEGATIVE_MESSAGE = "손해";
    private static final String PROFIT_POSITIVE_MESSAGE = "이익";
    private static final String WIN_PROFIT_RESULT_MESSAGE = "총 수익률은 %.2f입니다. (기준이 1 이기 때문에 결과적으로 %s라는 의미임)";
    private static final int LOTTO_PRICE = 1_000;

    private final EnumMap<RankPrize, Integer> result = new EnumMap<>(RankPrize.class);

    {
        Arrays.stream(RankPrize.values()).forEach(rank -> result.put(rank, RESULT_INIT_NUMBER));
    }

    public Result(final List<Lotto> issuedLotto, final WinLotto winLotto) {
        for (Lotto lotto : issuedLotto) {
            final RankPrize rankPrize = RankPrize.of(lotto.compare(winLotto.getLotto()),
                lotto.isContainNumber(winLotto.getBonusNumber()));
            result.put(rankPrize, result.getOrDefault(rankPrize, 0) + 1);
        }
    }

    public String getProfitOrNotMessage(final Money money) {
        final double profitRate = getProfitRate(money);
        String resultMessage = PROFIT_NEGATIVE_MESSAGE;
        if (profitRate >= 1) {
            resultMessage = PROFIT_POSITIVE_MESSAGE;
        }
        return String.format((WIN_PROFIT_RESULT_MESSAGE), profitRate, resultMessage);
    }

    private double getProfitRate(final Money money) {
        return getWinningPrice() / ((double) money.getLottoCount() * LOTTO_PRICE);
    }

    private int getWinningPrice() {
        int totalWinPrice = INIT_WIN_PRICE;
        for (RankPrize rankPrize : result.keySet()) {
            totalWinPrice += rankPrize.getPrice() * result.get(rankPrize);
        }
        return totalWinPrice;
    }

    public EnumMap<RankPrize, Integer> getResult() {
        return result;
    }
}
