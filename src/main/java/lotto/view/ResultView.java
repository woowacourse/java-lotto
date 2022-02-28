package lotto.view;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import lotto.model.Lotto;
import lotto.model.Lottos;
import lotto.model.Rank;
import lotto.model.number.LottoNumber;

public class ResultView {

    private static final String BUY_COUNT_MESSAGE = "개를 구매했습니다.";
    private static final String STATISTICS_MESSAGE = "당첨통계\n---------";
    private static final String COUNT_CORRESPOND_MESSAGE = "개 일치";
    private static final String PRICE_OPEN_BRACKET = " (";
    private static final String PRICE_CLOSE_BRACKET = "원) - ";
    private static final String COUNT_MESSAGE = "개\n";
    private static final String BONUS_BALL_CORRESPOND_MESSAGE = ", 보너스 볼 일치";
    private static final String BONUS_BALL_NOT_CORRESPOND_MESSAGE = "";
    private static final String START_REVENUE_MESSAGE = "총 수익률은 ";
    private static final String END_REVENUE_MESSAGE = "입니다.";

    public static void printBuyingLottosResult(final Lottos lottos) {
        printLottoCount(lottos);
        printBuyingLottos(lottos);
    }

    private static void printLottoCount(final Lottos lottos) {
        System.out.println(lottos.getLottoCount() + BUY_COUNT_MESSAGE);
    }

    private static void printBuyingLottos(final Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            printBuyingLotto(lotto);
        }
    }

    private static void printBuyingLotto(final Lotto lotto) {
        System.out.println(lotto.getLottoNumbers().getLottoNumbers().stream()
                .mapToInt(LottoNumber::getLottoNumber)
                .boxed()
                .collect(Collectors.toList()));
    }

    public static void printTotalRankResult(final Map<Rank, Integer> rankCount) {
        System.out.println();
        System.out.println(STATISTICS_MESSAGE);
        Set<Rank> keySet = rankCount.keySet();
        for (Rank rank : keySet) {
            printRank(rank, rankCount.get(rank));
        }
    }

    private static void printRank(final Rank rank, final Integer integer) {
        StringBuilder message = new StringBuilder();
        if (!rank.equals(Rank.LOSER)) {
            message.append(rank.getCount()).append(COUNT_CORRESPOND_MESSAGE);
            message.append(printBonusNumber(rank));
            message.append(PRICE_OPEN_BRACKET).append(rank.getPrice()).append(PRICE_CLOSE_BRACKET).append(integer)
                    .append(COUNT_MESSAGE);
        }
        System.out.print(message);
    }

    private static String printBonusNumber(final Rank rank) {
        if (rank.isWinBonusNumber()) {
            return BONUS_BALL_CORRESPOND_MESSAGE;
        }
        return BONUS_BALL_NOT_CORRESPOND_MESSAGE;
    }

    public static void printRevenue(final Lottos lottos) {
        System.out.println(START_REVENUE_MESSAGE + lottos.getRevenue() + END_REVENUE_MESSAGE);
    }
}
