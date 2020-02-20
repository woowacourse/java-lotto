package lotto.view;

import lotto.domain.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {
    public static final String COMMA = ", ";

    private OutputView() { /* prevent creating OutputView instance */ }

    public static void printLottoCount(int lottoCount) {
        System.out.println(String.format("%d개를 구매했습니다.", lottoCount));
    }

    public static void printLottos(Lottos lottos) {
        List<Lotto> lottoLines = lottos.getLottos();
        for (Lotto lotto : lottoLines) {
            printLottoNumbers(lotto);
        }
    }

    private static void printLottoNumbers(Lotto lotto) {
        List<Integer> numbers = lotto.getNumbers();
        String result = numbers.stream()
                .map(String::valueOf)
                .collect(joining(COMMA));
        StringBuilder sb = new StringBuilder();
        sb.append("[")
                .append(result)
                .append("]");

        System.out.println(sb.toString());
    }

    public static void printLottoResult(LottoResults lottoResults, PurchasePrice purchasePrice) {
        printHead();
        printMatchResults(lottoResults.getLottoResults());
        printEarningRate(lottoResults.calculateEarningRate(purchasePrice));
    }

    private static void printHead() {
        System.out.println("\n당첨 통계\n---------");
    }

    private static void printMatchResults(Map<MatchResult, Integer> matchResult) {
        MatchResult[] matchResults = MatchResult.values();
        for (MatchResult result : matchResults) {
            printMatchResultDetail(result);
            System.out.println(String.format("(%d원)- %d개", result.getPrize(), matchResult.get(result)));
        }
    }

    private static void printMatchResultDetail(MatchResult result) {
        if (result == MatchResult.FIVE_MATCH_WITH_BONUS_BALL) {
            System.out.print(String.format("%d개 일치, 보너스 볼 일치", result.getMatchCount()));
            return;
        }
        System.out.print(String.format("%d개 일치 ", result.getMatchCount()));
    }

    private static void printEarningRate(int earningRate) {
        System.out.println(String.format("총 수익률은 %d%%입니다.", earningRate));
    }

}
