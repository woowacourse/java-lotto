package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.domain.Rank;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoResult;
import lotto.domain.lotto.LottoTicket;

import static lotto.view.OutputMessages.*;

public class OutputView {

    private OutputView() {

    }
    
    public static void printResult(LottoResult lottoResult) {
        System.out.println(OUTPUT_WIN_STATISTICS_MESSAGES.getMessage());
        System.out.printf((OUTPUT_FIFTH_RESULT_FORMAT.getMessage()) + "%n",
                lottoResult.findNumberOfRank(Rank.FIFTH));
        System.out.printf((OUTPUT_FORTH_RESULT_FORMAT.getMessage()) + "%n",
                lottoResult.findNumberOfRank(Rank.FOURTH));
        System.out.printf((OUTPUT_THIRD_RESULT_FORMAT.getMessage()) + "%n",
                lottoResult.findNumberOfRank(Rank.THIRD));
        System.out.printf((OUTPUT_SECOND_RESULT_FORMAT.getMessage()) + "%n",
                lottoResult.findNumberOfRank(Rank.SECOND));
        System.out.printf((OUTPUT_FIRST_RESULT_FORMAT.getMessage()) + "%n",
                lottoResult.findNumberOfRank(Rank.FIRST));
        System.out.printf((OUTPUT_PROFIT_RESULT_FORMAT.getMessage()) + "%n",
                lottoResult.calculateProfitRate());
    }

    public static void printLottoTicket(LottoTicket lottoTicket) {
        System.out.printf((OUTPUT_PURCHASE_NUMBER_OF_LOTTO_MESSAGES).getMessage() + "%n",
                lottoTicket.getLength());
        for (LottoLine line : lottoTicket.getLottoLines()) {
            List<String> lottoNumbers = line.getValues().stream()
                    .sorted()
                    .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                    .collect(Collectors.toList());
            System.out.println("[" + String.join(",", lottoNumbers) + "]");
        }
    }
}
