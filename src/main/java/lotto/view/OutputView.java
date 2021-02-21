package lotto.view;

import static lotto.view.messages.OutputMessages.FIFTH_PLACE_RESULT_FORMAT;
import static lotto.view.messages.OutputMessages.FIRST_PLACE_RESULT_FORMAT;
import static lotto.view.messages.OutputMessages.FORTH_PLACE_RESULT_FORMAT;
import static lotto.view.messages.OutputMessages.OUTPUT_PROFIT_RESULT_FORMAT;
import static lotto.view.messages.OutputMessages.SECOND_PLACE_RESULT_FORMAT;
import static lotto.view.messages.OutputMessages.THIRD_PLACE_RESULT_FORMAT;
import static lotto.view.messages.OutputMessages.WINNING_LOTTO_LINE_STATISTICS;

import java.util.List;
import java.util.stream.Collectors;
import lotto.domain.lotto.LottoLine;
import lotto.domain.lotto.LottoTicket;
import lotto.domain.rank.Rank;
import lotto.domain.rank.Ranks;

public class OutputView {

    public static void printLottoResult(Ranks lottoResult) {
        System.out.println(WINNING_LOTTO_LINE_STATISTICS.getMessage());
        System.out.printf(FIFTH_PLACE_RESULT_FORMAT.getMessage(),
            lottoResult.getNumberOfRank(Rank.FIFTH));
        System.out.printf(FORTH_PLACE_RESULT_FORMAT.getMessage(),
            lottoResult.getNumberOfRank(Rank.FOURTH));
        System.out.printf(THIRD_PLACE_RESULT_FORMAT.getMessage(),
            lottoResult.getNumberOfRank(Rank.THIRD));
        System.out.printf(SECOND_PLACE_RESULT_FORMAT.getMessage(),
            lottoResult.getNumberOfRank(Rank.SECOND));
        System.out.printf(FIRST_PLACE_RESULT_FORMAT.getMessage(),
            lottoResult.getNumberOfRank(Rank.FIRST));
        System.out
            .printf(OUTPUT_PROFIT_RESULT_FORMAT.getMessage(), lottoResult.calculateProfitRate());
    }

    public static void printLottoTicket(LottoTicket lottoTicket) {
        System.out.println(lottoTicket.getLottoLineSize() + "개를 구매했습니다.");
        for (LottoLine line : lottoTicket.getLottoLines()) {
            List<String> lottoNumbers = line.getLottoNumbers().stream()
                .sorted()
                .map(lottoNumber -> String.valueOf(lottoNumber.getValue()))
                .collect(Collectors.toList());
            System.out.println("[" + String.join(", ", lottoNumbers) + "]");
        }
    }

}
