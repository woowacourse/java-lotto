package lotto.view;

import java.util.Map.Entry;
import java.util.stream.Collectors;
import lotto.domain.lotto.Lotto;
import lotto.domain.lotto.LottoNumber;
import lotto.domain.lotto.LottoRank;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.Lottos;

public class OutputView {

    private static final String BUY_COUNT = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String RESULT_MESSAGE_SKELETON = "%d개 일치%s(%d원) - %d개";
    private static final String INPUT_PURCHASE_PRICE = "구입금액을 입력해 주세요.";
    private static final String INPUT_WINNING_NUMBER = "지난 주 당첨 번호를 입력해 주세요.";
    private static final String INPUT_BONUS_NUMBER = "보너스 번호를 입력해 주세요.";
    private static final String WINNING_STATICS = "당첨 통계";
    private static final String BOUNDARY = "---------";
    private static final String SPACE = "";
    private static final String BONUS_BALL_COLLECT = ", 보너스 볼 일치";
    private static final String TOTAL_PROFIT_RATE = "총 수익률은 %.2f 입니다.";
    private static final String NUM_MANUAL_LOTTO = "수동으로 구매할 로또 수를 입력해 주세요.";
    private static final String MANUAL_LOTTO_PRINT = "수동으로 구매할 번호를 입력해 주세요";

    public static void numPurchasedLotto(Integer numManualLotto, Integer numAutoLotto) {
        System.out.println(String.format(BUY_COUNT, numManualLotto, numAutoLotto));
    }

    public static void inputMoney() {
        System.out.println(INPUT_PURCHASE_PRICE);
    }

    public static void lottosPrint(Lottos purchasedLottos) {
        for (Lotto lotto : purchasedLottos.getLottos()) {
            System.out.println(lotto.getNumbers()
                .stream()
                .map(LottoNumber::getValue)
                .collect(Collectors.toList()));
        }
    }

    public static void inputWinningNumber() {
        System.out.println(INPUT_WINNING_NUMBER);
    }

    public static void inputBonus() {
        System.out.println(INPUT_BONUS_NUMBER);
    }

    public static void totalWinning() {
        System.out.println(WINNING_STATICS);
        System.out.println(BOUNDARY);
    }

    public static void numMatchPrint(LottoResult lottoResult) {
        for (Entry<LottoRank, Integer> entrySet : lottoResult.getResult().entrySet()) {
            printResult(entrySet);
        }

    }

    private static void printResult(Entry<LottoRank, Integer> entrySet) {

        if (entrySet.getKey().getWinningMoney() == 0) {
            return;
        }
        if (entrySet.getKey() == LottoRank.SECOND) {
            bonusPrint(entrySet);
            return;
        }
        System.out.println(String.format(RESULT_MESSAGE_SKELETON
            , entrySet.getKey().getNumMatch()
            , SPACE
            , entrySet.getKey().getWinningMoney()
            , entrySet.getValue()));
    }

    private static void bonusPrint(Entry<LottoRank, Integer> entrySet) {
        System.out.println(String.format(RESULT_MESSAGE_SKELETON
            , entrySet.getKey().getNumMatch()
            , BONUS_BALL_COLLECT
            , entrySet.getKey().getWinningMoney()
            , entrySet.getValue()));
    }

    public static void profitRatePrint(LottoResult lottoResult) {
        final String profitRateMessageSkeleton = TOTAL_PROFIT_RATE;
        System.out.println(String.format(profitRateMessageSkeleton, lottoResult.getProfitRate()));
    }

    public static void errorPrint(Exception error) {
        System.out.println(error.getMessage());
    }

    public static void inputNumManualLotto() {
        System.out.println(NUM_MANUAL_LOTTO);
    }

    public static void manualNumberPrint() {
        System.out.println(MANUAL_LOTTO_PRINT);
    }
}
