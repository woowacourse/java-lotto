package lotto.model;

public class ReturnRatioGenerator {

    private ReturnRatioGenerator() {
    }

    public static double calculateReturnRatio(int money, WinningResults winningResults) {
        long totalReturnMoney = 0L;
        for (WinningResult response : winningResults.getResponses()) {
            totalReturnMoney += response.getWinningAmount() * response.getWinningCount();
        }
        double ratio = (double) totalReturnMoney / money;
        return Math.floor(ratio * 100) / 100;
    }

}
