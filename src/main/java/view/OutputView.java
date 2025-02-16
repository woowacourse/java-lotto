package view;

import domain.Lotto;
import domain.Number;
import domain.Rank;
import domain.WinningResult;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class OutputView {

    private static final String PURCHASE_LOTTO_SIZE_FORMAT = "%d개를 구매했습니다.%n";
    private static final String WINNING_RESULT_HEADER = "%n당첨 통계%n---------%n";
    private static final String WINNING_RESULT_FORMAT = "%d개 일치 (%d원)- %d개%n";
    private static final String WINNING_WITH_BONUS_RESULT_FORMAT = "%d개 일치, 보너스 볼 일치(%d원)- %d개%n";
    private static final String RATE_OF_RETURN_FORMAT = "총 수익률은 %.2f입니다.";
    private static final String DAMAGE_MESSAGE = "(기준이 1이기 때문에 결과적으로 손해라는 의미임)";
    private static final String ERROR_MESSAGE_FORMAT = "[ERROR] %s%n";

    public void printPurchaseLottos(List<Lotto> purchasedlottos) {
        System.out.printf(PURCHASE_LOTTO_SIZE_FORMAT, purchasedlottos.size());
        for (Lotto lotto : purchasedlottos) {
            Set<Number> numbers = lotto.getNumbers();
            String purchaseLottos = numbers.stream()
                    .map(Number::value)
                    .map(String::valueOf)
                    .collect(Collectors.joining(", "));
            System.out.println("[" + purchaseLottos + "]");
        }
        System.out.println();
    }

    public void printWinningResult(WinningResult winningResult) {
        System.out.printf(WINNING_RESULT_HEADER);
        printWinningRank(winningResult);
        printRateOfReturn(winningResult);
    }

    private void printWinningRank(WinningResult winningResult) {
        Map<Rank, Integer> result = winningResult.getWinningResult();
        for (Rank winning : Rank.values()) {
            if (winning == Rank.MISS) {
                continue;
            }
            int matchCount = result.getOrDefault(winning, 0);
            if (winning.isRequireBonus()) {
                System.out.printf(WINNING_WITH_BONUS_RESULT_FORMAT, winning.getMatchCount(),
                        winning.getWinningMoney().getAmount(),
                        matchCount);
                continue;
            }
            System.out.printf(WINNING_RESULT_FORMAT, winning.getMatchCount(), winning.getWinningMoney().getAmount(),
                    matchCount);
        }
    }

    private void printRateOfReturn(WinningResult winningResult) {
        double rateOfReturn = winningResult.calculateRateOfReturn();
        System.out.printf(RATE_OF_RETURN_FORMAT, rateOfReturn);
        if (rateOfReturn < 1) {
            System.out.print(DAMAGE_MESSAGE);
        }
    }

    public void printErrorMessage(RuntimeException e) {
        System.out.printf(ERROR_MESSAGE_FORMAT, e.getMessage());
    }
}
