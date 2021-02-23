package lotto.view;


import java.util.stream.Collectors;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;

public class OutputView {

    private static final String PURCHASE_RESULT_INFORMATION_FORMAT = "수동으로 %d개, 자동으로 %d개를 구매했습니다.\n";
    private static final String WINNING_LOTTO_LINE_STATISTICS = "\n당첨 통계\n---------";
    private static final String FIFTH_PLACE_RESULT_FORMAT = "3개 일치 (5000원)- %d개\n";
    private static final String FORTH_PLACE_RESULT_FORMAT = "4개 일치 (50000원)- %d개\n";
    private static final String THIRD_PLACE_RESULT_FORMAT = "5개 일치 (1500000원)- %d개\n";
    private static final String SECOND_PLACE_RESULT_FORMAT = "5개 일치, 보너스 볼 일치 (30000000원)- %d개\n";
    private static final String FIRST_PLACE_RESULT_FORMAT = "6개 일치 (2000000000원)- %d개\n";
    private static final String OUTPUT_PROFIT_RESULT_FORMAT = "총 수익률은 %.2f 입니다.(기준이 1이기 때문에 결과적으로 손해라는 의미임)";

    private OutputView() {
    }

    public static void printLottoResult(Ranks lottoResult) {
        System.out.println(WINNING_LOTTO_LINE_STATISTICS);
        System.out.printf(FIFTH_PLACE_RESULT_FORMAT, lottoResult.getNumberOfRank(Rank.FIFTH));
        System.out.printf(FORTH_PLACE_RESULT_FORMAT, lottoResult.getNumberOfRank(Rank.FOURTH));
        System.out.printf(THIRD_PLACE_RESULT_FORMAT, lottoResult.getNumberOfRank(Rank.THIRD));
        System.out.printf(SECOND_PLACE_RESULT_FORMAT, lottoResult.getNumberOfRank(Rank.SECOND));
        System.out.printf(FIRST_PLACE_RESULT_FORMAT, lottoResult.getNumberOfRank(Rank.FIRST));
        System.out.printf(OUTPUT_PROFIT_RESULT_FORMAT, lottoResult.calculateProfitRate());
    }

    public static void printLottoTicket(LottoTicket lottoTicket) {
        System.out.printf(PURCHASE_RESULT_INFORMATION_FORMAT, lottoTicket.getManualLottoLineCount()
            , lottoTicket.getAutoLottoLineCount());

        for (LottoLine line : lottoTicket.getLottoLines()) {
            String lottoNumbers = line.getLottoNumbers().stream()
                .sorted()
                .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                .collect(Collectors.joining(", ", "[", "]"));
            System.out.println(lottoNumbers);
        }
    }

}
