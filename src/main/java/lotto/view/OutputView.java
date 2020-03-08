package lotto.view;

import java.util.Map;
import java.util.stream.Collectors;

import lotto.domain.*;

public class OutputView {
    private static final String BUY_LOTTOS_SUCCESS = "수동으로 %d장, 자동으로 %d개를 구매했습니다.";
    private static final String STATISTICS_ANNOUNCE = "당첨 통계\n---------";
    private static final String RETRY_REQUEST = "다음과 같은 이유로 다시 시도해 주세요 - %s";
    private static final String EARNING_RATE_ANNOUNCE = "총 수익률은 %d%%입니다.";
    private static final String RESULT_STATISTICS_ANNOUNCE = "%d개 일치(%d원)- %d개";

    public static void printLottos(Lottos lottosManual, Lottos lottosAutomatic) {
        System.out.println(String.format(BUY_LOTTOS_SUCCESS, lottosManual.size(), lottosAutomatic.size()));
        for (Lotto lottoManual : lottosManual.getLottos()) {
            printLotto(lottoManual);
        }

        for (Lotto lottoAutomatic : lottosAutomatic.getLottos()) {
            printLotto(lottoAutomatic);
        }
    }

    public static void printResult(Result result) {
        System.out.println(STATISTICS_ANNOUNCE);
        printStatistics(result.getWinningRanks());
        printEarningRate(result.getEarningRate());
    }

    static void printRetryRequestWithMessage(String message) {
        System.out.println(String.format(RETRY_REQUEST, message));
    }

    private static void printEarningRate(int earningRate) {
        System.out.println(String.format(EARNING_RATE_ANNOUNCE, earningRate));
    }

    private static void printStatistics(WinningRanks winningRanks) {
        Map<Rank, Integer> winningResults = winningRanks.getWinningRanks();
        for (Rank rank : winningResults.keySet()) {
            String resultMessage = String.format(RESULT_STATISTICS_ANNOUNCE, rank.getMatchNumber(),
                    rank.calculateWinningMoney().getValue(), winningResults.get(rank));
            System.out.println(resultMessage);
        }
    }

    private static void printLotto(Lotto lotto) {
        String lottoString = lotto.getLottoNumbers().stream()
                .map(LottoNumber::toString)
                .collect(Collectors.joining(",", "[", "]"));

        System.out.println(lottoString);
    }

}
