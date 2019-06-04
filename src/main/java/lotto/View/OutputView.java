package lotto.View;

import lotto.domain.Lotto;
import lotto.domain.LottoNumber;
import lotto.domain.Money;
import lotto.domain.Rank;

import java.util.List;
import java.util.Map;

public class OutputView {

    public static void printCountOfLotto(Money money, int countOfManualLotto) {
        int countOfAutoLotto = money.calculateCountOfLotto() - countOfManualLotto;
        System.out.println("수동으로 " + countOfManualLotto + "장, 자동으로 " + countOfAutoLotto + "개를 구매했습니다.");
    }

    public static void printUserLottos(List<Lotto> userLottos) {
        userLottos.forEach(lotto -> printLotto(lotto.getLottoNumbers()));
    }

    private static void printLotto(List<LottoNumber> lottoNumbers) {
        System.out.println(lottoNumbers);
    }

    public static void printCountOfRank(Map<Rank, Integer> countOfRank) {
        countOfRank.forEach(OutputView::printOneRank);
    }

    private static void printOneRank(Rank rank, int count) {
        if (rank.getCountOfMatch() != 0) {
            System.out.println(rank.getCountOfMatch() + "개 일치 (" + rank.getWinningMoney() + "원) - " + count + "개");
        }
    }

    public static void printRateOfReturn(double rateOfReturn) {
        System.out.println("총 수익률은 " + String.format("%.2f", rateOfReturn) + "%입니다.");
    }
}
