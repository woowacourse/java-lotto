package lotto.view;

import lotto.domain.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    private static final String LOTTO_NUMBER_DELIMITER = ", ";
    private static final String LOTTO_NUMBER_OPEN_BRACKET = "[";
    private static final String LOTTO_NUMBER_CLOSE_BRACKET = "]";

    public static void printBoughtLottos(final BoughtLottos boughtLottos) {
        printNumberOfBoughtLotto(boughtLottos);
        List<Lotto> lottos = boughtLottos.getLottos();
        for (Lotto lotto : lottos) {
            printLottoNumbers(lotto);
        }
    }

    private static void printNumberOfBoughtLotto(BoughtLottos boughtLottos) {
        System.out.println(boughtLottos.countOfBoughtLottos() + "개를 구매했습니다.");
    }

    private static void printLottoNumbers(final Lotto lotto) {
        String lottoStatus = lotto.getNumbers().stream()
                .map(lottoNumber -> lottoNumber.getNumber() + "")
                .collect(Collectors.joining(LOTTO_NUMBER_DELIMITER));
        System.out.println(LOTTO_NUMBER_OPEN_BRACKET + lottoStatus + LOTTO_NUMBER_CLOSE_BRACKET);
    }

    public static void printLottoResult(Result result, int buyPrice) {
        System.out.println("당첨 통계");
        for (int i = Result.MIN_PRIZE_NUMBER; i <= Result.MAX_PRIZE_NUMBER; i++) {
            Prize prize = Prize.valueOf(i);
            printPrizedata(prize, result.getCountOfPrize(prize));
        }
        System.out.printf("총 수익률은 %.0f%% 입니다.", result.calculateRateOfReturn(buyPrice));
    }

    private static void printPrizedata(Prize prize, int prizeCount) {
        System.out.println(prize.getCountOfNumber() + "개 일치 (" + prize.getWinningAmount() + "원) - " + prizeCount + "개");
    }
}
