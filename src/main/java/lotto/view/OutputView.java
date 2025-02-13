package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.model.WinningResultResponse;
import lotto.model.WinningResultResponses;

public class OutputView {

    public void printIssuedLottos(final List<List<Integer>> issuedLottoNumbers) {
        for (List<Integer> issuedLottoNumber : issuedLottoNumbers) {
            System.out.println(issuedLottoNumber.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "[", "]")));
        }
    }

    public void printWinningResult(WinningResultResponses responses) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        String winningResultFormat = "%d개 일치 (%d원)- %d개";
        String winningSecondResultFormat = "%d개, 보너스 볼 일치(%d원)- %d개";

        for (WinningResultResponse response : responses.getResponses()) {
            if (response.isHasBonus() && response.getMatchingCount() == 5) {
                System.out.println(
                        winningSecondResultFormat.formatted(response.getMatchingCount(), response.getWinningAmount(),
                                response.getWinningCount()));
                continue;
            }
            System.out.println(winningResultFormat.formatted(response.getMatchingCount(), response.getWinningAmount(),
                    response.getWinningCount()));
        }
    }

    public void printWinningRatio(double returnRatio) {
        String winningRatioFormat = "총 수익률은 %.2f입니다.";
        System.out.println(winningRatioFormat.formatted(returnRatio));
    }

    public void printErrorMessage(final String message) {
        System.out.println(String.join(" : ", "[ERROR]", message));
    }

}
