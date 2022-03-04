package view;

import domain.Lotto;
import domain.LottoNumber;
import domain.Lottos;
import domain.Rank;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class OutputView {
    
    private static final String ERROR_MESSAGE = "[ERROR] ";

    private OutputView() {
    }

    public static void printGuideMessage(String message) {
        System.out.println(message);
    }

    public static void printErrorMessage(String errorMessage) {
        System.out.println(ERROR_MESSAGE + errorMessage);
    }

    public static void printLottos(Lottos lottos, int manualTicketCount, int autoTicketCount) {
        System.out.println(String.format("수동으로 %d장, 자동으로 %d개를 구매했습니다.", manualTicketCount, autoTicketCount));
        lottos.getLottos().stream()
                .forEach(OutputView::printLotto);
        System.out.println();
    }

    private static void printLotto(Lotto lotto) {
        String format = "[%s]";
        List<LottoNumber> lottoNumbers = new ArrayList<>(lotto.getLotto());
        Collections.sort(lottoNumbers);
        System.out.println(String.format(format, lottoNumbers.stream()
                .map(lottoNumber -> String.valueOf(lottoNumber.getLottoNumber()))
                .collect(Collectors.joining(", "))));
    }

    public static void printRankCounts(Map<Rank, Integer> ranks) {
        System.out.println("\n당첨 통계\n---------");
        ranks.forEach(OutputView::printRankCount);
    }

    private static void printRankCount(Rank rank, Integer count) {
        String format = "%d개 일치 (%d원)- %d개";
        if (rank.isSecond()) {
            format = "%d개 일치, 보너스 볼 일치(%d원)- %d개";
        }
        System.out.println(String.format(format, rank.getMatchCounts(), rank.getMoney(), count));
    }

    public static void printProfitRate(double profitRate) {
        String format = "총 수익률은 %.2f입니다.";
        System.out.println(String.format(format, profitRate));
    }
}
