package view;

import static constant.OutViewMessage.TOTAL_PROFIT_RATE_GUIDANCE;
import static constant.OutViewMessage.LOTTO_PURCHASE_GUIDANCE;
import static constant.OutViewMessage.WINNING_STATISTIC_ENTRY;
import static constant.OutViewMessage.WINNING_STATISTIC_TITLE;
import static constant.OutViewMessage.WINNING_STATISTICS_SEPARATOR;

import java.util.EnumMap;
import java.util.List;
import model.Lotto;
import model.Prize;
import service.LottoService;

public class OutputView {

    private static final String BENEFIT = "이익";
    private static final String DAMAGE = "손해";

    public static void printLottoCount(final LottoService lottoService) {
        print(LOTTO_PURCHASE_GUIDANCE.getMessage(lottoService.getTicketNumber()));
    }

    public static void printLottoTickets(final LottoService lottoService) {
        List<Lotto> lottoList = lottoService.getIssuedTickets();
        lottoList.stream()
                .map(lotto -> lotto.getNumbers().toString())
                .forEach(OutputView::print);
        printNewLine();
    }

    public static void printStatistics(EnumMap<Prize, Integer> prizeMap) {
        printNewLine();
        print(WINNING_STATISTIC_TITLE.getMessage());
        print(WINNING_STATISTICS_SEPARATOR.getMessage());

        prizeMap.keySet().stream()
                .map(prize -> WINNING_STATISTIC_ENTRY.getMessage(prize.getComment(), prizeMap.get(prize)))
                .forEach(OutputView::print);
    }

    public static void printBenefit(double benefit) {
        String result = checkBenefit(benefit);
        print(TOTAL_PROFIT_RATE_GUIDANCE.getMessage(benefit, result));
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
