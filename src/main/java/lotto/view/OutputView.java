package lotto.view;

import lotto.domain.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {
    public static final String COMMA = ", ";
    public static final int EMPTY_VALUE = 0;

    private OutputView() { /* prevent creating OutputView instance */ }

    public static void printLottoCountAndLottos(LottoCount lottoCount, Lottos lottos) {
        List<Lotto> lottoLines = lottos.get();
        printLottoCount(lottoCount);
        for (Lotto lotto : lottoLines) {
            printLottoNumbers(lotto);
        }
    }

    private static void printLottoCount(LottoCount lottoCount) {
        int manualLottoCount = lottoCount.getManualLottoCountValue();
        int autoLottoCount = lottoCount.calculateAutoLottoCount();
        System.out.println(String.format("수동으로 %d개, 자동으로 %d개를 구매했습니다.", manualLottoCount, autoLottoCount));
    }

    private static void printLottoNumbers(Lotto lotto) {
        List<LottoNumber> numbers = lotto.get();
        String result = numbers.stream()
                .map(LottoNumber::get)
                .map(String::valueOf)
                .collect(joining(COMMA));
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(result)
                .append("]");
        System.out.println(sb.toString());
    }

    public static void printLottoResultAndEarningsRate(MatchResults matchResults, int earningsRate) {
        printHead();
        printMatchResults(matchResults.get());
        printEarningRate(earningsRate);
    }

    private static void printHead() {
        System.out.println("\n당첨 통계\n---------");
    }

    private static void printMatchResults(Map<MatchResult, Integer> matchResult) {
        MatchResult[] matchResults = MatchResult.values();
        for (MatchResult result : matchResults) {
            printMatchResultDetail(result, matchResult.getOrDefault(result, EMPTY_VALUE));
        }
    }

    private static void printMatchResultDetail(MatchResult result, int matchResultCount) {
        if (result == MatchResult.FIVE_MATCH_WITH_BONUS_BALL) {
            System.out.print(String.format("%d개 일치, 보너스 볼 일치", result.getMatchCount()));
            System.out.println(String.format("(%d원)- %d개", result.getPrize(), matchResultCount));
            return;
        }
        System.out.print(String.format("%d개 일치 ", result.getMatchCount()));
        System.out.println(String.format("(%d원)- %d개", result.getPrize(), matchResultCount));
    }

    private static void printEarningRate(int earningsRate) {
        System.out.println(String.format("총 수익률은 %d%%입니다.", earningsRate));
    }
}
