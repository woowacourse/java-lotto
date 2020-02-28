package lotto.view;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Lottos;
import lotto.domain.Money;
import lotto.domain.Rank;
import lotto.domain.ResultStatistic;

/**
 * OutputView 클래스
 *
 * @author 히히, 토니
 * @version 1.0
 * <p>
 * 날짜 : 2020/02/20
 */
public class OutputView {
    private static final String ENTER_MONEY_FOR_LOTTO_GUIDE_MESSAGE = "구매금액을 입력해 주세요.";
    private static final String PURCHASED_LOTTO_NUMBER_IS = "수동으로 %d장, 자동으로 %d개를 구매했습니다.\n";
    private static final String ENTER_WINNING_LOTTO_GUIDE_MESSAGE = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String ENTER_BONUS_BALL_NUMBER_GUIDE_MESSAGE = "보너스 볼을 입력해 주세요.";
    private static final String RESULT_STATISTIC_MESSAGE = "\n당첨 통계\n--------";

    public static void askEnterMoneyForLotto() {
        System.out.println(ENTER_MONEY_FOR_LOTTO_GUIDE_MESSAGE);
    }

    public static void printExceptionMessage(Exception e) {
        System.out.println(e.getMessage());
    }

    public static void printAutoPurchasedLottos(int autoLottosNo, Lottos lottos) {
        printPurchasedLottos(0, autoLottosNo, lottos);
    }

    public static void printPurchasedLottos(int manualLottosNo, int autoLottosNo, Lottos lottos) {
        System.out.printf(PURCHASED_LOTTO_NUMBER_IS, manualLottosNo, autoLottosNo);

        List<String> purchasedLottos = new ArrayList<>();
        for (Lotto lotto : lottos.getLottos()) {
            purchasedLottos.add(lotto.getLottoNumbers()
                .stream()
                .map(LottoNumber::getLottoNumber)
                .map(Object::toString)
                .collect(Collectors.joining(", "))
            );
        }
        for (String purchasedLotto : purchasedLottos) {
            System.out.println("[" + purchasedLotto + "]");
        }
    }

    public static void askEnterWinningLotto() {
        System.out.println(ENTER_WINNING_LOTTO_GUIDE_MESSAGE);
    }

    public static void askEnterBonusLottoNumber() {
        System.out.println(ENTER_BONUS_BALL_NUMBER_GUIDE_MESSAGE);
    }

    public static void printResultStatistic(ResultStatistic result, Money money) {
        System.out.println(RESULT_STATISTIC_MESSAGE);
        printEachRankResult(Rank.FIFTH, result);
        printEachRankResult(Rank.FOURTH, result);
        printEachRankResult(Rank.THIRD, result);
        printEachRankResult(Rank.SECOND, result);
        printEachRankResult(Rank.FIRST, result);
        System.out.printf("총 수익률은 %d%%입니다.\n", result.calculateRevenueRate(money));
    }


    private static void printEachRankResult(Rank rank, ResultStatistic result) {
        String rankResultFormat = "%d개 일치 (%d원)- %d개\n";
        if (rank == Rank.SECOND) {
            rankResultFormat = "%d개 일치, 보너스 볼 일치 (%d원)- %d개\n";
        }

        System.out.printf(rankResultFormat,
            rank.getMatchCounts(),
            rank.getReward(),
            result.getResults().get(rank)
        );
    }

    public static void askEnterManualLottoAmount() {
        System.out.println("수동으로 구매할 로또 수를 입력해 주세요.");
    }

    public static void askEnterManualLottos() {
        System.out.println("수동으로 구매할 번호를 입력해 주세요.");
    }
}
