package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.model.WinningResultResponse;
import lotto.model.WinningResultResponses;

public class OutputView {
    private static final String winningResultFormat = "%d개 일치 (%d원)- %d개";
    private static final String winningSecondResultFormat = "%d개, 보너스 볼 일치(%d원)- %d개";

    public void printIssuedLottos(final List<List<Integer>> issuedLottoNumbers) {
        for (List<Integer> issuedLottoNumber : issuedLottoNumbers) {
            System.out.println(issuedLottoNumber.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "[", "]")));
        }
    }

    public void printWinningResult(final WinningResultResponses responses) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (WinningResultResponse response : responses.getResponses()) {
            printStatistics(response);
        }
    }

    private static void printStatistics(final WinningResultResponse response) {
        if (response.isHasBonus() && response.getMatchingCount() == 5) {
            System.out.println(
                    winningSecondResultFormat.formatted(response.getMatchingCount(), response.getWinningAmount(),
                            response.getWinningCount()));
            return;
        }
        System.out.println(winningResultFormat.formatted(response.getMatchingCount(), response.getWinningAmount(),
                response.getWinningCount()));
    }

    public void printWinningRatio(final double returnRatio) {
        String winningRatioFormat = "총 수익률은 %.2f입니다.";
        System.out.println(winningRatioFormat.formatted(returnRatio));
    }

    public void printErrorMessage(final String message) {
        System.out.println(String.join(" : ", "[ERROR]", message));
    }

}
