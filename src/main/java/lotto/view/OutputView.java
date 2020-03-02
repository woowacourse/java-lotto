package lotto.view;

import lotto.domain.*;

import java.util.List;
import java.util.Map;

import static java.util.stream.Collectors.joining;

public class OutputView {
    public static final String COMMA = ", ";

    private OutputView() { /* prevent creating OutputView instance */ }

    public static void printLottosInformation(LottoCount lottoCount, Lottos lottos) {
        printLottoCount(lottoCount);
        printLottos(lottos);
    }

    public static void printLottoCount(LottoCount lottoCount) {
        System.out.println(
                String.format("\n수동으로 %d장, 자동으로 %d장을 구매했습니다.", lottoCount.getManualCount(), lottoCount.getAutomaticCount()));
    }

    public static void printLottos(Lottos lottos) {
        List<Lotto> lottoLines = lottos.getLottos();
        for (Lotto lotto : lottoLines) {
            printLottoNumbers(lotto);
        }
    }

    private static void printLottoNumbers(Lotto lotto) {
        List<Ball> balls = lotto.getBalls();
        String numbers = balls.stream()
                .map(ball -> String.valueOf(ball.getNumber()))
                .collect(joining(COMMA));
        String result = "[" + numbers + "]";
        System.out.println(result);
    }

    public static void printLottoResult(Results results, PurchasePrice purchasePrice) {
        printHead();
        printMatchResults(results.getLottoResults());
        printEarningRate(results.calculateEarningRate(purchasePrice));
    }

    private static void printHead() {
        System.out.println("\n당첨 통계\n---------");
    }

    private static void printMatchResults(Map<MatchResult, Integer> matchResult) {
        MatchResult[] matchResults = MatchResult.values();
        for (MatchResult result : matchResults) {
            printMatchResult(matchResult, result);
        }
    }

    private static void printMatchResult(Map<MatchResult, Integer> matchResult, MatchResult result) {
        if (result == MatchResult.NONE) {
            return;
        }
        printMatchResultDetail(result);
        System.out.println(String.format("(%d원)- %d개", result.getPrize(), matchResult.get(result)));
    }

    private static void printMatchResultDetail(MatchResult result) {
        if (result == MatchResult.FIVE_MATCH_WITH_BONUS_BALL) {
            System.out.print(String.format("%d개 일치, 보너스 볼 일치 ", result.getMatchCount()));
            return;
        }
        System.out.print(String.format("%d개 일치 ", result.getMatchCount()));
    }

    private static void printEarningRate(int earningRate) {
        System.out.println(String.format("총 수익률은 %d%%입니다.", earningRate));
    }

    public static void requestManualLottoMessage() {
        System.out.println("\n수동으로 구매할 번호를 입력해 주세요.");
    }
}
