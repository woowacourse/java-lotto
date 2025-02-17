package lotto.view;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import lotto.model.winning.WinningResultResponse;
import lotto.model.winning.WinningResultResponses;
import lotto.util.NumberFormatter;

public class OutputView {

    public void printChangeAmount(final int changeAmount) {
        System.out.println("로또 구매 후 남은 잔돈은 %s원 입니다.".formatted(getFormattedMoney(changeAmount)));
    }

    public void printIssuedLottos(final List<List<Integer>> issuedLottoNumbers) {
        for (List<Integer> issuedLottoNumber : issuedLottoNumbers) {
            List<Integer> sortedLottoNumber = sortAscendingLottoNumber(issuedLottoNumber);
            System.out.println(sortedLottoNumber.stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining(", ", "[", "]")));
        }
    }

    private List<Integer> sortAscendingLottoNumber(final List<Integer> issuedLottoNumber) {
        return new ArrayList<>(issuedLottoNumber).stream()
                .sorted()
                .toList();
    }

    public void printWinningResult(final WinningResultResponses responses) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        for (WinningResultResponse response : getAscendingResultsByWinningAmount(responses)) {
            printStatistics(response);
        }
    }

    private List<WinningResultResponse> getAscendingResultsByWinningAmount(final WinningResultResponses responses) {
        return responses.getResponses()
                .stream()
                .sorted(Comparator.comparingLong(WinningResultResponse::getWinningAmount))
                .toList();
    }

    private void printStatistics(final WinningResultResponse response) {
        if (isNeedBonusBallMatchingMessage(response)) {
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

    private boolean isNeedBonusBallMatchingMessage(final WinningResultResponse response) {
        return response.isHasBonus() && response.getMatchingCount() == 5;
    }

    public void printWinningRatio(final double returnRatio) {
        System.out.println("총 수익률은 %s입니다.".formatted(NumberFormatter.formatReturnRatio(returnRatio)));
    }

    public void printErrorMessage(final String message) {
        System.out.println(String.join(" : ", "[ERROR]", message));
    }

    private String getFormattedMoney(final long moneyAmount) {
        return NumberFormatter.formatMoney(moneyAmount);
    }

}
