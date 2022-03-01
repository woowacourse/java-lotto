package view;

import domain.LottoTicket;
import domain.LottoNumber;
import domain.LottoResult;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {

    public static void printPurchaseInfo(int manualLottoCount, int autoLottoCount, List<LottoTicket> lottoTickets) {
        System.out.printf("\n수동으로 %d장, 자동으로 %d개를 구매했습니다.\n", manualLottoCount, autoLottoCount);

        StringBuilder builder = new StringBuilder();

        lottoTickets.stream()
                .map(LottoTicket::getNumbers)
                .map(OutputView::formatLottoNumbers)
                .forEach(builder::append);

        System.out.println(builder);
    }

    private static String formatLottoNumbers(List<LottoNumber> lottoNumbers) {
        String lottoNumberFormat = lottoNumbers.stream()
                .map(LottoNumber::getNumber)
                .map(Object::toString)
                .collect(Collectors.joining(", "));

        return "[" + lottoNumberFormat + "]\n";
    }

    public static void printLottoResults(Map<LottoResult, Integer> lottoResults) {
        System.out.println("\n당첨 통계\n---------");
        lottoResults.forEach(OutputView::formatLottoResult);
    }

    private static void formatLottoResult(LottoResult lottoResult, int count) {
        if (lottoResult.equals(LottoResult.SECOND)) {
            System.out.printf("%d개 일치, 보너스 볼 일치 (%d원) - %d개\n", lottoResult.getMatchCount(), lottoResult.getPrize(),
                    count);
            return;
        }
        System.out.printf("%d개 일치 (%d원) - %d개\n", lottoResult.getMatchCount(), lottoResult.getPrize(), count);
    }

    public static void printProfitRatio(float profitRatio) {
        System.out.printf("총 수익률은 %.2f입니다.\n", profitRatio);
    }
}
