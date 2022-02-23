package view;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import model.WinningPrize;

public class WinningResultOutputView implements OutputView<Map<WinningPrize, Integer>>{

    @Override
    public void printOutputData(Map<WinningPrize, Integer> winningResult) {
        System.out.println("당첨 통계");
        System.out.println("---------");
        winningResult.entrySet().forEach(this::printWinningResult);
    }

    private void printWinningResult(Entry<WinningPrize, Integer> winningPrizeInfo) {
        WinningPrize winningPrize = winningPrizeInfo.getKey();
        String winningMessage = winningPrize.getMatchCount() + "개 일치";
        if (winningPrize.isMatchBonus()) {
            winningMessage += ", 보너스 볼 일치";
        }
        winningMessage += "(" + winningPrize.getPrizeMoney() + "원)- " + winningPrizeInfo.getValue() + "개";
        System.out.println(winningMessage);
    }
}
