package lotto.view;

import static lotto.view.OutputMessages.OUTPUT_FIFTH_RESULT_FORMAT;
import static lotto.view.OutputMessages.OUTPUT_FIRST_RESULT_FORMAT;
import static lotto.view.OutputMessages.OUTPUT_FORTH_RESULT_FORMAT;
import static lotto.view.OutputMessages.OUTPUT_PROFIT_RESULT_FORMAT;
import static lotto.view.OutputMessages.OUTPUT_SECOND_RESULT_FORMAT;
import static lotto.view.OutputMessages.OUTPUT_THIRD_RESULT_FORMAT;
import static lotto.view.OutputMessages.OUTPUT_WIN_STATISTICS_MESSAGES;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;

public class OutputView {

    public static void printResult(LottoResult lottoResult) {
        System.out.println(OUTPUT_WIN_STATISTICS_MESSAGES.getMessage());
        System.out.printf((OUTPUT_FIFTH_RESULT_FORMAT.getMessage()) + "%n", lottoResult.getNumberOfRank(Rank.FIFTH));
        System.out.printf((OUTPUT_FORTH_RESULT_FORMAT.getMessage()) + "%n", lottoResult.getNumberOfRank(Rank.FOURTH));
        System.out.printf((OUTPUT_THIRD_RESULT_FORMAT.getMessage()) + "%n", lottoResult.getNumberOfRank(Rank.THIRD));
        System.out.printf((OUTPUT_SECOND_RESULT_FORMAT.getMessage()) + "%n", lottoResult.getNumberOfRank(Rank.SECOND));
        System.out.printf((OUTPUT_FIRST_RESULT_FORMAT.getMessage()) + "%n", lottoResult.getNumberOfRank(Rank.FIRST));
        System.out.printf((OUTPUT_PROFIT_RESULT_FORMAT.getMessage()) + "%n", lottoResult.calculateProfitRate());
    }

    public static void printLottoTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottoLineSize() + "개를 구매했습니다.");
        for (LottoLine line : lottoTicket.getLottoLines()) {
            List<String> lottoNumbers = line.getValues().stream().sorted()
                .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                .collect(Collectors.toList());
            System.out.println("[" + String.join(",", lottoNumbers) + "]");
        }
    }

}
