package lotto.view;

import lotto.model.WinningResultResponse;
import lotto.model.WinningResultResponses;

public class OutputView {
    public void printWinningResult(WinningResultResponses responses) {
        System.out.println("당첨 통계");
        System.out.println("---------");

        String winningResultFormat = "%d개 일치 (%d원)- %d개";
        String winningSecondResultFormat = "%d개, 보너스 볼 일치(%d원)- %d개";

        for (WinningResultResponse response : responses.getResponses()) {
            if (response.isHasBonus() && response.getMatchingCount() == 5) {
                System.out.println(winningSecondResultFormat.formatted(response.getMatchingCount(), response.getWinningAmount(), response.getWinningCount()));
                continue;
            }
            System.out.println(winningResultFormat.formatted(response.getMatchingCount(), response.getWinningAmount(), response.getWinningCount()));
        }

        // TODO: 수익률 계산해서 보여주기
    }
}
