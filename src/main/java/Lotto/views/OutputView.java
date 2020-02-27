package Lotto.views;

import Lotto.domain.*;

import java.util.Map;

public class OutputView {
    private static final String PURCHASED_LOTTO_MESSAGE = "개를 구매했습니다.";
    private static final String STATISTICS_FORMAT = "%d개 일치 %s%d원 - %d개%s";
    private static final String SECOND_RANK_ADDITIONAL_MESSAGE = "보너스 볼 일치 ";
    private static final String EMPTY_STRING = "";
    private static final String NEW_LINE = "\n";
    private static final String EARNING_RATE_MESSAGE = "총 수익률은 %.3f %%입니다.";

    public static void showPurchasedLottoCount(int purchasedLottoCount) {
        System.out.println(purchasedLottoCount + PURCHASED_LOTTO_MESSAGE);
    }

    public static void showPurchasedAutoLottos(Lottos purchasedAutoLottos) {
        System.out.println(purchasedAutoLottos.getLottos());
    }

    public static void showStatistics(Ranks ranks) {
        Map<Rank, Long> rankCounts = ranks.countRanks();
        rankCounts.keySet()
                .stream()
                .sorted()
                .forEach(rank -> System.out.printf(STATISTICS_FORMAT, rank.getHitCount(), printBonus(rank),
                        rank.getRankReward(), rankCounts.get(rank), NEW_LINE));
    }

    private static String printBonus(Rank rank) {
        if (rank == Rank.SECOND) {
            return SECOND_RANK_ADDITIONAL_MESSAGE;
        }
        return EMPTY_STRING;
    }

    private static void showEarningRate(Ranks ranks, PurchaseAmount purchaseAmount) {
        EarningRate earningRate = new EarningRate(ranks, purchaseAmount);
        System.out.printf(EARNING_RATE_MESSAGE, earningRate.getEarningRate());
    }
}
