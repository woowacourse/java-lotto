package lotto.view;

import lotto.domain.Lotto;
import lotto.domain.WinningValue;

import java.util.List;
import java.util.Map;

public class OutputView {
    public static void printPurchaseCount(int lottoTicketCount) {
        System.out.println(lottoTicketCount + "개를 구매했습니다.");
    }

    public static void printLottoNumbers(List<Lotto> lottos) {
        lottos.forEach(lotto ->
                System.out.println(lotto.getLottoNumbers()));
    }

    public static void printLottoResults(Map<WinningValue, Integer> lottoResult) {
        System.out.println("당첨 통계\n" +
                "---------");
        lottoResult.entrySet().forEach(OutputView::printLottoResult);

    }

    private static void printLottoResult(Map.Entry<WinningValue, Integer> result) {
        System.out.print(result.getKey().getMessage());
        System.out.print("(" + result.getKey().getReward() + "원)- ");
        System.out.println(result.getValue() + "개");
    }

}
