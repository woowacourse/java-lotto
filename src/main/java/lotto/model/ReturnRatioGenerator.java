package lotto.model;

import lotto.model.winning.WinningResultResponse;
import lotto.model.winning.WinningResultResponses;

public class ReturnRatioGenerator {

    private ReturnRatioGenerator() {
    }

    public static double calculateReturnRatio(int money, WinningResultResponses winningResultResponses) {
        long totalReturnMoney = 0L;
        for (WinningResultResponse response : winningResultResponses.getResponses()) {
            totalReturnMoney += response.getWinningAmount() * response.getWinningCount();
        }
        double ratio = (double) totalReturnMoney / money;
        return Math.floor(ratio * 100) / 100;
    }

}
