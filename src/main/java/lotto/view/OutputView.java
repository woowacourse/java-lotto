package lotto.view;

import lotto.domain.LottoResult;
import lotto.domain.LottoTickets;
import lotto.domain.Money;

public class OutputView {
    public static void printLottoNumbers(Money money, int numberOfManualLotto) {
        int numberOfAutoLotto = money.getNumberOfLotto() - numberOfManualLotto;
        System.out.printf("\n수동으로 %d장, 자동으로 %d장을 구매했습니다.\n", numberOfManualLotto, numberOfAutoLotto);
    }

    public static void printLottoTickets(LottoTickets lottoTickets) {
        System.out.println(lottoTickets);
    }

    public static void printLottoStatistics(LottoResult lottoResult) {
        System.out.println("\n당첨 통계");
        System.out.println("---------");
        System.out.println(lottoResult);
    }
}
