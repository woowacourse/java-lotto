package lotto.view;

import java.util.List;
import java.util.stream.Collectors;

import lotto.model.winning.WinningResultResponse;
import lotto.model.winning.WinningResultResponses;
import lotto.view.util.NumberFormatter;

public class OutputView {

    public void printChangeAmount(final int changeAmount) {
        System.out.println("로또 구매 후 남은 잔돈은 %s원 입니다.".formatted(getFormattedMoney(changeAmount)));
    }

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

    private void printStatistics(final WinningResultResponse response) {
        if (response.isHasBonus() && response.getMatchingCount() == 5) {
            System.out.println(
                    "%d개, 보너스 볼 일치(%s원)- %d개".formatted(
                            response.getMatchingCount(), getFormattedMoney(response.getWinningAmount()),
                            response.getWinningCount()
                    ));
            return;
        }
        System.out.println("%d개 일치 (%s원)- %d개".formatted(
                response.getMatchingCount(), getFormattedMoney(response.getWinningAmount()),
                response.getWinningCount()
        ));
    }

    public void printWinningRatio(final double returnRatio) {
        String winningRatioFormat = "총 수익률은 %.2f입니다.";
        System.out.println(winningRatioFormat.formatted(returnRatio));
    }

    public void printErrorMessage(final String message) {
        System.out.println(String.join(" : ", "[ERROR]", message));
    }

    private String getFormattedMoney(final long moneyAmount) {
        return NumberFormatter.formatMoney(moneyAmount);
    }

}
