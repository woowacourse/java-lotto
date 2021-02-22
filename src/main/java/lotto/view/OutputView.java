package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;

public class OutputView {
    private static final String WIN_STATISTICS_MESSAGES = "당첨 통계\n---------";
    private static final String FIFTH_RESULT_FORMAT = "3개 일치 (5000원)- %d개";
    private static final String FORTH_RESULT_FORMAT = "4개 일치 (50000원)- %d개";
    private static final String THIRD_RESULT_FORMAT = "5개 일치 (1500000원)- %d개";
    private static final String SECOND_RESULT_FORMAT = "5개 일치, 보너스 볼 일치 (30000000원)- %d개";
    private static final String FIRST_RESULT_FORMAT = "6개 일치 (2000000000원)- %d개";
    private static final String PROFIT_RESULT_FORMAT = "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String PURCHASE_NUMBER_OF_LOTTO_MESSAGES = "%d개를 구매했습니다.";

    private OutputView() {
    }

    public static void printResult(LottoResult lottoResult) {
        System.out.println(WIN_STATISTICS_MESSAGES);
        System.out.printf((FIFTH_RESULT_FORMAT) + "%n",
                lottoResult.findNumberOfRank(Rank.FIFTH));
        System.out.printf((FORTH_RESULT_FORMAT) + "%n",
                lottoResult.findNumberOfRank(Rank.FOURTH));
        System.out.printf((THIRD_RESULT_FORMAT) + "%n",
                lottoResult.findNumberOfRank(Rank.THIRD));
        System.out.printf((SECOND_RESULT_FORMAT) + "%n",
                lottoResult.findNumberOfRank(Rank.SECOND));
        System.out.printf((FIRST_RESULT_FORMAT) + "%n",
                lottoResult.findNumberOfRank(Rank.FIRST));
        System.out.printf((PROFIT_RESULT_FORMAT) + "%n",
                lottoResult.calculateProfitRate());
    }

    public static void printLottoTicket(LottoTicket lottoTicket) {
        System.out.printf((PURCHASE_NUMBER_OF_LOTTO_MESSAGES) + "%n",
                lottoTicket.getLength());
        for (LottoLine line : lottoTicket.getLottoLines()) {
            List<String> lottoNumbers = line.getValue().stream()
                    .sorted()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                    .collect(Collectors.toList());
            System.out.println("[" + String.join(",", lottoNumbers) + "]");
        }
    }
}
