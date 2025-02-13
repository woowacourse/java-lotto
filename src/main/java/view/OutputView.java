package view;

import static view.OutViewConstant.BENEFIT_RATE_GUIDANCE;
import static view.OutViewConstant.LOTTO_PURCHASE_GUIDANCE;
import static view.OutViewConstant.WINNING_STATISTIC;
import static view.OutViewConstant.WINNING_STATISTIC_GUIDANCE;
import static view.OutViewConstant.WINNING_STATISTIC_LINE_GUIDANCE;

import java.util.EnumMap;
import java.util.List;
import model.Lotto;
import model.Prize;
import service.LottoFactory;

public class OutputView {

    private static final String BENEFIT = "이익";
    private static final String DAMAGE = "손해";

    public static void printLottoCount(final LottoFactory lottoFactory) {
        print(LOTTO_PURCHASE_GUIDANCE.getMessage(lottoFactory.getTicketNumber()));
    }

    public static void printLottoTickets(final LottoFactory lottoFactory) {
        List<Lotto> lottoList = lottoFactory.getIssuedTickets();
        lottoList.stream()
                .map(lotto -> lotto.getNumbers().toString())
                .forEach(OutputView::print);
        printNewLine();
    }

    public static void printStatistics(EnumMap<Prize, Integer> prizeMap) {
        printNewLine();
        print(WINNING_STATISTIC_GUIDANCE.getMessage());
        print(WINNING_STATISTIC_LINE_GUIDANCE.getMessage());

        prizeMap.keySet().stream()
                .map(prize -> WINNING_STATISTIC.getMessage(prize.getComment(), prizeMap.get(prize)))
                .forEach(OutputView::print);
    }

    public static void printBenefit(double benefit) {
        String result = checkBenefit(benefit);
        print(BENEFIT_RATE_GUIDANCE.getMessage(benefit, result));
    }

    private static String checkBenefit(double benefit) {
        if (benefit >= 1) {
            return BENEFIT;
        }
        return DAMAGE;
    }

    private static void print(final String output) {
        System.out.println(output);
    }

    private static void printNewLine() {
        System.out.println();
    }
}
