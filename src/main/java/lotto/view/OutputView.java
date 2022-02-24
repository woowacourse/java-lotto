package lotto.view;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import lotto.domain.Lotto;
import lotto.domain.LottoWinningNumbers;
import lotto.domain.Lottos;
import lotto.domain.Rank;

public class OutputView {

    public static void printLottos(Lottos lottos) {
        System.out.println(lottos.getLottos().size() + "개를 구매했습니다.");

        for (Lotto lotto : lottos.getLottos()) {
            printLotto(lotto);
        }
        System.out.println();
    }

    private static void printLotto(Lotto lotto) {
        System.out.println(lotto.getNumbers().toString());
    }

    public static void printWinningResult(LottoWinningNumbers lottoWinningNumbers) {
        System.out.println();
        System.out.println("당첨 통계");
        System.out.println("---------");
        printBasicWinningResult(Rank.FIFTH, lottoWinningNumbers.getRankCount(Rank.FIFTH));
        printBasicWinningResult(Rank.FOURTH, lottoWinningNumbers.getRankCount(Rank.FOURTH));
        printBasicWinningResult(Rank.THIRD, lottoWinningNumbers.getRankCount(Rank.THIRD));
        printSecondResult(Rank.SECOND, lottoWinningNumbers.getRankCount(Rank.SECOND));
        printBasicWinningResult(Rank.FIRST, lottoWinningNumbers.getRankCount(Rank.FIRST));
    }

    private static void printBasicWinningResult(Rank rank, int count) {
        System.out.println(rank.getMatchCount()+"개 일치 (" + rank.getMoney() + "원) - " + count +"개");
    }

    private static void printSecondResult(Rank rank, int count) {
        System.out.println(rank.getMatchCount()+"개 일치, 보너스 볼 일치 (" + rank.getMoney() + "원) - " + count +"개");
    }

    public static void printProfit(double profit) {
        System.out.println("총 수익률은 "+ Double.parseDouble(String.format("%.2f", profit)) +"입니다.");
    }
}
