package lotto.view;

import java.util.stream.Collectors;
import lotto.model.Rank;
import lotto.model.lotto.Lotto;
import lotto.model.lotto.Lottos;
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

    public static void printBuyingLottosResult(Lottos lottos) {
        printLottoCount(lottos);
        printBuyingLottos(lottos);
    }

    private static void printLottoCount(Lottos lottos) {
        System.out.println(lottos.getLottoCount() + BUY_COUNT_MESSAGE);
    }

    private static void printBuyingLottos(Lottos lottos) {
        for (Lotto lotto : lottos.getLottos()) {
            printBuyingLotto(lotto);
        }
    }

    private static void printBuyingLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers().getLottoNumbers().stream()
                .mapToInt(LottoNumber::getLottoNumber)
                .boxed()
                .collect(Collectors.toList()));
    }

    public static void printTotalResult(Lottos lottos) {
        System.out.println();
        System.out.println(STATISTICS_MESSAGE);
        for (Rank rank : Rank.values()) {
            System.out.print(printRank(rank, lottos.getCount(rank)));
        }
        printRevenue(lottos);
    }

    private static String printRank(Rank rank, Integer integer) {
        StringBuilder message = new StringBuilder();
        if (!rank.equals(Rank.LOSER)) {
            message.append(rank.getCount()).append(COUNT_CORRESPOND_MESSAGE);
            message.append(printBonusNumber(rank));
            message.append(PRICE_OPEN_BRACKET).append(rank.getPrice()).append(PRICE_CLOSE_BRACKET).append(integer)
                    .append(COUNT_MESSAGE);
        }
        return message.toString();
    }

    private static String printBonusNumber(Rank rank) {
        if (rank.isWinBonusNumber()) {
            return BONUS_BALL_CORRESPOND_MESSAGE;
        }
        return BONUS_BALL_NOT_CORRESPOND_MESSAGE;
    }

    public static void printRevenue(Lottos lottos) {
        System.out.println(START_REVENUE_MESSAGE + lottos.getRevenue() + END_REVENUE_MESSAGE);
    }
}
