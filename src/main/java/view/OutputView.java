package view;

import static constant.OutputViewMessage.FIFTH_PLACE_STATISTICS;
import static constant.OutputViewMessage.FIRST_PLACE_STATISTICS;
import static constant.OutputViewMessage.FOUR_PLACE_STATISTICS;
import static constant.OutputViewMessage.LOTTO_PURCHASE_GUIDANCE;
import static constant.OutputViewMessage.SECOND_PLACE_STATISTICS;
import static constant.OutputViewMessage.THIRD_PLACE_STATISTICS;
import static constant.OutputViewMessage.TOTAL_PROFIT_RATE_RESULT;
import static constant.OutputViewMessage.LOTTO_PURCHASE_RESULT;
import static constant.OutputViewMessage.WINNING_BONUS_GUIDANCE;
import static constant.OutputViewMessage.WINNING_LOTTO_GUIDANCE;
import static constant.OutputViewMessage.WINNING_STATISTIC_TITLE;
import static constant.OutputViewMessage.WINNING_STATISTICS_SEPARATOR;

import java.util.EnumMap;
import java.util.List;
import model.Lotto;
import model.Prize;
import model.LottoFactory;

public class OutputView {

    public static void printLottoPurchaseGuidance() {
        print(LOTTO_PURCHASE_GUIDANCE.getMessage());
    }

    public static void printWinningLottoGuidance() {
        print(WINNING_LOTTO_GUIDANCE.getMessage());
    }

    public static void printWinningBonusGuidance() {
        print(WINNING_BONUS_GUIDANCE.getMessage());
    }

    public static void printLottoCount(final LottoFactory lottoFactory) {
        print(LOTTO_PURCHASE_RESULT.getMessage(lottoFactory.getLottoCount()));
    }

    public static void printLottoTickets(final LottoFactory lottoFactory) {
        List<Lotto> issuedLottoTickets = lottoFactory.getIssuedLottoTickets();
        issuedLottoTickets.stream()
                .map(Lotto::toString)
                .forEach(OutputView::print);

        printNewLine();
    }

    public static void printStatistics(EnumMap<Prize, Integer> prizes) {
        printNewLine();
        print(WINNING_STATISTIC_TITLE.getMessage());
        print(WINNING_STATISTICS_SEPARATOR.getMessage());
        print(FIFTH_PLACE_STATISTICS.getMessage(prizes.get(Prize.FIFTH_PLACE)));
        print(FOUR_PLACE_STATISTICS.getMessage(prizes.get(Prize.FOUR_PLACE)));
        print(THIRD_PLACE_STATISTICS.getMessage(prizes.get(Prize.THIRD_PLACE)));
        print(SECOND_PLACE_STATISTICS.getMessage(prizes.get(Prize.SECOND_PLACE)));
        print(FIRST_PLACE_STATISTICS.getMessage(prizes.get(Prize.FIRST_PLACE)));
    }

    public static void printBenefit(LottoFactory lottoFactory, EnumMap<Prize, Integer> prizes) {
        double winningAmount = lottoFactory.getWinningAmount(prizes);
        String result = lottoFactory.lossOrGain(winningAmount);
        print(TOTAL_PROFIT_RATE_RESULT.getMessage(winningAmount, result));
    }

    private static void print(final String output) {
        System.out.println(output);
    }

    private static void printNewLine() {
        System.out.println();
    }
}
